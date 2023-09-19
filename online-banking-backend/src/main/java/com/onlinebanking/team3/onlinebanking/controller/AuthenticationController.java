package com.onlinebanking.team3.onlinebanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.onlinebanking.team3.onlinebanking.config.JwtUtil;
import com.onlinebanking.team3.onlinebanking.model.AuthenticationRequest;
import com.onlinebanking.team3.onlinebanking.model.AuthenticationResponse;
import com.onlinebanking.team3.onlinebanking.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

 @Autowired
 private AuthenticationManager authenticationManager;

 @Autowired
 private UserService userService;

 @Autowired
 private JwtUtil jwtUtil;

 @PostMapping("/login")
 public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
     final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getPhoneNumber());
     authenticate(authenticationRequest.getPhoneNumber(), authenticationRequest.getPassword());

     final String token = jwtUtil.generateToken(userDetails.getUsername());
     return ResponseEntity.ok(new AuthenticationResponse(token));
 }

 private void authenticate(String phoneNumber, String password) throws Exception {
     try {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, password));
     } catch (Exception e) {
         throw new Exception("Incorrect username or password", e);
     }
 }
}
