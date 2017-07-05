package com.eric.monitoringserverjava.rules;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@RequestMapping("/api")
@RestController
public class RuleController {
	private RuleService ruleService;

	@Autowired
	public RuleController (RuleService ruleService) {
		this.ruleService = ruleService;
	}

	@RequestMapping(value = "/rules", method = RequestMethod.GET)
	public Flux<Rule> getRestRules () {
		return ruleService.getRules();
	}

	@RequestMapping(value = "/rules/{id}", method = RequestMethod.GET)
	public Mono<Rule> getRestRules (@PathVariable Publisher<String> id) {
		return ruleService.getRules(id);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.POST)
	public Mono<ResponseEntity<Rule>> createRule (Rule rule) {
		return ruleService.createRule(rule).map(
		  createdRule -> new ResponseEntity<>(createdRule, HttpStatus.CREATED)
		);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.PUT)
	public Mono<ResponseEntity<Rule>> updateRule (Rule rule) {
		return ruleService.createRule(rule).map(
		  updatedRule -> new ResponseEntity<>(updatedRule, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@RequestMapping(value = "/rules", method = RequestMethod.DELETE)
	public Mono<Void> deleteRule (Rule rule) {
		return ruleService.deleteRule(rule);
	}
}
