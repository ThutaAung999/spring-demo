package com.turingjavaee5batch.demo.dao;

import java.util.List;

import com.turingjavaee5batch.demo.model.Book;

public interface BookDao {

	List<Book> getAllBooks();

	Book getBookById(String id);

	void save(Book book);
}
