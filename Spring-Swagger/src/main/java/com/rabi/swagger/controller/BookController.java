package com.rabi.swagger.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.model.Book;
import com.rabi.swagger.service.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bookRabi")
public class BookController {
	@Autowired
	BookService bookServ;
	
	@GetMapping("/searchBook/{bookId}")
	@ApiOperation(value="Search Book by Rabi")
	public Optional<Book> 
	searchBook1(@PathVariable int bookId) {
		return bookServ.searchBook(bookId);
	}
	@PostMapping("/saveBook")
	@ApiOperation(value="Save Book by Rabi")
	public void
		saveBook(@RequestBody Book book) {
		bookServ.saveBook(book);
	}
	@DeleteMapping("/deleteBook/{bookId}")
	public List<Book> deleteBook(@PathVariable int bookId) {
		return bookServ.deleteBook(bookId);
	}
	
	
}
