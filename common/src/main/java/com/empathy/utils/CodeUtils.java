package com.empathy.utils;

import java.util.Random;

/**
 * Created by MI on 2017/12/21.
 */
public class CodeUtils {

    public static String getCode() {
        Long time = System.currentTimeMillis();
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        String str = time + "" + i;
        return str;
    }

}
