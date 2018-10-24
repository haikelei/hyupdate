package com.empathy.pay.alipay;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/20 13:32
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class RefundContent {

    private String out_trade_no;
    private String trade_no;
    private String out_request_no;//本次退款请求流水号，部分退款时必传
    private String refund_amount;
}
