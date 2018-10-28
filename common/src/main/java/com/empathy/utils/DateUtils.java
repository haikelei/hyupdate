/**
 *
 */
package com.empathy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tybest
 * @date 2017年6月15日 下午1:50:18
 * @email tybest@126.com
 * @desc
 */
public final class DateUtils {

    public static final String DEF_TIME = "yyyyMMddHHmmssSSS";
    public static final String DEF_TIME_MILLI = "yyyyMMddHHmmss";
    public static final String DEF_DATE = "yyyy-MM-dd";
    public static final String DEF_DATE_MONTH = "yyyy-MM";
    public static final String DEF_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis());
//        System.out.println(getDateStr(new Date(),DEF_TIME));
//        System.out.println(getDateStr(new Date(),DEF_DATE));
//        System.out.println(getDateStr(new Date(),DEF_DATETIME));
        //String str = "0 2 2 ? * *";
        // String[] ss = str.split("\\s+");
//        for(String s: ss){
//            System.out.println(s);
//        }
        //System.out.println(ss[1]);
        //System.out.println(ss[2]);
        //System.out.println(getNextWeekStr(new Date(),"yyyy-MM-dd",0));
        //System.out.println(getNextWeekStr(new Date(),"yyyy-MM-dd",-1));
//        System.out.println(getIntervalMonths("2017-08-09","2017-09-09"));
        System.out.println(getMonthTail("2017-02-09"));

        LocalDateTime.now();
        Instant.now();
        DateTimeFormatter.ofPattern("");

    }

    public static String getNowStr() {
        return getDateStr(new Date(), DEF_DATE);
    }

    /**
     * 时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateStr(final Date date, final String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 相差天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalDays(String start, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATE);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(sdf.parse(start));
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(sdf.parse(end));
            int day1 = cal1.get(Calendar.DAY_OF_YEAR);
            int day2 = cal2.get(Calendar.DAY_OF_YEAR);
            int year1 = cal1.get(Calendar.YEAR);
            int year2 = cal2.get(Calendar.YEAR);
            if (year1 != year2) {
                int timeDistance = 0;
                for (int i = year1; i < year2; i++) {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                        timeDistance += 366;
                    } else {
                        timeDistance += 365;
                    }
                }
                return timeDistance + (day2 - day1);
            } else    //不同年
            {
                return day2 - day1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 相差月数，同一个月差额为1
     *
     * @param start
     * @param end
     * @return
     */
    public static int getIntervalMonths(String start, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATE);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(sdf.parse(start));
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(sdf.parse(end));
            int month1 = cal1.get(Calendar.MONTH);
            int month2 = cal2.get(Calendar.MONTH);
            int year1 = cal1.get(Calendar.YEAR);
            int year2 = cal2.get(Calendar.YEAR);
            int yy = year2 - year1;
            int mm = month2 - month1;
            int ym = yy * 12;
            return ym + mm + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getMonthTail(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATE);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(sdf.parse(dateStr));
            cal1.set(Calendar.DAY_OF_MONTH, cal1.getActualMaximum(Calendar.DAY_OF_MONTH));
            return sdf.format(cal1.getTime());
        } catch (Exception ex) {

        }
        return "";
    }

    /**
     * 偏移天数
     *
     * @param date
     * @param pattern
     * @param days
     * @return
     */
    public static String getNextDateStr(final Date date, final String pattern, final int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    public static String getNextDateStr(final String dateStr, final int days) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATE);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateStr));
        } catch (ParseException e) {
            //e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_YEAR, days);
        return sdf.format(c.getTime());
    }

    /**
     * @param date
     * @param pattern
     * @param weeks
     * @return
     */
    public static String getNextWeekStr(final Date date, final String pattern, final int weeks) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.WEEK_OF_YEAR, weeks);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    /**
     * @param date
     * @param pattern
     * @param months
     * @return
     */
    public static String getNextMonthStr(final Date date, final String pattern, final int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(c.getTime());
    }

    public static String getNextMonthFirst(final String dateStr) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATE);
        try {
            c.setTime(sdf.parse(dateStr));
            c.add(Calendar.MONTH, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(c.getTime()).substring(0, 7) + "-01 00:00:00";
    }

    /**
     * 非当年 2017-09-09 09:09:09
     * 当年非当月 09-09 09:08:08
     * 当年当月 09 09:09:08
     * 当年当月当天 09:09:09
     * 当年当月当天当时 09:09
     *
     * @param date
     * @return
     */
    public static String getFmtTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEF_DATETIME);

        return sdf.format(date);
    }

    /** 获取本周一 零点的时间戳 */
    public static Date getWeekStartDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        Date date = cal.getTime();

        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        return cal.getTime();
    }

}
