package com.eric.monitoringserverjava.users;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByName (String name);
}
