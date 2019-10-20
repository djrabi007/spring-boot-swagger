package com.rabi.swagger.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rabi.swagger.dao.BookRepository;
import com.rabi.swagger.model.Book;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class BookService {
	@Autowired
	BookRepository repo;
	public Optional<Book> searchBook(int bookId) {
		return repo.findById(bookId);
	}
	public Book saveBook(Book book) {
		return repo.save(book);
	}
	public List<Book> deleteBook(int bookId) {
		repo.deleteById(bookId);
		return repo.findAll();
	}
	
    public Book saveOrUpdate(Book newBook,int bookId) {

    	Book existingBook= repo.findById(bookId)
    			.orElseThrow(()->new EntityNotFoundException());
    	BeanUtils.copyProperties(newBook, existingBook);
    	return repo.save(existingBook);
    	
    	/*Book existingBook= repo.findById(bookId).get();
		if(null== existingBook) {
		throw new Exception("Book Not Found!!");
		}
		else {
			existingBook.setBookname(newBook.getBookname());
			existingBook.setPrice(newBook.getPrice());
			return repo.save(existingBook);
		}*/
		
		
		
    }
    
    public List<Book> allBook() {
		return repo.findAll();
	}

}
