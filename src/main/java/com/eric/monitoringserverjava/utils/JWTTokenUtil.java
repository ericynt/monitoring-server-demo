package com.eric.monitoringserverjava.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eric.monitoringserverjava.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.eric.monitoringserverjava.utils.DateUtil.toDate;

/**
 *
 */
public class JWTTokenUtil {
	private static Logger LOGGER = LoggerFactory.getLogger(JWTTokenUtil.class);

	private static final String ROLES = "roles";
	private static final String GUEST = "GUEST";
	private static final int HOURS = 2;

	public static String createToken (User user, String tokenSecret) {
		String token = null;
		try {
			String[] roleStrings = Arrays.stream(user.getRoles()).map(User.Role::toString).toArray(String[]::new);
			token = JWT.create()
			  .withSubject(user.getName())
			  .withExpiresAt(toDate(LocalDateTime.now().plusHours(HOURS)))
			  .withArrayClaim(ROLES, roleStrings)
			  .sign(
				Algorithm.HMAC512(tokenSecret)
			  );

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Created token {} for user {}", user, token);
			}
		} catch (UnsupportedEncodingException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("UnsupportedEncoding used while trying to create token for user {}", user);
			}
		}

		return token;
	}

	public static void verifyJWTToken (String tokenSecret, User user, String token) throws UnsupportedEncodingException {
		String[] roleStrings = getRoleStrings(user);
		JWTVerifier jwtVerifier;

		if (roleStrings.length == 1 && roleStrings[0].equals(GUEST)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Verifying token {}, for user {}, with {} role.", token, user, GUEST);
			}

			jwtVerifier = JWT
			  .require(Algorithm.HMAC512(tokenSecret))
			  .withSubject(user.getName())
			  .withArrayClaim(ROLES, roleStrings)
			  .acceptExpiresAt(60 * 60 * 22)
			  .build();
		} else {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Verifying token {} for user {}", token, user);
			}

			jwtVerifier = JWT
			  .require(Algorithm.HMAC512(tokenSecret))
			  .withSubject(user.getName())
			  .withArrayClaim(ROLES, roleStrings)
			  .build();
		}

		jwtVerifier.verify(token);
	}

	public static String getUserNameFromToken (String token) {
		String userName;

		DecodedJWT decodedJWT = getDecodedJWT(token);
		userName = decodedJWT.getSubject();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieved username {} from token {}", userName, token);
		}

		return userName;
	}

	public static String[] getRoleStrings (com.eric.monitoringserverjava.users.User retrievedUser) {
		return Arrays.stream(retrievedUser.getRoles()).map(
		  com.eric.monitoringserverjava.users.User.Role::toString
		).toArray(String[]::new);
	}

	private static DecodedJWT getDecodedJWT (String token) {
		return JWT.decode(token);
	}
}
