package com.helipy.commons.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.text.StringSubstitutor;

import java.util.List;
import java.util.Map;

/**
 * Pack:       com.helipy.commons.util
 * File:       StringUtils
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-02-08 16:43
 */
public class StringUtils {

    /**
     * 执行字符串模板替换
     *
     * @param template 占位符语法 ${var1} ${var2:-默认值}, 占位符可以重复,重复的占位符都会被替换掉
     * @param param    key为占位符变量 var1, var2
     * @return 返回占位符被参数替换掉的字符串. 参数里没有的占位符将按原样返回(仍为 ${var} 形式)
     */
    public static String templateReplace(String template, Map<String, Object> param) {
        if (param == null) {
            return template;
        }
        StringSubstitutor sub = new StringSubstitutor(param);
        return sub.replace(template);
    }


    /**
     * 执行字符串替换, 最多 maxCount 次
     * 是对 python str.replace(old, new, count) 的近似.
     * 注意: 这个函数 fromRegex 是正则语法, python 里是原始字符串
     *
     * @param text
     * @param fromRegex
     * @param to
     * @param maxCount  <=0 时,不执行替换
     * @return
     */
    public static String replace(String text, String fromRegex, String to, int maxCount) {
        if (Strings.isNullOrEmpty(text) || Strings.isNullOrEmpty(fromRegex) || to == null || maxCount <= 0) {
            return text;
        }
        String newText = text.replaceFirst(fromRegex, to);
        for (int i = 1; i < maxCount; ++i) {
            if (newText.equals(text)) {
                return newText;
            }
            text = newText;
            newText = text.replaceFirst(fromRegex, to);
        }
        return newText;
    }

    /**
     * 注意, "叁" 还有另一种写法 "参"(繁体大写三)
     */
    private static final List<String> bigChineseNumStrList =
            Lists.newArrayList("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖");
    private static final List<String> smallChineseNumStrList =
            Lists.newArrayList("零", "一", "二", "三", "四", "五", "六", "七", "八", "九");

    /**
     * 阿拉伯数字字符 转中文大写 ("零", "壹", "贰", ...)
     *
     * @param num 字符, 范围在 '0'-'9'
     * @return 中文大写 字符串
     */
    public static String toBigChineseNum(char num) {
        return toChineseNumSingle(num - '0', true);
    }

    /**
     * 阿拉伯数字字符 转中文小写 ("零", "一", "二", ...)
     *
     * @param num 字符, 范围在 '0'-'9'
     * @return 中文大写 字符串
     */
    public static String toSmallChineseNum(char num) {
        return toChineseNumSingle(num - '0', false);
    }

    public static String toChineseNumSingle(int num, boolean useBig) {
        if (num < 0 || num > 10) {
            throw new RuntimeException("toChineseNumSingle support only single digit");
        }
        if (useBig) {
            return bigChineseNumStrList.get(num);
        } else {
            return smallChineseNumStrList.get(num);
        }
    }
}
