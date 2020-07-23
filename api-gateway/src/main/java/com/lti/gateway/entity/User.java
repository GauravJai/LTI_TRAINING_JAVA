package com.lti.gateway.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
	//@DBRef
	private String role;
	
	/*
	 * public void addRole(Role role){ if(this.roles==null) this.roles = new
	 * HashSet<Role>(); this.roles.add(role); }
	 */

}