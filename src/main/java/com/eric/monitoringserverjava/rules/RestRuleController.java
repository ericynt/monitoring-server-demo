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
public class RestRuleController {
	private RestRuleService restRuleService;

	@Autowired
	public RestRuleController (RestRuleService restRuleService) {
		this.restRuleService = restRuleService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(method = RequestMethod.GET)
	public Flux<RestRule> getRestRules () {
		return restRuleService.getRestRules();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Mono<RestRule> getRestRules (@PathVariable Publisher<String> id) {
		return restRuleService.getRestRules(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.POST)
	public Mono<ResponseEntity<RestRule>> createRestRule (@RequestBody RestRule restRule) {
		return restRuleService.createRestRule(restRule).map(
		  rr -> new ResponseEntity<>(rr, HttpStatus.CREATED)
		);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.PUT)
	public Mono<ResponseEntity<RestRule>> updateRestRule (@RequestBody RestRule restRule) {
		return restRuleService.updateRestRule(restRule).map(
		  rr -> new ResponseEntity<>(rr, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.DELETE)
	public Mono<Void> deleteRestRule (@RequestBody RestRule restRule) {
		return restRuleService.deleteRestRule(restRule);
	}
}
