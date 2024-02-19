package com.turingjavaee5batch.demo.services.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleBean {

	public ExampleBean() {
		log.info("ExampleBean created...");
	}

	@PostConstruct
	public void init() {
		log.info("ExampleBean  is initialized");
	}

	@PreDestroy
	public void destroy() {
		log.info("ExampleBean  is destroy");
	}

	public void api() {
		log.info("API method from example custom bean");
	}
}
