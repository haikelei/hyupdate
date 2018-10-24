/**
 *
 */
package com.empathy.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.Map;

public final class HttpHelper {

    private HttpHelper() {
    }

    public static final String doPost(String url, String body, Map<String, String> headers) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost req = new HttpPost(url);
        try {
            if (headers != null) {
                for (String key : headers.keySet()) {
                    req.setHeader(key, headers.get(key));
                }
            }
            req.setHeader("Content-Type", "application/json");
            req.setHeader("Accept", "application/json");
            if (StringUtils.isNotBlank(body)) {
                req.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
            }
            CloseableHttpResponse resp = client.execute(req);
            HttpEntity result = resp.getEntity();
            final String rt = StringUtils.toEncodedString(EntityUtils.toByteArray(result), Charset.defaultCharset());
            EntityUtils.consume(result);
            return rt;
        } catch (Exception e) {

        }
        return null;
    }

    public static final String doGet(String url, Map<String, String> headers) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet req = new HttpGet(url);
        String result = null;
        try {
            if (headers != null) {
                for (String key : headers.keySet()) {
                    req.setHeader(key, headers.get(key));
                }
            }
            req.setHeader("Content-Type", "application/json");
            req.setHeader("Accept", "application/json");
            CloseableHttpResponse res = client.execute(req);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(res.getEntity());
            }
            EntityUtils.consume(res.getEntity());
        } catch (Exception e) {
        }
        return result;
    }

    public static final String doGet(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet req = new HttpGet(url);
        String result = null;
        try {
            req.setHeader("Content-Type", "application/json");
            req.setHeader("Accept", "application/json");
            CloseableHttpResponse res = client.execute(req);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(res.getEntity());
            }
            EntityUtils.consume(res.getEntity());
        } catch (Exception e) {

        }
        return result;
    }
}
