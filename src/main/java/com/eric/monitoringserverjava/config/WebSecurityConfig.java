package com.eric.monitoringserverjava.config;

import com.eric.monitoringserverjava.security.CustomAuthenticationUserDetailsService;
import com.eric.monitoringserverjava.security.CustomPreAuthenticatedProcessingFilter;
import com.eric.monitoringserverjava.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import static com.eric.monitoringserverjava.users.User.Role.*;

@Configuration
@EnableWebSecurity
@ComponentScan("com.eric.monitoringserverjava")
// Enable pre / post security annotations
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    
    @Autowired
    public WebSecurityConfig (UserService userService) {
        this.userService = userService;
    }
    
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http
                .addFilterBefore(
                        customPreAuthenticatedProcessingFilter(),
                        AbstractPreAuthenticatedProcessingFilter.class
                )
                .authenticationProvider(preauthAuthProvider())
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").hasAnyRole(ADMIN.toString(), USER.toString(), GUEST.toString());
        
    }
    
    @Bean
    public CustomAuthenticationUserDetailsService customAuthenticationUserDetailsService () {
        return new CustomAuthenticationUserDetailsService(userService);
    }
    
    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(preauthAuthProvider());
    }
    
    @Bean
    public PreAuthenticatedAuthenticationProvider preauthAuthProvider () {
        PreAuthenticatedAuthenticationProvider preauthAuthProvider = new PreAuthenticatedAuthenticationProvider();
        preauthAuthProvider.setPreAuthenticatedUserDetailsService(customAuthenticationUserDetailsService());
        
        return preauthAuthProvider;
    }
    
    @Bean
    public CustomPreAuthenticatedProcessingFilter customPreAuthenticatedProcessingFilter () throws Exception {
        CustomPreAuthenticatedProcessingFilter filter = new CustomPreAuthenticatedProcessingFilter(userService);
        filter.setAuthenticationManager(authenticationManager());
        
        return filter;
    }
}