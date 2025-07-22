package com.foodify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodify.entities.Restaurant;
import com.foodify.entities.User;
import com.foodify.repositories.RestaurantRepository;
import com.foodify.repositories.UserRepository;
import com.foodify.responsewrapper.MyResponseWrapper;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MyResponseWrapper responseWrapper;
	
	public ResponseEntity<?> addRestaurant(Long ownerId, Restaurant restaurant)
	{
        Optional<User> owner = userRepository.findById(ownerId);
       
        if(owner.isPresent())
        {
        	restaurant.setOwner(owner.get());
        	Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        	responseWrapper.setMessage("Following Restaurant Created");
        	responseWrapper.setData(savedRestaurant);
        	return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
        }
        else {
        	responseWrapper.setMessage("Owner not Found");
        	responseWrapper.setData(null);
        	return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
        }
	}
	
	
	
	public ResponseEntity<?> getAllRestaurants()
	{
		List<Restaurant> allRestaurants =  restaurantRepository.findAll();
		if(allRestaurants.isEmpty())
		{
			responseWrapper.setMessage("No Restaurants exists");
			responseWrapper.setData(null);
			return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND	);
		}
		else {
			responseWrapper.setMessage("Following Restaurants Found");
			responseWrapper.setData(allRestaurants);
			return new ResponseEntity<>(responseWrapper, HttpStatus.FOUND);
		}
	}
}
