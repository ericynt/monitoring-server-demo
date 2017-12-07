package com.eric.monitoringserverjava.dashboard;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 *
 */
public interface RuleResultRepository extends ReactiveCrudRepository<RuleResult, String> {
	void deleteByStartTimeBefore(LocalDateTime startTime);
}
