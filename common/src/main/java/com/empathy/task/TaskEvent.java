package com.empathy.task;

import java.io.Serializable;

/**
 * @author tybest
 * @date 2017/7/31 17:11
 * @email zhoujian699@126.com
 * @desc
 **/
public class TaskEvent implements Serializable {

    private EventNode node;

    public void set(EventNode node) {
        this.node = node;
    }

    public EventNode get() {
        return this.node;
    }

}
