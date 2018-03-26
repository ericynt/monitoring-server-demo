package com.eric.monitoringserverjava.contex;

import com.eric.monitoringserverjava.dashboard.RuleResultService;
import com.eric.monitoringserverjava.jobs.JobExecutor;
import com.eric.monitoringserverjava.jobs.JobSyncer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Component
@WebListener
public class ContextManager implements ServletContextListener {
    Logger LOGGER = LoggerFactory.getLogger(ContextManager.class);
    // TODO load from property file
    private static final int PERIOD = 10;
    private static final long INITIAL_DELAY = 0L;

    private ScheduledExecutorService scheduler;
    private JobSyncer jobSyncer;
    private RuleResultService ruleResultService;

    @Autowired
    public ContextManager (JobSyncer jobSyncer, RuleResultService ruleResultService) {
        this.jobSyncer = jobSyncer;
        this.ruleResultService = ruleResultService;
    }

    @Override
    public void contextInitialized (ServletContextEvent servletContextEvent) {
        scheduler = Executors.newScheduledThreadPool(2);
        scheduler.scheduleAtFixedRate(jobSyncer, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(
                () -> {
                    LOGGER.debug("Cleaning old rule results.");
                    try {
                        ruleResultService.deleteByStartTimeBefore(LocalDateTime.now()
                                                                               .minusMinutes(1));
                    } catch (Exception e) {
                        LOGGER.debug("Something went wrong while cleaning.", e);
                    }
                },
                30L,
                10L,
                TimeUnit.SECONDS
        );
    }

    @Override
    public void contextDestroyed (ServletContextEvent servletContextEvent) {
        jobSyncer.getRunningJobExecutors()
                 .forEach(JobExecutor::stop);
        scheduler.shutdown();
    }
}
