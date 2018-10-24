package com.empathy.config;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: R</p>
 * <p>Description: 数据回传类
 * 涉及到要求更加安全的api接口的，需要深入制定通信协议。
 * </p>
 * <p>Company: 浙江企业在线有限公司</p>
 *
 * @author chitry@126.com
 * @date 2018年4月24日 下午5:09:19
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
