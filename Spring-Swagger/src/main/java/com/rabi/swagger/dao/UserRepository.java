package com.rabi.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabi.swagger.model.BookCategoryNew;
import com.rabi.swagger.model.Note;
import com.rabi.swagger.model.USER;

public interface UserRepository extends JpaRepository<USER, Integer>{
}