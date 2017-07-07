package com.eric.monitoringserverjava.jobs;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Service
public class JobServiceImpl implements JobService {
	private static Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

	private JobRepository repository;

	@Autowired
	public JobServiceImpl (JobRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<Job> getAllJobs () {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving jobs.");
		}

		return repository.findAll();
	}

	@Override
	public Mono<Job> getJobsById (Publisher<String> id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving job by Id.");
		}

		return repository.findById(id);
	}

	@Override
	public Mono<Job> createJob (Job job) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Creating job: {}.", job);
		}

		return repository.save(job);
	}

	@Override
	public Mono<Job> updateJob (Job job) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Updating job: {}.", job);
		}

		return repository.save(job);
	}

	@Override
	public Mono<Void> deleteJob (Job job) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting job: {}.", job);
		}

		return  repository.delete(job);
	}
}
