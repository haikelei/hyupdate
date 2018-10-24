package com.empathy.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/6/28 14:38
 * @desc
 */
public final class EncryptUtils {


    public static void main(String[] args) {
        System.out.println(encrypt("888888"));
    }

    public static String encrypt(String plaintext) {
        if ((plaintext == null) || ("".equals(plaintext)))
            return null;
        byte[] secretArr = null;
        try {
            byte[] byteArr = Base64.getEncoder().encode(plaintext.getBytes("UTF-8"));
            char ch = 'F';
            byte[] key = ("淼龘" + ch + "^").getBytes("UTF-8");
            secretArr = Arrays.copyOf(byteArr, byteArr.length + key.length);
            for (int i = 0; i < key.length; ++i)
                secretArr[(byteArr.length + i)] = key[i];
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DigestUtils.md5Hex(secretArr).toUpperCase();
    }
}
