package com.turingjavaee5batch.demo.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.turingjavaee5batch.demo.dao.BookDao;
import com.turingjavaee5batch.demo.model.Book;

@Repository
public class BookDaoImpl implements BookDao {

	List<Book> books;

	public BookDaoImpl() {
		this.books = new ArrayList<Book>();

		this.books.add(new Book("1", "Title 1 ", "Auther 1 "));
		this.books.add(new Book("2", "Title 2 ", "Auther 2 "));
	}

	@Override
	public List<Book> getAllBooks() {
		return this.books;
	}

	@Override
	public Book getBookById(String id) {
		return this.books.stream().filter(book -> book.getId().equals(id)).collect(Collectors.toList()).get(0);
	}

	@Override
	public Book save(Book book) {
		 this.books.add(book);
		 
		 return book;
	}

	@Override
	public Book update(Book book) {
	
		Book  originalBook=this.getBookById(book.getId());
			originalBook.setAuthor(book.getAuthor());
			originalBook.setTitle(book.getTitle());
			
			return originalBook;
	}

	@Override
	public void deleteBookByID(String id) {
		List<Book> books= this.books.stream()
						.filter(book ->! book.getId().equals(id))
						.collect(Collectors.toList());
		
		this.books=books;
	}
}
