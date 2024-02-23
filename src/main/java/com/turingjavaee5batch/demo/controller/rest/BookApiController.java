package com.turingjavaee5batch.demo.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingjavaee5batch.demo.controller.rest.error.ApiError;
import com.turingjavaee5batch.demo.model.Book;
import com.turingjavaee5batch.demo.services.BookService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookApiController {
	

	@Autowired
	BookService bookService;
	
	@GetMapping
	List<Book> getAllBook(){
		
		log.info("GET  /api/books");
		return this.bookService.getAllBooks();
	}

	
	@GetMapping("/{bookId}")
	ResponseEntity<Object> getBookById(@PathVariable String bookId){
		log.info("GET  /api/books/"+bookId);
		
		Book book=null;
		try {
				book=this.bookService.getBookById(bookId);
				return ResponseEntity.ok(book);
		} catch (Exception e) {
				log.error("getBookById eror"+e.getMessage());
				ApiError error=new ApiError("1001", "No such  book with bookId :"+bookId);
				return  ResponseEntity.badRequest().body(error);
		}		
	}

	@PostMapping
	ResponseEntity<Object> saveBook(@RequestBody  @Valid Book  book,BindingResult result) {
		log.info("POST saveBook"+book);
		
		if(result.hasErrors()) {
			log.info("Validation error in  creation book : "+result);
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		else
		{
		return ResponseEntity.status(HttpStatus.CREATED).body( this.bookService.save(book));
		}
		
	}
}
  
