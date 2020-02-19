package com.rabi.swagger.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.dao.BookCategoryNewRepository;
import com.rabi.swagger.dao.BookNewRepository;
import com.rabi.swagger.dao.CategoryRepoByJDBC;
import com.rabi.swagger.dao.CategoryRepository;
import com.rabi.swagger.dao.NoteRepository;
import com.rabi.swagger.dao.NoteUserRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepository;
import com.rabi.swagger.dao.UserRepository;
import com.rabi.swagger.model.Book;
import com.rabi.swagger.model.BookCategoryNew;
import com.rabi.swagger.model.BookNew;
import com.rabi.swagger.model.CATEGORY;
import com.rabi.swagger.model.Note;
import com.rabi.swagger.model.Reminder;
import com.rabi.swagger.model.USER;

import io.swagger.annotations.ApiOperation;
/**
 * https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-hsql/
 * @author Rabi
 *
 */
@RestController
@RequestMapping("/categoryRabi")
public class CategoryRabiController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	CategoryRepository catRepo;
	@Autowired
	CategoryRepoByJDBC catRepoJdbc;

	
	
	@PostMapping("/saveUserCategoryByService/{userName}")
	@ApiOperation(value="Save User(1) Category(M)- by Service ")
	@Transactional
	public USER saveUserCategory(@RequestBody Set<CATEGORY> catSet 
										, @PathVariable String userName ) {
		USER user=new USER(userName);
		Set<CATEGORY> catFinalSet= new HashSet<>();
		 user.setCatSet(catSet);
		 user=userRepo.save(user);
		//Update Note with Info of User (ID..i.e Object Save)
        for(CATEGORY catNew:catSet)
        {
        	catNew.setUserCatNew(user);
        	catFinalSet.add(catNew);
        }
        //Save Reminder into Database
        catRepo.saveAll(catFinalSet);
        return user;
	}
	
	
	
	@PostMapping("/saveCategoryByService")
	@ApiOperation(value="Save Category only- by Service ")
	@Transactional
	public CATEGORY saveCategory(@RequestBody CATEGORY cat ) {
        //Save CATEGORY into Database
       return  catRepo.save(cat);
	}
	
	@PutMapping("/updateCategoryByCategoryIdService/{catId}")
	@ApiOperation(value="UPDATE Category- by Service ")
	@Transactional
	public List<CATEGORY> updateCategoryByCatId(@RequestBody CATEGORY cat,@PathVariable int catId) {
		return catRepoJdbc.updateCategoryByCatId(cat, catId);
		
	}
	
	
	@DeleteMapping("/deleteCategoryByCatIdService/{catId}")
	@ApiOperation(value="DELETE Category by Cat Id - by Service ")
	@Transactional
	public List<CATEGORY> deleteCategoryByCatId(@PathVariable int catId) {
		return catRepoJdbc.deleteCategoryByCatId(catId);
		
	}
	
	
	@GetMapping("/allCategory")
	 public List<CATEGORY> allCATEGORY() {
			return catRepo.findAll();
		}
	@GetMapping("/getCategoryByCatID/{catId}")
	 public List<CATEGORY> getCategoryByCatId(@PathVariable int catId) {
		return catRepoJdbc.getCategoryByCatId(catId);
		}
	
	@GetMapping("/getCategoryByUserID/{userId}")
	 public List<CATEGORY> getCategoryByUserId(@PathVariable int userId) {
		return catRepoJdbc.getCategoryByUserId(userId);
		
		}
	
	
	@GetMapping("/getCategoryByCatIdUserID/{catId}/{userId}")
	 public List<CATEGORY> getCategoryByCatIdUserId(@PathVariable int catId,@PathVariable int userId) {
		
		return catRepoJdbc.getCategoryByCatIdUserId(catId, userId);
		}
}
