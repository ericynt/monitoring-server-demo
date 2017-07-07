package com.eric.monitoringserverjava.endpoints;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Service
public class EndpointServiceImpl implements EndpointService {
	private static Logger LOGGER = LoggerFactory.getLogger(EndpointServiceImpl.class);

	private EndpointRepository repository;

	@Autowired
	public EndpointServiceImpl (EndpointRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<EndpointConfig> createEndpointConfig (EndpointConfig endpointConfig) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Creating endpointconfig: {}.", endpointConfig);
		}

		return repository.save(endpointConfig);
	}

	@Override
	public Flux<EndpointConfig> getAllEndpointConfigs () {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving endpointconfigs.");
		}

		return repository.findAll();

	}

	@Override
	public Mono<EndpointConfig> getEndpointConfigById (Publisher<String> id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving endpointconfigs by Id.");
		}

		return repository.findById(id);
	}

	@Override
	public Mono<EndpointConfig> updateEndpointConfig (EndpointConfig endpointConfig) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Updating endpointconfig: {}.", endpointConfig);
		}

		return repository.save(endpointConfig);
	}

	@Override
	public Mono<Void> deleteEndpointConfig (EndpointConfig endpointConfig) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting endpointconfig: {}.", endpointConfig);
		}

		return repository.delete(endpointConfig);
	}
}
