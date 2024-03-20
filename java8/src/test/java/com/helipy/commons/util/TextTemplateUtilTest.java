package com.helipy.commons.util;

import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Pack:       com.helipy.commons.util
 * File:       TextTemplateUtilTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-09-20 14:32
 */
public class TextTemplateUtilTest {

    @Test
    void commonsTextTest() {
        Map<String, Object> valuesMap = new HashMap();
        valuesMap.put("code", 1234);
        valuesMap.put("msg", "你好");
        String templateString = "码:${code},消息:${msg:-},码2:${code}";
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        String content = sub.replace(templateString);
        System.out.println(content);
    }
}
