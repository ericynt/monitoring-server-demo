package com.eric.monitoringserverjava.endpoints;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

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
        LOGGER.debug("Creating endpoint config: {}.", endpointConfig);

        return repository.save(endpointConfig);
    }

    @Override
    public Flux<EndpointConfig> getAllEndpointConfigs () {
        LOGGER.debug("Retrieving endpoint configs.");

        return repository.findAll();

    }

    @Override
    public Mono<EndpointConfig> getEndpointConfigById (Publisher<String> id) {
        LOGGER.debug("Retrieving endpoint configs by Id.");

        return repository.findById(id);
    }

    @Override
    public Mono<EndpointConfig> updateEndpointConfig (EndpointConfig endpointConfig) {
        LOGGER.debug("Updating endpoint config: {}.", endpointConfig);

        return repository.save(endpointConfig);
    }

    @Override
    public Mono<Void> deleteEndpointConfig (EndpointConfig endpointConfig) {
        LOGGER.debug("Deleting endpointconfig: {}.", endpointConfig);

        return repository.delete(endpointConfig);
    }

    @Override
    public URI getURI (EndpointConfig endpointConfig) {
        URI uri = null;
        try {
            uri = new URI(
                    endpointConfig.getProtocol()
                                  .toString()
                                  .toLowerCase()
                            + "://" + endpointConfig.getHost()
                            + ':'
                            + endpointConfig.getPort() + endpointConfig.getPath()
            );
        } catch (URISyntaxException e) {
            LOGGER.error("Unable to create URI from endpointconfig: {}.", endpointConfig);
        }

        return uri;
    }
}
