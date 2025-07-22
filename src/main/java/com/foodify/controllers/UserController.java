package com.foodify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodify.entities.User;
import com.foodify.responsewrapper.MyResponseWrapper;
import com.foodify.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	MyResponseWrapper responseWrapper;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user)
	{
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		return userService.login(user);
	}

}
