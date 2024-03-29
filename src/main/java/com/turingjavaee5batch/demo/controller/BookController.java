package com.turingjavaee5batch.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.turingjavaee5batch.demo.aop.Log;
import com.turingjavaee5batch.demo.model.Book;
import com.turingjavaee5batch.demo.services.BookService;
import com.turingjavaee5batch.demo.services.exception.BusinessLogicException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes("cart")
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// binder.setValidator(new BookValidator());
	}

	@ModelAttribute
	void addEmptyBook(Model model) {
		log.info("Add Empty Book");
		List<String> category = new ArrayList<String>();
		category.add("Sci Fi");
		category.add("Art");
		category.add("Fiction");
		model.addAttribute("category", category);
	}

	@Log
	@GetMapping
	String getAllBook(Model model) {
		List<Book> books = this.bookService.getAllBooks();
		for (Book book : books) {
			log.info("Book  : " + book);
		}
		model.addAttribute("books", books);
		return "/books/book";
	}

	@GetMapping("/{id}")
	String getBookById(Model model, @PathVariable Long id, @ModelAttribute("category") List<String> category) {
		// List<Book> modelBooks = (ArrayList<Book>)model.getAttribute("books");
		log.info("Get book by Id size = " + category.size());
		Book book = this.bookService.getBookById(id);

		List<Book> books = new ArrayList<Book>();
		books.add(book);

		model.addAttribute("books", books);
		return "/books/book";
	}

	@GetMapping("/new")
	String bookForm(Model model) {
		Book book = new Book();
		// book.setId("one");
		model.addAttribute("book", book);
		return "/books/newBook";
	}

	@PostMapping("/new")
	String createBook(@Valid @ModelAttribute Book book, BindingResult result) {
		log.info("Create book " + book);
		if (result.hasErrors()) {
			log.info("Book have error ");
			return "/books/newBook";
		} else {
			this.bookService.save(book);
			return "redirect:/books/new";
		}

	}

	@GetMapping("/edit/{id}")
	String bookEditForm(Model model, @PathVariable Long id) {
		log.info("Edit book" + id);

		Book book = this.bookService.getBookById(id);
		model.addAttribute("book", book);
		return "/books/editBook";
	}

	@PostMapping("/edit/{id}")
	String bookEditFormSubmit(Model model, 
						@Valid @ModelAttribute Book book, BindingResult result) {
		log.info("Edit book post " + book.getId());

		if (result.hasErrors()) {
			log.info("Book have error ");
			model.addAttribute("book", book);
			return "/books/editBook";
		} else {
			this.bookService.update(book);
			return "redirect:/books";
		}
	}
	
	
	@GetMapping("/delete/{id}")
	String deleteBook(Model model,@PathVariable Long id)
	{
		log.info("Delete book" +id);
		try {
			this.bookService.deleteBookById(id);
		} catch (BusinessLogicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/books";
	}
	

	@GetMapping("/cart")
	String cartForm(Model model) {
		log.info("Cart form");
		List<String> cart = new ArrayList<String>();
		model.addAttribute("cart", cart);

		return "/books/cart";
	}

	@PostMapping("/cart")
	String cartFormSubmit(Model model, @ModelAttribute("cart") ArrayList<String> cart, @RequestParam String bookId) {
		log.info("Cart form submit bookId " + bookId);
		cart.add(bookId);
		// List<String> cart = new ArrayList<String>();
		// model.addAttribute("cart", cart);
		log.info("Cart Item " + cart.size());
		return "/books/cart";
	}

	@GetMapping("/error")
	String error(Model  model) throws AuthenticationException{
		
		throw new AuthenticationException("controller level exception ;");
	}
	@ExceptionHandler(AuthenticationException.class)
	public String authException() {
		
		log.info("Got Auth controller level exception");
		return "/error/403";
	}
	
}
