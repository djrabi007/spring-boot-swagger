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

import com.rabi.swagger.dao.BookRepoByJDBC;
import com.rabi.swagger.dao.BookRepository;
import com.rabi.swagger.model.Book;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class BookService {
	@Autowired
	BookRepository repo;
	
	@Autowired
	BookRepoByJDBC  bookjdbc;
	
	public Optional<Book> searchBookByH2(int bookId) {
		return repo.findById(bookId);
	}
	
	public Book saveBook(Book book) {
		return repo.save(book);
	}
	
	public List<Book> deleteBook(int bookId) {
		repo.deleteById(bookId);
		return repo.findAll();
	}
	
	//JDBC ----GET
	public Book searchBookByJdbc(int bookId) {
		return bookjdbc.searchBookByJdbc(bookId);
	}
	//JDBC ----POST
	public String saveBookByJdbc(Book book) {
		String message="";
		if(bookjdbc.saveBookByJdbc(book) >0) {
			message ="SUCCESSFULLY SAVED BOOK!!";
		}
		else{
			message ="Failure to add!!";
		}
		return message;
	}
	//JDBC ----DELETE
	public String  deleteBookByJdbc(int bookId) {
		String message="";
		if(bookjdbc.deleteBookByJdbc(bookId)>0) {
			message ="SUCCESSFULLY DELETED BOOK!!";
		}
		else{
			message ="Failure to add!!";
		}
		return message;
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
