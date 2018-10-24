package com.empathy.domain.enumer;

/**
 * Created by MI on 2018/4/13.
 */
public enum LoginType {

    PHONE_LOGIN("phone", "phone"),//手机号登录
    QQ_LOGIN("qq", "qq"), //qq登录
    WECHAT_LOGIN("wechat", "wechat"),;//微信登录


    LoginType(String type, String way) {
        this.type = type;
        this.way = way;
    }

    String type;
    String way;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public static String getWay(String type) {
        for (LoginType fileTypeAndPath : LoginType.values()) {
            if (fileTypeAndPath.getType().equals(type)) {
                return fileTypeAndPath.getWay();
            }
        }
        return null;
    }
}
