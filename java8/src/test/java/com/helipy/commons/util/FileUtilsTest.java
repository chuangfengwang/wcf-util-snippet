package com.helipy.commons.util;

import com.google.common.base.Joiner;
import com.google.common.io.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Pack:       com.helipy.commons.util
 * File:       FileUtilsTest
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-01-19 14:39
 */
class FileUtilsTest {

    @Test
    void copyJarResource() throws IOException {
        // 测试: 复制 resource 中的文件到当前目录, 然后读取复制的文件
        String bzMetaFileName = "text-conf/b.txt";
        String tmpFile = FileUtils.copyJarResource(bzMetaFileName);
        try (InputStream is = new FileInputStream(tmpFile)) {
            String string = CharStreams.toString(new InputStreamReader(is, StandardCharsets.UTF_8));
            System.out.println(string);
            assertNotNull(string);
        }
    }

    @Test
    void readComIdTest() throws IOException {
        String fileInJarName = "text-conf/b.txt";
        List<String> lines = FileUtils.loadLineFromJarResource(fileInJarName);
        System.out.println(Joiner.on("\n").join(lines));
    }

    @Test
    void pathFileNameTest() {
        String fileName = FileUtils.pathFileName("text-conf/a.txt");
        System.out.println(fileName);
    }
}