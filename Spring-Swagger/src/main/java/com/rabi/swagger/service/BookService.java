package com.rabi.swagger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabi.swagger.dao.BookRepository;
import com.rabi.swagger.model.Book;

@Service
public class BookService {
	@Autowired
	BookRepository repo;
	public Optional<Book> searchBook(int bookId) {
		return repo.findById(bookId);
	}
	public void saveBook(Book book) {
		repo.save(book);
	}
	public List<Book> deleteBook(int bookId) {
		repo.deleteById(bookId);
		return repo.findAll();
	}

}
