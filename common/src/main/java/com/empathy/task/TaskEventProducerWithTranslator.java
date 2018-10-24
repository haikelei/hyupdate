package com.empathy.task;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @author tybest
 * @date 2017/7/31 17:19
 * @email zhoujian699@126.com
 * @desc
 **/
public class TaskEventProducerWithTranslator {

    private final RingBuffer<TaskEvent> ringBuffer;

    private static final EventTranslatorOneArg<TaskEvent, EventNode> TRANSLATOR = new EventTranslatorOneArg<TaskEvent, EventNode>() {
        @Override
        public void translateTo(TaskEvent taskEvent, long l, EventNode node) {
            taskEvent.set(node);
        }
    };

    public TaskEventProducerWithTranslator(RingBuffer<TaskEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }


    public void onData(EventNode node) {
        ringBuffer.publishEvent(TRANSLATOR, node);
    }
}
