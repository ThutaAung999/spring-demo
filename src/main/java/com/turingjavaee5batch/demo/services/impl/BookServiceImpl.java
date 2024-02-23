package com.turingjavaee5batch.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turingjavaee5batch.demo.dao.BookDao;
import com.turingjavaee5batch.demo.model.Book;
import com.turingjavaee5batch.demo.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;

	@Override
	public List<Book> getAllBooks() {
		return this.bookDao.getAllBooks();
	}

	@Override
	public Book getBookById(String id) {

		return this.bookDao.getBookById(id);
	}

	@Override
	public Book save(Book book) {
		return this.bookDao.save(book);
		
	}

	@Override
	public Book update(Book book) {
		return this.bookDao.update(book);		
	}

	@Override
	public void deleteBookById(String id) {
		this.bookDao.deleteBookByID(id);		
	}

}
