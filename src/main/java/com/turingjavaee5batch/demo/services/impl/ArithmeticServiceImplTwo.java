package com.turingjavaee5batch.demo.services.impl;

import org.springframework.stereotype.Service;

import com.turingjavaee5batch.demo.services.ArithmeticService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//@Qualifier("arithTwo")
public class ArithmeticServiceImplTwo implements ArithmeticService {

	public ArithmeticServiceImplTwo() {
		log.info("Arithmetic  Service  impl Two  created");
	}

	@Override
	public int add(int a, int b) {
		log.info("using the version Two");
		return a + b;
	}
}
