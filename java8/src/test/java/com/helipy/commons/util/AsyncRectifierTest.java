package com.helipy.commons.util;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * Pack:       com.helipy.commons.util
 * File:       AsyncRectifierTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2024-03-19 13:53
 */
public class AsyncRectifierTest {
    ThreadPoolExecutor buildDocThreadPool = RunUtils.createThreadPool("AsyncRectifierTestAsyncRectifier",
            5, 5, 60, new SynchronousQueue<>());

    final AsyncRectifier asyncRectifier = new AsyncRectifier<Integer, Long>(
            "AsyncRectifierTestAsyncRectifier", 5, 1000, 100, 100, buildDocThreadPool
    ) {
        @Override
        public List<Long> handleBatch(List<Integer> inBatch) {
            // 如何处理每批数据
            List<Long> collect = inBatch.stream()
                    .map(a -> a * ThreadLocalRandom.current().nextLong(100))
                    .filter(b -> b > 1000_0000)
                    .collect(Collectors.toList());
            return collect;
        }

        @Override
        public void handleResult(List<Long> outBatch) {
            // 如何处理每批数据的结果
            System.out.println(JSON.toJSONString(outBatch));
        }
    };

    private List<Integer> genInput(int batchSize) {
        List<Integer> array = new ArrayList<>(batchSize);
        for (int i = 0; i < batchSize; ++i) {
            array.add(ThreadLocalRandom.current().nextInt());
        }
        return array;
    }

    @Test
    void asyncRectifierTest() throws InterruptedException {
        // 启动整流器
        asyncRectifier.startLoop();
        for (int i = 0; i < 10; ++i) {
            List<Integer> array = genInput(10);
            // 丢给整流器处理
            asyncRectifier.putAll(array);
        }

        Thread.sleep(2000);
    }
}