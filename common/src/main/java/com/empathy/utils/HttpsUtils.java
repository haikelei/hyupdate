package com.empathy.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author tybest
 * @date 2017/12/7 10:50
 * @email zhoujian699@126.com
 * @desc TSL的前身为SSL
 * <p>
 * Nginx：
 * Syntax: ssl_protocols [SSLv2] [SSLv3] [TLSv1] [TLSv1.1] [TLSv1.2];
 * Default: ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
 * Context: http, server
 * Enables the specified protocols. The TLSv1.1 and TLSv1.2 parameters work only when the OpenSSL library of version 1.0.1 or higher is used.
 * IE 6 默认只支持 SSLv2 和 SSLv3
 **/
public final class HttpsUtils {

    private static final Logger LOG = LoggerFactory.getLogger(HttpsUtils.class);

    private static SSLConnectionSocketFactory getSslsf() {
        SSLContextBuilder builder = new SSLContextBuilder();
        SSLContext sslcontext = null;
        SSLConnectionSocketFactory sslsf = null;
        try {
            sslcontext = builder.loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            LOG.error("Build SSLConnectionSocketFactory Failed, The reason: ", e);
        }
        return sslsf;
    }

    /**
     * GET请求
     *
     * @param url
     * @param headers
     * @return
     * @throws Exception
     */
    public static String doGet(final String url, final Map<String, String> headers) {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(getSslsf())
                .build();
        String result = null;
        HttpGet get = new HttpGet(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                get.setHeader(key, headers.get(key));
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(get);
            HttpEntity entity = response.getEntity();
            final int code = response.getStatusLine().getStatusCode();
            if (200 == code) {
                result = EntityUtils.toString(entity, Charset.defaultCharset());
            } else {
                result = "ERROR_GET: code=" + code + ";reason=" + response.getStatusLine().getReasonPhrase();
            }
            EntityUtils.consume(entity);
        } catch (Exception ex) {
            LOG.error("Do GET request Failed, The reason: ", ex);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {

            }
        }
        return result;
    }

    /**
     * POST请求,JSON消息体
     *
     * @param url
     * @param body
     * @param headers
     * @throws Exception
     */
    public static String doPost(final String url, final String body, final Map<String, String> headers) {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(getSslsf())
                .build();
        String result = null;
        HttpPost post = new HttpPost(url);
        if (headers != null) {
            for (String key : headers.keySet()) {
                post.setHeader(key, headers.get(key));
            }
        }
        if (StringUtils.isNotBlank(body)) {
            post.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
        }
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(post);
            HttpEntity entity = response.getEntity();
            final int code = response.getStatusLine().getStatusCode();
            if (200 == code) {
                result = EntityUtils.toString(entity, Charset.defaultCharset());
            } else {
                result = "ERROR_POST: code=" + code + ";reason=" + response.getStatusLine().getReasonPhrase();
            }
            EntityUtils.consume(entity);
        } catch (Exception ex) {
            LOG.error("Do POST request Failed, The reason: ", ex);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {

            }
        }
        return result;
    }
}
