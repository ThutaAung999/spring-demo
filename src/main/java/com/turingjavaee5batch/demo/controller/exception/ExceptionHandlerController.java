package com.turingjavaee5batch.demo.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.turingjavaee5batch.demo.exception.AuthenticationException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(AuthenticationException.class)
	public String defaultErrorHandler(HttpServletRequest req, Exception e) 
				throws Exception {
		log.info("Handle by gloabl auth exception");
		return "/error/403";
	}
	
}
