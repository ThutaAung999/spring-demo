package com.turingjavaee5batch.demo.services;

import java.util.List;

import com.turingjavaee5batch.demo.model.Book;

public interface BookService {

	List<Book> getAllBooks();

	Book getBookById(String id);

	void save(Book book);
	
	void update(Book book);
	
	void deleteBookById(String id);
}
