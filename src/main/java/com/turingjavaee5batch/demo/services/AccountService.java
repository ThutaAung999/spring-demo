package com.turingjavaee5batch.demo.services;

public interface AccountService {
	boolean login(String username, String password) throws AuthenticationException;
}
