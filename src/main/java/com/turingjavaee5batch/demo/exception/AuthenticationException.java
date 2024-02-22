package com.turingjavaee5batch.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AuthenticationException extends Exception {
	public AuthenticationException(String message)
	{
		super(message);
	}
}
