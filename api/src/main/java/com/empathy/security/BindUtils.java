package com.empathy.security;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author tybest
 * @date 2017年6月9日 上午11:38:48
 * @email tybest@126.com
 * @desc
 */
public final class BindUtils {
    /**
     * setter 赋值
     */
    public static void setValue(final Object target, final String methodName, final String value, final Method[] ms) {
        for (Method m : ms) {
            final Class<?>[] pts = m.getParameterTypes();
            if (pts != null && pts.length > 0 && m.getName().equals(methodName)) {
                try {
                    m.invoke(target, convert(pts[0], value));
                } catch (Exception e) {
                    // do something
                }
                break;
            }
        }
    }
    /**
     * 获得方法
     * @param methodName
     * @param ms
     * @return
     */
//	private static Method getSetterMethod(final String methodName, final Method[] ms){
//		for(Method m: ms){
//			final String mn = m.getName();
//			if(mn.equals(methodName)){
//				return m;
//			}
//		}
//		return null;
//	}
    /**
     * @param methodName
     * @param target
     * @param ms
     * @return
     */
//	private static Class<?> getSetterType(final String methodName, Object target, final Method[] ms){
//		for(Method m: ms){
//			final Class<?>[] pts = m.getParameterTypes();
//			final String mn = m.getName();
//			if(pts != null && pts.length>0){
//				if(mn.equals(methodName)){
//					return pts[0];
//				}
//			}
//		}
//		return null;
//	}

    private static final String REG_NUM = "^(-?\\d+)(\\.\\d+)?$";

    private static boolean isNumber(String numStr) {
        return Pattern.compile(REG_NUM).matcher(numStr).find();
    }

    /**
     * 字符串转换 不允许有集合
     */
    private static Object convert(Class<?> type, String value) {
        if (StringUtils.isBlank(value) && (
                type == int.class
                        || type == long.class
                        || type == float.class
                        || type == double.class
                        || type == byte.class
                        || type == char.class
                        || type == boolean.class
                        || type == short.class)) {//基础类型不能为null
            return 0;
        }
        if (StringUtils.isBlank(value)) {
            return null;
        }
        boolean isNum = isNumber(value);
        if (type == Double.class || type == double.class) {
            if (!isNum) {
                return null;
            }
            return Double.valueOf(value);
        } else if (type == int.class || type == Integer.class) {
            if (!isNum) {
                return null;
            }
            return Integer.valueOf(value);
        } else if (type == float.class || type == Float.class) {
            if (!isNum) {
                return null;
            }
            return Float.valueOf(value);
        } else if (type == long.class || type == Long.class) {
            if (!isNum) {
                return null;
            }
            return Long.valueOf(value);
        } else if (type == char.class || type == Char.class) {
            return value.toCharArray();
        } else if (type == short.class || type == Short.class) {
            return Short.valueOf(value);
        } else if (type == String.class) {
            return value;
        } else if (type == byte.class || type == Byte.class) {
            return value.getBytes();
        } else if (type == boolean.class || type == Boolean.class) {
            return value.toLowerCase().equals("true");
        } else if (type == BigDecimal.class) {
            if (!isNum) {
                return null;
            }
            return new BigDecimal(value);
        } else if (type == Date.class) {
            return stringToDate(value);
        } else {
            return null;
        }
    }

    private static Date stringToDate(String dateString) {
        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateString, "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd",
                    "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss");
        } catch (ParseException e) {
            return null;
        }
    }
}
