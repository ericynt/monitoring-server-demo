package com.eric.monitoringserverjava.rules;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface RestRuleService {
	Flux<RestRule> getRestRules ();

	Mono<RestRule> getRestRules (String id);

	Mono<RestRule> createRestRule (RestRule rule);

	Mono<RestRule> updateRestRule (RestRule rule);

	Mono<Void> deleteRestRule (RestRule rule);
}
