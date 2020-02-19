package com.rabi.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabi.swagger.model.BookCategoryNew;
import com.rabi.swagger.model.BookNew;
import com.rabi.swagger.model.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Integer>{
}