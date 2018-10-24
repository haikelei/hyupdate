package com.empathy.domain.enumer;

/**
 * Created by MI on 2018/4/23.
 */
public enum CommentType {

    PHONE_LOGIN("recording", "recording"),//录音评论
    WECHAT_LOGIN("dynamic", "dynamic"),;//动态评论


    CommentType(String type, String way) {
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
        for (CommentType commentType : CommentType.values()) {
            if (commentType.getWay().equals(type)) {
                return commentType.getWay();
            }
        }
        return null;
    }
}
