package com.helipy.commons.util;

import com.google.common.base.Strings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Pack:        com.helipy.commons.util
 * File:       DatetimeUtils
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2022-12-14 20:53
 */
public class DatetimeUtils {
    /**
     * 一个时间的对应那一天的开始时间
     *
     * @param now
     * @return
     */
    public static LocalDateTime startTimeOfDay(LocalDateTime now) {
        // 获取一天的开始时间
        return LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 一个时间对应那一天的结束时间
     *
     * @param now
     * @return
     */
    public static LocalDateTime endTimeOfDay(LocalDateTime now) {
        return LocalDateTime.of(now.toLocalDate(), LocalTime.MAX);
    }

    /**
     * LocalDateTime 转 unix 时间戳: 毫秒
     *
     * @param localDatetime
     * @return
     */
    public static long toUnixTimestampMilli(LocalDateTime localDatetime) {
        return localDatetime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 各种时间格式
     */
    private static final Pattern formatter8Pattern = Pattern.compile("[\\d]{4}-[\\d]{2}-[\\d]{2}");
    private static final Pattern formatter7Pattern = Pattern.compile("[\\d]{4}[\\d]{2}[\\d]{2}");
    private static final DateTimeFormatter formatter8 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatter7 = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private static final DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");

    /**
     * 解析时间字符串
     *
     * @param timeStr
     * @return 解析出的时间. 空字符串返回 null, 不能解析时报运行时异常 DateTimeParseException
     * @throws DateTimeParseException 非空字符串无法解析时
     */
    public static LocalDateTime parseDateTime(String timeStr) {
        if (Strings.isNullOrEmpty(timeStr)) {
            return null;
        }
        // 修复日期为 00 的日期为当月第一天
        if (formatter7Pattern.matcher(timeStr).matches() && timeStr.endsWith("00")) {
            // 19970100
            timeStr = timeStr.substring(0, 6) + "01";
        } else if (formatter8Pattern.matcher(timeStr).matches() && timeStr.endsWith("00")) {
            // 1997-01-00
            timeStr = timeStr.substring(0, 8) + "01";
        }
        // 修复月份为 00 的月份为当年第一月
        if (formatter7Pattern.matcher(timeStr).matches() && "00".equals(timeStr.substring(4, 6))) {
            // 19970001
            timeStr = timeStr.substring(0, 4) + "01" + timeStr.substring(6);
        } else if (formatter8Pattern.matcher(timeStr).matches() && "00".equals(timeStr.substring(5, 7))) {
            // 1997-00-01
            timeStr = timeStr.substring(0, 5) + "01" + timeStr.substring(7);
        }
        try {
            LocalDate localDate = LocalDate.parse(timeStr, formatter8);
            return LocalDateTime.of(localDate, LocalTime.MIN);
        } catch (DateTimeParseException e8) {
            try {
                LocalDate localDate = LocalDate.parse(timeStr, formatter7);
                return LocalDateTime.of(localDate, LocalTime.MIN);
            } catch (DateTimeParseException e7) {
                try {
                    return LocalDateTime.parse(timeStr, formatter1);
                } catch (DateTimeParseException e1) {
                    try {
                        return LocalDateTime.parse(timeStr, formatter2);
                    } catch (DateTimeParseException e2) {
                        try {
                            return LocalDateTime.parse(timeStr, formatter3);
                        } catch (DateTimeParseException e3) {
                            try {
                                return LocalDateTime.parse(timeStr, formatter4);
                            } catch (DateTimeParseException e4) {
                                try {
                                    return LocalDateTime.parse(timeStr, formatter5);
                                } catch (DateTimeParseException e5) {
                                    return LocalDateTime.parse(timeStr, formatter6);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Double ageByMonthBirthday(String birthdayStr, String nowStr) {
        if (Strings.isNullOrEmpty(birthdayStr)) {
            return null;
        }
        LocalDateTime now = null;
        if (Strings.isNullOrEmpty(nowStr)) {
            now = LocalDateTime.now();
        } else {
            now = parseDateTime(nowStr);
        }
        if (now == null) {
            return null;
        }
        LocalDateTime birthday = parseDateTime(birthdayStr);
        return ageByMonthBirthday(birthday, now);
    }

    public static Double ageByMonthBirthday(LocalDateTime birthday, LocalDateTime now) {
        if (birthday == null || now == null) {
            return null;
        }
        int monthNow = now.getYear() * 12 + now.getMonth().getValue();
        int monthBirth = birthday.getYear() * 12 + birthday.getMonth().getValue();
        return (monthNow - monthBirth) / 12.;
    }

    public static Integer ageByYearBirthday(String birthdayStr, String nowStr) {
        if (Strings.isNullOrEmpty(birthdayStr)) {
            return null;
        }
        LocalDateTime now = null;
        if (Strings.isNullOrEmpty(nowStr)) {
            now = LocalDateTime.now();
        } else {
            now = parseDateTime(nowStr);
        }
        if (now == null) {
            return null;
        }
        LocalDateTime birthday = parseDateTime(birthdayStr);
        return ageByYearBirthday(birthday, now);
    }

    public static Integer ageByYearBirthday(LocalDateTime birthday, LocalDateTime now) {
        if (birthday == null || now == null) {
            return null;
        }
        return now.getYear() - birthday.getYear();
    }
}
