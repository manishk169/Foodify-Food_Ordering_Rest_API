package com.foodify.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodify.entities.User;
import com.foodify.repositories.UserRepository;
import com.foodify.responsewrapper.MyResponseWrapper;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MyResponseWrapper responseWrapper;

	public ResponseEntity<?> register(User user) {

		Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser.isPresent()) {
			responseWrapper.setMessage("User Already Exists!");
			responseWrapper.setData(null);
			return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
		} else {
			User savedUser = userRepository.save(user);
			responseWrapper.setMessage("User Registered!");
			responseWrapper.setData(savedUser);
			return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<?> login(User user) {
		//Optional<User> existingUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
	    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
	    
	   
	    
		if (existingUser.isPresent()) {
			
			if(existingUser.get().getPassword().equals(user.getPassword()))
			{
				responseWrapper.setData(existingUser.get());
				responseWrapper.setMessage("Logged In!");
				return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
			}
			else {
				responseWrapper.setData(null);
				responseWrapper.setMessage("Invalid Password");
				return new ResponseEntity<>(responseWrapper, HttpStatus.UNAUTHORIZED);
			}
			
		} else {
			responseWrapper.setData(null);
			responseWrapper.setMessage("User doesn't Exists!");
			return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
		}
	}
}
