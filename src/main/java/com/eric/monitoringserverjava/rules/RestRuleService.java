package com.eric.monitoringserverjava.rules;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface RestRuleService {
	Flux<RestRule> getRestRules ();

	Mono<RestRule> getRestRules (Publisher<String> id);

	Mono<RestRule> createRestRule (RestRule restRule);

	Mono<RestRule> updateRestRule (RestRule restRule);

	Mono<Void> deleteRestRule (RestRule restRule);
}
