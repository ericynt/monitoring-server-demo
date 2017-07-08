package com.eric.monitoringserverjava.rules;

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
@RequestMapping("/api")
@RestController
public class RuleController {
	private RuleService ruleService;

	@Autowired
	public RuleController (RuleService ruleService) {
		this.ruleService = ruleService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "/rules", method = RequestMethod.GET)
	public Flux<Rule> getRestRules () {
		return ruleService.getRules();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "/rules/{id}", method = RequestMethod.GET)
	public Mono<Rule> getRestRules (@PathVariable Publisher<String> id) {
		return ruleService.getRules(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/rules", method = RequestMethod.POST)
	public Mono<ResponseEntity<Rule>> createRule (@RequestBody Rule rule) {
		return ruleService.createRule(rule).map(
		  createdRule -> new ResponseEntity<>(createdRule, HttpStatus.CREATED)
		);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/rules", method = RequestMethod.PUT)
	public Mono<ResponseEntity<Rule>> updateRule (@RequestBody Rule rule) {
		return ruleService.createRule(rule).map(
		  updatedRule -> new ResponseEntity<>(updatedRule, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/rules", method = RequestMethod.DELETE)
	public Mono<Void> deleteRule (@RequestBody Rule rule) {
		return ruleService.deleteRule(rule);
	}
}
