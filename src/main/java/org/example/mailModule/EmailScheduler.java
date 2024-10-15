package org.example.mailModule;



import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailScheduler {

    public static long getInitialDelay(LocalTime targetTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRun = now.with(targetTime);

        if (now.isAfter(nextRun)) {
            nextRun = nextRun.plusDays(1);
        }

        return Duration.between(now, nextRun).toMinutes();
    }

    public static void scheduleDailyEmail(Runnable emailTask, LocalTime targetTime) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        long initialDelay = getInitialDelay(targetTime);
        long period = TimeUnit.DAYS.toMillis(1);

        scheduler.scheduleAtFixedRate(emailTask, initialDelay, period, TimeUnit.MINUTES);
        log.info("Task scheduled to run daily at " + targetTime);
    }

    public static LocalDateTime calculateNextRun(LocalTime targetTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRun = now.with(targetTime);

        if (now.isAfter(nextRun)) {
            nextRun = nextRun.plusDays(1);
        }

        return nextRun;
    }

}
