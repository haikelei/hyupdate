package com.empathy.task;

/**
 * @author tybest
 * @date 2017/7/31 17:09
 * @email zhoujian699@126.com
 * @desc
 **/
public class DisruptorServer {

    private TaskEventProducer producer;

    public void start() throws Exception {
//        Executor executor = Executors.newCachedThreadPool();
//        TaskEventFactory factory = new TaskEventFactory();
//        int size = 1024;
//        Disruptor<TaskEvent> disruptor = new Disruptor<>(factory,size,executor);
//        disruptor.handleEventsWith(new TaskEventHandler());
//        disruptor.start();
//
//        //client
//        RingBuffer<TaskEvent> ringBuffer = disruptor.getRingBuffer();
//        TaskEventProducer producer = new TaskEventProducer(ringBuffer);
//        this.producer = producer;
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        for(long i=0;i<100;i++){
//            bb.putLong(0,i);
//            producer.onData(bb);
//            Thread.sleep(1000);
//        }
    }

    public void add(EventNode node) {
        if (null != producer) {
            producer.onData(node);
        }
    }
}
