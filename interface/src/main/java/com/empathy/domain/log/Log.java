package com.empathy.domain.log;

import com.empathy.common.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author tybest
 * @date 2017/10/12 10:14
 * @email zhoujian699@126.com
 * @desc
 **/
@Getter
@Setter
public class Log extends BaseDomain {

    private Long userId;
    /**
     * @see com.empathy.common.Constants.LogType
     */
    private int type;
    private Long targetId;
    private long times = 0;
    private String province;
    private String city;
    private String country;
    private String content;
    private String ip;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
