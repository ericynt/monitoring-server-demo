package com.eric.monitoringserverjava.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eric.monitoringserverjava.users.User;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.eric.monitoringserverjava.utils.DateUtil.toDate;

/**
 *
 */
public class JWTTokenUtil {
	public static final String ROLES = "roles";
	public static final String GUEST = "GUEST";

	public static String createToken (User user, String tokenSecret) {
		String token = null;
		try {
			String[] roleStrings = Arrays.stream(user.getRoles()).map(User.Role::toString).toArray(String[]::new);
			token = JWT.create()
			  .withSubject(user.getName())
			  .withExpiresAt(toDate(LocalDateTime.now().plusHours(2)))
			  .withArrayClaim(ROLES, roleStrings)
			  .sign(
				Algorithm.HMAC512(tokenSecret)
			  );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return token;
	}

	public static void verifyJWTToken (String tokenSecret, User user, String token) throws UnsupportedEncodingException {
		String[] roleStrings = getRoleStrings(user);
		JWTVerifier jwtVerifier;

		if (roleStrings.length == 1 && roleStrings[0].equals(GUEST)) {
			jwtVerifier = JWT
			  .require(Algorithm.HMAC512(tokenSecret))
			  .withSubject(user.getName())
			  .withArrayClaim(ROLES, roleStrings)
			  .acceptExpiresAt(60 * 60 * 22)
			  .build();
		} else {
			jwtVerifier = JWT
			  .require(Algorithm.HMAC512(tokenSecret))
			  .withSubject(user.getName())
			  .withArrayClaim(ROLES, roleStrings)
			  .build();
		}

		jwtVerifier.verify(token);
	}

	public static String getUserNameFromToken (String token, String tokenSecret) {
		String user = null;

		try {
			DecodedJWT decodedJWT = getDecodedJWT(token, tokenSecret);
			user = decodedJWT.getSubject();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return user;
	}

	public static String[] getRoleStrings (com.eric.monitoringserverjava.users.User retrievedUser) {
		return Arrays.stream(retrievedUser.getRoles()).map(
		  com.eric.monitoringserverjava.users.User.Role::toString
		).toArray(String[]::new);
	}

/*	public static String[] getRolesFromToken (String token, String tokenSecret) {
		String[] roles = null;

		try {
			DecodedJWT decodedJWT = getDecodedJWT(token, tokenSecret);
			roles = decodedJWT.getClaim(ROLES).asArray(String.class);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return roles;
	}*/

/*	public static boolean isExpired (String token, String tokenSecret) {
		DecodedJWT decodedJWT;
		Date expiresAt = null;

		try {
			decodedJWT = getDecodedJWT(token, tokenSecret);
			expiresAt = decodedJWT.getExpiresAt();

			if (expiresAt == null) {
				return true;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return toLocalDateTime(expiresAt).isBefore(LocalDateTime.now());
	}*/

	private static DecodedJWT getDecodedJWT (String token, String tokenSecret) throws UnsupportedEncodingException {
		return JWT.decode(token);
	}
}
