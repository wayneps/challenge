package com.enrollment.enrolleetracker.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author waynes
 *
 *       Purpose:  Provides role based global security. 
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// NoOpPasswordEncoder is used for demonstration only. Ii is not secure. 
	// Use DelegatingPasswordEncoder in a production environment  
	@SuppressWarnings("deprecation")
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("user1").password("password1")
				.roles("USER").and().withUser("admin1").password("password1")
				.roles("USER", "ADMIN");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/enrollees/**")
				.hasRole("USER").antMatchers("/users/**").hasRole("USER").antMatchers("/dependents/**")
				.hasRole("USER").antMatchers("/users/**").hasRole("USER")
				.antMatchers("/**").hasRole("ADMIN").and().csrf().disable()
				.headers().frameOptions().disable();
	}

}
