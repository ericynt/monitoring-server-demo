package com.eric.monitoringserverjava.users;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 *
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {
}
