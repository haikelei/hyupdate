package com.empathy.schedule;

import com.empathy.domain.schedule.ScheduleJob;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author tybest
 * @date 2017/9/20 14:34
 * @email zhoujian699@126.com
 * @desc 调度任务工具
 **/
public final class ScheduleUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleUtils.class);
    private static final String SCHEDULE_JOB = "scheduleJob";

    public static void invokeMethod(ScheduleJob job) {
        Object obj = null;
        Class clazz = null;
        if (StringUtils.isNotBlank(job.getSpringId())) {
            obj = SpringHelper.getBean(job.getSpringId());
        } else if (StringUtils.isNotBlank(job.getInvokeClass())) {
            try {
                clazz = Class.forName(job.getInvokeClass());
                obj = clazz.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (null == obj) {
            LOG.error("任务名 = [" + job.getJobName() + "]--------未启动成功，请检查配置是否正确！！！！");
            return;
        }
        clazz = obj.getClass();
        Method method = null;
        try {
            method = clazz.getMethod(job.getInvokeMethod(), Long.class);
        } catch (NoSuchMethodException e) {
            LOG.error("任务名称 = [" + job.getJobName() + "]-------未启动成功，方法名配置错误！！！！");
            e.printStackTrace();
        }
        if (null != method) {
            try {
                method.invoke(obj, job.getUserId());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        LOG.info("任务名称 = [" + job.getJobName() + "]--------启动成功。");
    }

    /**
     * 添加job
     *
     * @param factory
     * @param job
     * @throws SchedulerException
     */
    public static void addJob(SchedulerFactoryBean factory, ScheduleJob job) throws SchedulerException {
        if (null == job || !ScheduleJob.RUNNING.equals(job.getJobStatus())) {
            return;
        }
        Scheduler scheduler = factory.getScheduler();
        LOG.debug(scheduler.getSchedulerName() + "-----add");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            Class clazz = ScheduleJob.CURRENT_IS.equals(job.getConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put(SCHEDULE_JOB, job);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {//exists and upgrade
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 所有job
     *
     * @param factory
     * @return
     * @throws SchedulerException
     */
    public static List<ScheduleJob> getAllJob(SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleJob> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                ScheduleJob job = new ScheduleJob();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDescription("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     * 获取正在执行的job
     *
     * @param factory
     * @return
     * @throws SchedulerException
     */
    public static List<ScheduleJob> getRunningJob(SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList<>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            ScheduleJob job = new ScheduleJob();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停任务
     *
     * @param factory
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void pauseJob(SchedulerFactoryBean factory, ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复任务
     *
     * @param factory
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void resumeJob(SchedulerFactoryBean factory, ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除任务
     *
     * @param factory
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void deleteJob(SchedulerFactoryBean factory, ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);

    }

    /**
     * 立即运行任务
     *
     * @param factory
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void runAJobNow(SchedulerFactoryBean factory, ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新任务表达式
     *
     * @param factory
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void updateJobCron(SchedulerFactoryBean factory, ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }
}
