package com.rabi.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabi.swagger.model.BookCategoryNew;
import com.rabi.swagger.model.BookNew;

public interface BookNewRepository extends JpaRepository<BookNew, Integer>{
}