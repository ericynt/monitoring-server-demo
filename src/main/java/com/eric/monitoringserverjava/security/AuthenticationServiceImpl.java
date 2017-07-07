package com.eric.monitoringserverjava.security;

import com.eric.monitoringserverjava.users.User;
import com.eric.monitoringserverjava.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.eric.monitoringserverjava.utils.JWTTokenUtil.createToken;

/**
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
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

		if (userRetrieved != null && userRetrieved.getPassword().equals(user.getPassword())) {
			String token = createToken(userRetrieved, tokenSecret);
			user.setToken(token);
			userService.updateUser(user).subscribe();

			return token;
		} else {
			return "";
		}
	}
}
