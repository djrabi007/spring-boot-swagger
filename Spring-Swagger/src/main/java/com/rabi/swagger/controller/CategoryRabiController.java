package com.rabi.swagger.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.dao.CategoryRepoByJDBC;
import com.rabi.swagger.dao.CategoryRepository;
import com.rabi.swagger.dao.UserRepository;
import com.rabi.swagger.model.CATEGORY;
import com.rabi.swagger.model.Reminder;
import com.rabi.swagger.model.USER;

import io.swagger.annotations.ApiOperation;
/**
 * https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-hsql/
 * @author Rabi
 *
 */
@RestController
@RequestMapping("/categoryservice")
@CrossOrigin("http://localhost:4200") 
public class CategoryRabiController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	CategoryRepository catRepo;
	@Autowired
	CategoryRepoByJDBC catRepoJdbc;

	
	
	
	
	
	
	
	
	@PostMapping("/api/v1/category/{userName}")
	//@PostMapping("/saveReminderByService")
	@ApiOperation(value="Save category +username - by Service ")
	@Transactional
	public CATEGORY saveCategoryByUserId(@RequestBody CATEGORY cat ,@PathVariable String userName) {
        
		USER user=new USER(userName);
		user.setId(userName); //Manually Assign!!!!!!!!!!!!!!! as No @GeneratedValue at @ID
		
		Set<CATEGORY> catFinalSet= new HashSet<>();
		cat.setUserCatNew(user);
		catFinalSet.add(cat);
		
		user.setCatSet(catFinalSet);
		 
		user=userRepo.save(user);
		//Save Reminder into Database
		return catRepo.save(cat);
	}
	
	
	@PostMapping("/api/v1/category")
	//@PostMapping("/saveCategoryByService")
	@ApiOperation(value="Save Category only- by Service ")
	@Transactional
	public CATEGORY saveCategory(@RequestBody CATEGORY cat ) {
        //Save CATEGORY into Database
       return  catRepo.save(cat);
	}
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
	
	@PutMapping("/api/v1/category/{catId}")
	//@PutMapping("/updateCategoryByCategoryIdService/{catId}")
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
	
	
	@DeleteMapping("/api/v1/category/{userId}/{catId}")
	@ApiOperation(value="DELETE Category by Cat Id and User Id  - by Service ")
	@Transactional
	public List<CATEGORY> deleteCategoryByCatIdUserId(@PathVariable int catId,@PathVariable String userId) {
		return catRepoJdbc.deleteCategoryByCatIdUserId(catId,userId);
		
	}
	
	
	@GetMapping("/allCategory")
	 public List<CATEGORY> allCATEGORY() {
			return catRepo.findAll();
		}
	@GetMapping("/getCategoryByCatID/{catId}")
	 public List<CATEGORY> getCategoryByCatId(@PathVariable int catId) {
		return catRepoJdbc.getCategoryByCatId(catId);
		}
	
	@GetMapping("/api/v1/category/{userId}")
	//@GetMapping("/getCategoryByUserID/{userId}")
	 public List<CATEGORY> getCategoryByUserId(@PathVariable String userId) {
		return catRepoJdbc.getCategoryByUserId(userId);
		
		}
	
	
	@GetMapping("/api/v1/category/{userId}/{catId}")
	//@GetMapping("/getCategoryByCatIdUserID/{catId}/{userId}")
	 public List<CATEGORY> getCategoryByCatIdUserId(@PathVariable int catId,@PathVariable String userId) {
		
		return catRepoJdbc.getCategoryByCatIdUserId(catId, userId);
		}
}
