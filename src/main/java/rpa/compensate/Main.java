package rpa.compensate;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import rpa.compensate.window.Frame;
import rpa.compensate.window.KeyHook;

/**
 * @author yangsiguo
 * @date 2019/6/1
 * @desc TODO add description in here
 */
public class Main {

    public static void main(String[] args) {

        Frame.createFrame();
        //定时器每日调用
        try {
            startCronScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        KeyHook.keyHook();

    }

    private static void startCronScheduler() throws SchedulerException {
        //jobDetail
        JobDetail jobDetail = JobBuilder.newJob(CompensateJob.class).withIdentity("cronJob").build();
        String cron = PropertiesUtil.getValueByKey("cron");
        System.out.println(cron);
        //cronTrigger
        //每日的9点40触发任务
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 40 9 * * ? ")).build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();

        //Scheduler实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
