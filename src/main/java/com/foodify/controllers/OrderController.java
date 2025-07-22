package com.foodify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodify.entities.Order;
import com.foodify.responsewrapper.MyResponseWrapper;
import com.foodify.services.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	MyResponseWrapper responseWrapper;
	
	
	@PostMapping("/orders/place/{customerId}")
	public ResponseEntity<?> placeOrder(@PathVariable long customerId, @RequestBody Order order)
	{
		return orderService.placeOrder(customerId, order);
	}
	
	

}
