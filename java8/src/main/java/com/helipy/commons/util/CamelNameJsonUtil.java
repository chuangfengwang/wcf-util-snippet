package com.helipy.commons.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * Pack:       com.helipy.commons.util
 * File:       CamelNameJsonUtil
 * Desc: 驼峰名称的JSON key转换为下划线格式, 会递归一个最大深度
 *
 * @author wangchuangfeng
 * CreateTime: 2023-09-22 18:31
 */
@Slf4j
public class CamelNameJsonUtil {

    /**
     * json key 大写转下划线最大遍历深度
     */
    private static final int MAX_JSON_CONVERT_DEEP = 10;

    /**
     * key 驼峰转下划线. 用于 java 风格参数转 python 风格参数
     *
     * @param object
     * @return
     */
    public static JSONObject keyLowerCamelToUnderScore(Object object) {
        String msg = JSON.toJSONString(object);
        return keyLowerCamelToUnderScore(JSON.parseObject(msg), 0);
    }

    /**
     * key 驼峰转下划线. 用于 java 风格参数转 python 风格参数
     *
     * @param list
     * @return
     */
    public static JSONArray keyLowerCamelToUnderScore(List<?> list) {
        String msg = JSON.toJSONString(list);
        return keyLowerCamelToUnderScore(JSON.parseArray(msg), 0);
    }


    /**
     * key 名称, 小写驼峰转下划线 notice: 只能处理 value是基本类型,JSONObject类型,JSONArray类型; 不能处理 value 是一般类型的情况;
     * 对于存在循环引用的,结果可能不对.
     * 改进: 用 System.identityHashCode(obj) 处理循环引用
     *
     * @param jsonObject
     * @param curDeep    当前深度
     * @return
     */
    public static JSONObject keyLowerCamelToUnderScore(JSONObject jsonObject, int curDeep) {
        JSONObject newJsonObject = new JSONObject();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String newKey = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey());
            // 递归出口: 值是基本类型
            if (entry.getValue() == null || entry.getValue() instanceof Number || entry.getValue() instanceof CharSequence
                    || entry.getValue() instanceof Boolean || entry.getValue() instanceof Character) {
                newJsonObject.put(newKey, entry.getValue());
            }
            // 元素是 list
            if (entry.getValue() instanceof JSONArray) {
                if (curDeep > MAX_JSON_CONVERT_DEEP) {
                    // 深度过大,不再转换
                    newJsonObject.put(newKey, entry.getValue());
                } else {
                    newJsonObject.put(newKey, keyLowerCamelToUnderScore((JSONArray) entry.getValue(), curDeep + 1));
                }
            }
            // 元素是对象
            if (entry.getValue() instanceof JSONObject) {
                if (curDeep > MAX_JSON_CONVERT_DEEP) {
                    // 深度过大,不再转换
                    newJsonObject.put(newKey, entry.getValue());
                } else {
                    newJsonObject.put(newKey, keyLowerCamelToUnderScore((JSONObject) entry.getValue(), curDeep + 1));
                }
            }
        }
        return newJsonObject;
    }

    public static JSONArray keyLowerCamelToUnderScore(JSONArray jsonArray, int curDeep) {
        JSONArray newArray = new JSONArray(jsonArray.size());
        for (Object item : jsonArray) {
            // 递归出口: 元素是基本类型
            if (item == null || item instanceof Number || item instanceof CharSequence
                    || item instanceof Boolean || item instanceof Character) {
                newArray.add(item);
            }
            // 元素是对象
            if (item instanceof JSONObject) {
                if (curDeep > MAX_JSON_CONVERT_DEEP) {
                    // 深度过大,不再转换
                    newArray.add(item);
                } else {
                    newArray.add(keyLowerCamelToUnderScore((JSONObject) item, curDeep + 1));
                }
            }
            // 元素还是 list
            if (item instanceof JSONArray) {
                if (curDeep > MAX_JSON_CONVERT_DEEP) {
                    // 深度过大,不再转换
                    newArray.add(item);
                } else {
                    newArray.add(keyLowerCamelToUnderScore((JSONArray) item, curDeep + 1));
                }
            }
        }
        return newArray;
    }

}
