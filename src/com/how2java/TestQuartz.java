package com.how2java;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @Description
 *              几个基本的东西：
 *              触发器 Trigger： 什么时候工作
 *              任务 Job: 做什么工作
 *              调度器 Scheduler: 搭配 Trigger和Job
 *
 *              Trigger 主要就是 SimpleTrigger 和 CronTrigger
 *
 *              Job 其实是由 3 个部分组成：
 *              JobDetail: 用于描述这个Job是做什么的
 *              实现Job的类: 具体干活的
 *              JobDataMap: 给 Job 提供参数用的
 * @Author ZBJ
 * @Date 2019/12/25 13:54
 **/
public class TestQuartz {
    public static void main(String[] args) throws Exception{
            //创建调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //定义一个job, 指定干活的类MainJob, 指定任务名称和分组 还可以定义属性
            JobDetail jobdetail = JobBuilder.newJob(MailJob.class).withIdentity("mailJob", "mailGroup").build();
            //定义一个触发器
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ? "))
                    .build();
 
            //将job加入调度
            Date ft = scheduler.scheduleJob(jobdetail, trigger);

            System.out.println("使用的Cron表达式是："+trigger.getCronExpression());
            //启动此调度
            scheduler.start();
              
            //等待200秒，让前面的任务都执行完了之后，再关闭调度器
            Thread.sleep(20000);
            //调度器不关闭，那么任务就在条件的约束下一直执行
            scheduler.shutdown(true);
    }
}