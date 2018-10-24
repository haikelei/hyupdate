package com.empathy.pay.weixin;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author tybest
 * @date 2017/8/20 13:51
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"appid", "mch_id", "nonce_str", "op_user_id", "out_refund_no", "out_trade_no", "refund_fee", "refund_fee_type", "sign", "transaction_id", "total_fee"})
public class WxRefund implements Serializable {

    private String appid;
    private String mch_id;
    private String nonce_str;
    private String op_user_id;//默认为商户号
    private String out_refund_no;//退款订单号
    private String out_trade_no;//二选一 32
    private Integer refund_fee;//分
    private String refund_fee_type = "CNY";
    private String sign;
    private String transaction_id;//二选一 28
    private Integer total_fee;//订单金额
}
