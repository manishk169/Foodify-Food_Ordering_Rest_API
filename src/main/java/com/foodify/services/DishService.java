package com.foodify.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.foodify.entities.Dish;
import com.foodify.entities.Restaurant;
import com.foodify.repositories.DishRepository;
import com.foodify.repositories.RestaurantRepository;
import com.foodify.responsewrapper.MyResponseWrapper;

@Service
public class DishService {


	@Autowired
	DishRepository dishRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	MyResponseWrapper responseWrapper;

 
	
	public ResponseEntity<?> addDish(Long restaurantId ,Dish dish)
	{
	   Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
	
	   if(restaurant.isPresent())
	   {
		   dish.setRestaurant(restaurant.get());
		   restaurant.get().getDishes().add(dish); //adding dish to restaurant's dishes list
		   Dish savedDish = dishRepository.save(dish);
		   responseWrapper.setMessage("Following dish added to Restaurant with id : " + restaurantId);
		   responseWrapper.setData(savedDish);
		   return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
	   }
	   else {
		   responseWrapper.setMessage("No such restaurant Found");
		   responseWrapper.setData(null);
		   return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
	   }
	}
}
