package com.lti.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lti.users.entity.User;
import com.lti.users.repository.UserRepository;
import com.lti.users.request.UserRequest;
import com.lti.users.response.UserResponse;

@Service
public class UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	/*
	 * @Autowired private RoleRepository roleRepository;
	 */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserResponse saveUser(UserRequest userRequest) {
		User user = new User();
		user.setEmail(userRequest.getEmail());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		user.setEnabled(true);
		user.setRole(userRequest.getRole());
		userRepository.save(user);
		return user != null ? buildUserResponse(user) : null;
	}

	public UserResponse findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user != null ? buildUserResponse(user) : null;
	}

	private UserResponse buildUserResponse(User user) {
		return new UserResponse(user.getFirstName() + " " + user.getLastName(), user.getEmail(), user.getPassword(),
				user.getRole());
		// user.getRoles().stream().map(role ->
		// role.getRole()).collect(Collectors.toSet()));
	}

}