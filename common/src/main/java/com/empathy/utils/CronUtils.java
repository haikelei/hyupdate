package com.empathy.utils;

import java.util.Date;

/**
 * @author tybest
 * @date 2017/9/20 17:03
 * @email zhoujian699@126.com
 * @desc
 **/
public final class CronUtils {

    private static final String DEF_FMT = "ss mm HH dd MM ? yyyy";

    /**
     * 固定的时点重复
     *
     * @return
     */
    public static String repeatTime2Cron(Integer month, Integer day, Integer hour, Integer minute, Integer seconds) {

        return "";
    }

    /**
     * 每天M时N分执行
     *
     * @param hour
     * @param minute
     * @return
     */
    public static String fixedTimeToCron(Integer hour, Integer minute) {
        //每天10:15执行
        //0 15 10 ? * * = 0 15 10 * * ? = 0 15 10 * * ? * =
        //0 15 10 * * ? 2005
        return "0 " + minute + " " + hour + " ? * *";
    }

    /**
     * 执行一次
     *
     * @param date
     * @return
     */
    public static String onceTime2Cron(Date date) {

        return "";
    }
}
