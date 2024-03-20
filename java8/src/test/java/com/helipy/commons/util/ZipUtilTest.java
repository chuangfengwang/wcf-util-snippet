package com.helipy.commons.util;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Pack:       com.helipy.commons.util
 * File:       ZipUtilTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-11-30 14:44
 */
public class ZipUtilTest {
    String text = "{\"batchSize\":3,\"text\":\"较强的沟通能力 严谨的逻辑思维 性格开朗 在做事方面：事事有着落 件件有回音\"}";
    Path path = Paths.get("", "a.zip");

    @Test
    void compressGzip() throws IOException {
        byte[] bytes = ZipUtil.compressGzip(text);
        Files.write(path, bytes);
    }

    @Test
    void decompressGzip() throws IOException {
        byte[] bytes = Files.readAllBytes(path);
        String text = ZipUtil.decompressGzip(bytes);
        System.out.println(text);
        assertEquals(text, this.text);
    }
}