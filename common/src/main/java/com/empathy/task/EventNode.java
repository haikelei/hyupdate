package com.empathy.task;

import lombok.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tybest
 * @date 2017/7/31 17:10
 * @email zhoujian699@126.com
 * @desc 事件节点
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventNode implements Serializable {
    private int type;//类型
    private Date time = Calendar.getInstance().getTime();//发送时间
    private String content;//内容
    private Long userId;//发起人
    private Long targetId;//接收人
    private Long referId;//关联人
}