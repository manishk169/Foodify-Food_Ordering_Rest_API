package com.foodify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodify.entities.Payment;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Long>{

}

