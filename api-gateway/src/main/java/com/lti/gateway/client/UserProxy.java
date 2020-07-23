package com.lti.gateway.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lti.gateway.response.UserResponse;

@FeignClient(name = "USER-APP")
@RibbonClient(name = "USER-APP")
public interface UserProxy {
	@GetMapping("user/find/{emailId}")
	public ResponseEntity<UserResponse> findUserDetail(@PathVariable("emailId") String emailId);

}
