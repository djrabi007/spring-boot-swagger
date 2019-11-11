package com.rabi.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabi.swagger.model.AngularUser;


public interface AngularUserRepo
         extends JpaRepository<AngularUser, Long>{

}