package com.turingjavaee5batch.demo.controller.rest.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

	String errorCode;
	String message;
	
}
