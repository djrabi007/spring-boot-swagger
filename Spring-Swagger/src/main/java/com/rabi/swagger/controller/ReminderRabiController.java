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

import com.rabi.swagger.dao.NoteRepository;
import com.rabi.swagger.dao.NoteUserRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepository;
import com.rabi.swagger.dao.UserRepository;
import com.rabi.swagger.model.Reminder;
import com.rabi.swagger.model.USER;

import io.swagger.annotations.ApiOperation;
/**
 * https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-hsql/
 * @author Rabi
 *
 */
@RestController
@RequestMapping("/reminderservice")
@CrossOrigin("http://localhost:4200") 
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
	

	
	//@PostMapping("/api/v1/reminder/{userName}")
	@PostMapping("/saveUserReminderByService/{userName}")
	@ApiOperation(value="Save User(1) Reminder(M)- by Service ")
	@Transactional
	public USER saveUserReminder(@RequestBody Set<Reminder> remSet 
										, @PathVariable String userName ) {
		USER user=new USER(userName);
		Set<Reminder> remFinalSet= new HashSet<>();
		 user.setRemSet(remSet);
		 user.setId(userName); //Manually Assign!!!!!!!!!!!!!!! as No @GeneratedValue at @ID
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
	
	@PostMapping("/api/v1/reminder/{userName}")
	//@PostMapping("/saveReminderByService")
	@ApiOperation(value="Save Reminder +username - by Service ")
	@Transactional
	public Reminder saveReminderByUserId(@RequestBody Reminder rem ,@PathVariable String userName) {
        
		USER user=new USER(userName);
		user.setId(userName); //Manually Assign!!!!!!!!!!!!!!! as No @GeneratedValue at @ID
		
		Set<Reminder> remFinalSet= new HashSet<>();
		rem.setUserRemNew(user);
		remFinalSet.add(rem);
		 
		user.setRemSet(remFinalSet);
		 
		user=userRepo.save(user);
		//Save Reminder into Database
		return  reminderRepo.save(rem);
	}
	
	@PostMapping("/api/v1/reminder111111111")
	//@PostMapping("/saveReminderByService")
	@ApiOperation(value="Save Reminder only- by Service ")
	@Transactional
	public Reminder saveReminder(@RequestBody Reminder rem ) {
        //Save Reminder into Database
       return  reminderRepo.save(rem);
	}
	
	@PutMapping("/api/v1/reminder/{remId}")
	//@PutMapping("/updateReminderByReminderIdService/{remId}")
	@ApiOperation(value="UPDATE Reminder- by Service ")
	@Transactional
	public List<Reminder> updateReminderByRemId(@RequestBody Reminder rem,@PathVariable int remId) {
		return remRepoJdbc.updateReminderByRemId(rem,remId);
	}
	
	
	@DeleteMapping("/api/v1/reminder/{remId}")
	//@DeleteMapping("/deleteReminderByRemIdService/{remId}")
	@ApiOperation(value="DELETE Reminder by Rem Id - by Service ")
	@Transactional
	public List<Reminder> deleteReminderByRemId(@PathVariable int remId) {
		return remRepoJdbc.deleteReminderByRemId(remId);
	}
	
	
	@DeleteMapping("/api/v1/reminder/{userId}/{remId}")
	@ApiOperation(value="DELETE Reminder by Rem Id and User Id - by Service ")
	@Transactional
	public List<Reminder> deleteReminderByRemIdUserId(@PathVariable String userId,@PathVariable int remId) {
		return remRepoJdbc.deleteReminderByRemIdUserId(userId,remId);
	}
	
	
	@GetMapping("/api/v1/reminder")
	//@GetMapping("/allReminder")
	 public List<Reminder> allReminder() {
			return reminderRepo.findAll();
		}
	@GetMapping("/getReminderByRemID/{remId}")
	 public List<Reminder> getReminderByRemId(@PathVariable int remId) {
		return remRepoJdbc.getReminderByRemId(remId);
		}
	
	@GetMapping("/api/v1/reminder/{userId}")
	//@GetMapping("/getReminderByUserID/{userId}")
	 public List<Reminder> getReminderByUserId(@PathVariable String userId) {
		return remRepoJdbc.getReminderByUserId(userId);
		}
	
	@GetMapping("/api/v1/reminder/{userId}/{remId}")
	//@GetMapping("/getReminderByRemIDUserID/{remId}/{userId}")
	 public List<Reminder> getReminderByRemIdUserId(@PathVariable int remId,@PathVariable String userId) {
		return remRepoJdbc.getReminderByRemIdUserId(remId,userId);
		}
}
