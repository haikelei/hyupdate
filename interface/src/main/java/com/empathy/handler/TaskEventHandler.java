package com.empathy.handler;


import com.empathy.service.ITaskService;
import com.empathy.task.EventNode;
import com.empathy.task.TaskEvent;
import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tybest
 * @date 2017/7/31 17:13
 * @email zhoujian699@126.com
 * @desc
 **/
public class TaskEventHandler implements EventHandler<TaskEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(TaskEventHandler.class);

    private final ITaskService taskService;

    public TaskEventHandler(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void onEvent(TaskEvent taskEvent, long l, boolean b) throws Exception {
        final EventNode node = taskEvent.get();
        if (null != node) {
            final int type = node.getType();
            switch (type) {
                case 12://添加日志
                    taskService.addLog(node);
                    break;
                default:
                    break;
            }
        }
    }

    public static enum TaskType {
        log(12, "日志");
        private final int type;
        private final String desc;

        TaskType(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public int getType() {
            return this.type;
        }

        public String getDesc() {
            return this.desc;
        }

        /**
         * 根据积分
         *
         * @param type
         * @return
         */
        public static String getMemo(int type) {
            TaskType[] vs = TaskType.values();
            for (TaskType v : vs) {
                if (v.getType() == type) {
                    return v.getDesc();
                }
            }
            return "";
        }
    }
}
