package com.eric.monitoringserverjava.jobs;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 *
 */
public interface JobRepository extends ReactiveCrudRepository<Job, String> {}
