package com.empathy.domain.file;


import com.empathy.domain.album.Album;
import com.empathy.domain.article.Article;
import com.empathy.domain.banner.BaseBanner;
import com.empathy.domain.baseGift.BaseGift;
import com.empathy.domain.baseRecording.BaseRecording;
import com.empathy.domain.bidding.File;
import com.empathy.domain.classify.BaseClassify;
import com.empathy.domain.classify.BaseMainClassify;
import com.empathy.domain.live.BaseLive;

/**
 * Created by MI on 2017/12/21.
 */
public enum FilePurpose {

    USERINFO("user", File.class),//用户头像
    ARTICLE("article", Article.class),//文章图片
    LIVE("live", BaseLive.class),//主播间图片
    AlBUM("album", Album.class),//专辑图片
    BANNER("banner", BaseBanner.class),//banner图片
    GIFT("gift", BaseGift.class),//礼物图片
    BASELIVE("idCard",BaseLive.class),//认证主播图片
    MAINCLASSIFY("mainClassify", BaseMainClassify.class),//主分类图片
    RECORDING("recording", BaseRecording.class),//录音图片
    CLASSIFY("classify",BaseClassify.class),//分类图片
    APP("app",String.class);//APP

    FilePurpose(String code, Class belongToClass) {
        this.code = code;
        this.belongToClass = belongToClass;
    }

    String code;
    Class belongToClass;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Class getBelongToClass() {
        return belongToClass;
    }

    public void setBelongToClass(Class belongToClass) {
        this.belongToClass = belongToClass;
    }


    public static FilePurpose getByCode(String code) {
        for (FilePurpose filePurpose : FilePurpose.values()) {
            if (filePurpose.getCode().equals(code)) {
                return filePurpose;
            }
        }
        return null;
    }
}
