package org.example.mailModule;


import org.example.job.EmailJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailScheduler {

    private static final Logger log = LoggerFactory.getLogger(EmailScheduler.class);

    public static void startScheduler() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail jobDetail = JobBuilder.newJob(EmailJob.class)
                    .withIdentity("emailJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("emailTrigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 6 * * ?"))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
            log.info("Scheduler started successfully.");
        } catch (SchedulerException e) {
            log.error("Error starting scheduler: ", e);
        }
    }


}
