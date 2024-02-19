package com.turingjavaee5batch.demo.services.impl;

import org.springframework.stereotype.Service;

import com.turingjavaee5batch.demo.services.AccountService;
import com.turingjavaee5batch.demo.services.AuthenticationException;

@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public boolean login(String username, String password) throws AuthenticationException {

		if ("admin".equals(username) && "admin".equals(password)) {
			return true;
		} else {
			throw new AuthenticationException("Valid username and password");
		}
	}

}
