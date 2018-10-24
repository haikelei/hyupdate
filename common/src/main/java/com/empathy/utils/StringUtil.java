package com.empathy.utils;

import java.math.BigDecimal;

/**
 * Created by yhy on 2017/5/15.
 */
public class StringUtil {

    //判定字符串是否为空
    public static boolean isNotEmpty(String str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNotDoubleEmpty(Double str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNotIntegerEmpty(Integer str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNotLongEmpty(Long str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

    public static Boolean isNotByteEmpty(Byte str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

    public static Boolean isNotBigDecimalEmpty(BigDecimal str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }
}
