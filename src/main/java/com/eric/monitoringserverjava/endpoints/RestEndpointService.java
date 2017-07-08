package com.eric.monitoringserverjava.endpoints;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface RestEndpointService {
	Mono<RestEndpointConfig> createRestEndpointConfig (RestEndpointConfig endpointConfig);

	Flux<RestEndpointConfig> getAllRestEndpointConfigs ();

	Mono<RestEndpointConfig> getRestEndpointConfigById (Publisher<String> id);

	Mono<RestEndpointConfig> updateRestEndpointConfig (RestEndpointConfig endpointConfig);

	Mono<Void> deleteRestEndpointConfig (RestEndpointConfig endpointConfig);
}
