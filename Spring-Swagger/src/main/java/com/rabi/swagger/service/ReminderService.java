package com.rabi.swagger.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabi.swagger.dao.ReminderRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepository;
import com.rabi.swagger.dao.UserRepository;
import com.rabi.swagger.model.Reminder;
import com.rabi.swagger.model.USER;

@Service
public class ReminderService {

	@Autowired
	ReminderRepoByJDBC reminderJdbc;
	
	@Autowired
	ReminderRepository reminderRepo;
	
	@Autowired
	UserRepository userRepo;


	public List<Reminder> updateReminderByRemId(Reminder reminder, int remId) {
		return reminderJdbc.updateReminderByRemId(reminder, remId);

	}

	public List<Reminder> deleteReminderByRemId(int remId) {
		return reminderJdbc.deleteReminderByRemId(remId);
	}

	public List<Reminder> deleteReminderByRemIdUserId(String userId, int remId) {
		return reminderJdbc.deleteReminderByRemIdUserId(userId, remId);
	}

	public List<Reminder> getReminderByRemId(int remId) {
		return reminderJdbc.getReminderByRemId(remId);
	}

	public List<Reminder> getReminderByUserId(String userId) {
		return reminderJdbc.getReminderByUserId(userId);
	}

	public List<Reminder> getReminderByRemIdUserId(int remId, String userId) {
		return reminderJdbc.getReminderByRemIdUserId(remId, userId);
	}

	public Reminder saveReminderByUserId(Reminder rem, String userName) {

		USER user = new USER(userName);
		user.setId(userName); // Manually Assign!!!!!!!!!!!!!!! as No @GeneratedValue at @ID

		Set<Reminder> remFinalSet = new HashSet<>();
		rem.setUserRemNew(user);
		remFinalSet.add(rem);

		user.setRemSet(remFinalSet);

		user = userRepo.save(user);
		// Save Reminder into Database
		return reminderRepo.save(rem);
	}

	public List<Reminder> allReminder() {
		return reminderRepo.findAll();
	}

	@Deprecated
	public Reminder saveReminder(Reminder rem) {
		// Save Reminder into Database
		return reminderRepo.save(rem);
	}

	@Deprecated
	public USER saveUserReminder(Set<Reminder> remSet, String userName) {
		USER user = new USER(userName);
		Set<Reminder> remFinalSet = new HashSet<>();
		user.setRemSet(remSet);
		user.setId(userName); // Manually Assign!!!!!!!!!!!!!!! as No @GeneratedValue at @ID
		user = userRepo.save(user);
//Update Note with Info of User (ID..i.e Object Save)
		for (Reminder remNew : remSet) {
			remNew.setUserRemNew(user);
			remFinalSet.add(remNew);
		}
//Save Reminder into Database
		reminderRepo.saveAll(remFinalSet);
		return user;
	}

}
