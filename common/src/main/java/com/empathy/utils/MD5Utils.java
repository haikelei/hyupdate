package com.empathy.utils;

import java.security.MessageDigest;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/5/8 14:08
 * @desc
 */
public final class MD5Utils {

    public static void main(String[] args) {
        System.out.println(Long.toHexString(3506693255152640L));
    }

    public static String md5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String encrypt(String s) {
        return md5(md5(s));
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }



    private static char hexs[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f' };

    public static String encode(String source) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");

            byte[] sbs = source.getBytes("UTF8");
            digester.update(sbs);
            byte[] rbs = digester.digest();
            int j = rbs.length;
            char result[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = rbs[i];
                result[k++] = hexs[b >>> 4 & 0xf];
                result[k++] = hexs[b & 0xf];
            }
            return new String(result);
        } catch (Exception e) {
            return null;
        }
    }



    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};
}
