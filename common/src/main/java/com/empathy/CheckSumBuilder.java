package com.empathy;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * @ Description    网易云信抄送消息验证工具类
 * @ Author         dong
 * @ Date           2018-09-25 11:41
 */
public class CheckSumBuilder {

        // 计算并获取CheckSum
        public static String getCheckSum(String appSecret, String nonce, String curTime) {
            return encode("sha1", appSecret + nonce + curTime);
        }

        // 计算并获取md5值
        public static String getMD5(String requestBody) {
            return encode("md5", requestBody);
        }

        private static String encode(String algorithm, String value) {
            if (value == null) {
                return null;
            }
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
                messageDigest.update(value.getBytes());
                return getFormattedText(messageDigest.digest());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        private static String getFormattedText(byte[] bytes) {
            int len = bytes.length;
            StringBuilder buf = new StringBuilder(len * 2);
            for (int j = 0; j < len; j++) {
                buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
                buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
            }
            return buf.toString();
        }
        private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 获取request的请求体
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] readBody(HttpServletRequest request) throws IOException {
            if (request.getContentLength() > 0) {
                byte[] body = new byte[request.getContentLength()];
                IOUtils.readFully(request.getInputStream(), body);
                return body;
            } else
                return null;
        }

}
