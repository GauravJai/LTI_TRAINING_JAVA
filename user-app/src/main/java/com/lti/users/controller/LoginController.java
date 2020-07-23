package com.lti.users.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.lti.users.request.UserRequest;
import com.lti.users.response.UserResponse;
import com.lti.users.service.UserDetailsService;

@RestController
@RequestMapping("user")
public class LoginController {
	@Autowired
	private UserDetailsService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> createNewUser(@RequestBody @Valid UserRequest userRequest) {
		UserResponse userResponse = null;
		if (Strings.isNullOrEmpty(userRequest.getEmail())
				|| userService.findUserByEmail(userRequest.getEmail()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else {
			userResponse = userService.saveUser(userRequest);
		}
		return userResponse != null ? new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED)
				: new ResponseEntity<UserResponse>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/login/{userId}")
	public ResponseEntity<UserResponse> login(@PathVariable("userId") String userId) {
		UserResponse userResponse = this.userService.findUserByEmail(userId);
		return userResponse != null ? new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK)
				: new ResponseEntity<UserResponse>(userResponse, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/find/{emailId}")
	public ResponseEntity<UserResponse> findUserDetail(@PathVariable("emailId") String emailId) {
		System.out.println("***findUserDetail***");
		UserResponse userResponse = this.userService.findUserByEmail(emailId);
		return userResponse != null ? new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK)
				: new ResponseEntity<UserResponse>(userResponse, HttpStatus.NOT_FOUND);
	}

}