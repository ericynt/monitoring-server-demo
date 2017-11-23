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
@RequestMapping("/api/rules")
@RestController
public class RuleController {
	private RuleService ruleService;

	@Autowired
	public RuleController (RuleService ruleService) {
		this.ruleService = ruleService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(method = RequestMethod.GET)
	public Flux<Rule> getRestRules () {
		return ruleService.getRules();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Mono<Rule> getRestRules (@PathVariable Publisher<String> id) {
		return ruleService.getRules(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.POST)
	public Mono<ResponseEntity<Rule>> createRestRule (@RequestBody Rule rule) {
		return ruleService.createRule(rule).map(
		  rr -> new ResponseEntity<>(rr, HttpStatus.CREATED)
		);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.PUT)
	public Mono<ResponseEntity<Rule>> updateRestRule (@RequestBody Rule rule) {
		return ruleService.updateRule(rule).map(
		  rr -> new ResponseEntity<>(rr, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.DELETE)
	public Mono<Void> deleteRestRule (@RequestBody Rule rule) {
		return ruleService.deleteRule(rule);
	}
}
