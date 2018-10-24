package com.empathy.service;

import com.empathy.task.EventNode;

import java.util.Date;

/**
 * @author tybest
 * @date 2017/8/16 15:36
 * @email zhoujian699@126.com
 * @desc
 **/
public interface ITaskService {

    void fireEvent(EventNode node);

    void fireLog(Long userId, Long targetId, int type, long times);

    void fireLog(Long userId, String content);

    void addLog(EventNode node);

    void saveMessage(Long fid, Long tid, Date time, String content, int type, Long reference, String ext);
}
