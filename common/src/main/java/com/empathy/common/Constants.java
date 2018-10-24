package com.empathy.common;

import lombok.Getter;

import java.util.UUID;

/**
 * @author tybest
 * @date 2017年6月15日 下午1:26:43
 * @email tybest@126.com
 * @desc
 */
public final class Constants {

    public static final Long SYS_ID = 888888L;//系统ID

    public static final String IP = "127.0.0.1";

    public static final String LOGIN_KEY = "empathy_login_key_";

    public static final String WEB_LOGIN_ = "web_login_";
    /**
     * APP登录键值开头
     */
    public static final String API_LOGIN_KEY = "api_login_";
    //特殊token用于避免登录
    public static final String IGNORE_TOKEN = "000000";
    //默认用户密码
    public static final String DEFAULT_PWD = "888888";

    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    @Getter
    public static enum LogType {
        login(1, "登录"),
        register(2, "注册"),
        retrieve_pwd(3, "找回密码");
        private int type;
        private String memo;

        LogType(int type, String memo) {
            this.type = type;
            this.memo = memo;
        }

        public static String getMemo(int type) {
            LogType[] vs = LogType.values();
            for (LogType v : vs) {
                if (v.getType() == type) {
                    return v.getMemo();
                }
            }
            return "";
        }
    }

    /**
     * 消息类型
     */
    @Getter
    public static enum MessageType {
        SYS_MSG(1, "系统消息");
        private int type;
        private String memo;

        MessageType(int type, String memo) {
            this.type = type;
            this.memo = memo;
        }

        public static String getTypeStr(int type) {
            MessageType[] vs = MessageType.values();
            for (MessageType v : vs) {
                if (v.getType() == type) {
                    return v.getMemo();
                }
            }
            return "";
        }
    }

}
