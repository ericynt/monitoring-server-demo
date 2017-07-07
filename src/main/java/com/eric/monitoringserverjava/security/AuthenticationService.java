package com.eric.monitoringserverjava.security;

import com.eric.monitoringserverjava.users.User;

/**
 *
 */
public interface AuthenticationService {
	String authenticate (User user);
}
