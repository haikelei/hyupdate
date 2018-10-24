package com.empathy.pay.alipay;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tybest
 * @date 2017/9/8 17:53
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class AliRspBean implements Serializable {
    private String notify_time;//Y  yyyy-MM-dd HH:mm:ss
    private String notify_type;//Y 64 通知的类型 	trade_status_sync
    private String notify_id;//Y 128 通知校验ID	ac05099524730693a8b330c5ecf72da9786
    private String charset;//Y 10 编码格式，如utf-8、gbk、gb2312等	utf-8
    private String version;//Y
    private String sign_type;//Y 10 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2	RSA2
    private String sign;//Y
    private String trade_no; //支付宝交易凭证号	2013112011001004330000121536
    private String out_trade_no;//原支付请求的商户订单号	6823789339978248
    private String out_biz_no;//商户业务ID，主要是退款通知中返回退款申请的流水号	HZRF001
    private String buyer_id;//买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字	2088102122524333
    private String buyer_logon_id;//买家支付宝账号	15901825620
    private String seller_id;//卖家支付宝用户号	2088101106499364
    private String seller_email;//卖家支付宝账号	zhuzhanghu@alitest.com
    private String trade_status;//交易目前所处的状态，见交易状态说明	TRADE_CLOSED
    private String total_amount;//9,2 本次交易支付的订单金额，单位为人民币（元）	20
    private String receipt_amount;//9,2 商家在交易中实际收到的款项，单位为元	15
    private String invoice_amount;//开票金额 用户在交易中支付的可开发票的金额	10.00
    private String buyer_pay_amount;//用户在交易中支付的金额	13.88
    private String point_amount;//使用集分宝支付的金额	12.00
    private String refund_fee;//退款通知中，返回总退款金额，单位为元，支持两位小数	2.58
    private String subject;//商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来	当面付交易
    private String body;//该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来	当面付交易内容
    private String gmt_create;//该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss	2015-04-27 15:45:57
    private String gmt_payment;//该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss	2015-04-27 15:45:57
    private String gmt_refund;//该笔交易的退款时间。格式为yyyy-MM-dd HH:mm:ss.S	2015-04-28 15:45:57.320
    private String gmt_close;//该笔交易结束时间。格式为yyyy-MM-dd HH:mm:ss	2015-04-29 15:45:57
    private String fund_bill_list;//支付成功的各个渠道金额信息，详见资金明细信息说明	[{“amount”:“15.00”,“fundChannel”:“ALIPAYACCOUNT”}]
    private String passback_params;//公共回传参数，如果请求时传递了该参数，则返回给商户时会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝 	merchantBizType%3d3C%26merchantBizNo%3d2016010101111
    private String voucher_detail_list;//本交易支付时所使用的所有优惠券信息，详见优惠券信息说明	[{“amount”:“0.20”,“merchantContribute”:“0.00”,“name”:“一键创建券模板的券名称”,“otherContribute”:“0.20”,“type”:“ALIPAY_DISCOUNT_VOUCHER”,“memo”:“学生卡8折优惠”]
    private Integer orderId;
}
