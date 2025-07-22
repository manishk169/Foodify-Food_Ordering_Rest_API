package com.foodify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FoodifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodifyApplication.class, args);
	}

}
