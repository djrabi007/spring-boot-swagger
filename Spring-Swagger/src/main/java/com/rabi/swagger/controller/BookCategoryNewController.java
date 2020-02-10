package com.rabi.swagger.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.dao.BookCategoryNewRepository;
import com.rabi.swagger.model.BookCategoryNew;
import com.rabi.swagger.model.BookNew;

import io.swagger.annotations.ApiOperation;
/**
 * https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-hsql/
 * @author Rabi
 *
 */
@RestController
@RequestMapping("/bookCategoryNewRabi")
public class BookCategoryNewController {
	
	@Autowired
	BookCategoryNewRepository repo;
	
	
	@GetMapping("/saveBookCategoryByHardCode")
	@ApiOperation(value="Save Book Category- by HardCode")
	@Transactional
	public List<BookCategoryNew> saveBookCategoryByHardcode() {
		final BookCategoryNew categoryA = new BookCategoryNew("Category A");
        Set<BookNew> bookAs = new HashSet<>();
        bookAs.add(new BookNew("Book A1", categoryA));
        bookAs.add(new BookNew("Book A2", categoryA));
        bookAs.add(new BookNew("Book A3", categoryA));
        categoryA.setBooks(bookAs);

        final BookCategoryNew categoryB = new BookCategoryNew("Category B");
        Set<BookNew> bookBs = new HashSet<>();
        bookBs.add(new BookNew("Book B1", categoryB));
        bookBs.add(new BookNew("Book B2", categoryB));
        bookBs.add(new BookNew("Book B3", categoryB));
        categoryB.setBooks(bookBs);

        return repo.saveAll(Arrays.asList(categoryA, categoryB));
		
	}
	

	//TODO- NOT Working properly
	@PostMapping("/saveBookCategoryByService")
	@ApiOperation(value="Save Book Category- by Service (NOT Working properly)")
	@Transactional
	public List<BookCategoryNew> saveBookCategory(@RequestBody List<BookCategoryNew> bookCatList ) {

        return repo.saveAll(bookCatList);
		
	}
	
	@GetMapping("/allBookCategory")
	 public List<BookCategoryNew> allBookCategory() {
			return repo.findAll();
		}
	
}
