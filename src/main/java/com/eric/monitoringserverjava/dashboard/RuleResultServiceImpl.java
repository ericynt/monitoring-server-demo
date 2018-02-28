package com.eric.monitoringserverjava.dashboard;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 *
 */
@Service
public class RuleResultServiceImpl implements RuleResultService {
	private static Logger LOGGER = LoggerFactory.getLogger(RuleResultServiceImpl.class);

	private RuleResultRepository repository;

	@Autowired
	public RuleResultServiceImpl (RuleResultRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<RuleResult> getAllRuleResults () {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving all rule results.");
		}

		return repository.findAll();
	}

	@Override
	public Mono<RuleResult> getRuleResultById (Publisher<String> id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving rule results by Id.");
		}

		return repository.findById(id);
	}

	@Override
	public Mono<RuleResult> createRuleResult (RuleResult ruleResult) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Creating rule result: {}.", ruleResult);
		}

		return repository.save(ruleResult);
	}

	@Override
	public Mono<RuleResult> updateRuleResult (RuleResult ruleResult) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Updating rule result: {}.", ruleResult);
		}

		return repository.save(ruleResult);
	}

	@Override
	public Mono<Void> deleteRuleResult (RuleResult ruleResult) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting rule result: {}.", ruleResult);
		}

		return repository.delete(ruleResult);
	}

	@Override
	public void deleteByStartTimeBefore (LocalDateTime before) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting rule results before: {}.", before);
		}

		repository.deleteBystartTimeBefore(before).subscribe();
	}
}
