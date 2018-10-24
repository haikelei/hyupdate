package com.empathy.pay.alipay;

import com.alibaba.fastjson.JSON;
import com.empathy.pay.PayConstant;
import com.empathy.pay.PayUtils;
import com.empathy.pay.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author tybest
 * @date 2017/8/20 13:36
 * @email zhoujian699@126.com
 * @desc 支付宝帮助类
 **/
public final class AlipayUtils {

    /**
     * 创建订单支付信息
     *
     * @param subject     支付主题
     * @param totalAmount 总金额 二位小数
     * @param back        原样返回的数据
     * @return
     */
    public static String buildPayInfo(final String subject, final String totalAmount, Map<String, Object> back, final String tradeNo) {
        boolean rsa2 = true;
        Map<String, String> params = buildOrderParamMap(PayConstant.APP_ID, rsa2, JSON.toJSONString(buildBizContent(subject, totalAmount, tradeNo, back)));
        String orderParam = buildOrderParam(params);
        String privateKey = PayConstant.APP_PRIVATE_KEY;
        String sign = getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        return orderInfo.replaceAll("\\+", "%20");
    }

    /**
     * @param app_id
     * @param rsa2
     * @param bizContent
     * @return
     */
    private static Map<String, String> buildOrderParamMap(String app_id, boolean rsa2, String bizContent) {
        final Map<String, String> keyValues = new LinkedHashMap<>();
        keyValues.put("app_id", app_id);
        keyValues.put("biz_content", bizContent);
        keyValues.put("charset", "utf-8");
        keyValues.put("format", "json");
        keyValues.put("method", "alipay.trade.app.pay");
        keyValues.put("notify_url", PayConstant.NOTIFY_URL);
        keyValues.put("sign_type", "RSA2");
        keyValues.put("timestamp", PayUtils.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        keyValues.put("version", "1.0");
        return keyValues;
    }

    /**
     * 第一步、生成交易订单内容
     *
     * @param subject     256 商品的标题/交易标题/订单标题/订单关键字等。大乐透
     * @param totalAmount
     * @return
     */
    private static BizContent buildBizContent(String subject, String totalAmount, String tradeNo, Map<String, Object> back) {
        BizContent content = new BizContent();
        content.setSubject(subject);
        content.setOut_trade_no(tradeNo);
        content.setTimeout_express(PayConstant.TIMEOUT_EXPRESS);
        content.setTotal_amount(totalAmount);// 订单金额
        content.setProduct_code(PayConstant.PRODUCT_CODE);
        content.setPassback_params(JSON.toJSONString(back));//原路返回，用来更新订单信息
        return content;
    }

    private static String buildOrderParam(final Map<String, String> map) {
        List<String> keys = new ArrayList<>(map.keySet());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }
        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }

    private static String buildKeyValue(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }

    private static String getSign(Map<String, String> map, String rsaKey, boolean rsa2) {
        List<String> keys = new ArrayList<>(map.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(buildKeyValue(key, value, false));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, false));

        String oriSign = SignUtils.sign(authInfo.toString(), rsaKey, rsa2);
        String encodedSign = "";

        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "sign=" + encodedSign;
    }
}
