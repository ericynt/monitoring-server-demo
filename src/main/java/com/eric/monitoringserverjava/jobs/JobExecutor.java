package com.eric.monitoringserverjava.jobs;

import com.eric.monitoringserverjava.dashboard.RuleResult;
import com.eric.monitoringserverjava.remotes.RemoteService;
import com.eric.monitoringserverjava.rules.Rule;
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
class JobExecutor {
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

	public void start () {
		scheduler.scheduleAtFixedRate(() -> {
			  LocalDateTime startTime = LocalDateTime.now();
			  ResponseEntity<String> responseEntity = remoteService.sendGetRequest(job.getEndpointConfig().getUrl());
			  List<Rule> rules = job.getRules();
			  Duration duration = Duration.between(startTime, LocalDateTime.now());
			  List<RuleResult> ruleResult = rules.stream().map((r) -> {
				  return new RuleResult(
				    null,
					job.getId(),
					startTime,
					duration.getSeconds(),
					// TODO use sameJSONAs
					responseEntity.getBody().equals(
					  r.getExpectedResponseBody()
					)
				  );
			  }).collect(Collectors.toList());
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
