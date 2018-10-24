package com.empathy.domain.schedule;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tybest
 * @date 2017/9/20 14:01
 * @email zhoujian699@126.com
 * @desc 任务定时器
 **/
@Getter
@Setter
public class ScheduleJob implements Serializable {

    public static final String RUNNING = "1";
    public static final String STOP = "0";
    public static final String CURRENT_IS = "1";
    public static final String CURRENT_NOT = "0";


    private Long jobId;
    private Date createTime;
    private Date updateTime;
    private String jobName;
    private String jobGroup;
    private String jobStatus;
    private String cronExpression;
    private String description;
    private String invokeClass;
    private String invokeMethod;
    private String concurrent;//并发性
    private String springId;//
    private String params;
    private Long userId;
}
