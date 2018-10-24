package com.empathy.common;

import com.empathy.ex.NotLoginException;
import com.empathy.task.EventNode;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author tybest
 * @date 2017/8/5 8:44
 * @email zhoujian699@126.com
 * @desc
 **/
public abstract class AbstractBaseController {

    protected Long getId(HttpServletRequest request) {
        final Object obj = request.getSession().getAttribute(Constants.LOGIN_KEY);
        if (obj == null) {
            throw new NotLoginException();
        }
        LoginSession session = (LoginSession) obj;
        return session.getId();
    }

    protected RspResult success() {
        RspResult result = new RspResult();
        return result;
    }

    protected RspResult error(String msg) {
        RspResult result = new RspResult();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    protected EventNode buildNode(Long userId, Long targetId, int type) {
        EventNode node = new EventNode();
        node.setUserId(userId);
        node.setTargetId(targetId);
        node.setType(type);

        return node;
    }

    protected String listToString(List<Long> ids) {
        StringBuilder sb = new StringBuilder();
        if (ids != null) {
            for (Long id : ids) {
                sb.append(id).append(",");
            }
        }
        return sb.toString();
    }

    /**
     * once
     *
     * @param source
     * @return
     */
    protected String decode(String source) {
        try {
            return URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //
        }
        return "";
    }

    /**
     * twice
     *
     * @param source
     * @return
     */
    protected String decode2(String source) {
        try {
            return URLDecoder.decode(URLDecoder.decode(source, "UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //
        }
        return "";
    }

    /**
     * 判定请求是否来自手机端
     *
     * @param request
     * @return
     */
    protected boolean isMobile(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = {"iphone", "android", "ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile"};
        if (request.getHeader("User-Agent") != null) {
            String agent = request.getHeader("User-Agent");
            for (String mobileAgent : mobileAgents) {
                if (agent.toLowerCase().contains(mobileAgent)
                        && agent.toLowerCase().indexOf("windows nt") <= 0 && agent.toLowerCase().indexOf("macintosh") <= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }
}
