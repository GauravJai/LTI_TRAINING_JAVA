package com.lti.gateway.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lti.gateway.client.UserProxy;
import com.lti.gateway.response.UserResponse;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserProxy userProxy;
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserResponse user = userProxy.findUserDetail(email).getBody();
		if (user != null) {
			List<GrantedAuthority> authorities = getUserAuthority(user.getRole());
			return buildUserForAuthentication(user, authorities);
		} else {
			throw new UsernameNotFoundException("username not found");
		}
	}
	
	private List<GrantedAuthority> getUserAuthority(String userRole) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userRole));
		
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(UserResponse user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

	/*
	 * private List<GrantedAuthority> getUserAuthority(Set<String> userRoles) {
	 * Set<GrantedAuthority> roles = new HashSet<>(); userRoles.forEach((role) -> {
	 * roles.add(new SimpleGrantedAuthority(role)); });
	 * 
	 * List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles); return
	 * grantedAuthorities; }
	 * 
	 * private UserDetails buildUserForAuthentication(UserResponse user,
	 * List<GrantedAuthority> authorities) { return new
	 * org.springframework.security.core.userdetails.User(user.getEmail(),
	 * user.getPassword(), authorities); }
	 */

}