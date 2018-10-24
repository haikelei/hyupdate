package com.empathy.pay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tybest
 * @date 2017/8/20 13:45
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class Passback implements Serializable {

    private Long orderId;
}
