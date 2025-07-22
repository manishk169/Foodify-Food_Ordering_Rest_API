package com.foodify.responsewrapper;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MyResponseWrapper {

	private Object data;
	private String message;
}
