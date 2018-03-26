package com.eric.monitoringserverjava.rules;

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
public class RuleServiceImpl implements RuleService {
    private static Logger LOGGER = LoggerFactory.getLogger(RuleService.class);

    private RuleRepository repository;

    @Autowired
    public RuleServiceImpl (RuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Rule> getRules () {
        LOGGER.debug("Getting rules.");

        return repository.findAll();
    }

    @Override
    public Mono<Rule> getRules (Publisher<String> id) {
        LOGGER.debug("Getting rules by Id.");

        return repository.findById(id);
    }

    @Override
    public Mono<Rule> createRule (Rule rule) {
        LOGGER.debug("Creating rule: {}.", rule);

        return repository.save(rule);
    }

    @Override
    public Mono<Rule> updateRule (Rule rule) {
        LOGGER.debug("Updating rule: {}.", rule);

        return repository.save(rule);
    }

    @Override
    public Mono<Void> deleteRule (Rule rule) {
        LOGGER.debug("Deleting rule: {}.", rule);

        return repository.delete(rule);
    }
}
