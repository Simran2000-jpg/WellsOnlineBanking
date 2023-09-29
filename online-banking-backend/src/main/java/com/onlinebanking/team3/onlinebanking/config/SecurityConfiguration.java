package com.onlinebanking.team3.onlinebanking.config;

import com.onlinebanking.team3.onlinebanking.filter.JWTAuthenticationFilter;
import com.onlinebanking.team3.onlinebanking.filter.JWTAuthorizationFilter;
import com.onlinebanking.team3.onlinebanking.service.AuthenticationUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final AuthenticationUserDetailService authenticationUserDetailService;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		return auth.userDetailsService(authenticationUserDetailService).passwordEncoder(bCryptPasswordEncoder).and().build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
						auth -> auth.
								requestMatchers(HttpMethod.POST, AuthenticationConfigConstants.CREATE_USER).permitAll()
								.anyRequest().authenticated()
				)
				.addFilter(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationManagerBuilder.class))))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(http.getSharedObject(AuthenticationManagerBuilder.class))))
				// this disables session creation on Spring Security
				.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
}

