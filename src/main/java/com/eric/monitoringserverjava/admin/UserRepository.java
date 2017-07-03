package com.eric.monitoringserverjava.admin;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 *
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {

}
