package com.empathy.file;

import com.empathy.cache.OsInfo;
import com.empathy.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tybest
 * @date 2017/10/26 10:38
 * @email zhoujian699@126.com
 * @desc 本地文件系统
 **/
public class LocalFSUtils {
    private static final Set<String> SUFFIXES = new HashSet<>();
    private static final String linux_dir = "/www/lfs/";
    private static final String window_dir = "D:/www/lfs/";
    private static String CUR_DIR = null;

    static {
        if (OsInfo.isLinux()) {
            CUR_DIR = linux_dir;
        } else if (OsInfo.isWindows()) {
            CUR_DIR = window_dir;
        }
        SUFFIXES.add(".png");
        SUFFIXES.add(".jpg");
        SUFFIXES.add(".jpeg");
        SUFFIXES.add(".gif");
        SUFFIXES.add(".tif");
        SUFFIXES.add(".bmp");
    }


    private String genFileName() {
        return DateUtils.getDateStr(new Date(), DateUtils.DEF_TIME);
    }

    private String getSuffix(String originalName) {
        final int index = originalName.lastIndexOf(".");
        if (StringUtils.isNotBlank(originalName) && index > -1) {
            return originalName.substring(index).toLowerCase();
        }
        return "";
    }
}
