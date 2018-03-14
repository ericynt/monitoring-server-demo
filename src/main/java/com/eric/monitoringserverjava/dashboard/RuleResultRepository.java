package com.eric.monitoringserverjava.dashboard;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 *
 */
public interface RuleResultRepository extends ReactiveCrudRepository<RuleResult, String> {
    Flux<RuleResult> deleteBystartTimeBefore (LocalDateTime startTime);
}
