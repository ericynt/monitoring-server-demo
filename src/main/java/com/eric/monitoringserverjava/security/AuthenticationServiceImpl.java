package com.eric.monitoringserverjava.security;

import com.eric.monitoringserverjava.users.User;
import com.eric.monitoringserverjava.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.eric.monitoringserverjava.utils.JWTTokenUtil.createToken;

/**
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	private UserService userService;

	@Value("${jwt.token.secret}")
	String tokenSecret;

	@Autowired
	public AuthenticationServiceImpl (UserService userService) {
		this.userService = userService;
	}

	@Override
	public String authenticate (User user) {
		User userRetrieved = userService.getUserByName(user.getName());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieved user: {}.", userRetrieved);
		}

		if (userRetrieved != null && userRetrieved.getPassword().equals(user.getPassword())) {
			String token = createToken(userRetrieved, tokenSecret);
			userRetrieved.setToken(token);
			userService.updateUser(userRetrieved).subscribe();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Created token: {}.", token);
			}

			return token;
		} else {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("Failed to create token.");
			}

			return "";
		}
	}
}
