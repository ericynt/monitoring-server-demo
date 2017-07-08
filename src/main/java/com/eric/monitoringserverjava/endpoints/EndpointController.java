package com.eric.monitoringserverjava.endpoints;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@RequestMapping("api/")
@RestController
public class EndpointController {
	private EndpointService endpointService;

	@Autowired
	public EndpointController (EndpointService endpointService) {
		this.endpointService = endpointService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "endpoint-configs/", method = RequestMethod.GET)
	Flux<EndpointConfig> getAllEndpointConfigs () {
		return endpointService.getAllEndpointConfigs();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "endpoint-configs/{id}", method = RequestMethod.GET)
	Mono<EndpointConfig> getEndpointConfigById (@PathVariable Publisher<String> id) {
		return endpointService.getEndpointConfigById(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "endpoint-configs/", method = RequestMethod.POST)
	Mono<ResponseEntity<EndpointConfig>> createEndpointConfig (@RequestBody EndpointConfig endpointConfig) {
		return endpointService.createEndpointConfig(endpointConfig).map(
		  (createdEndpointConfig) -> new ResponseEntity<>(createdEndpointConfig, HttpStatus.CREATED)
		);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "endpoint-configs/", method = RequestMethod.PUT)
	Mono<ResponseEntity> updateEndpointConfig (@RequestBody EndpointConfig endpointConfig) {
		return endpointService.updateEndpointConfig(endpointConfig).map(
		  (updatedEndpointConfig) -> new ResponseEntity(updatedEndpointConfig, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "endpoint-configs/", method = RequestMethod.DELETE)
	Mono<Void> deleteEndpointConfig (@RequestBody EndpointConfig endpointConfig) {
		return endpointService.deleteEndpointConfig(endpointConfig);
	}
}
