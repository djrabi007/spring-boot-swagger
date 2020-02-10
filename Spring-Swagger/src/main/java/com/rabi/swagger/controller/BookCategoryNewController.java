package com.rabi.swagger.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabi.swagger.dao.BookCategoryNewRepository;
import com.rabi.swagger.dao.BookNewRepository;
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
	BookCategoryNewRepository bookCatrepo;
	
	@Autowired
	BookNewRepository bookNewRepo;
	
	
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

        return bookCatrepo.saveAll(Arrays.asList(categoryA, categoryB));
		
	}
	

	@PostMapping("/saveBookCategoryByService/{categorieType}")
	@ApiOperation(value="Save Book Category- by Service ")
	@Transactional
	public BookCategoryNew saveBookCategory(@RequestBody Set<BookNew> bookSet 
										, @PathVariable String categorieType ) {
		 BookCategoryNew categorie = new BookCategoryNew(categorieType);
		 Set<BookNew> bookSetFinal= new HashSet<>();
		 //Update Category with Set<BookNew>
		categorie.setBooks(bookSet);
		categorie= bookCatrepo.save(categorie);
		
		//Update BookNew with Info of CategoryNew
        for(BookNew bookNew:bookSet)
        {
        	bookNew.setBookCategoryNew(categorie);
        	bookSetFinal.add(bookNew);
        }
        //Save BookNew into Database
        bookNewRepo.saveAll(bookSetFinal);
        
        return categorie;
        
		
	}
	
	@GetMapping("/allBookCategory")
	 public List<BookCategoryNew> allBookCategory() {
			return bookCatrepo.findAll();
		}
	
}
