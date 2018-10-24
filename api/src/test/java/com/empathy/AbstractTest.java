package com.empathy;

import com.alibaba.fastjson.JSON;
import com.empathy.utils.HttpHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tybest
 * @date 2017/8/31 9:35
 * @email zhoujian699@126.com
 * @desc
 **/
public abstract class AbstractTest {

    private static final Map<String, String> headers = new HashMap<>();

    private static final String BASE_URL = "http://localhost:8080/";

//    private static final String BASE_URL = "http://121.43.111.133:8080/xmxb/";

    static {
        headers.put("platform", "1");
        headers.put("token", "000000");//测试token
        headers.put("version", "1.0.0");
        headers.put("uid", "3518039882613760");
        headers.put("accesstime", null);
    }

    /**
     * POST请求
     *
     * @param url
     * @param json
     */
    protected void doPost(String url, Object json) {
        System.out.println(HttpHelper.doPost(BASE_URL + url, JSON.toJSONString(json), headers));
    }

    /**
     * GET请求
     *
     * @param url
     */
    protected void doGet(String url) {
        HttpHelper.doGet(BASE_URL + url, headers);
//        System.out.println(HttpHelper.doGet(BASE_URL+url, headers));
    }
}
