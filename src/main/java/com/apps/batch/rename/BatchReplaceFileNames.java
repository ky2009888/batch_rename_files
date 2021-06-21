package com.apps.batch.rename;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 批量替换指定目录中的文件名称.
 *
 * @author root
 */
@Slf4j(topic = "批量替换文件名中指定的字符串")
public class BatchReplaceFileNames {

    public static void main(String[] args) {
        batchReplaceTargetFileName();
    }

    /**
     * 批量替换文件名中的字符串，但不改变文件后缀名.
     */
    private static void batchReplaceTargetFileName() {
        String fileDir = "/root/Desktop/test/conf";
        String replaceFileName = "L";
        String targetFileName = "l";
        File[] files = FileUtil.ls(fileDir);
        for (File file : files) {
            handleDir(file, targetFileName, replaceFileName);
        }
    }

    /**
     * 循环处理目录，直到找到所有的文件.
     *
     * @param file 当前文件或目录.
     * @param targetFileName 原有的文件名(或部分字符串).
     * @param replaceFileName 新的文件名(或部分字符串).
     */
    private static void handleDir(File file, String targetFileName, String replaceFileName) {
        if (file.isDirectory()) {
            File[] files = FileUtil.ls(file.getAbsolutePath());
            for (File subFile : files) {
                handleDir(subFile, targetFileName, replaceFileName);
            }
        } else {
            String oldName = file.getName();
            if (oldName.contains(targetFileName)) {
                String newFileName = oldName.replace(targetFileName, replaceFileName);
                FileUtil.rename(file, newFileName, false, true);
            }
            log.info("{}", file.getAbsolutePath());
        }
    }
}
