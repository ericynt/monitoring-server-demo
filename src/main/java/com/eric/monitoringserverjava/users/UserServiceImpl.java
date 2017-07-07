package com.eric.monitoringserverjava.users;

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
public class UserServiceImpl implements UserService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository repository;

	@Autowired
	public UserServiceImpl (UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<User> getUsers () {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieved users.");
		}

		return repository.findAll();
	}

	@Override
	public Mono<User> getUserById (Publisher<String> id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieved user by Id.");
		}

		return repository.findById(id);
	}

	@Override
	public User getUserByName (String name) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieved user by name.");
		}

		// TODO fix me
		return repository.findAll().filter(retrievedName -> retrievedName.getName().equals(name)).blockFirst();
	}

	@Override
	public Mono<User> createUser (User user) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created users: {}", user);
		}

		return repository.save(user);
	}

	@Override
	public Mono<User> updateUser (User user) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Updated users: {}", user);
		}

		return repository.save(user);
	}

	@Override
	public Mono<Void> deleteUser (User user) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleted users: {}", user);
		}

		return repository.delete(user);
	}
}
