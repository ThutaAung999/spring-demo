package com.turingjavaee5batch.demo.controller;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Iterator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.turingjavaee5batch.demo.model.Book;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mvc")
public class MvcController {

	@GetMapping("books/api/1")
	@ResponseBody
	Book getBook() {
		return new Book(1L, "Title_1", "Auther_1");
	}

	@GetMapping("books/api/2")
	ResponseEntity<Book> getBook2() {
		return ResponseEntity.ok(new Book(2L, "Title_2", "Auther_2"));
	}

	@GetMapping("/books/request")
	String servletRequest(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

		Enumeration<String> headers = servletRequest.getHeaderNames();
		Iterator<String> iterator = headers.asIterator();
		while (iterator.hasNext()) {
			String header = iterator.next();
			log.info("Header :" + header + " =>" + servletRequest.getHeader(header));
		}

		log.info("Path  URI: " + servletRequest.getRequestURI());
		log.info("Path  URL: " + servletRequest.getRequestURL());

		// servletResponse.setContentType("text/plain");
		servletResponse.addHeader("Authorization", "key");

		return "hello";// view name
	}

	@GetMapping("/books/session")
	String session(HttpSession session, Principal user, @CookieValue("JSESSIONID") String sessionId,
			@RequestHeader("Accept-Encoding") String encoding) {

		log.info("Session ID : " + sessionId);
		log.info("Authenticated user : " + user);
		log.info("Accept-Encoding  :  " + encoding);
		session.setAttribute("message", "Put  By session");
		return "hello";// view name
	}

	@GetMapping("/books/getSession")
	String getSession(Model model, @SessionAttribute String message, HttpSession session) {

		log.info("SessionAttribute :" + message);
		model.addAttribute("message", session.getAttribute("message"));
		return "hello";// view name
	}

	@GetMapping("/books/{id}")
	String bookById(Model model, @PathVariable String id) {

		model.addAttribute("message", " Book id :" + id);
		return "hello";// view name
	}

	@GetMapping("/books/search")
	String bookSearch(Model model, @RequestParam(name = "title", required = false, defaultValue = "") String title) {

		log.info("book search  controller  title :" + title);
		model.addAttribute("message", " Book title:" + title);
		return "hello";// view name
	}

	@GetMapping("/hello")
	String hello(Model model) {
		log.info("GET hello controller");
		model.addAttribute("message", "Hello , Welcome  from Spring Model");
		return "hello";// view name
	}

	@PostMapping("hello")
	String helloPost(Model model) {
		log.info("POST hello controller");
		model.addAttribute("message", "Hello , Welcome  from Spring Model");
		return "hello";// view name
	}

}
