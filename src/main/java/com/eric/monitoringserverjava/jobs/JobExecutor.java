package com.eric.monitoringserverjava.jobs;

import com.eric.monitoringserverjava.dashboard.RuleResult;
import com.eric.monitoringserverjava.dashboard.RuleResultService;
import com.eric.monitoringserverjava.remotes.RemoteService;
import com.eric.monitoringserverjava.rules.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

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
public class JobExecutor {
	private Logger LOGGER = LoggerFactory.getLogger(JobExecutor.class);
	private ScheduledExecutorService scheduler;
	private Job job;
	private RemoteService remoteService;
	private RuleResultService ruleResultService;

	JobExecutor (Job job, RemoteService remoteService, RuleResultService ruleResultService) {
		this.job = job;
		this.remoteService = remoteService;
		this.ruleResultService = ruleResultService;

		scheduler = Executors.newSingleThreadScheduledExecutor();
	}

	public Job getJob () {
		return job;
	}

	public void start () {
		scheduler.scheduleAtFixedRate(() -> {
			  LocalDateTime startTime = LocalDateTime.now();
			  if(LOGGER.isDebugEnabled()) {
				  LOGGER.debug("Starting request at {} for job {}.", startTime, job);
			  }
			  ResponseEntity<String> responseEntity = remoteService.sendGetRequest(job.getEndpointConfig().getUrl());
			  List<Rule> rules = job.getRules();
			  Duration duration = Duration.between(startTime, LocalDateTime.now());
			  if (LOGGER.isDebugEnabled()) {
				  LOGGER.debug("Completed request for job {}, with duration {}.", job, duration);
			  }
			  List<RuleResult> ruleResults = rules.stream().map((r) -> {
			  	RuleResult rr = new RuleResult(
					null,
					job.getId(),
					startTime,
					duration.toMillis(),
					// TODO use sameJSONAs
					responseEntity.getBody().equals(
					  r.getExpectedResponseBody()
					)
			  	);
			  	ruleResultService.createRuleResult(rr).subscribe();
			  	if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Rule result {} created.", rr);
				}

			  	return rr;
			  }).collect(Collectors.toList());

			  if (LOGGER.isDebugEnabled()) {
				  LOGGER.debug("Request rule results: {}.", ruleResults);
			  }
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
