package com.helipy.commons.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Pack:       com.helipy.commons.util
 * File:       QpsLogger
 * Desc: qps 记录器
 *
 * @author wangchuangfeng
 * CreateTime: 2024-03-14 18:06
 */
@Slf4j
public class QpsLogger {
    /**
     * QPS 记录器名称,标记当前 qpsLogger
     */
    private final String name;
    /**
     * 计数的
     */
    private final AtomicLong counter = new AtomicLong(0);
    /**
     * 上次打印时间
     */
    private final AtomicReference<LocalDateTime> lastLogTime = new AtomicReference<>(LocalDateTime.now());
    /**
     * 打印间隔, 毫秒
     */
    private final long loggerInterval;

    /**
     * 几个常亮
     */
    private static final long MILLION_CONSTANT = 1000_000L;
    private static final long NANO_SECOND_CONSTANT = 1000_000_000L;
    private static final double NANO_CONSTANT = 1000_000_000d;

    public QpsLogger(String name, long loggerInterval) {
        this.name = name;
        this.loggerInterval = loggerInterval;
    }

    public long add() {
        return add(1);
    }

    public long add(int delta) {
        long c = counter.addAndGet(delta);
        logQps();
        return c;
    }

    public Double logQps() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(lastLogTime.get(), now);
        long nanoDuration = duration.getSeconds() * NANO_SECOND_CONSTANT + duration.getNano();
        double qps = counter.get() / (nanoDuration / NANO_CONSTANT);
        if (nanoDuration >= loggerInterval * MILLION_CONSTANT) {
            log.info("{}:{}, QPS:{}", this.getClass().getSimpleName(), name, qps);
            // 更新计数和时间
            counter.set(0L);
            lastLogTime.set(now);
        }
        return qps;
    }
}
