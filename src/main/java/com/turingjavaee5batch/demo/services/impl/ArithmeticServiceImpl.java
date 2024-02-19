package com.turingjavaee5batch.demo.services.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.turingjavaee5batch.demo.services.ArithmeticService;

import lombok.extern.slf4j.Slf4j;

@Primary
@Slf4j
@Service
public class ArithmeticServiceImpl implements ArithmeticService {

	public ArithmeticServiceImpl() {
		log.info("Arithmetic  Service  impl  created");
	}

	@Override
	public int add(int a, int b) {
		return a + b;
	}
}
