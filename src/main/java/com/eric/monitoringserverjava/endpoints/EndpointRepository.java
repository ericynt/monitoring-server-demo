package com.eric.monitoringserverjava.endpoints;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 *
 */
public interface EndpointRepository extends ReactiveCrudRepository<EndpointConfig, String> {
}
