package com.test.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class ExecuteCronTrigger{

	public static void main(String[] args) throws SchedulerException, InterruptedException {
		
		SchedulerFactory schfa = new StdSchedulerFactory();
		Scheduler sch = schfa.getScheduler();
		JobDetail jobdetail = JobBuilder.newJob(SampleJob.class)
			    .withIdentity("cronjob1", "crongroup1").build();
		//Executes after every minute 0 0/1 * 1/1 * ? *
		CronTrigger crontrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "crongroup1")
			    .withSchedule(craeteSchedule("0 0/1 * 1/1 * ? *")).build();
		sch.scheduleJob(jobdetail, crontrigger);
		jobdetail = JobBuilder.newJob(SampleJob.class)
			    .withIdentity("cronjob2", "crongroup1").build();
		//Executes after every 2 minute 0 0/2 * 1/1 * ? *
		crontrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "crongroup1")
			    .withSchedule(craeteSchedule("0 0/2 * 1/1 * ? *")).build();
		sch.scheduleJob(jobdetail, crontrigger);
		sch.start();
		Thread.sleep(100L * 1000L);
		sch.shutdown(true);
	}
	private static ScheduleBuilder<CronTrigger> craeteSchedule(String cronExpression){
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(cronExpression);
		return builder;
	}
} 