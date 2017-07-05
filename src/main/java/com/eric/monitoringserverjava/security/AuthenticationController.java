package com.eric.monitoringserverjava.security;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class AuthenticationController {
	@RequestMapping(value = "/authentications", method = RequestMethod.POST)
	public String authenticate (@RequestBody User user) {
		return null;
	}
}
