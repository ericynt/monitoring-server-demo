package com.eric.monitoringserverjava.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Service
public class RestRuleServiceImpl implements RestRuleService {
	RestRuleRepository restRuleRepository;

	@Autowired
	public RestRuleServiceImpl (RestRuleRepository restRuleRepository) {
		this.restRuleRepository = restRuleRepository;
	}

	@Override
	public Flux<RestRule> getRestRules () {
		return restRuleRepository.findAll();
	}

	@Override
	public Mono<RestRule> getRestRules (String id) {
		return restRuleRepository.findById(id);
	}

	@Override
	public Mono<RestRule> createRestRule (RestRule rule) {
		return restRuleRepository.save(rule);
	}

	@Override
	public Mono<RestRule> updateRestRule (RestRule rule) {
		return restRuleRepository.save(rule);
	}

	@Override
	public Mono<Void> deleteRestRule (RestRule rule) {
		return restRuleRepository.delete(rule);
	}
}
