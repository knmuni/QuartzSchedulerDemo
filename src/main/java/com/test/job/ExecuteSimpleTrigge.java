package com.test.job;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class ExecuteSimpleTrigge {
	
	public static void main(String[] args) throws SchedulerException {
		
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(SampleJob.class).withIdentity("MySampleJob", "SimpleTriggeGroup").build();
		SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("MySampleJobTrigger", "SimpleTriggeGroup")
			    .startAt(new Date(Calendar.getInstance().getTimeInMillis()+ 3000))
			    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
			    		.withRepeatCount(5)).build();
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
		try {
			//wait for 20 seconds to finish the job
			//Thread.sleep(20000);
		    long startTime = System.nanoTime();
		    System.out.println("Total Time :" +startTime);
			TimeUnit.SECONDS.sleep(20);
		    System.out.println("Total Time :" +(System.nanoTime()-startTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//shutdown scheduler gracefully
		scheduler.shutdown(true);
	}
}
