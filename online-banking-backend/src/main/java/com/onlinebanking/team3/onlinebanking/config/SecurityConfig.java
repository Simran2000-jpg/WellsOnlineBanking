package com.onlinebanking.team3.onlinebanking.config;

//SecurityConfig.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

 @Autowired
 private JwtRequestFilter jwtRequestFilter;

 @Autowired
 private UserDetailsService userDetailsService;

 @Autowired
 private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 }

 protected void configure(HttpSecurity http) throws Exception {
     http.csrf().disable()
         .authorizeRequests()
         .requestMatchers("/api/auth/login").permitAll()
         .anyRequest().authenticated()
         .and()
         .exceptionHandling()
         .authenticationEntryPoint(customAuthenticationEntryPoint);

     http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
     return new BCryptPasswordEncoder();
 }


 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
         throws Exception {
     return authenticationConfiguration.getAuthenticationManager();
 }

}

