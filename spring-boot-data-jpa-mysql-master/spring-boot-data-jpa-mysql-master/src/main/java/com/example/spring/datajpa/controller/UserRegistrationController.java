package com.example.spring.datajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.IUserService;
import com.example.spring.datajpa.model.User;


@RestController
@RequestMapping("/api/users/registration")
public class UserRegistrationController {

	@Autowired
	IUserService impl;
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		System.out.println("user "+user);
		try {
			
			User _user = impl.createUser(user);
			
			return new ResponseEntity<User>(_user, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginCheck(@RequestParam String username, 
											@RequestParam String password) {
		
		System.out.println("user "+username+"   "+"Password "+password);
		try {
			
			String message = impl.login(username, password);
			
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
