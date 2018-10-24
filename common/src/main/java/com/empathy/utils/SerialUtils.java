package com.empathy.utils;

import java.io.*;

/**
 * @author tybest
 * @date 2017/8/20 13:22
 * @email zhoujian699@126.com
 * @desc
 **/
public final class SerialUtils {

    public static byte[] objToBytes(Object value) {
        byte[] bytes = null;
        if (value != null) {
            ByteArrayOutputStream baos = null;
            ObjectOutputStream oos = null;
            try {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(value);
                bytes = baos.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (baos != null) {
                        baos.close();
                    }
                    if (oos != null) {
                        oos.close();
                    }
                } catch (IOException e) {
                    // some
                }
            }
        }
        return bytes;
    }

    public static Object byteToObj(final byte[] bytes) {
        Object obj = null;
        if (bytes != null && bytes.length > 0) {
            ByteArrayInputStream bais = null;
            ObjectInputStream ois = null;
            try {
                bais = new ByteArrayInputStream(bytes);
                ois = new ObjectInputStream(bais);
                obj = ois.readObject();
            } catch (Exception ex) {
                //donothing
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    // auto-generated catch block
                }
            }
        }
        return obj;
    }
}
