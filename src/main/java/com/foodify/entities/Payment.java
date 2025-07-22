package com.foodify.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_sequence", initialValue = 5001, allocationSize = 1)
    private Long id;

    @OneToOne
    private Order order;

    private String status; // PENDING, PAID, FAILED
    private String method;
    private Double amount;

}
