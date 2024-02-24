package com.turingjavaee5batch.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.turingjavaee5batch.demo.services.AccountService;
import com.turingjavaee5batch.demo.services.AuthenticationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	AccountService accountService;

	@GetMapping
	public String login() {
		log.info("Login GET handler");
		return "login";
	}

	@PostMapping
	public String loginSubmit(@RequestParam String username, @RequestParam String password)
			throws AuthenticationException {
		log.info("Login Form Post    username : " + username + "   , Password  :" + password);

		try {
			boolean result = this.accountService.login(username, password);

		} catch (AuthenticationException ae) {
			return "redirect:/login";
		}

		return "redirect:/";
	}
}