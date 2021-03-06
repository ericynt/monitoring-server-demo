package com.eric.monitoringserverjava.security;

import com.eric.monitoringserverjava.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Arrays;
import java.util.List;

import static com.eric.monitoringserverjava.utils.JWTTokenUtil.getRoleStrings;

/**
 *
 */
public class CustomAuthenticationUserDetailsService implements
        AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationUserDetailsService.class);

    private UserService userService;

    @Value("${jwt.token.secret}")
    String tokenSecret;

    @Autowired
    public CustomAuthenticationUserDetailsService (UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserDetails (PreAuthenticatedAuthenticationToken authentication) throws
            UsernameNotFoundException {
        Object principal = authentication.getPrincipal();
        com.eric.monitoringserverjava.users.User retrievedUser = userService.getUserByName(principal.toString());

        if (retrievedUser == null) {
            LOGGER.error("No corresponding user found in db for principal {}.", principal.toString());

            throw new UsernameNotFoundException("User not found.");
        }

        String[] roleStrings = getRoleStrings(retrievedUser);
        List<GrantedAuthority> grantedAuthorities = new SimpleAttributes2GrantedAuthoritiesMapper()
                .getGrantedAuthorities(Arrays.asList(roleStrings));

        LOGGER.debug("Adding roles {} to user details.", String.join(", ", roleStrings));

        return new User(principal.toString(), "", grantedAuthorities);
    }
}
