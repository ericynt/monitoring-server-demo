package com.eric.monitoringserverjava.security;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface UserService {
	Flux<User> getUsers ();

	Mono<User> getUserById (Publisher<String> id);

	Mono<User> createUser (User user);

	Mono<User> updateUser (User user);

	Mono<Void> deleteUser (User user);
}
