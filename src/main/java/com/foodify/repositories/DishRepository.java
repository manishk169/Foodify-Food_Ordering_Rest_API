package com.foodify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodify.entities.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

}
