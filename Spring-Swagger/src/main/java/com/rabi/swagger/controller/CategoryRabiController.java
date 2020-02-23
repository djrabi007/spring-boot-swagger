package com.rabi.swagger.controller;

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

import com.rabi.swagger.model.CATEGORY;
import com.rabi.swagger.model.USER;
import com.rabi.swagger.service.CategoryService;

import io.swagger.annotations.ApiOperation;

/**
 * https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-hsql/
 * 
 * @author Rabi
 *
 */
@RestController
@RequestMapping("/categoryservice")
@CrossOrigin("http://localhost:4200")
public class CategoryRabiController {

	@Autowired
	CategoryService catService;

	@PostMapping("/api/v1/category/{userName}")
	@ApiOperation(value = "Save category +username - by Service ")
	@Transactional
	public CATEGORY saveCategoryByUserId(@RequestBody CATEGORY cat, @PathVariable String userName) {
		return catService.saveCategoryByUserId(cat, userName);
	}

	@PostMapping("/api/v1/category")
	@ApiOperation(value = "Save Category only- by Service ")
	@Transactional
	public CATEGORY saveCategory(@RequestBody CATEGORY cat) {
		return catService.saveCategory(cat);
	}

	@PostMapping("/saveUserCategoryByService/{userName}")
	@ApiOperation(value = "Save User(1) Category(M)- by Service ")
	@Transactional
	public USER saveUserCategory(@RequestBody Set<CATEGORY> catSet, @PathVariable String userName) {
		return catService.saveUserCategory(catSet, userName);
	}

	@PutMapping("/api/v1/category/{catId}")
	@ApiOperation(value = "UPDATE Category- by Service ")
	@Transactional
	public List<CATEGORY> updateCategoryByCatId(@RequestBody CATEGORY cat, @PathVariable int catId) {
		return catService.updateCategoryByCatId(cat, catId);
	}

	@DeleteMapping("/deleteCategoryByCatIdService/{catId}")
	@ApiOperation(value = "DELETE Category by Cat Id - by Service ")
	@Transactional
	public List<CATEGORY> deleteCategoryByCatId(@PathVariable int catId) {
		return catService.deleteCategoryByCatId(catId);

	}

	@DeleteMapping("/api/v1/category/{userId}/{catId}")
	@ApiOperation(value = "DELETE Category by Cat Id and User Id  - by Service ")
	@Transactional
	public List<CATEGORY> deleteCategoryByCatIdUserId(@PathVariable int catId, @PathVariable String userId) {
		return catService.deleteCategoryByCatIdUserId(catId, userId);

	}

	@GetMapping("/allCategory")
	public List<CATEGORY> allCATEGORY() {
		return catService.allCATEGORY();
	}

	@GetMapping("/getCategoryByCatID/{catId}")
	public List<CATEGORY> getCategoryByCatId(@PathVariable int catId) {
		return catService.getCategoryByCatId(catId);
	}

	@GetMapping("/api/v1/category/{userId}")
	public List<CATEGORY> getCategoryByUserId(@PathVariable String userId) {
		return catService.getCategoryByUserId(userId);

	}

	@GetMapping("/api/v1/category/{userId}/{catId}")
	public List<CATEGORY> getCategoryByCatIdUserId(@PathVariable int catId, @PathVariable String userId) {
		return catService.getCategoryByCatIdUserId(catId, userId);
	}
}
