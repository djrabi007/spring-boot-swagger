package com.rabi.swagger.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.dao.AngularUserRepo;
import com.rabi.swagger.model.AngularUser;

@RestController
@RequestMapping("/registerRabi")
@CrossOrigin("http://localhost:4200")
public class RegistrationController {

	@Autowired
	private AngularUserRepo userRepo;
	
	//@CrossOrigin("http://localhost:4200")
	@PostMapping("/register")
	public String register(@RequestBody AngularUser bu) {
		userRepo.save(bu);
		return "Hi  " +bu.getName()+"  Saved successfully!!!";
		
	}
	@GetMapping("/allUsers")
	public List<AngularUser> findAllUser() {
		return userRepo.findAll();
	}
	
	@GetMapping("/findUser/{id}")
	public Optional<AngularUser> findUserById(@PathVariable Long id ) {
		return userRepo.findById(id);
	}
	@GetMapping("/findUser/{email}")
	public Optional<AngularUser> findUserByEmail(@PathVariable Long email ) {
		return userRepo.findById(email);
	}
	
	@DeleteMapping("/cancel/{id}")
	public List<AngularUser> cancelRegistration(@PathVariable Long id ) {
		userRepo.deleteById(id);
		return userRepo.findAll();
	}
	
}