package com.lti.users.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
	private String username;
    private String email;
    private String password;
    private String role;
   // private String registrationConfirmationToken;

}
