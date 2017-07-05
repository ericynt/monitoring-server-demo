package com.eric.monitoringserverjava.jobs;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface JobService {
	Flux<Job> getAllJobs ();

	Mono<Job> getJobsById (Publisher<String> id);

	Mono<Job> createJob (Job job);

	Mono<Job> updateJob (Job job);

	Mono<Void> deleteJob (Job job);
}
