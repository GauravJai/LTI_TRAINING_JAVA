package com.lti.users.request;

import lombok.Data;

@Data
public class UserRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;

}
