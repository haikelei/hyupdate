package com.empathy.service.impl;

import com.empathy.dao.MessageDao;
import com.empathy.domain.log.Log;
import com.empathy.handler.TaskEventHandler;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.ITaskService;
import com.empathy.task.*;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tybest
 * @date 2017/8/16 15:39
 * @email zhoujian699@126.com
 * @desc
 **/
@Service
public class TaskService extends AbstractBaseService implements ITaskService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

    private static final Map<Long, String> locations = new ConcurrentHashMap<>();

    private static final int CORES = Runtime.getRuntime().availableProcessors();

    @Autowired
    private MessageDao messageDao;

    @Value("${disruptor.open}")
    private boolean open;

    private TaskEventProducer producer = null;

    @PostConstruct
    public void start() {
        if (open) {
            LOG.info("初始化Disruptor开始");
            TaskEventFactory factory = new TaskEventFactory();
            int size = 2048;
            Disruptor<TaskEvent> disruptor = new Disruptor<>(factory, size, new NamedThreadFactory("task-thread"));
            disruptor.handleEventsWith(new TaskEventHandler(this));
            disruptor.start();
            RingBuffer<TaskEvent> ringBuffer = disruptor.getRingBuffer();
            this.producer = new TaskEventProducer(ringBuffer);
            LOG.info("初始化Disruptor完成");
        }
    }

    @Override
    public void fireEvent(EventNode node) {
        if (producer != null) {
            producer.onData(node);
        }
    }

    @Override
    public void fireLog(Long userId, Long targetId, int type, long times) {
        if (producer != null) {
            EventNode node = new EventNode();
            node.setUserId(userId);
            node.setTargetId(targetId);
            node.setType(TaskEventHandler.TaskType.log.getType());
            node.setContent(type + "/" + times);
            producer.onData(node);
        }
    }

    @Override
    public void fireLog(Long userId, String content) {
        if (producer != null) {
            EventNode node = new EventNode();
            node.setUserId(userId);
            node.setTargetId(userId);
            node.setType(TaskEventHandler.TaskType.log.getType());
            node.setContent(content);
            producer.onData(node);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void addLog(EventNode node) {
        Log log = new Log();
        log.setUserId(node.getUserId());
        log.setTargetId(node.getTargetId());
        if (node.getContent().contains("/")) {
            String[] es = node.getContent().split("/");
            log.setType(Integer.parseInt(es[0]));
            log.setTimes(Long.parseLong(es[1]));
        } else {
            log.setType(1);
            log.setContent(node.getContent());
            log.setTimes(0);
        }
        //  log.setCreated(node.getTime());
    }


    @Override
    public void saveMessage(Long fid, Long tid, Date time, String content, int type, Long reference, String ext) {

    }
}
