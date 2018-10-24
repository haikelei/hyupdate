package com.empathy.security;

import com.empathy.ex.BizBaseException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.UUID;


/**
 * @author tyb
 * @date 2016年7月10日上午11:03:32
 * @desc 请求帮助类
 */
public final class ReqUtils {
    private static final String DEF_CHARSET = "UTF-8";

    public static String getUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 将请求体转换成字符串
     *
     * @return
     */
    public static String reqToStr(final HttpServletRequest req) {
        InputStream is = null;
        try {
            is = req.getInputStream();
            byte[] buf = new byte[1024];
            byte[] reqBufs = null;
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                reqBufs = ArrayUtils.addAll(reqBufs, ArrayUtils.subarray(buf, 0, len));
            }
            is.close();
            String receive = null;
            if (reqBufs != null) {
                receive = new String(reqBufs, DEF_CHARSET);
            }
            return receive;
        } catch (IOException e) {
            throw new BizBaseException("");
        } finally {
        }
    }

    @SuppressWarnings("unchecked")
    public static ReqHeader toReqHead(final HttpServletRequest req) {
        Enumeration<String> headers = req.getHeaderNames();
        ReqHeader head = new ReqHeader();
        if (headers != null) {
            final Method[] ms = head.getClass().getDeclaredMethods();//不包括继承的方法
            while (headers.hasMoreElements()) {
                String header = headers.nextElement();
                String value = req.getHeader(header);
                if (StringUtils.isBlank(value)) {
                    continue;
                }
                if (StringUtils.isBlank(value)) {
                    continue;
                }
                final String setName = "set" + firstUpper(header);
                BindUtils.setValue(head, setName, value, ms);
            }
        }
        return head;
    }

    public static void fillHeader(final ReqHeader header, final HttpServletRequest req) {
        Enumeration<String> headers = req.getHeaderNames();
        final Method[] ms = header.getClass().getDeclaredMethods();//不包括继承的方法
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            String value = req.getHeader(name);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if (StringUtils.isBlank(value)) {
                continue;
            }
            final String setName = "set" + firstUpper(name);
            BindUtils.setValue(header, setName, value, ms);
        }
    }


    @SuppressWarnings("unchecked")
    public static void initGetParam(final HttpServletRequest req, final Object bo, final StringBuilder sb) {
        Enumeration<String> headers = req.getParameterNames();
        if (headers != null) {
            sb.append("?");
            final Method[] ms = bo.getClass().getMethods();//包括继承的方法
            while (headers.hasMoreElements()) {
                String header = headers.nextElement();
                String value = req.getParameter(header);
                if (StringUtils.isBlank(value)) {
                    continue;
                }
                sb.append(header).append("=").append(value).append("&");
                final String setName = "set" + firstUpper(header);
                BindUtils.setValue(bo, setName, value, ms);
            }
        }
    }


    private static String firstUpper(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(final HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf("0:") != -1) {
            ip = "本地";
        }
        String base = request.getHeader("origin");//被转换成宽带地址了
        return ip + "(" + base + ")";
    }
}
