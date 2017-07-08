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
	private RestEndpointService restEndpointService;

	@Autowired
	public EndpointController (RestEndpointService restEndpointService) {
		this.restEndpointService = restEndpointService;
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(method = RequestMethod.GET)
	Flux<RestEndpointConfig> getAllRestEndpointConfigs () {
		return restEndpointService.getAllRestEndpointConfigs();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER','GUEST')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Mono<RestEndpointConfig> getRestEndpointConfigById (@PathVariable Publisher<String> id) {
		return restEndpointService.getRestEndpointConfigById(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.POST)
	Mono<ResponseEntity<RestEndpointConfig>> createRestEndpointConfig (@RequestBody RestEndpointConfig restEndpointConfig) {
		return restEndpointService.createRestEndpointConfig(restEndpointConfig).map(
		  (rec) -> new ResponseEntity<>(rec, HttpStatus.CREATED)
		);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.PUT)
	Mono<ResponseEntity> updateRestEndpointConfig (@RequestBody RestEndpointConfig restEndpointConfig) {
		return restEndpointService.updateRestEndpointConfig(restEndpointConfig).map(
		  (rec) -> new ResponseEntity(rec, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(method = RequestMethod.DELETE)
	Mono<Void> deleteRestEndpointConfig (@RequestBody RestEndpointConfig restEndpointConfig) {
		return restEndpointService.deleteRestEndpointConfig(restEndpointConfig);
	}
}
