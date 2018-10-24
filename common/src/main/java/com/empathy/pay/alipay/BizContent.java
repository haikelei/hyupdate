package com.empathy.pay.alipay;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tybest
 * @date 2017/8/20 13:29
 * @email zhoujian699@126.com
 * @desc 支付宝支付体
 **/
@Getter
@Setter
public class BizContent {
    //	private String body;// N 128 交易的具体描述 多种累加 IPhone 6S
    private String subject;// Y 256 商品的标题/交易标题/订单标题/订单关键字等。大乐透
    private String out_trade_no;//Y 64 商户网站唯一订单号
    private String timeout_express;//N 6  该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
    private String total_amount;//Y 9 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
    //	private String seller_id;//N 16 收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
    private String product_code;//Y 64 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
    //	private String goods_type;//N 2 商品主类型：0—虚拟类商品，1—实物类商品 	注：虚拟类商品不支持使用花呗渠道 默认0
    private String passback_params;//N 512 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝 merchantBizType%3d3C%26merchantBizNo%3d2016010101111
//	private String promo_params;//N 512 优惠参数	注：仅与支付宝协商后可用  {"storeIdType":"1"}
//	private String extend_params;//N 业务扩展参数，详见下面的  {"sys_service_provider_id":"2088511833207846"}
//	private String enable_pay_channels;//N 128 可用渠道，用户只能在指定渠道范围内支付	当有多个渠道时用“,”分隔	注：与disable_pay_channels互斥 pcredit,moneyFund,debitCardExpress
//	private String disable_pay_channels;//N 128 禁用渠道，用户不可用指定渠道支付	当有多个渠道时用“,”分隔	注：与enable_pay_channels互斥 pcredit,moneyFund,debitCardExpress

}
