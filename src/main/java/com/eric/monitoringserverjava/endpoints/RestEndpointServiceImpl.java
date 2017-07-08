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
public class RestEndpointServiceImpl implements RestEndpointService {
	private static Logger LOGGER = LoggerFactory.getLogger(RestEndpointServiceImpl.class);

	private RestEndpointRepository repository;

	@Autowired
	public RestEndpointServiceImpl (RestEndpointRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<RestEndpointConfig> createRestEndpointConfig (RestEndpointConfig restEndpointConfig) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Creating rest endpoint config: {}.", restEndpointConfig);
		}

		return repository.save(restEndpointConfig);
	}

	@Override
	public Flux<RestEndpointConfig> getAllRestEndpointConfigs () {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving rest endpoint configs.");
		}

		return repository.findAll();

	}

	@Override
	public Mono<RestEndpointConfig> getRestEndpointConfigById (Publisher<String> id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving rest endpoint configs by Id.");
		}

		return repository.findById(id);
	}

	@Override
	public Mono<RestEndpointConfig> updateRestEndpointConfig (RestEndpointConfig restEndpointConfig) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Updating rest endpoint config: {}.", restEndpointConfig);
		}

		return repository.save(restEndpointConfig);
	}

	@Override
	public Mono<Void> deleteRestEndpointConfig (RestEndpointConfig restEndpointConfig) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting endpointconfig: {}.", restEndpointConfig);
		}

		return repository.delete(restEndpointConfig);
	}
}
