package com.empathy.pay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author tybest
 * @date 2017/8/20 13:33
 * @email zhoujian699@126.com
 * @desc
 **/
public final class PayUtils {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();


    /**
     * 外部订单号必须唯一。支付
     *
     * @return
     */
    public static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        key = key + RANDOM.nextInt(10, 49);
        key = key.substring(0, 16);
        return key;
    }

    /**
     * 退款外部请求订单号
     *
     * @return
     */
    public static String getOutRequestTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        key = key + RANDOM.nextInt(50, 99);
        key = key.substring(0, 16);
        return key;
    }

    /**
     * 时间字符串
     *
     * @param now
     * @param pattern
     * @return
     */
    public static String getDateStr(Date now, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(now);
    }
}
