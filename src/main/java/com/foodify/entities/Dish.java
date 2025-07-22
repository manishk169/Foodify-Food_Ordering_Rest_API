package com.foodify.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Dish {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_seq")
    @SequenceGenerator(name = "dish_seq", sequenceName = "dish_sequence", initialValue = 501, allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;
    
}
