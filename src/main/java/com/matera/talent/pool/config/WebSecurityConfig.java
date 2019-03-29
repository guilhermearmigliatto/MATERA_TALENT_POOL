package com.matera.talent.pool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * This method configures rules of users
	 * For example name/password and the type of authentication
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// This is an example of design pattern called Builder.
		auth.inMemoryAuthentication()
	    .withUser("matera")
	    // Needs to put {noop} to work with passwords in memory
	    .password("{noop}matera")
	    .roles("USER");
	}
	
	/**
	 * This method configures the permissions of Http Methods and URIs.
	 * For example HttpMethod.DELETE of /employees needs a Basic Authentication.
	 * The user and password is matera/matera
	 * Needs to put in Header the Key "Authorization" and Value "Basic bWF0ZXJhOm1hdGVyYQ=="
	 */
	protected void configure(HttpSecurity http) throws Exception {
		// This is an example of design pattern called Builder.
		http.httpBasic()
			.and()
				.authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers(HttpMethod.POST, "/employees/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/employees/**").permitAll()
				.antMatchers(HttpMethod.GET, "/employees/**").permitAll()
				// DELET Method needs authentication
				.antMatchers(HttpMethod.DELETE, "/employees/**").authenticated()
			.and()
				// Disabling frameOptions for the h2-console run properly.
				.headers().frameOptions().disable()
			.and()
				.csrf().disable();
	}
	
}
