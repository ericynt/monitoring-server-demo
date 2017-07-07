package com.eric.monitoringserverjava.security;

import com.eric.monitoringserverjava.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;

import static com.eric.monitoringserverjava.utils.JWTTokenUtil.getRoleStrings;

/**
 *
 */
public class CustomAuthenticationUserDetailsService implements AuthenticationUserDetailsService {
	UserService userService;

	@Value("${jwt.token.secret}")
	String tokenSecret;

	@Autowired
	public CustomAuthenticationUserDetailsService (UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserDetails (Authentication authentication) throws UsernameNotFoundException {
		Object principal = authentication.getPrincipal();
		com.eric.monitoringserverjava.users.User retrievedUser = userService.getUserByName(principal.toString());

		if (retrievedUser == null) {
			throw new UsernameNotFoundException("User not found.");
		}

		List<GrantedAuthority> grantedAuthorities = new SimpleAttributes2GrantedAuthoritiesMapper()
		  .getGrantedAuthorities(Arrays.asList(getRoleStrings(retrievedUser)));

		return new User(principal.toString(), "", grantedAuthorities);
	}
}
