package com.turingjavaee5batch.demo.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.turingjavaee5batch.demo.dao.BookDao;
import com.turingjavaee5batch.demo.model.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	List<Book> books;

	public BookDaoImpl() {
		this.books = new ArrayList<Book>();

		this.books.add(new Book(1L, "Title 1 ", "Auther 1 "));
		this.books.add(new Book(2L, "Title 2 ", "Auther 2 "));
	}

	@Override
	public List<Book> getAllBooks() {
	
		 return jdbcTemplate.query(
			      "SELECT id, title, author FROM Book;",
			      this::mapRowToBook);
		 //return books;
	}
	private Book mapRowToBook(ResultSet row, int rowNum)throws SQLException
	{
		return new Book(row.getLong("ID"),row.getString("title"),row.getString("author"));
	}	

	@Override
	public Book getBookById(Long id) {
		
		/*
		 * return jdbcTemplate.queryForObject(
		 * "SELECT id, title,author FROM book where id=?", this::mapRowToBook, id);
		 */
		List<Book> books = jdbcTemplate.query(
			      "SELECT id, title, author FROM Book WHERE ID=?",
			      this::mapRowToBook,
			      id);
		return books.get(0);

//		return this.books.stream().filter(book -> book.getId().equals(id)).collect(Collectors.toList()).get(0);
	}

	@Override
	public Book save(Book book) {
		/*
		 * this.books.add(book); 
		 */
	/*	 jdbcTemplate.update(
				"INSERT INTO  Book (title, author) values (?, ?)",				
				book.getTitle(),
				book.getAuthor());
		*/
		
		
		String INSERT_SQL =  "INSERT INTO Book ( author, title) values ( ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(INSERT_SQL, new String[] {"id"});
			            ps.setString(1, book.getAuthor());
			            ps.setString(2, book.getTitle());
			            return ps;
			        }
			    },
			    keyHolder);
			book.setId(keyHolder.getKey().longValue());
			
		 return book;		
	}

	@Override
	public Book update(Book book) {
	
		/*
		 * Book originalBook=this.getBookById(book.getId());
		 * originalBook.setAuthor(book.getAuthor());
		 * originalBook.setTitle(book.getTitle());
		 * 
		 * return originalBook;
		 */
		
		this.jdbcTemplate.update(
                "UPDATE Book SET author = ?, title=? WHERE ID = ?", 
                book.getAuthor(),book.getTitle(), book.getId());
		
		return book;
	}

	@Override
	public Book deleteBookByID(Long id) {
		/*
		Book deletedBook=this.getBookById(id);		
		List<Book> books= this.books.stream()
						.filter(book ->! book.getId().equals(id))
						.collect(Collectors.toList());
		
						this.books=books;
		return deletedBook;
		*/
		
		Book deletedBook = this.getBookById(id);
		 this.jdbcTemplate.update(
	                "DELETE FROM Book WHERE ID = ?", 
	                id);
		return deletedBook;
				
	}

	
}
