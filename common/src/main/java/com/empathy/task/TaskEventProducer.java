package com.empathy.task;

import com.lmax.disruptor.RingBuffer;

/**
 * @author tybest
 * @date 2017/7/31 17:15
 * @email zhoujian699@126.com
 * @desc
 **/
public class TaskEventProducer {

    private final RingBuffer<TaskEvent> ringBuffer;

    public TaskEventProducer(RingBuffer<TaskEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(EventNode node) {
        final long seq = ringBuffer.next();
        try {
            TaskEvent event = ringBuffer.get(seq);
            event.set(node);
        } finally {
            ringBuffer.publish(seq);
        }
    }
}
