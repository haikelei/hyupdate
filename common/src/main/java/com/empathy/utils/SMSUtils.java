package com.empathy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.empathy.cache.CacheUtils;
import com.empathy.pay.Base64;
import com.empathy.pay.weixin.MyX509TrustManager;
import lombok.Getter;
import lombok.Setter;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author tybest
 * @date 2017/9/27 11:29
 * @email zhoujian699@126.com
 * @desc 短信平台
 **/
public final class SMSUtils {


    //http://www.yuntongxun.com/ 容联
    //private static final String SID = "8aaf07085d7cf73f015d97e439fe0886";
    private static final String SID = "8aaf07086077a6e60160960996400ec8";
    //private static final String TOKEN = "980b64c24fd04efa8413f4a05749734e";
    private static final String TOKEN = "0338a71e097f48f59a57ae23385ccbde";
    //private static final String BASE_URL = "https://app.cloopen.com:8883";
    private static final String BASE_URL = "https://app.cloopen.com:8883";
    //private static final String APPID = "8aaf07085d7cf73f015d97e43b9c088d";
    private static final String APPID = "8aaf07086077a6e60160960996a70ecf";
    private static final String PATH = "/2013-12-26/Accounts/" + SID + "/SMS/TemplateSMS?sig=";
    private static final String REGISTER_TPL_ID = "208740";
    private static final String RETRIEVE_PWD_TPL_ID = "208741";
    private static final String UPDATE_PWD_TPL_ID = "208742";
    private static final String SECURE_PWD_TPL_ID = "209662";
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();
    private static final String PREFIX = "sms_";
    private static final String SECURE_PREFIX = "sms_secure_";
    private static final String BINDING_PREFIX = "sms_binding_";
    private static final String RETRIEVE_PREFIX = "sms_retrieve_";
    private static final String FORGET_PREFIX = "sms_forget_";
    private static final String CHANGE_BINDING_PREFIX = "sms_change_binding_";
    private static final String LOGIN_PREFIX = "sms_login_";
    private static final String GET_GOODS_PREFIX = "sms_get_goods";
    private static final String SERVICE_GOODS_PREFIX = "sms_service_goods";
    private static final String CHANGE_DEAL_PASSWORD_PREFIX = "sms_change_password";
    private static final String UPD_DEAL_PASSWORD_PREFIX = "sms_upd_password";
    private static final String BE_PROVE_PREFIX = "sms_prove";

    public static void main(String[] args) {//默认单位分钟
        sendYzm("13116705162", "123456", "3", REGISTER_TPL_ID);
    }

    private static String getSig() {
        return MD5Utils.md5(SID + TOKEN + DateUtils.getDateStr(new Date(), DateUtils.DEF_TIME_MILLI)).toUpperCase();
    }

    private static String getAuthorization() {
        return Base64.encode((SID + ":" + DateUtils.getDateStr(new Date(), DateUtils.DEF_TIME_MILLI)).getBytes());
    }

    public static void beforeChangeBinding(String phone) {
        //String code = String.valueOf(random.nextLong(100000,999999));
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(CHANGE_BINDING_PREFIX + phone, code, 180000);
    }

    public static void beforeLogin(String phone) {
        //String code = String.valueOf(random.nextLong(100000,999999));
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(LOGIN_PREFIX + phone, code, 180000);
    }

    public static void beforeGetGoods(String phone) {
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(GET_GOODS_PREFIX + phone, code, 180000);
    }

    public static void beforeServiceGoods(String phone) {
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(SERVICE_GOODS_PREFIX + phone, code, 180000);
    }

    public static void changePassword(String phone) {
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(CHANGE_DEAL_PASSWORD_PREFIX + phone, code, 180000);
    }

    public static void changeDealPassword(String phone) {
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(UPD_DEAL_PASSWORD_PREFIX + phone, code, 180000);
    }

    public static void beProve(String phone) {
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(BE_PROVE_PREFIX + phone, code, 180000);
    }


    @Getter
    @Setter
    static class Node {
        private String to;
        private String appId = APPID;
        private String templateId;
        private List<String> datas = new ArrayList<>();
        //private String data;
    }

    //注册
    public static boolean afterRegister(String phone, String code) {
        Object original = CacheUtils.get(PREFIX + phone);
        if (original == null) {
            return false;
        }
        if (code.equals(String.valueOf(original))) {
            return true;
        }
        return false;
    }

    public static void beforeRegister(String phone) {
        //String code = String.valueOf(random.nextLong(100000,999999));
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(PREFIX + phone, code, 180000);
    }

    public static void beforeForget(String phone) {
        //String code = String.valueOf(random.nextLong(100000,999999));
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(FORGET_PREFIX + phone, code, 180000);
    }

    public static void beforeBinding(String phone) {
        //String code = String.valueOf(random.nextLong(100000,999999));
        String code = "1234";
        sendYzm(phone, code, "5", REGISTER_TPL_ID);
        CacheUtils.put(BINDING_PREFIX + phone, code, 180000);
    }

    //设置安全密码
    public static boolean afterSetSecure(String phone, String code) {
        Object original = CacheUtils.get(SECURE_PREFIX + phone);
        if (original == null) {
            return false;
        }
        if (code.equals(String.valueOf(original))) {
            return true;
        }
        return false;
    }

    public static void beforeSetSecure(String phone) {
        //String code = String.valueOf(random.nextLong(100000,999999));
        String code = "1234";
        sendYzm(phone, code, "5", SECURE_PWD_TPL_ID);
        CacheUtils.put(SECURE_PREFIX + phone, code, 180000);
    }

    //找回密码
    public static boolean afterRetrieve(String phone, String code) {
        Object original = CacheUtils.get(RETRIEVE_PREFIX + phone);
        if (original == null) {
            return false;
        }
        if (code.equals(String.valueOf(original))) {
            return true;
        }
        return false;
    }

    public static void beforeRetrieve(String phone) {
        //String code = String.valueOf(random.nextLong(100000,999999));
        String code = "1234";
        sendYzm(phone, code, "5", RETRIEVE_PWD_TPL_ID);
        CacheUtils.put(RETRIEVE_PREFIX + phone, code, 180000);
    }

    /**
     * 短信验证码
     *
     * @param phone 电话
     * @param code  验证码
     * @param time  有效时间
     * @param id    短信模板ID
     */
    public static void sendYzm(String phone, String code, String time, String id) {
        Node node = new Node();
        node.setTo(phone);
        List<String> datas = new ArrayList<>();
        datas.add(code);
        datas.add(time);
        node.setDatas(datas);
        node.setTemplateId(id);
        httpsRequest(JSON.toJSONString(node), "POST");
    }

    private static JSONObject httpsRequest(String body, String requestMethod) {
        JSONObject jsonObject = null;
        try {
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            URL url = new URL(BASE_URL + PATH + getSig());
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Content-Length", "256");
            conn.setRequestProperty("Authorization", getAuthorization());
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(3000);
            conn.setRequestMethod(requestMethod);
            conn.getOutputStream().write(body.getBytes());
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
            System.out.println(jsonObject);
        } catch (Exception e) {
            //
        }
        return jsonObject;
    }
}
