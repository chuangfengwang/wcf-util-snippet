package com.helipy.commons.util;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Pack:       com.helipy.commons.util
 * File:       FileUtils
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-01-17 21:07
 */
@Slf4j
public class FileUtils {

    public static String copyJarResource(String jarClassPathFileName) throws IOException {
        String toPath = "./" + jarClassPathFileName;
        return copyJarResource(jarClassPathFileName, toPath);
    }

    public static String copyJarResource(String jarClassPathFileName, String toPath) throws IOException {
        // 获取文件
        Resource resource = new ClassPathResource(jarClassPathFileName);
        File tmpFile = new File(toPath);

        if (tmpFile.getAbsolutePath().equals(resource.getURI().getPath())) {
            // 临时文件和 resource 是同一个文件，直接加载，避免复盖写入
            return toPath;
        } else {
            // 从jar中或者classPath中复制进临时文件
            org.apache.commons.io.FileUtils.copyToFile(resource.getInputStream(), tmpFile);
            return toPath;
        }
    }

    /**
     * 按行读取文件
     *
     * @param path
     * @return
     */
    public static List<String> readLines(String path) {
        File file = new File(path);
        try {
            return com.google.common.io.Files.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("read file error. path:{}, error=", path, e);
            return null;
        }
    }

    public static List<String> readLines(String dir, String path) {
        File file = new File(dir, path);
        try {
            return com.google.common.io.Files.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("read file error. path:{}, error=", path, e);
            return null;
        }
    }

    public static void writeFile(InputStream inputStream, String dir, String path) {
        try {
            Files.copy(inputStream, Paths.get(dir, path), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("write file error. path:{}, error=", path, e);
        }
    }

    public static String pathFileName(String path) {
        if (Strings.isNullOrEmpty(path)) {
            return null;
        }
        if (path.endsWith(File.separator)) {
            log.error("extract fileName from dir: {}", path);
            return "";
        }
        List<String> pieces = Splitter.on(File.separator).splitToList(path);
        String fileName = pieces.get(pieces.size() - 1);
        return fileName;
    }

    /**
     * 文件是否存在
     *
     * @param dir  目录路径
     * @param path 目录下文件路径
     * @return
     */
    public static boolean exists(String dir, String path) {
        if (Strings.isNullOrEmpty(dir)) {
            return Files.exists(Paths.get(path));
        } else {
            return Files.exists(Paths.get(dir, path));
        }
    }

    public static void deleteFile(String filePath) throws IOException {
        // 删除临时文件(在容器中,这里的删除会报错,原因还未查明)
        Files.delete(Paths.get(filePath));
    }

    public static List<String> loadLineFromJarResource(String classPathFileName) throws IOException {
        String copyFilePath = copyJarResource(classPathFileName);
        List<String> lines = com.google.common.io.Files.readLines(new File(copyFilePath), StandardCharsets.UTF_8);
        return lines;
    }

}
