package com.foodify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodify.entities.Dish;
import com.foodify.responsewrapper.MyResponseWrapper;
import com.foodify.services.DishService;

@RestController
public class DishController {

	@Autowired
	DishService dishService;
	
	@Autowired
	MyResponseWrapper responseWrapper;
	
	
	@PostMapping("/restaurants/{restaurantId}/add-dish")
	public ResponseEntity<?> addDish(@PathVariable Long restaurantId, @RequestBody Dish dish)
	{
		return dishService.addDish(restaurantId, dish);
	}
	
	
}
