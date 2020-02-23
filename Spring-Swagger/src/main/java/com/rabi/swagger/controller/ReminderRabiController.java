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

import com.rabi.swagger.dao.NoteRepository;
import com.rabi.swagger.dao.NoteUserRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepository;
import com.rabi.swagger.dao.UserRepository;
import com.rabi.swagger.model.Reminder;
import com.rabi.swagger.model.USER;
import com.rabi.swagger.service.ReminderService;

import io.swagger.annotations.ApiOperation;

/**
 * https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-hsql/
 * 
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

	@Autowired
	ReminderService remService;

	@PostMapping("/saveUserReminderByService/{userName}")
	@ApiOperation(value = "Save User(1) Reminder(M)- by Service ")
	@Transactional
	public USER saveUserReminder(@RequestBody Set<Reminder> remSet, @PathVariable String userName) {
		return remService.saveUserReminder(remSet, userName);
	}

	@PostMapping("/api/v1/reminder/{userName}")
	@ApiOperation(value = "Save Reminder +username - by Service ")
	@Transactional
	public Reminder saveReminderByUserId(@RequestBody Reminder rem, @PathVariable String userName) {

		return remService.saveReminderByUserId(rem, userName);
	}

	@PostMapping("/api/v1/reminder111111111")
	@ApiOperation(value = "Save Reminder only- by Service ")
	@Transactional
	public Reminder saveReminder(@RequestBody Reminder rem) {
		return remService.saveReminder(rem);
	}

	@PutMapping("/api/v1/reminder/{remId}")
	@ApiOperation(value = "UPDATE Reminder- by Service ")
	@Transactional
	public List<Reminder> updateReminderByRemId(@RequestBody Reminder rem, @PathVariable int remId) {
		return remService.updateReminderByRemId(rem, remId);
	}

	@DeleteMapping("/api/v1/reminder/{remId}")
	@ApiOperation(value = "DELETE Reminder by Rem Id - by Service ")
	@Transactional
	public List<Reminder> deleteReminderByRemId(@PathVariable int remId) {
		return remService.deleteReminderByRemId(remId);
	}

	@DeleteMapping("/api/v1/reminder/{userId}/{remId}")
	@ApiOperation(value = "DELETE Reminder by Rem Id and User Id - by Service ")
	@Transactional
	public List<Reminder> deleteReminderByRemIdUserId(@PathVariable String userId, @PathVariable int remId) {
		return remService.deleteReminderByRemIdUserId(userId, remId);
	}

	@GetMapping("/api/v1/reminder")
	public List<Reminder> allReminder() {
		return remService.allReminder();
	}

	@GetMapping("/getReminderByRemID/{remId}")
	public List<Reminder> getReminderByRemId(@PathVariable int remId) {
		return remService.getReminderByRemId(remId);
	}

	@GetMapping("/api/v1/reminder/{userId}")
	public List<Reminder> getReminderByUserId(@PathVariable String userId) {
		return remService.getReminderByUserId(userId);
	}

	@GetMapping("/api/v1/reminder/{userId}/{remId}")
	public List<Reminder> getReminderByRemIdUserId(@PathVariable int remId, @PathVariable String userId) {
		return remService.getReminderByRemIdUserId(remId, userId);
	}
}
