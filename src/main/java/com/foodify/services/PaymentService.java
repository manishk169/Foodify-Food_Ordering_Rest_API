package com.foodify.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodify.entities.Order;
import com.foodify.entities.Payment;
import com.foodify.repositories.OrderRepository;
import com.foodify.repositories.PaymentRepository;
import com.foodify.responsewrapper.MyResponseWrapper;

@Service
public class PaymentService {


	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	MyResponseWrapper responseWrapper;
	
	@Autowired
	OrderRepository orderRepository;

	
	
	public ResponseEntity<?> createPayment(long orderId, String method)
	{
		Optional<Order> order = orderRepository.findById(orderId);
		
		if(order.isPresent())
		{
			Payment payment = new Payment();
			payment.setOrder(order.get());
			payment.setStatus("PENDING");
			payment.setMethod(method);
			payment.setAmount(order.get().getTotalPrice());
			
			Payment savedPayment = paymentRepository.save(payment);
			
			responseWrapper.setMessage("Payment created! awaiting confirmation");
			responseWrapper.setData(savedPayment);
			return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
			
		}
		else {
			responseWrapper.setMessage("Order not Found!");
			responseWrapper.setData(null);
			return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	public ResponseEntity<?> updatePaymentStatus(long paymentId, String status)
	{
		Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
		
		if(paymentOpt.isPresent())
		{
			Payment payment = paymentOpt.get();
			payment.setStatus(status);
			paymentRepository.save(payment);
			
			if(payment.getStatus().equalsIgnoreCase("PAID"))
			{
				payment.getOrder().setStatus("ACCEPTED");
			}
			else {
				payment.getOrder().setStatus("REJECTED");
			}
			
			orderRepository.save(payment.getOrder());
			 
		   responseWrapper.setMessage("Payment Status Updated to " + status);
		   responseWrapper.setData(payment);
		   return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
		}
		else {
			responseWrapper.setData(null);
            responseWrapper.setMessage("Payment not found");
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
		}
	}
}
