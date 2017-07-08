package com.eric.monitoringserverjava.jobs;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@RequestMapping("api/")
@RestController
public class JobController {
	private JobService jobService;

	@Autowired
	public JobController (JobService jobService) {
		this.jobService = jobService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "jobs/", method = RequestMethod.GET)
	Flux<Job> getAllJobs () {
		return jobService.getAllJobs();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "jobs/{id}", method = RequestMethod.GET)
	Mono<Job> getJobById (@PathVariable Publisher<String> id) {
		return jobService.getJobsById(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "jobs/", method = RequestMethod.POST)
	Mono<ResponseEntity<Job>> createJob (@RequestBody Job job) {
		return jobService.createJob(job).map(
		  createdJob -> new ResponseEntity<>(createdJob, HttpStatus.CREATED)
		);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "jobs/", method = RequestMethod.PUT)
	Mono<ResponseEntity> updateJob (@RequestBody Job job) {
		return jobService.createJob(job).map(
		  updatedJob -> new ResponseEntity(updatedJob, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.noContent().build());
	}
}
