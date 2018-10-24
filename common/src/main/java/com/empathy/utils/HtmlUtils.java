package com.empathy.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/6/20 17:19
 * @desc
 */
public final class HtmlUtils {

    private static final Map<Character, String> SPECIALS = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(fmtHTML("我们都是中国人"));
    }

    static {
        SPECIALS.put('<', "&lt;");
        SPECIALS.put('>', "&gt;");
        SPECIALS.put(' ', "&nbsp;");
        SPECIALS.put('"', "&quot;");
        SPECIALS.put('\'', "&apos;");
        SPECIALS.put('&', "&amp;");
    }

    public static String fmtHTML(String source) {
        if (StringUtils.isEmpty(source)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        StringWriter sw = new StringWriter(source.length() * 2);
        translate(source, sw);
        return sw.toString();
    }

    /**
     * 转码，处理富文本特殊字符或者防止SQL注入
     *
     * @param source
     * @param out
     */
    private static void translate(CharSequence source, StringWriter out) {
        final int len = source.length();
        for (int i = 0; i < len; i++) {
            char cha = source.charAt(i);
            if (SPECIALS.containsKey(cha)) {
                out.write(SPECIALS.get(cha));
            } else {
                out.write(cha);
            }
        }
    }

}
