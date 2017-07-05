package com.eric.monitoringserverjava.rules;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Service
public class RuleServiceImpl implements RuleService {
	private RuleRepository repository;

	@Autowired
	public RuleServiceImpl (RuleRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<Rule> getRules () {
		return repository.findAll();
	}

	@Override
	public Mono<Rule> getRules (Publisher<String> id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Rule> createRule (Rule rule) {
		return repository.save(rule);
	}

	@Override
	public Mono<Rule> updateRule (Rule rule) {
		return repository.save(rule);
	}

	@Override
	public Mono<Void> deleteRule (Rule rule) {
		return repository.delete(rule);
	}
}
