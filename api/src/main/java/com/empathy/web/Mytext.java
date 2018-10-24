package com.empathy.web;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by MI on 2018/5/12.
 */
public class Mytext {



    public static void main(String[] args) {





    }


    public static BigDecimal getCibFee(BigDecimal money) {
        //周建的代码
//        BigDecimal bd = new BigDecimal("0.6");
//        bd = bd.multiply(money).setScale(2,BigDecimal.ROUND_HALF_UP);
//        final BigDecimal min = new BigDecimal(6);
//        final BigDecimal max = new BigDecimal(600);
//        if(bd.compareTo(min) <0){
//            bd = min;
//        }
//        if(bd.compareTo(max) > 0){
//            bd = max;
//        }
//        return bd;
        BigDecimal bd = new BigDecimal("10");
        if (money.doubleValue() <= 10) {

        } else if (money.doubleValue() > 10 && money.doubleValue() <= 50) {
            bd = new BigDecimal("15");

        } else if (money.doubleValue() > 50 && money.doubleValue() <= 100) {
            bd = new BigDecimal("20");
        } else {
            bd = new BigDecimal("0.2");
            bd = bd.multiply(money).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        return bd;
    }
}
