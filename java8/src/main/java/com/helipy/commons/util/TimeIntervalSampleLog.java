package com.helipy.commons.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Pack:       com.qunar.corp.algo.aiops.algolog.web.util
 * File:       TimeIntervalSampleLog
 * Desc: 按时间间隔采样打印日志
 * User:       chuangfeng.wang
 * CreateTime: 2024-12-11 16:54
 */
@Slf4j
public class TimeIntervalSampleLog {
    private final String name;
    private final int intervalMilliSecond;
    /**
     * 上次打印时间
     */
    private final AtomicReference<LocalDateTime> lastLogTime = new AtomicReference<>(LocalDateTime.now());

    /**
     * 几个常量
     */
    private static final long MILLION_CONSTANT = 1000_000L;
    private static final long NANO_SECOND_CONSTANT = 1000_000_000L;

    public TimeIntervalSampleLog(String name, int intervalMilliSecond) {
        this.name = name;
        this.intervalMilliSecond = intervalMilliSecond;
    }

    public void samplePrintLog(String msg) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastLogTime.get(), now);
        long nanoDuration = duration.getSeconds() * NANO_SECOND_CONSTANT + duration.getNano();
        if (nanoDuration >= intervalMilliSecond * MILLION_CONSTANT) {
            log.info("{}:{}, msg: {}", this.getClass().getSimpleName(), name, msg);
            // 更新时间
            lastLogTime.set(now);
        }
    }
}
