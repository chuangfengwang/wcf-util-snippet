package com.helipy.commons.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Pack:       com.helipy.commons.util
 * File:       KeyByQueueProcessor
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2024-03-14 21:19
 */
@Slf4j
public abstract class KeyByQueueProcessor<IN, OUT> {
    /**
     * 名称
     */
    private final String name;
    /**
     * 运行状态标记
     */
    private final AtomicBoolean isProcessRunning;
    /**
     * 每个处理线程使用多大的缓存队列,是数据队列,不是线程的任务队列
     */
    private final int perThreadQueueSize;
    /**
     * 分成几个分区,对应的,将开启这么多个线程和数据队列
     */
    private final int partitionNum;
    /**
     * 每批次最多处理多少数据
     */
    private final int processBatchSize;
    /**
     * 每批次最大等待间隔
     */
    private final long processIntervalTime;

    /**
     * 接收到的消息-队列,每个 abs(keyBy())%instanceThreadNum 得到的分区对应一个队列
     * key: 分区代号
     */
    private final Map<Integer, BlockingQueue<IN>> processRecordQueueMap;
    /**
     * 处理消息的线程池,主要处理逻辑由这组线程池执行
     * key: 分区代号
     */
    private final Map<Integer, ListeningExecutorService> processThreadPoolMap;
    /**
     * 处理异常的线程池
     */
    private final ListeningExecutorService exceptionHandleThreadPool;
    /**
     * 分派消息到对应处理线程的线程池,一般只需要一个线程
     */
    private final ListeningExecutorService assignThreadPool;
    /**
     * 接受消息,处理成功与失败的qps记录器
     */
    private final QpsLogger receiveQpsLogger;
    private final QpsLogger successQpsLogger;
    private final QpsLogger failQpsLogger;

    /**
     * 线程名字格式
     * {name}-pqp{partitionId}
     */
    private static final String THREAD_NAME_FORMAT = "%s-pqp%02d";


    public KeyByQueueProcessor(
            String name,
            int perThreadQueueSize,
            int partitionNum,
            int processBatchSize,
            long processIntervalTime) {
        this.name = name;
        this.perThreadQueueSize = perThreadQueueSize;
        this.partitionNum = partitionNum;
        this.processBatchSize = processBatchSize;
        this.processIntervalTime = processIntervalTime;

        // 初始化队列和线程池
        this.processRecordQueueMap = new ConcurrentHashMap<>();
        this.processThreadPoolMap = new ConcurrentHashMap<>();

        // 处理异常的线程池
        this.exceptionHandleThreadPool = MoreExecutors.listeningDecorator(
                RunUtils.createThreadPool(name + "-exceptionHandle", 2, 2, 1000, new SynchronousQueue<>()));
        // 分发消息的线程池
        this.assignThreadPool = MoreExecutors.listeningDecorator(
                RunUtils.createThreadPool(name + "-assign", 1,1, 1000, Queues.newArrayBlockingQueue(this.partitionNum * 2)));

        receiveQpsLogger = new QpsLogger("KeyByQueueProcessor-recv-" + name, 30_000);
        successQpsLogger = new QpsLogger("KeyByQueueProcessor-succ-" + name, 30_000);
        failQpsLogger = new QpsLogger("KeyByQueueProcessor-fail-" + name, 30_000);

        // 运行状态
        this.isProcessRunning = new AtomicBoolean(false);
    }

    public KeyByQueueProcessor<IN, OUT> start() {
        isProcessRunning.set(true);
        assignThreadPool.submit(this::startDistribute);
        return this;
    }

