package com.eric.monitoringserverjava.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eric.monitoringserverjava.users.User;
import com.eric.monitoringserverjava.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Optional;

import static com.eric.monitoringserverjava.utils.JWTTokenUtil.getUserNameFromToken;
import static com.eric.monitoringserverjava.utils.JWTTokenUtil.verifyJWTToken;

/**
 *
 */
public class CustomPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
	private UserService userService;

	private static final String AUTHORIZATION = "Authorization";

	@Value("${jwt.token.secret}")
	String tokenSecret;

	@Autowired
	public CustomPreAuthenticatedProcessingFilter (UserService userService) {
		this.userService = userService;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal (HttpServletRequest httpServletRequest) {
		return Optional.ofNullable(httpServletRequest.getHeader(AUTHORIZATION))
		  .map(
			(header) -> {
				String[] parts = header.split(" ");

				if (parts.length > 1) {
					String token = String.join("", Arrays.copyOfRange(parts, 1, parts.length));
					String userName =  getUserNameFromToken(token, tokenSecret);
					User user = userService.getUserByName(userName);

					try {
						verifyJWTToken(tokenSecret, user, token);
					} catch (UnsupportedEncodingException | JWTVerificationException e) {
						e.printStackTrace();

						return null;
					}

					return  userName;
				} else {
					return null;
				}
			}
		  ).orElse(null);
	}

	@Override
	protected Object getPreAuthenticatedCredentials (HttpServletRequest httpServletRequest) {
		return "";
	}
}
