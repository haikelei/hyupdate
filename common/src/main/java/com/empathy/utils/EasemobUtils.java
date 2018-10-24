package com.empathy.utils;

import com.alibaba.fastjson.JSON;
import com.empathy.common.Constants;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tybest
 * @date 2017/8/10 15:52
 * @email zhoujian699@126.com
 * @desc easemob_app_key=1129161108115307#yog
 * easemob_client_id=YXA6fD7mEKVcEeaLOP_VYO7OMw
 * easemob_client_secret=YXA6wCV7ACivcWAHr9e57yRDQxXfOew
 * easemob_base_url=http://a1.easemob.com/1129161108115307/yog
 * easemob_token=${easemob_base_url}/token
 * easemob_users=${easemob_base_url}/users
 * easemob_chatgroups=${easemob_base_url}/chatgroups
 * easemob_chatgroup=${easemob_base_url}/chatgroup
 * easemob_messages=${easemob_base_url}/messages
 * easemob_chatmessages=${easemob_base_url}/chatmessages
 **/
public final class EasemobUtils {
    private static final Logger LOG = LoggerFactory.getLogger(EasemobUtils.class);
    private static volatile int retries = 0;
    private static final String APPKEY = "1190170731115552#xmxb";
    private static final String ORGNAME = "1190170731115552";
    private static final String APPNAME = "xmxb";
    private static final String CLIENT_ID = "YXA6ZgN28KMlEeewgFHfdQlsJg";
    private static final String CLIENT_SECRET = "YXA6umcnIK6xCLAl2mc-mBRTMvfWEH8";
    private static final String BASE_URL = "http://a1.easemob.com/1190170731115552/xmxb/";
    private static final Map<Long, String> TOKEN = new ConcurrentHashMap<>();
    private static final long TIMEOUT = 1800000;//30分钟

    public static void main(String[] args) {
        //register("test","测试");
        System.out.println(getToken());
    }

    public static EasemobNode buildNode() {
        EasemobNode node = new EasemobNode();
        node.setAppkey(APPKEY);
        node.setAccess_token(getToken());
        return node;
    }

    /**
     * @param username
     */
    public static void register(String username) {
        Map<String, Object> param = new HashMap<>();
        param.put("username", username);// 环信账号
        param.put("password", Constants.DEFAULT_PWD);// 环信密码(数字和字母组合)
        final int code = doPost(BASE_URL + "users", param);
        if (code != 200) {
            LOG.error("注册用户失败");
        }
    }

    private static int doPost(final String url, final Object object) {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpResponse response = null;
            HttpPost post = new HttpPost(url);
            if (object != null) {
                post.setEntity(new StringEntity(JSON.toJSONString(object), "UTF-8"));
            }
            post.addHeader("Content-Type", "application/json");
            response = client.execute(post);
            return response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            LOG.error("调用环信失败", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                LOG.error("关闭调用环信连接失败", e);
            }
        }
        return 500;
    }

    @Getter
    @Setter
    private static class RspDTO {
        private int code;
        private String data;
    }

    @Getter
    @Setter
    private static class TokenDTO {
        private String access_token;
        private Long expires_in;
        private String application;
    }

    private static boolean isExpire() {
        Iterator<Map.Entry<Long, String>> it = TOKEN.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry<Long, String> node = it.next();
            long time = node.getKey().longValue();
            long now = System.currentTimeMillis();
            if (now > time + TIMEOUT) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private static String getCacheToken() {
        Iterator<Map.Entry<Long, String>> it = TOKEN.entrySet().iterator();
        Map.Entry<Long, String> node = it.next();
        return node.getValue();
    }

    /**
     * 获取环信token
     *
     * @return
     */
    public static String getToken() {
        if (!isExpire()) {
            return getCacheToken();
        }
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("client_id", CLIENT_ID);
        body.put("client_secret", CLIENT_SECRET);
        final String result = HttpHelper.doPost(BASE_URL + "token", JSON.toJSONString(body), null);
        if (StringUtils.isNotBlank(result)) {
            TokenDTO token = JSON.parseObject(result, TokenDTO.class);
            TOKEN.clear();
            TOKEN.put(System.currentTimeMillis(), token.getAccess_token());
            retries = 0;
            return token.getAccess_token();
        } else {
            if (retries < 3) {
                retries++;
                return getToken();
            } else {
                retries = 0;
                LOG.error("重试3次依然未获得环信token");
            }
            LOG.error("获取token失败");
        }
        retries = 0;
        return "";
    }
}
