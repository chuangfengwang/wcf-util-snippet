package com.helipy.commons.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * Pack:        com.helipy.commons.util
 * File:       DatetimeUtilsTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2022-12-21 15:09
 */
class DatetimeUtilsTest {

    @Test
    void startTimeOfDay() {
        LocalDateTime startTimeOfDay = DatetimeUtils.startTimeOfDay(LocalDateTime.now());
        System.out.println(startTimeOfDay);
    }

    @Test
    void endTimeOfDay() {
        LocalDateTime endTimeOfDay = DatetimeUtils.endTimeOfDay(LocalDateTime.now());
        System.out.println(endTimeOfDay);
    }

    @Test
    void toUnixTimestampMilli() {
        long nextDayStartMilli = DatetimeUtils.toUnixTimestampMilli(DatetimeUtils.startTimeOfDay(LocalDateTime.now().plusDays(1)));
        System.out.println(nextDayStartMilli / 1000);
    }

    @Test
    public void parseDateTime1() {
        System.out.println(DatetimeUtils.parseDateTime("1997-07-01 00:00"));
        System.out.println(DatetimeUtils.parseDateTime("19900101"));
        System.out.println(DatetimeUtils.parseDateTime("2000-02-29"));
        System.out.println(DatetimeUtils.parseDateTime("2000-01-01 00:00:00"));

        System.out.println(DatetimeUtils.parseDateTime("1997-07-00"));
        System.out.println(DatetimeUtils.parseDateTime("20170700"));
        System.out.println(DatetimeUtils.parseDateTime("19970700"));

        System.out.println(DatetimeUtils.parseDateTime("1997-00-01"));
        System.out.println(DatetimeUtils.parseDateTime("19970001"));

        System.out.println(DatetimeUtils.parseDateTime("1997-00-00"));
        System.out.println(DatetimeUtils.parseDateTime("19970000"));
    }

    @Test
    public void ageByMonthBirthday1() {
        System.out.println(DatetimeUtils.ageByMonthBirthday("19940201", null));
        System.out.println(DatetimeUtils.ageByMonthBirthday("2000-01-01", null));
        System.out.println(DatetimeUtils.ageByMonthBirthday("2000-01-01", "2024-01-01"));
        System.out.println(DatetimeUtils.ageByMonthBirthday("2000-10-01", "2024-01-01"));
    }

    @Test
    public void ageByYearBirthday1() {
        System.out.println(DatetimeUtils.ageByYearBirthday("19940201", null));
        System.out.println(DatetimeUtils.ageByYearBirthday("2000-01-01", null));
        System.out.println(DatetimeUtils.ageByYearBirthday("2000-01-01", "2024-01-01"));
        System.out.println(DatetimeUtils.ageByYearBirthday("2000-10-01", "2024-01-01"));
    }
}