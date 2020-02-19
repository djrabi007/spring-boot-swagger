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
import com.rabi.swagger.dao.NoteRepository;
import com.rabi.swagger.dao.NoteUserRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepository;
import com.rabi.swagger.dao.UserRepository;
import com.rabi.swagger.model.Book;
import com.rabi.swagger.model.BookCategoryNew;
import com.rabi.swagger.model.BookNew;
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
@RequestMapping("/reminderRabi")
public class ReminderRabiController {
	@Autowired
	NoteRepository noteRepo;
	@Autowired
	ReminderRepository reminderRepo;
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	NoteUserRepoByJDBC noteRepoJdbc;
	@Autowired
	ReminderRepoByJDBC remRepoJdbc;
	

	
	
	@PostMapping("/saveUserReminderByService/{userName}")
	@ApiOperation(value="Save User(1) Reminder(M)- by Service ")
	@Transactional
	public USER saveUserReminder(@RequestBody Set<Reminder> remSet 
										, @PathVariable String userName ) {
		USER user=new USER(userName);
		Set<Reminder> remFinalSet= new HashSet<>();
		 user.setRemSet(remSet);
		 user=userRepo.save(user);
		//Update Note with Info of User (ID..i.e Object Save)
        for(Reminder remNew:remSet)
        {
        	remNew.setUserRemNew(user);
        	remFinalSet.add(remNew);
        }
        //Save Reminder into Database
        reminderRepo.saveAll(remFinalSet);
        return user;
	}
	
	
	
	@PostMapping("/saveReminderByService")
	@ApiOperation(value="Save Reminder only- by Service ")
	@Transactional
	public Reminder saveReminder(@RequestBody Reminder rem ) {
        //Save Reminder into Database
       return  reminderRepo.save(rem);
	}
	
	@PutMapping("/updateReminderByReminderIdService/{remId}")
	@ApiOperation(value="UPDATE Reminder- by Service ")
	@Transactional
	public List<Reminder> updateReminderByRemId(@RequestBody Reminder rem,@PathVariable int remId) {
		return remRepoJdbc.updateReminderByRemId(rem,remId);
	}
	
	
	@DeleteMapping("/deleteReminderByRemIdService/{remId}")
	@ApiOperation(value="DELETE Reminder by Rem Id - by Service ")
	@Transactional
	public List<Reminder> deleteReminderByRemId(@PathVariable int remId) {
		return remRepoJdbc.deleteReminderByRemId(remId);
	}
	
	
	@GetMapping("/allReminder")
	 public List<Reminder> allReminder() {
			return reminderRepo.findAll();
		}
	@GetMapping("/getReminderByRemID/{remId}")
	 public List<Reminder> getReminderByRemId(@PathVariable int remId) {
		return remRepoJdbc.getReminderByRemId(remId);
		}
	
	@GetMapping("/getReminderByUserID/{userId}")
	 public List<Reminder> getReminderByUserId(@PathVariable int userId) {
		return remRepoJdbc.getReminderByUserId(userId);
		}
	
	
	@GetMapping("/getReminderByRemIDUserID/{remId}/{userId}")
	 public List<Reminder> getReminderByRemIdUserId(@PathVariable int remId,@PathVariable int userId) {
		return remRepoJdbc.getReminderByRemIdUserId(remId,userId);
		}
}
