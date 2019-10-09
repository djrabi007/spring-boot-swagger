package com.rabi.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabi.swagger.model.Book;

public interface BookRepository 
	extends JpaRepository<Book, Integer> {

}
