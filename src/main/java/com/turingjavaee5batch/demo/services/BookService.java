package com.turingjavaee5batch.demo.services;

import java.util.List;

import com.turingjavaee5batch.demo.model.Book;
import com.turingjavaee5batch.demo.services.exception.BusinessLogicException;

public interface BookService {

	List<Book> getAllBooks();

	Book getBookById(String id);

	Book save(Book book);
	
	Book update(Book book);
	
	Book deleteBookById(String id) throws BusinessLogicException;
}
