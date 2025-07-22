package com.foodify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodify.responsewrapper.MyResponseWrapper;
import com.foodify.services.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	MyResponseWrapper responseWrapper;
	
	
	@PostMapping("/payment/create/{orderId}")
	public ResponseEntity<?> createPayment(@PathVariable long orderId, @RequestParam String method)
	{
		return paymentService.createPayment(orderId, method);
	}
	
	
	@PutMapping("/payment/update/{paymentId}")
	public ResponseEntity<?> updatePaymentStatus(@PathVariable long paymentId, @RequestParam String status)
	{
		return paymentService.updatePaymentStatus(paymentId, status);
	}

}
