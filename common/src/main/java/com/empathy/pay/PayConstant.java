package com.empathy.pay;

/**
 * @author tybest
 * @date 2017/8/20 13:24
 * @email zhoujian699@126.com
 * @desc
 **/
public interface PayConstant {

    String SUCCESS = "success";
    String FAILURE = "failure";

    /**
     * 支付渠道
     */
    enum Channel {
        alipay,
        weixin,
        ebank;
    }

    enum OrderStatus {
        wait_pay,
        pay_success,
        pay_failure,
        cancel,
        close,
        delete;
    }

    /**
     * 支付主题
     */
    enum Subject {
        buy_course,
        integral_withdraw,;

    }

    //支付宝变量
    String I_GATEWAY = "https://openapi.alipay.com/gateway.do";
    String APP_ID = "2016041801310296";
    String NOTIFY_URL = "http://looks.s3.natapp.cc/hy/alipay/notifyUrl";
    //    String NOTIFY_URL = "http://118.31.2.16/xmxb/user/order/alipay/notify";
    //应用私钥
    String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCAqwjZVesTeeUuKgywPfZRs4K6Wax0ITCS+Arr0s2TYdsuUX0ubFmh1EDKJrqJCKMcOFYPuqCrn3CSSatnti/hamPBmnGgoTtBdi6zDpNXMiL25GI/D3YZfM3rMB6mf5Awhv1ZQoED67RphiJWHMI7wrPDa1TfYNTYoz/oX22epPvfzmaYLltEPiPf6HyYhQGdsWbT5jZibUThE9tZV84Hf9S/HCnN5rmElpD6PM1XIHdseVdBTabQFbc0z1cRb+68rGKr05X2VlWPxshza0JHyHSKJ/HrR5B6iTkXL8weKrkxlVU+yXVDB2Nw4edwEZK6IlDcdNPHJ3N8c/MNtjYvAgMBAAECggEALCL/gRYg8mhg/mn8OkS3ByWt1WDHcHqmH/QoQuboEAOEXSok59TRFlsp8m5AlJs64IZOt0yMqvKhefz9aDEt0YRePd5N1g0Yt4bj1BZO4p1XMxv6u6kBGRMfy+qQyQp/GrZjl8fhW8fx9jTF+IYa9sb+RS2/SnT77mhplruNbdqr691SpgAa5vCO915dFiXqQ2JG8ek1F1MyDHuvisL5nui3npZPmpzt+/8sa3emkLP9MPKxvhJpNSUUbaWvIkSZR2TVIbZF1yUVeH79ZDHR9d6/AbHzZsfqxd+gFZY9j5FC9psrru8JlvVy5lvN9S+Mh0/Q+uPRfNv4bVVkNiqQ8QKBgQC2WYx6dcCEFsdrlIEhUSaB9Zlaw10eWaN+OyTJb85C0e6BIBuh27X5cDPX2okfrc+SMM9C/DlUj+rNDVwzmlHpDMssYu5jFUp52BUUOblhgxF2MEKvg+VaqFf2S5GYDU6ZM+M3Rq70QR9dFTb7QAX3O8R+2b0XidRQcotw2Zqc6QKBgQC0ovNyLuasXEiRX45ALq5mOaQ199K5iSHaa5xUMXREjUIWTtMAhy1Q3wXDWpLqnTRzcIalJ4Erkp7X25AZY1Q7Ows20zx0POuxeaCrdHliJOVmCixCOYXhcV9M9j+p3uHzAyYYKQ+lUmdE6UX1V99noCkMP8SQidTY7/4asnnrVwKBgFUcVOovnczJSQkYhnEDjIKqHIr1lrf8xMe9WT1AFziu/bB9SQhyIWyshzYfxVLo8HJen863JRcoGULeEoPvan3pP8eW4YeTv3BBgA0xqCowX+0XN3juihWXb38A5JLg2ye8xTfeoPhWkQoEReZdiqtL1ToT7SNYbKWj4RuqJXVJAoGBAJMECLVG/NENbrZ6mhEcrS5f/Yk7AROmS5o0eRCBC4r7mO0FDHBilHw8d7lOT0KJ3fWSgNOL+nJXK9atSXOQRy7J2E+vzoUgTAeu4RiKfHxnYRkScIEZxh/9IL5HsPr1ftpFFs0VJjm8fnY0kY6kDRyO2rmwcK/erhA+C4b4+YeTAoGAUiwofVlNCrqqIQTKAw1VN5XlJahgSnmcvOmT+ZwZQKmT3Dsc77b/047vcJqdaE+/7/xWZvfV/O0szqCmUOInICOUfcjnS6lv00KeUPov8Pkj7xjRj6YnlCLbM8/UzyVTypnMtIpSHE0g0CIq5sP4DwR3Z7Le0vm/WQ7E8SivAvQ=";
    //应用公钥
    String APP_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgKsI2VXrE3nlLioMsD32UbOCulmsdCEwkvgK69LNk2HbLlF9LmxZodRAyia6iQijHDhWD7qgq59wkkmrZ7Yv4WpjwZpxoKE7QXYusw6TVzIi9uRiPw92GXzN6zAepn+QMIb9WUKBA+u0aYYiVhzCO8Kzw2tU32DU2KM/6F9tnqT7385mmC5bRD4j3+h8mIUBnbFm0+Y2Ym1E4RPbWVfOB3/Uvxwpzea5hJaQ+jzNVyB3bHlXQU2m0BW3NM9XEW/uvKxiq9OV9lZVj8bIc2tCR8h0iifx60eQeok5Fy/MHiq5MZVVPsl1QwdjcOHncBGSuiJQ3HTTxydzfHPzDbY2LwIDAQAB";
    String SIGN_TYPE2 = "RSA2";//SHA256WithRSA （强烈推荐使用），强制要求RSA密钥的长度至少为2048
    String ALI_TRADE_APP_PAY = "alipay.trade.query";
    //支付宝公钥
    String ALI_PAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";//支付宝公钥
    String PAY_SUCCESS = "TRADE_SUCCESS";
    String TIMEOUT_EXPRESS = "30m";//30分钟 最晚付款时间为1天
    String PRODUCT_CODE = "QUICK_MSECURITY_PAY";
    String JSON = "json";
    String UTF8 = "UTF-8";


    //微信相关参数
    String WX_APPID = "wx694efa838cc4b737";
    String WX_KEY = "7b7d8069fe51e0cffa42b64e6b4ad2b7";
    String WX_NOTIFY_URL = "http://121.43.111.133:8080/xmxb/user/order/weixin/notify";
    //    String WX_NOTIFY_URL = "http://118.31.2.16/xmxb/user/order/weixin/notify";
    String WX_MCH_ID = "1462321802";//商户号
    String WX_TRADE_TYPE = "APP";
    String WX_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    String WX_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
}
