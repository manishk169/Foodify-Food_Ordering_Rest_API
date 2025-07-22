package com.foodify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodify.entities.Restaurant;
import com.foodify.responsewrapper.MyResponseWrapper;
import com.foodify.services.RestaurantService;

@RestController
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	MyResponseWrapper responseWrapper;
	
	@PostMapping("/restaurants/{ownerId}")
	public ResponseEntity<?> addRestaurant(@PathVariable Long ownerId, @RequestBody Restaurant restaurant)
	{
		return restaurantService.addRestaurant(ownerId, restaurant);
	}
	
	@GetMapping("/restaurants")
	public ResponseEntity<?> getAllRestaurants()
	{
		return restaurantService.getAllRestaurants();
	}

	
}
