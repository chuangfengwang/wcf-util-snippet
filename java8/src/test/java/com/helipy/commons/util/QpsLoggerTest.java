package com.helipy.commons.util;


import org.junit.jupiter.api.Test;

/**
 * Pack:       com.helipy.commons.util
 * File:       QpsLoggerTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2024-03-14 18:29
 */
public class QpsLoggerTest {
    private QpsLogger qpsLogger = new QpsLogger("QpsLoggerTest", 1000);

    @Test
    void add() throws InterruptedException {
        for (int i = 0; i < 1_0000; ++i) {
            qpsLogger.add();
            Thread.sleep(1);
        }
        System.out.println("qps:" + qpsLogger.logQps());
    }
}