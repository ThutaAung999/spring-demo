package com.turingjavaee5batch.demo.services.impl;

import com.turingjavaee5batch.demo.services.PrototypeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrototypeBean implements PrototypeService {

	public PrototypeBean() {
		log.info("Prototype bean  created :" + this);
	}

	@Override
	public void api() {
		log.info("API called.");

	}
}
