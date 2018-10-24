package com.empathy.schedule;

import com.empathy.domain.schedule.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/3/3 16:41
 * @desc 禁止并发执行
 */
@DisallowConcurrentExecution
public final class QuartzJobFactoryDisallowConcurrentExecution implements Job {

    @Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
        ScheduleJob job = (ScheduleJob) ctx.getMergedJobDataMap().get("scheduleJob");
        if (null != job) {
            ScheduleUtils.invokeMethod(job);
        }
    }
}
