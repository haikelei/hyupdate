package com.empathy.task;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

/**
 * @author tybest
 * @date 2017/10/12 15:44
 * @email zhoujian699@126.com
 * @desc 用户在线时间
 **/
@Getter
@Setter
public class UserOnlineTime {

    private Long userId;
    private Date ontime = Calendar.getInstance().getTime();

    private Date offtime = Calendar.getInstance().getTime();

    /**
     * 获得时间差
     *
     * @return
     */
    public long getInterval() {
        if (ontime != null && offtime != null) {
            return (this.offtime.getTime() - this.ontime.getTime()) / 1000;
        }
        return 0;
    }
}
