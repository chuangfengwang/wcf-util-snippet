package com.helipy.commons.util;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Pack:       com.kanzhun.fc.feature.update.service.util
 * File:       KeyByQueueProcessorTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2024-03-15 10:51
 */
public class KeyByQueueProcessorTest {
    private KeyByQueueProcessor keyByQueueProcessor = new KeyByQueueProcessor<TestParam, Void>(
            "KeyByQueueProcessorTest",
            200,
            3,
            100,
            100
    ) {
        @Override
        public int keyBy(TestParam t) {
            return (int) (t.getKey() % Integer.MAX_VALUE);
        }

        @Override
        public List<Void> handle(List<TestParam> inBatch) {
            if (ThreadLocalRandom.current().nextFloat() < 0.01) {
                throw new RuntimeException("处理异常");
            }
            System.out.println("handle inBatch.size:" + inBatch.size());
            return Collections.emptyList();
        }
    };

    /**
     * 单线程提交
     */
    @Test
    public void put1() {
        keyByQueueProcessor.start();
        for (int i = 0; i < 10000_00; ++i) {
            TestParam param = new TestParam();
            param.setKey(ThreadLocalRandom.current().nextLong());
            keyByQueueProcessor.put(param);
        }
        keyByQueueProcessor.close();
    }

    /**
     * 多线程提交
     *
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    @Test
    public void put2() throws ExecutionException, InterruptedException, TimeoutException {
        keyByQueueProcessor.start();
        int threadNum = 10;
        ThreadPoolExecutor threadPool = RunUtils.createThreadPool("submit-test", threadNum);
        List<CompletableFuture<Void>> futureList = new LinkedList<>();
        for (int j = 0; j < threadNum; ++j) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                for (int i = 0; i < 1000_00; ++i) {
                    TestParam param = new TestParam();
                    param.setKey(ThreadLocalRandom.current().nextLong());
                    keyByQueueProcessor.put(param);
                }
            }, threadPool);
            futureList.add(future);
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]))
                .get(300, TimeUnit.SECONDS);
        keyByQueueProcessor.close();
    }

    @Data
    public static class TestParam {
        private long key;
    }
}