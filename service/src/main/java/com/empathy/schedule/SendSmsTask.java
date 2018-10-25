package com.empathy.schedule;

import com.empathy.utils.sms.SmsNewUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendSmsTask implements Job {



    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String phone = jobExecutionContext.getJobDetail().getJobDataMap().getString("phone");

        // 直播预约短信发送
        SmsNewUtils.beginAppointment(phone);
        System.out.println("发送短信任务具体执行"+phone);
    }
}
