package com.empathy.pay.weixin;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author tybest
 * @date 2017/8/21 15:29
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"return_code", "return_msg"})
public class WxResponse {
    private String return_code = "SUCCESS";
    private String return_msg = "";
}
