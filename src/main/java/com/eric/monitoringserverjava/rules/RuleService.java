package com.eric.monitoringserverjava.rules;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface RuleService {
    Flux<Rule> getRules ();

    Mono<Rule> getRules (Publisher<String> id);

    Mono<Rule> createRule (Rule rule);

    Mono<Rule> updateRule (Rule rule);

    Mono<Void> deleteRule (Rule rule);
}
