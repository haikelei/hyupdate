package com.empathy.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tybest
 * @date 2017/8/10 15:29
 * @email zhoujian699@126.com
 * @desc 七牛上传下载
 **/
public final class QiniuUtils {

    private final static Auth auth;
    private static final Configuration CONFIG = new Configuration();
    private static final String accessKey = "FOKKcanAxm2iqZZ91_54edSkSxtRIwxHx1gwU76e";
    private static final String secretKey = "rdnUG20npfXITqG0lQqN7Wm-PhdxCUZHN6L9x40I";
    private static final String domain = "http://owx0gugga.bkt.clouddn.com/";
    private static final String bucket = "xmxb";

    static {
        auth = Auth.create(accessKey, secretKey);
    }

    public static void main(String[] args) {
        System.out.println(getToken(null));
        //yslgUcynDAeoLyUVLmAhUMqF1fw3W2YBrQZv1mTp:94ZWZU8mRRzmeubWQSCBENn38kE=:eyJzY29wZSI6InlvdXhpIiwiZGVhZGxpbmUiOjE1MDIzNTcxMzJ9
    }

    /**
     * 获取上传token
     *
     * @param key
     * @return
     */
    public static String getToken(final String key) {
        if (StringUtils.isBlank(key)) {
            return auth.uploadToken(bucket);
        }
        return auth.uploadToken(bucket, key);
    }


    /**
     * 七牛前缀
     *
     * @return
     */
    public static String getDomain() {
        return domain;
    }

    /**
     * 通过地址删除
     *
     * @param url
     * @return
     */
    public static void delByUrl(final String url) {
        final int index = url.lastIndexOf("/");
        if (StringUtils.isNotBlank(url) && index > -1) {
            final String key = url.substring(url.lastIndexOf("/"));
            delete(key);
        }
    }

    /**
     * 删除文件
     *
     * @param key
     */
    public static void delete(final String key) {
        BucketManager bm = new BucketManager(auth, CONFIG);
        try {
            bm.delete(bucket, key);
        } catch (QiniuException e) {
            //e.printStackTrace();
        }
    }
}
