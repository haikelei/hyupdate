package com.empathy.utils.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 发送短信基础类
 *
 */
public class SmsBase {
    private static Integer x_eid = 10653;
    private static String x_uid = "jindongfanguser";
    private static String x_pwd_md5 = "a838518abb114ae194e670c797d024cf";
    private static Integer x_gate_id = 300;

    public static String SendSms(String mobile, String content) throws UnsupportedEncodingException {
        Integer x_ac = 10;//发送信息
        HttpURLConnection httpconn = null;
        String result = "-20";
        String memo = content.trim();
        StringBuilder sb = new StringBuilder();
        sb.append("http://gateway.woxp.cn:6630/utf8/web_api/?x_eid=");
        sb.append(x_eid);
        sb.append("&x_uid=").append(x_uid);
        sb.append("&x_pwd_md5=").append(x_pwd_md5);
        sb.append("&x_ac=").append(x_ac);
        sb.append("&x_gate_id=").append(x_gate_id);
        sb.append("&x_target_no=").append(mobile);
        sb.append("&x_memo=").append(URLEncoder.encode(memo, "utf-8"));
        try {
            URL url = new URL(sb.toString());
            httpconn = (HttpURLConnection) url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));
            result = rd.readLine();
            rd.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpconn != null) {
                httpconn.disconnect();
                httpconn = null;
            }
        }
        return result;
    }
}