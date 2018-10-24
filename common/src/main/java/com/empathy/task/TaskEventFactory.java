package com.empathy.task;

import com.lmax.disruptor.EventFactory;

/**
 * @author tybest
 * @date 2017/7/31 17:12
 * @email zhoujian699@126.com
 * @desc
 **/
public class TaskEventFactory implements EventFactory<TaskEvent> {

    @Override
    public TaskEvent newInstance() {
        return new TaskEvent();
    }
}
