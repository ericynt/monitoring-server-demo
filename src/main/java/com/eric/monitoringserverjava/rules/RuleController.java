package com.eric.monitoringserverjava.rules;

import org.springframework.beans.factory.annotation.Autowired;
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
	RestRuleService restRuleService;

	@Autowired
	public RuleController (RestRuleService restRuleService) {
		this.restRuleService = restRuleService;
	}

	@RequestMapping(value = "/rules", method = RequestMethod.GET)
	public Flux<RestRule> getRestRules () {
		return restRuleService.getRestRules();
	}

	@RequestMapping(value = "/rules/{id}", method = RequestMethod.GET)
	public Mono<RestRule> getRestRules (@PathVariable String id) {
		return restRuleService.getRestRules(id);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.POST)
	public Mono<RestRule> createRestRule (RestRule rule) {
		return restRuleService.createRestRule(rule);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.PUT)
	public Mono<RestRule> updateRestRule (RestRule rule) {
		return  restRuleService.updateRestRule(rule);
	}

	@RequestMapping(value = "/rules", method = RequestMethod.DELETE)
	public Mono<Void> deleteRestRule (RestRule rule) {
		return restRuleService.deleteRestRule(rule);
	}
}
