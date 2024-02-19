package com.turingjavaee5batch.demo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.turingjavaee5batch.demo.model.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("BookValidator's validate methods");

		ValidationUtils.rejectIfEmpty(errors, "id", "required", new Object[] { "id" });
		ValidationUtils.rejectIfEmpty(errors, "title", "required", new Object[] { "title" });
		ValidationUtils.rejectIfEmpty(errors, "author", "required", new Object[] { "author" });
		
		Book book=(Book)target;
		if(!book.getTitle().isEmpty()) {
			String firstChar =book.getTitle().charAt(0)+"";
			if(!firstChar.toUpperCase().equals(firstChar)) {
				errors.rejectValue("title", "lowercase");
			}
		} 
	}

	
	
}
