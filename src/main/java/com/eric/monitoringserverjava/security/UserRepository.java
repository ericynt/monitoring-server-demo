package com.eric.monitoringserverjava.security;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 *
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {

}
