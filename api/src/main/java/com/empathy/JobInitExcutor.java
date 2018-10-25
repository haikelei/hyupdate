package com.empathy;

import com.empathy.dao.ScheduleTaskDao;
import com.empathy.domain.schedule.ScheduleJob;
import com.empathy.schedule.SendSmsTask;
import com.empathy.schedule.SpringHelper;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Date;
import java.util.List;

@Lazy(false)
public class JobInitExcutor implements ApplicationContextAware, InitializingBean {
    SchedulerFactoryBean schedulerFactoryBean = SpringHelper.getBean(SchedulerFactoryBean.class);

    @Autowired
    ScheduleTaskDao scheduleTaskDao;

    @Override
    public void afterPropertiesSet() throws Exception {

        // 这里从数据库中获取任务信息数据

        Scheduler sched = schedulerFactoryBean.getScheduler();

        List<ScheduleJob> jobList = scheduleTaskDao.getAll();
        for (ScheduleJob job : jobList) {
            if (System.currentTimeMillis()<job.getStartTime()-30*60*1000){//当前时间小于开播前半小时
                JobDetail realJob = JobBuilder.newJob(SendSmsTask.class)
                        // 根据name和默认的group(即"DEFAULT_GROUP")创建trigger的key
                        .withIdentity(String.valueOf(job.getId()), "sendSms")
                        .usingJobData("phone",job.getPhone())
                        //创建触发器
                        .build();
                SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                        .withIdentity(String.valueOf(job.getId()), "sendSms")
                        .startAt(new Date(Long.valueOf(job.getStartTime()-30*60*1000))) // some Date
                        .build();

                sched.scheduleJob(realJob, trigger);
            }
        }
        // 调度启动
        sched.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
