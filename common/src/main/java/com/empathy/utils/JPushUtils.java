package com.empathy.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author tybest
 * @date 2017/8/10 15:52
 * @email zhoujian699@126.com
 * @desc 极光推送
 **/
public final class JPushUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JPushUtils.class);

    private static final String appkey = "7ad95b637b9eb2492a080ff2";
    private static final String mastersecret = "2a6e01140501c360bdc4d562";

    private static final JPushClient client;

    static {
        client = new JPushClient(mastersecret, appkey);
    }

    public static void main(String[] args) {
        push(2, "100d8559094310a5971", "cest", null);
    }

    public static void push(final int platform, final String registrationId, final String content, final Map<String, String> extras) {
        if (1 == platform) {
            pushIOS(content, extras, registrationId);
        } else if (2 == platform) {
            pushAndroid(registrationId, "送小弟通知", content, extras);
        }
    }

    public static void broadcast(final int platform, final String alert, final Map<String, String> extras, List<String> regIds) {
        if (regIds.size() == 0) {
            return;
        }
        if (1 == platform) {
            broadcastIos(alert, extras, listToArray(regIds));
        } else if (2 == platform) {
            broadcastAndroid(alert, extras, listToArray(regIds));
        }
    }

    private static String[] listToArray(List<String> regIds) {
        int size = regIds.size();
        String[] rt = new String[size];
        int i = 0;
        for (String regId : regIds) {
            rt[i] = regId;
            i++;
        }
        return rt;
    }

    private static void broadcastAndroid(String alert, Map<String, String> extras, String... regIds) {
        try {
            client.sendAndroidNotificationWithRegistrationID("系统消息", alert, extras, regIds);
        } catch (APIConnectionException e) {
            //e.printStackTrace();
        } catch (APIRequestException e) {
            //e.printStackTrace();
        }
    }

    private static void broadcastIos(String alert, Map<String, String> extras, String... regIds) {
        try {
            client.sendIosNotificationWithRegistrationID(alert, extras, regIds);
        } catch (APIConnectionException e) {
            //e.printStackTrace();
        } catch (APIRequestException e) {
            //e.printStackTrace();
        }
    }

    /**
     * 推送android
     *
     * @param registrationId
     * @param title
     * @param content
     * @param extras
     */
    private static void pushAndroid(final String registrationId, final String title, final String content, final Map<String, String> extras) {
        try {
            PushResult result = client.sendAndroidNotificationWithRegistrationID(title, content, extras, registrationId);
            if (200 == result.getResponseCode()) {
                LOG.info("推送：{}成功", content);
            } else {
                LOG.error("推送：{} 给 {} 失败，错误码：{}", content, registrationId, result.statusCode);
            }
        } catch (Exception e) {
            LOG.error("推送：{} 给 {} 失败，原因：{}", content, registrationId, e);
        }
    }

    private static void pushIOS(final String alert, final Map<String, String> extras, final String registrationId) {
        try {
            PushResult result = client.sendIosNotificationWithRegistrationID(alert, extras, registrationId);
            if (200 == result.getResponseCode()) {
                LOG.info("推送：{}成功", alert);
            } else {
                LOG.error("推送：{} 给 {} 失败，错误码：{}", alert, registrationId, result.statusCode);
            }
        } catch (Exception e) {
            LOG.error("推送：{} 给 {} 失败，原因：{}", alert, registrationId, e);
        }
    }
}
