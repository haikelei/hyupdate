package com.empathy;

import org.junit.Test;

/**
 * @author tybest
 * @date 2017/10/10 17:42
 * @email zhoujian699@126.com
 * @desc
 **/
public class LoginTest extends AbstractTest {

    @Test
    public void captcha() {
        doGet("get/captcha?phone=13601685139");
    }

//    @Test
//    public void register(){
//        RegisterBo bo = new RegisterBo();
//        bo.setPlatform(1);
//        bo.setAckPwd("123456");
//        bo.setPassword("123456");
//        bo.setPhone("13601685139");
//        bo.setNickname("tybest");
//        bo.setCaptcha("178493");
//        doPost("register",bo);
//    }
}
