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
@RequestMapping("api/rest-endpoint-configs/")
@RestController
public class EndpointController {
	private EndpointService endpointService;

	@Autowired
	public EndpointController (EndpointService endpointService) {
		this.endpointService = endpointService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(method = RequestMethod.GET)
	Flux<EndpointConfig> getAllRestEndpointConfigs () {
		return endpointService.getAllEndpointConfigs();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Mono<EndpointConfig> getRestEndpointConfigById (@PathVariable Publisher<String> id) {
		return endpointService.getEndpointConfigById(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.POST)
	Mono<ResponseEntity<EndpointConfig>> createRestEndpointConfig (@RequestBody EndpointConfig endpointConfig) {
		return endpointService.createEndpointConfig(endpointConfig).map(
		  (rec) -> new ResponseEntity<>(rec, HttpStatus.CREATED)
		);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.PUT)
	Mono<ResponseEntity> updateRestEndpointConfig (@RequestBody EndpointConfig endpointConfig) {
		return endpointService.updateEndpointConfig(endpointConfig).map(
		  (rec) -> new ResponseEntity(rec, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.DELETE)
	Mono<Void> deleteRestEndpointConfig (@RequestBody EndpointConfig endpointConfig) {
		return endpointService.deleteEndpointConfig(endpointConfig);
	}
}
