package com.eric.monitoringserverjava.security;

import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@RequestMapping("api/")
@RestController
public class UserController {
	private UserService userService;

	public UserController (UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "users/", method = RequestMethod.GET)
	Flux<User> getUsers () {
		return userService.getUsers();
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.GET)
	Mono<User> getUserById (@PathVariable Publisher<String> id) {
		return userService.getUserById(id);
	}

	@RequestMapping(value = "users/", method = RequestMethod.POST)
	Mono<ResponseEntity<User>> createUser (User user) {
		return userService.createUser(user).map(
		  createdUser -> new ResponseEntity<>(createdUser, HttpStatus.CREATED)
		);
	}

	@RequestMapping(value = "users/", method = RequestMethod.PUT)
	Mono<ResponseEntity<User>> updateUser (User user) {
		return userService.updateUser(user).map(
		  createdUser -> new ResponseEntity<>(createdUser, HttpStatus.OK)
		).defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@RequestMapping(value = "users/", method = RequestMethod.DELETE)
	Mono<Void> deleteUser (User user) {
		return userService.deleteUser(user);
	}
}
