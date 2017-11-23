package com.eric.monitoringserverjava.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Component
@WebListener
public class JobManager implements ServletContextListener {
	// TODO load from property file
	private static final int PERIOD = 10;
	private static final long INITIAL_DELAY = 0L;

	private ScheduledExecutorService scheduler;
	private JobSyncer jobSyncer;

	@Autowired
	public JobManager (JobSyncer jobSyncer) {
		this.jobSyncer = jobSyncer;
	}

	@Override
	public void contextInitialized (ServletContextEvent servletContextEvent) {
		scheduler = Executors.newSingleThreadScheduledExecutor();

		scheduler.scheduleAtFixedRate(jobSyncer, INITIAL_DELAY, PERIOD, TimeUnit.MINUTES);
	}

	@Override
	public void contextDestroyed (ServletContextEvent servletContextEvent) {
		jobSyncer.getRunningJobExecutors().forEach(JobExecutor::stop);
		scheduler.shutdown();
	}
}
