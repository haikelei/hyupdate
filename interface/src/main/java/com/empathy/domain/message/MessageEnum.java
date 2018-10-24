package com.empathy.domain.message;

import lombok.Getter;

/**
 * @author tybest
 * @date 2017/11/3 14:28
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
public enum MessageEnum {
    arrive(1, "上车提醒"),
    leave(2, "下车提醒"),
    order(3, "订单相关通知"),
    order_refund(4, "车票退款相关通知"),
    route_on(5, "线路开通提醒"),
    expense_deadline(6, "消费期限提醒"),
    coupon(7, "优惠券");

    private int type;
    private String memo;

    MessageEnum(int type, String memo) {
        this.type = type;
        this.memo = memo;
    }
}
