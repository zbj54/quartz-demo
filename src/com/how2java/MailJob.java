package com.how2java;


import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MailJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {

        //在这个job的实际执行类中，还可以通过这个context获取很多调度相关信息以便操作，这里暂时没有使用

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String now = sdf.format(new  Date());
        
        System.out.printf("job中的相关操作, 当前时间是: %s%n" , now);
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}