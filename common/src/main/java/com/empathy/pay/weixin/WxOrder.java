package com.empathy.pay.weixin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author tybest
 * @date 2017/8/20 13:50
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"appid", "attach", "body", "mch_id", "nonce_str", "notify_url", "out_trade_no", "spbill_create_ip", "total_fee", "trade_type", "sign"})
public class WxOrder {
    private String appid;
    private String attach;//附加数据
    private String body;//商品描述
    private String mch_id;//商户号
    private String nonce_str;//随机字符串 <= 32
    private String notify_url;//
    private String out_trade_no;//
    private String spbill_create_ip;
    private String total_fee;
    private String trade_type;
    private String sign;
}
