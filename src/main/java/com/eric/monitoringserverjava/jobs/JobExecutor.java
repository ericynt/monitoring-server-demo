package com.eric.monitoringserverjava.jobs;

import com.eric.monitoringserverjava.dashboard.RuleResult;
import com.eric.monitoringserverjava.remotes.RemoteService;
import com.eric.monitoringserverjava.rules.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 */
class JobExecutor {
	private Logger LOGGER = LoggerFactory.getLogger(JobExecutor.class);
	private ScheduledExecutorService scheduler;
	private Job job;
	private RemoteService remoteService;

	JobExecutor (Job job, RemoteService remoteService) {
		this.job = job;
		this.remoteService = remoteService;

		scheduler = Executors.newSingleThreadScheduledExecutor();
	}

	Job getJob () {
		return job;
	}

	void start () {
		scheduler.scheduleAtFixedRate(() -> {
			  LocalDateTime startTime = LocalDateTime.now();
			  LOGGER.debug("Starting request at {} for job {}.", startTime, job);
			  URI url = job.getEndpointConfig().getUrl();
			  ResponseEntity<String> responseEntity = remoteService.sendGetRequest(url);
			  List<Rule> rules = job.getRules();
			  Duration duration = Duration.between(startTime, LocalDateTime.now());
			  LOGGER.debug("Completed request for job {}, with duration {}.", job, duration);
			  List<RuleResult> ruleResults = rules.stream().map((r) -> new RuleResult(
				null,
				job.getId(),
				startTime,
				duration.toMillis(),
				// TODO use sameJSONAs
				responseEntity.getBody().equals(
				  r.getExpectedResponseBody()
				)
			  )).collect(Collectors.toList());
			  LOGGER.debug("Request rule results: {}.", ruleResults);
		  },
		  0L,
		  job.getIntervalSeconds(),
		  TimeUnit.SECONDS
		);
	}

	public void stop () {
		scheduler.shutdown();
	}
}
