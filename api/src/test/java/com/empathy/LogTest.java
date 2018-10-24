package com.empathy;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author tybest
 * @date 2017/11/14 9:43
 * @email zhoujian699@126.com
 * @desc
 **/
public class LogTest extends AbstractTest {

    private volatile int num = 0;

    @Test
    public void log() {

        while (num < 1000000) {
            doGet("course/more");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num);
            num++;
        }

    }
}
