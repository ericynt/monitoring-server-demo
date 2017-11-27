package com.eric.monitoringserverjava.jobs;

import com.eric.monitoringserverjava.dashboard.RuleResultService;
import com.eric.monitoringserverjava.remotes.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Component
public class JobSyncer implements Runnable {
	private JobService jobService;
	private RemoteService remoteService;
	private RuleResultService ruleResultService;

	private Set<JobExecutor> runningJobExecutors = new HashSet<>();

	public Set<JobExecutor> getRunningJobExecutors () {
		return runningJobExecutors;
	}

	@Autowired
	public JobSyncer (JobService jobService, RemoteService remoteService, RuleResultService ruleResultService) {
		this.jobService = jobService;
		this.remoteService = remoteService;
		this.ruleResultService = ruleResultService;
	}

	@Override
	public void run () {
		jobService.getAllJobs().collectList().subscribe((dbJobs) -> {
			dbJobs.forEach(this::checkDBJob);
			runningJobExecutors.forEach((rj) -> checkRunningJobExecutor(rj, dbJobs));
		});
	}

	private void checkDBJob (Job j) {
		if (runningJobExecutors.stream().anyMatch(rj -> rj.getJob().getId().equals(j.getId()))) {
			return;
		}

		startJob(j);
	}

	private void checkRunningJobExecutor (JobExecutor je, List<Job> dbJobs) {
		if (dbJobs.stream().anyMatch(dbj -> dbj.getId().equals(je.getJob().getId()))) {
			return;
		}

		stopJobExecutor(je);
	}

	private void startJob (Job j) {
		JobExecutor jobExecutor = new JobExecutor(j, remoteService, ruleResultService);
		jobExecutor.start();
		runningJobExecutors.add(jobExecutor);
	}

	private void stopJobExecutor (JobExecutor je) {
		je.stop();
		runningJobExecutors.remove(je);
	}
}