    public void close() {
        isProcessRunning.set(false);

        // 等待队列处理完成: 等待最多 100 次，每次 processIntervalTime 毫秒
        try {
            int maxWaitCount = 100;
            while (!isAllQueueEmpty() && maxWaitCount-- > 0) {
                Thread.sleep(processIntervalTime);
            }
        } catch (InterruptedException e) {
            // do nothing
            Thread.currentThread().interrupt();
        } finally {
            if (!isAllQueueEmpty()) {
                log.error("processRecordQueueMap.value is not empty before close. KeyByQueueProcessor={}. remainQueuesSize={}",
                        name, processRecordQueueMap.values().stream().map(Collection::size).reduce(0, Integer::sum));
            }
        }
    }

    /**
     * 任务分发循环
     */
    private void startDistribute() {
        // 处理完队列里的消息再退出
        while (isProcessRunning.get() || !isAllQueueEmpty()) {
            // 空 map 时避免 load 过高
            if (processRecordQueueMap.isEmpty() || isAllQueueEmpty()) {
                try {
                    Thread.sleep(processIntervalTime);
                } catch (InterruptedException e) {
                    log.warn("interrupt when waiting for IN object. KeyByQueueProcessor={}. error=", name, e);
                    Thread.currentThread().interrupt();
                }
            }
            // 异步分发处理消息
            for (Integer key : processRecordQueueMap.keySet()) {
                ListeningExecutorService threadPool = processThreadPoolMap.get(key);
                BlockingQueue<IN> processRecordQueue = processRecordQueueMap.get(key);
                List<IN> recordList = Lists.newArrayListWithCapacity(processBatchSize);
                try {
                    Queues.drain(processRecordQueue, recordList, processBatchSize, Duration.ofMillis(processIntervalTime));
                    if (!recordList.isEmpty()) {
                        ListenableFuture<List<OUT>> future = threadPool.submit(() -> {
                            try {
                                List<OUT> outList = handle(recordList);
                                successQpsLogger.add(recordList.size());
                                return outList;
                            } catch (Throwable e) {
                                log.error("Throwable when processRecords. KeyByQueueProcessor={}, " +
                                                "processorThreadPoolMap.key={}, " +
                                                "recordList.size={}, recordList.get(0)={}. error=",
                                        name, key, recordList.size(), recordList.get(0), e);
                                failQpsLogger.add(recordList.size());
                                return Collections.emptyList();
                            }
                        });
                        Futures.addCallback(future, new FutureCallback<Object>() {
                            @Override
                            public void onSuccess(Object result) {
                                // nothing need to do
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                log.error("error occurred when process recordList. KeyByQueueProcessor={}, " +
                                                "processorThreadPoolMap.key={}, recordList.size={}, recordList={}. error=",
                                        name, key, recordList.size(), recordList, t);
                            }
                        }, exceptionHandleThreadPool);
                    }
                } catch (InterruptedException e) {
                    log.warn("KeyByQueueProcessor={} receiver Queues.drain was interrupted! processorThreadPoolMap.key={}, queuedRecordSize={}. error=",
                            name, key, processRecordQueue.size(), e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private boolean isAllQueueEmpty() {
        for (BlockingQueue<IN> blockingQueue : processRecordQueueMap.values()) {
            if (!blockingQueue.isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public void put(IN t) {
        Integer hash = Math.abs(keyBy(t)) % partitionNum;
        processRecordQueueMap.putIfAbsent(hash, Queues.newArrayBlockingQueue(this.perThreadQueueSize));
        processThreadPoolMap.computeIfAbsent(hash, key -> {
            String threadPoolName = String.format(THREAD_NAME_FORMAT, name, hash);
            ThreadPoolExecutor executor = RunUtils.createThreadPool(threadPoolName, 1, 1, 1000, Queues.newArrayBlockingQueue(10));
            return MoreExecutors.listeningDecorator(executor);
        });

        try {
            processRecordQueueMap.get(hash).put(t);
            receiveQpsLogger.add();
        } catch (InterruptedException e) {
            log.warn("KeyByQueueProcessor meet InterruptedException. error=", e);
            Thread.currentThread().interrupt();
        }
    }

    public abstract int keyBy(IN t);

    public abstract List<OUT> handle(List<IN> inBatch);
}
