package com.test.job;

import java.util.Calendar;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class ExecuteJob {

	public static void main(String[] args) throws SchedulerException, InterruptedException {
		
		SchedulerFactory schfa = new StdSchedulerFactory();
		Scheduler sch = schfa.getScheduler();
		JobDetail jobdetail = JobBuilder.newJob(SampleJob.class)
		    .withIdentity("mySampleJob", "mySampleGroup").build();
		
	    Calendar  c=Calendar.getInstance();
	    c.add(Calendar.MINUTE, 1);
		//This willExecutes only one time
		Trigger trigger = TriggerBuilder.newTrigger()
			.withIdentity("mySampletrigger", "mySampleGroup")
		    .startAt(c.getTime()).build();
		sch.scheduleJob(jobdetail, trigger);
		sch.start();
		Thread.sleep(70L * 1000L);
		sch.shutdown(true);
		
		/*	//Set job details.
try{
    		//Set job details.
    		JobDetail job = JobBuilder.newJob(SampleJob.class)
    			.withIdentity("helloJob", "group1").build();
 
        	//Set the scheduler timings.
    		Trigger trigger = TriggerBuilder.newTrigger()
    		  .withIdentity("simpleTrigger", "group1")
    		  .withSchedule(SimpleScheduleBuilder.simpleSchedule()
    		  .withIntervalInSeconds(10).repeatForever()).build();
 
        	//Execute the job.
    		Scheduler scheduler = 
    				new StdSchedulerFactory().getScheduler();
        	scheduler.start();
        	scheduler.scheduleJob(job, trigger);
    	}catch(Exception e){
    		e.printStackTrace();
    	}    	*/
		
	}
}
