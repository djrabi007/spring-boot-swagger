package com.rabi.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabi.swagger.model.AngularUser;


public interface AngularUserRepo
         extends JpaRepository<AngularUser, Long>{
	
	List<AngularUser> findByEmail(String email);

}