package com.helipy.commons.util;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Pack:      com.helipy.commons.util
 * File:       AsyncRectifier
 * Desc:       异步消息整流器
 * 接收消息,并以固定批量(超时时消息会不足批量大小)处理,处理结果使用回调函数(handleResult)加工
 *
 * @author wangchuangfeng
 * CreateTime: 2023-11-16 16:54
 */
@Slf4j
public abstract class AsyncRectifier<IN, OUT> {
    /**
     * 消息批量处理逻辑
     *
     * @param inBatch
     * @return
     */
    public abstract List<OUT> handleBatch(List<IN> inBatch);

    public abstract void handleResult(List<OUT> outBatch);

    /**
     * 标记
     */
    private final String identify;
    /**
     * 是否在运行
     */
    @Getter
    private final AtomicBoolean isRunning;
    /**
     * 参数队列
     */
    private BlockingQueue<IN> inBlockingQueue;
    /**
     * 结果队列
     */
    @Getter
    private BlockingQueue<OUT> outBlockingQueue;
    /**
     * 每批次最大量
     */
    private int batchSize;
    /**
     * 批次间最大时间间隔
     */
    private int maxInterval;
    /**
     * 异步提交作业的线程
     */
    private ListeningExecutorService submitThreadPool;
    private ThreadPoolExecutor submitThreadPoolInner;

    public AsyncRectifier(String identify, int batchSize, int maxInterval,
                          int maxInQueueSize, int maxOutQueueSize,
                          ThreadPoolExecutor submitThreadPoolInner) {
        this.identify = identify;
        this.batchSize = batchSize;
        this.maxInterval = maxInterval;
        // 初始化时不运行
        this.isRunning = new AtomicBoolean(false);
        // 输入阻塞队列
        this.inBlockingQueue = Queues.newArrayBlockingQueue(maxInQueueSize);
        // 输出阻塞队列
        this.outBlockingQueue = Queues.newArrayBlockingQueue(maxOutQueueSize);
        // 异步提交线程池
        this.submitThreadPoolInner = submitThreadPoolInner;
        this.submitThreadPool = MoreExecutors.listeningDecorator(this.submitThreadPoolInner);
    }

    public void put(IN in) {
        try {
            inBlockingQueue.put(in);
        } catch (InterruptedException e) {
            log.error("MsgRectifier put error by InterruptedException. e=", e);
            Thread.currentThread().interrupt();
        }
    }

    public void putAll(Iterable<IN> inIter) {
        for (IN in : inIter) {
            put(in);
        }
    }

    private void cacheResult(Iterable<OUT> results) {
        for (OUT out : results) {
            try {
                outBlockingQueue.put(out);
            } catch (InterruptedException e) {
                log.error("MsgRectifier cacheResult error by InterruptedException. e=", e);
                Thread.currentThread().interrupt();
            }
        }
    }


    public void startLoop() {
        isRunning.set(true);
        // 启动计数线程
        submitThreadPool.submit(this::drainResultLoop);
        submitThreadPool.submit(this::submitLoop);
    }

    void drainResultLoop() {
        while (isRunning.get() || !outBlockingQueue.isEmpty()) {
            List<OUT> resultList = Lists.newArrayListWithCapacity(batchSize);
            try {
                Queues.drain(outBlockingQueue, resultList, batchSize, Duration.ofMillis(maxInterval));
                if (!resultList.isEmpty()) {
                    ListenableFuture<?> future = submitThreadPool.submit(() -> {
                        try {
                            handleResult(resultList);
                        } catch (Throwable e) {
                            log.error("Throwable when AsyncRectifier handleResult. identify={}, " +
                                            "resultList.size={}, resultList.get(0)={}. error=",
                                    identify, resultList.size(), JSON.toJSONString(resultList.get(0)), e);
                        }
                    });
                    Futures.addCallback(future, new FutureCallback<Object>() {
                        @Override
                        public void onSuccess(Object result) {
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            log.error("error occurred when AsyncRectifier handleResult. resultList.size={}, resultList.get(0)={}. error=",
                                    resultList.size(), JSON.toJSONString(resultList.get(0)), t);
                        }
                    }, submitThreadPool);
                }
            } catch (InterruptedException e) {
                log.warn("identify={} AsyncRectifier outBlockingQueue Queues.drain was interrupted! msgQueueSize={}. error={}",
                        identify, inBlockingQueue.size(), e);
                Thread.currentThread().interrupt();
            }
        }
    }

    void submitLoop() {
        while (isRunning.get() || !inBlockingQueue.isEmpty()) {
            List<IN> msgList = Lists.newArrayListWithCapacity(batchSize);
            try {
                Queues.drain(inBlockingQueue, msgList, batchSize, Duration.ofMillis(maxInterval));
                if (!msgList.isEmpty()) {
                    ListenableFuture<?> future = submitThreadPool.submit(() -> {
                        try {
                            List<OUT> outBatch = handleBatch(msgList);
                            // 队列满时会阻塞
                            cacheResult(outBatch);
                        } catch (Throwable e) {
                            log.error("Throwable when AsyncRectifier handleBatch. identify={}, " +
                                            "msgList.size={}, msgList.get(0)={}. error=",
                                    identify, msgList.size(), JSON.toJSONString(msgList.get(0)), e);
                        }
                    });
                    Futures.addCallback(future, new FutureCallback<Object>() {
                        @Override
                        public void onSuccess(Object result) {
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            log.error("error occurred when AsyncRectifier handleBatch. msgList.size={}, msgList.get(0)={}. error=",
                                    msgList.size(), JSON.toJSONString(msgList.get(0)), t);
                        }
                    }, submitThreadPool);
                }
            } catch (InterruptedException e) {
                log.warn("identify={} AsyncRectifier inBlockingQueue Queues.drain was interrupted! msgQueueSize={}. error={}",
                        identify, inBlockingQueue.size(), e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
