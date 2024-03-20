package com.helipy.commons.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pack:       com.helipy.commons.util
 * File:       JavaToStringUtilsTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-01-11 16:44
 */
class JavaToStringUtilsTest {
    @Test
    public void toObject() throws Exception {
        DemoBean demoBean1 = DemoBean.builder()
                .c1('c').c2('d').s1((short) 1).s2((short) 2)
                .i1(1).i2(2).l1(1L).l2(2L)
                .f1(1.0F).f2(2.0F).d1(1.0D).d2(2.0D)
                .ss1("").ss2("null").date(new Date())
                .a(new A()).aList(Arrays.asList(new A(), new A()))
                .aArray((A[]) Arrays.asList(new A(), new A()).toArray())
                .build();
        {
            Map<String, A> aMap = new HashMap<>();
            aMap.put("1", new A());
            aMap.put("2", new A());
            aMap.put("3", new A());
            demoBean1.setAMap(aMap);
        }
        String demoBean1String = demoBean1.toString();
        System.out.println(demoBean1String);

        DemoBean demoBean2 = JavaToStringUtils.toObject(DemoBean.class, demoBean1String);
        System.out.println(demoBean2);
        assertEquals(demoBean1String, demoBean2.toString());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class DemoBean {
        private char c1;
        private Character c2;
        private short s1;
        private Short s2;
        private int i1;
        private Integer i2;
        private long l1;
        private long l2;
        private float f1;
        private Float f2;
        private double d1;
        private double d2;
        private String ss1;
        private String ss2;
        private String ss3 = null;
        private Date date;

        private A a;
        private List<A> aList;
        private A[] aArray;

        private Map<String, A> aMap;
    }

    @Data
    static class A {
        private static int num = 1;

        private int i = 11 + num++;
        private Long l = 22L + num++;
        private Date date = new Date(System.currentTimeMillis() + num++);
    }
}
