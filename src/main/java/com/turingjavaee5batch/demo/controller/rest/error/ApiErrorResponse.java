package com.turingjavaee5batch.demo.controller.rest.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

	String errorCode;
	String message;
	
}
