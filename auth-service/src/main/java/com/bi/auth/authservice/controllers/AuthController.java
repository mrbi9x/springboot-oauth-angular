package com.bi.auth.authservice.controllers;

import javax.validation.Valid;

import com.bi.auth.authservice.entities.UserEntity;
import com.bi.auth.authservice.payloads.ApiResponse;
import com.bi.auth.authservice.payloads.JwtAuthenticationResponse;
import com.bi.auth.authservice.payloads.SigninRequest;
import com.bi.auth.authservice.payloads.SignupRequest;
import com.bi.auth.authservice.securities.JwtConfig;
import com.bi.auth.authservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtConfig jwtConfig;

	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest signinRequest) {
		String token = userService.loginUser(signinRequest.getUsername(), signinRequest.getPassword());
		return ResponseEntity
				.ok(JwtAuthenticationResponse.builder().accessToken(token).tokenType(jwtConfig.getPrefix()).build());
	}

	@PostMapping("/auth/signup")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
		UserEntity newUser = new UserEntity();
		newUser.setUsername(signupRequest.getUsername());
		newUser.setEmail(signupRequest.getEmail());
		newUser.setPassword(signupRequest.getPassword());
		newUser = userService.createUser(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().success(true).message("Created user success.").build());
	}

}
