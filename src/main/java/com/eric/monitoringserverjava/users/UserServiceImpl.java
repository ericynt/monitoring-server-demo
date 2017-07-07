package com.eric.monitoringserverjava.users;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private UserRepository repository;

	@Autowired
	public UserServiceImpl (UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<User> getUsers () {
		return repository.findAll();
	}

	@Override
	public Mono<User> getUserById (Publisher<String> id) {
		return repository.findById(id);
	}

	@Override
	public User getUserByName (String name) {
		// TODO fix me
		return repository.findAll().filter(retrievedName -> retrievedName.getName().equals(name)).blockFirst();
	}

	@Override
	public Mono<User> createUser (User user) {
		return repository.save(user);
	}

	@Override
	public Mono<User> updateUser (User user) {
		return repository.save(user);
	}

	@Override
	public Mono<Void> deleteUser (User user) {
		return repository.delete(user);
	}
}
