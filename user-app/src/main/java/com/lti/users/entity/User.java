package com.lti.users.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private String id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private String role;
	/*
	 * @DBRef private Role role;
	 */

}