package com.eric.monitoringserverjava.endpoints;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 *
 */
public interface RestEndpointRepository extends ReactiveCrudRepository<RestEndpointConfig, String> {
}
