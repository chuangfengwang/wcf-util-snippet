package com.helipy.commons.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Pack:       com.helipy.commons.util
 * File:       SampleLog
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-08-16 10:34
 */
public class SampleLog {
    /**
     * 工具类不需要实例化
     */
    private SampleLog() {
    }

    /**
     * 判断是否需要采样. 在 divisor 次中, 平均采样 1 次
     *
     * @param divisor
     * @return
     */
    public static boolean sampleHit(int divisor) {
        return sampleHit(divisor, 1);
    }

    /**
     * 判断是否需要采样. 在 divisor 次中, 平均采样 numerator 次
     *
     * @param divisor
     * @param numerator
     * @return
     */
    public static boolean sampleHit(int divisor, int numerator) {
        return ThreadLocalRandom.current().nextInt(0, divisor) < numerator;
    }
}
