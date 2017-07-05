package com.eric.monitoringserverjava.jobs;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Service
public class JobServiceImpl implements JobService {
	private JobRepository repository;

	@Autowired
	public JobServiceImpl (JobRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<Job> getAllJobs () {
		return repository.findAll();
	}

	@Override
	public Mono<Job> getJobsById (Publisher<String> id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Job> createJob (Job job) {
		return repository.save(job);
	}

	@Override
	public Mono<Job> updateJob (Job job) {
		return repository.save(job);
	}

	@Override
	public Mono<Void> deleteJob (Job job) {
		return  repository.delete(job);
	}
}
