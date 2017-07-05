package com.eric.monitoringserverjava.endpoints;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Service
public class EndpointServiceImpl implements EndpointService {
	private EndpointRepository repository;

	@Autowired
	public EndpointServiceImpl (EndpointRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<EndpointConfig> createEndpointConfig (EndpointConfig endpointConfig) {
		return repository.save(endpointConfig);
	}

	@Override
	public Flux<EndpointConfig> getAllEndpointConfigs () {
		return repository.findAll();
	}

	@Override
	public Mono<EndpointConfig> getEndpointConfigById (Publisher<String> id) {
		return repository.findById(id);
	}

	@Override
	public Mono<EndpointConfig> updateEndpointConfig (EndpointConfig endpointConfig) {
		return repository.save(endpointConfig);
	}

	@Override
	public Mono<Void> deleteEndpointConfig (EndpointConfig endpointConfig) {
		return repository.delete(endpointConfig);
	}
}
