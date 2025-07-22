package com.foodify.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodify.entities.Dish;
import com.foodify.entities.Order;
import com.foodify.entities.OrderItem;
import com.foodify.entities.User;
import com.foodify.repositories.DishRepository;
import com.foodify.repositories.OrderRepository;
import com.foodify.repositories.UserRepository;
import com.foodify.responsewrapper.MyResponseWrapper;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	MyResponseWrapper responseWrapper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DishRepository dishRepository;

	public ResponseEntity<?> placeOrder(long customerId, Order order) {
		Optional<User> customer = userRepository.findById(customerId);

		if (customer.isPresent() && customer.get().getRole().equalsIgnoreCase("CUSTOMER")) {
			double totalPrice = 0.0;

			for (OrderItem item : order.getItems()) {
				Dish dish = dishRepository.findById(item.getDish().getId())
						.orElseThrow(() -> new RuntimeException("Dish not found"));

				item.setDish(dish);
				item.setItemPrice(dish.getPrice());
				item.setOrder(order);
				totalPrice += item.getItemPrice() * item.getQuantity();
			}

			order.setCustomer(customer.get());
			order.setTotalPrice(totalPrice);
			order.setStatus("PENDING");

			orderRepository.save(order);

			responseWrapper.setMessage("Order Placed");
			responseWrapper.setData(order);
			return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
		} else {
			responseWrapper.setMessage("Order Failed! Only customers can place order");
			responseWrapper.setData(null);
			return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
		}
	}
}
