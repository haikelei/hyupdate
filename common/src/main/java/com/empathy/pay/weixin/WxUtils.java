package com.empathy.pay.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.empathy.pay.PayConstant;
import com.empathy.pay.XmlUtils;
import com.empathy.utils.MD5Utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.*;

/**
 * @author tybest
 * @date 2017/8/20 13:54
 * @email zhoujian699@126.com
 * @desc
 **/
public final class WxUtils {

    private static final Logger LOG = LoggerFactory.getLogger(WxUtils.class);

    public static void main(String[] args) {
        Map<String, Object> back = new HashMap<>();
        back.put("orderId", 123);
        System.out.println(getWxInfo("购买课程", "127.0.0.1", "1", back, "20171027160000123"));
    }

    /**
     * 微信支付订单
     *
     * @param body    支付体
     * @param ip      IP地址
     * @param price   价格  分
     * @param back    原样返回的数据
     * @param tradeNo 订单号
     * @return
     */
    public static String getWxInfo(final String body, final String ip, final String price, Map<String, Object> back, String tradeNo) {
        String prepayId = null;
        SortedMap<Object, Object> parameters = new TreeMap<>();
        parameters.put("appid", PayConstant.WX_APPID);
        parameters.put("attach", JSON.toJSONString(back));
        parameters.put("mch_id", PayConstant.WX_MCH_ID);
        parameters.put("nonce_str", WxUtils.createNoncestr());
        parameters.put("body", body);
        parameters.put("out_trade_no", tradeNo); //订单id
        parameters.put("fee_type", "CNY");
        parameters.put("total_fee", price);
        parameters.put("spbill_create_ip", ip/*getIpAddr(request)*/);
        parameters.put("notify_url", PayConstant.WX_NOTIFY_URL);
        parameters.put("trade_type", PayConstant.WX_TRADE_TYPE);
        String sign = WxUtils.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        String requestXML = WxUtils.getRequestXml(parameters);
        String result = WxUtils.httpsRequest(PayConstant.WX_UNIFIED_ORDER_URL, "POST", requestXML);
        LOG.info("获取微信支付信息结果：{}", result);
        try {
            Map<String, String> map = XmlUtils.doXMLParse(result);
            if (map.containsKey("prepay_id")) {
                prepayId = map.get("prepay_id");
            } else {
                LOG.error("获取微信支付信息错误！");
            }
        } catch (JDOMException e) {
            LOG.error("解析xml文件错误！,{}", e);
        } catch (IOException e) {
            LOG.error("IO错误！,{}", e);
        }
        return prepayId;
    }

    /**
     * 退款
     *
     * @param tradeNo  订单号
     * @param refundNo 退款订单号
     * @param money    退款金额
     * @param total    订单总额
     * @return 是否退款成功
     */
    public static boolean refund(String tradeNo, String refundNo, Integer money, Integer total) {
        boolean success = true;
        SortedMap<Object, Object> parameters = new TreeMap<>();
        parameters.put("appid", PayConstant.WX_APPID);
        parameters.put("mch_id", PayConstant.WX_MCH_ID);
        parameters.put("nonce_str", createNoncestr());
        parameters.put("op_user_id", PayConstant.WX_MCH_ID); //订单id
        parameters.put("out_refund_no", refundNo);
        parameters.put("out_trade_no", tradeNo);
        parameters.put("refund_fee", money);
        parameters.put("refund_fee_type", "CNY");
        parameters.put("total_fee", total);
        String sign = createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        String requestXML = getRequestXml(parameters);
        String result = httpsRequest(PayConstant.WX_REFUND_URL, "POST", requestXML);
        try {
            Map<String, String> map = XmlUtils.doXMLParse(result);
            String code = map.get("return_code");
            if ("SUCCESS".equals(code)) {
//                WxRefundResponse wx = (WxRefundResponse)XmlUtils.toObject(WxRefundResponse.class,result);
            }
        } catch (JDOMException e) {
        } catch (IOException e) {
        }
        return success;
    }


    private static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
//	            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
//	          log.error("连接超时：{}", ce);
        } catch (Exception e) {
//	          log.error("https请求异常：{}", e);
        }
        return null;
    }

    private static JSONObject httpsRequest(String requestUrl, String requestMethod) {
        JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(3000);
            conn.setRequestMethod(requestMethod);
            //conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
        } catch (Exception e) {
            System.out.println(e);
        }
        return jsonObject;
    }

    private static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

    private static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    private static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Object, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + PayConstant.WX_KEY);
        String sign = MD5Utils.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    private static final String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 微信 校验
     *
     * @param order
     * @return
     */
    private static final String getSign(final WxOrder order) {
        StringBuilder sb = new StringBuilder();
        sb.append("appid=").append(order.getAppid()).append("&")
                .append("attach=").append(order.getAttach()).append("&")
                .append("body=").append(order.getBody()).append("&")
                .append("mch_id=").append(order.getMch_id()).append("&")
                .append("nonce_str=").append(order.getNonce_str()).append("&")
                .append("notify_url=").append(order.getNotify_url()).append("&")
                .append("out_trade_no=").append(order.getOut_trade_no()).append("&")
                .append("spbill_create_ip=").append(order.getSpbill_create_ip()).append("&")
                .append("total_fee=").append(order.getTotal_fee()).append("&")
                .append("trade_type=").append(order.getTrade_type());
        sb.append("&key=").append(PayConstant.WX_KEY);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
    }

    private static String createNoncestr(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < length; i++) {
            Random rd = new Random();
            res += chars.indexOf(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    private static String createNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<Object, Object>> es = packageParams.entrySet();
        Iterator<Map.Entry<Object, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + PayConstant.WX_KEY);
        //算出摘要
        String mysign = MD5Utils.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
        String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();
        //System.out.println(tenpaySign + "    " + mysign);
        return tenpaySign.equals(mysign);
    }
}
