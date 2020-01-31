package com.rabi.swagger.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.dao.BookRepository;
import com.rabi.swagger.model.Book;
import com.rabi.swagger.service.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bookRabi")
public class BookController {
	@Autowired
	BookService bookServ;
	
	@Autowired
	BookRepository repo;
	
	@GetMapping("/searchBookByH2/{bookId}")
	@ApiOperation(value="Search Book by Rabi -H2")
	public Optional<Book> 
	searchBookByH2(@PathVariable int bookId) {
		return bookServ.searchBookByH2(bookId);
	}
	
	@PostMapping("/saveBook")
	@ApiOperation(value="Save Book by Rabi")
	public Book
		saveBook(@RequestBody Book book) {
		return bookServ.saveBook(book);
		
	}
	
	@DeleteMapping("/deleteBook/{bookId}")
	@ApiOperation(value="DELETE Book by Rabi -H2")
	public List<Book> deleteBook(@PathVariable int bookId) {
		return bookServ.deleteBook(bookId);
	}
	//  JDBC ----GET
	@GetMapping("/searchBookByJDBC/{bookId}")
	@ApiOperation(value="Search Book by Rabi- JDBC")
	public Book
	searchBookByJdbc(@PathVariable int bookId) {
		return bookServ.searchBookByJdbc(bookId);
	}
	
	//  JDBC ----POST
	@PostMapping("/saveBookByJdbc")
	@ApiOperation(value="Save Book by Rabi -JDBC")
	public String
	saveBookByJdbc(@RequestBody Book book) {
		return bookServ.saveBookByJdbc(book);
		
	}
	//  JDBC ----DELETE
	@DeleteMapping("/deleteBookByJdbc/{bookId}")
	@ApiOperation(value="DELETE Book by Rabi -JDBC")
	public String deleteBookByJdbc(@PathVariable int bookId) {
		return bookServ.deleteBookByJdbc(bookId);
	}
	
	
	
	@PutMapping("/saveBook/{bookId}")
	   Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Integer bookId){
			return bookServ.saveOrUpdate(newBook,bookId);
			
	    }
	@GetMapping("/allBook")
	 public List<Book> allBook() {
			return bookServ.allBook();
		}
	
}
