package com.eric.monitoringserverjava.endpoints;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 *
 */
public interface EndpointService {
    Mono<EndpointConfig> createEndpointConfig (EndpointConfig endpointConfig);

    Flux<EndpointConfig> getAllEndpointConfigs ();

    Mono<EndpointConfig> getEndpointConfigById (Publisher<String> id);

    Mono<EndpointConfig> updateEndpointConfig (EndpointConfig endpointConfig);

    Mono<Void> deleteEndpointConfig (EndpointConfig endpointConfig);

    URI getURI (EndpointConfig endpointConfig);
}
