package com.eric.monitoringserverjava.rules;

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
public class RestRestRuleServiceImpl implements RestRuleService {
	private static Logger LOGGER = LoggerFactory.getLogger(RestRestRuleServiceImpl.class);

	private RestRuleRepository repository;

	@Autowired
	public RestRestRuleServiceImpl (RestRuleRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<RestRule> getRestRules () {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting rest rules.");
		}

		return repository.findAll();
	}

	@Override
	public Mono<RestRule> getRestRules (Publisher<String> id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting rest rules by Id.");
		}

		return repository.findById(id);
	}

	@Override
	public Mono<RestRule> createRestRule (RestRule restRule) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Creating rest rule: {}.", restRule);
		}

		return repository.save(restRule);
	}

	@Override
	public Mono<RestRule> updateRestRule (RestRule restRule) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Updating rest rule: {}.", restRule);
		}

		return repository.save(restRule);
	}

	@Override
	public Mono<Void> deleteRestRule (RestRule restRule) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting rule: {}.", restRule);
		}

		return repository.delete(restRule);
	}
}
