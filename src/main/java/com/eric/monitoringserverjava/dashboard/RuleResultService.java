package com.eric.monitoringserverjava.dashboard;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface RuleResultService {
	Flux<RuleResult> getAllRuleResults ();

	Mono<RuleResult> getRuleResultById (Publisher<String> id);

	Mono<RuleResult> createRuleResult (RuleResult ruleResult);

	Mono<RuleResult> updateRuleResult (RuleResult ruleResult);

	Mono<Void> deleteRuleResult (RuleResult ruleResult);
}
