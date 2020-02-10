package com.rabi.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabi.swagger.model.BookCategoryNew;

public interface BookCategoryNewRepository extends JpaRepository<BookCategoryNew, Integer>{
}