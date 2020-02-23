package com.rabi.swagger.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabi.swagger.dao.NoteRepository;
import com.rabi.swagger.dao.NoteUserRepoByJDBC;
import com.rabi.swagger.dao.ReminderRepository;
import com.rabi.swagger.dao.UserRepository;
import com.rabi.swagger.model.Note;
import com.rabi.swagger.model.Reminder;
import com.rabi.swagger.model.USER;

@Service
public class NoteService {

	@Autowired
	NoteRepository noteRepo;
	@Autowired
	ReminderRepository reminderRepo;
	@Autowired
	UserRepository userRepo;

	@Autowired
	NoteUserRepoByJDBC noteRepoJdbc;

	public Note searchNoteByNoteId(int noteId) {
		return noteRepoJdbc.searchNoteByNoteId(noteId);
	}

	public List<Note> searchNoteByUserId(String userId) {
		return noteRepoJdbc.searchNoteByUserId(userId);
	}

	public List<Note> searchNoteByNoteIdUserId(int noteId, String userId) {
		return noteRepoJdbc.searchNoteByNoteIdUserId(noteId, userId);
	}

	public List<Note> updateAllNotesByNoteIdUserId(Note note, int noteId, String userId) {
		return noteRepoJdbc.updateAllNotesByNoteIdUserId(note, noteId, userId);
	}

	public List<Note> deleteAllNotesByUserId(String userId) {
		return noteRepoJdbc.deleteAllNotesByUserId(userId);

	}

	public List<Note> deleteAllNotesByNoteIdUserId(int noteId, String userId) {
		return noteRepoJdbc.deleteAllNotesByNoteIdUserId(noteId, userId);

	}

	public Note saveReminderByUserId(Note note, String userName) {

		USER user = new USER(userName);
		user.setId(userName); // Manually Assign!!!!!!!!!!!!!!! as No @GeneratedValue at @ID

		Set<Note> noteFinalSet = new HashSet<>();
		note.setUserNew(user);
		noteFinalSet.add(note);

		user.setNoteSet(noteFinalSet);

		user = userRepo.save(user);
		// Save Note into Database
		return noteRepo.save(note);
	}

	@Deprecated
	public Note createNote(Note note) {
		return noteRepo.save(note);
	}

	@Deprecated
	public USER saveUserNote(Set<Note> noteSet, String userName) {
		USER user = new USER(userName);
		Set<Note> noteFinalSet = new HashSet<>();
		user.setNoteSet(noteSet);
		user = userRepo.save(user);
		// Update Note with Info of User (ID..i.e Object Save)
		for (Note noteNew : noteSet) {
			noteNew.setUserNew(user);
			noteFinalSet.add(noteNew);
		}
		// Save BookNew into Database
		noteRepo.saveAll(noteFinalSet);
		return user;
	}

	@Deprecated
	public Note saveNoteReminder(Set<Reminder> remSet, String noteName) {
		Note note = new Note(noteName);
		Set<Reminder> remFinalSet = new HashSet<>();
		// Update Note with Set<Reminder>
		note.setReminder(remSet);
		note = noteRepo.save(note);
		// Update Reminder with Info of Note (ID..i.e Object Save)
		for (Reminder remNew : remSet) {
			remNew.setNoteNew(note);
			remFinalSet.add(remNew);
		}
		// Save BookNew into Database
		reminderRepo.saveAll(remFinalSet);
		return note;
	}

	@Deprecated
	public List<Note> saveReminderNoteByHardcode() {
		final Note noteA = new Note("Note A");
		Set<Reminder> remASet = populateReminderSet(noteA, "RemA1-Roddur", "RemA2- Roddue");
		noteA.setReminder(remASet);

		final Note noteB = new Note("Note B");
		Set<Reminder> remBSet = populateReminderSet(noteB, "RemB1-Swagata", "RemB2- Swagata");
		noteB.setReminder(remBSet);

		final Note noteC = new Note("Note C");
		Set<Reminder> remCSet = populateReminderSet(noteC, "RemB1-Rabi", "RemB2- Rabi");
		noteC.setReminder(remCSet);

		return noteRepo.saveAll(Arrays.asList(noteA, noteB, noteC));

	}

	@Deprecated
	public List<Note> allNote() {
		return noteRepo.findAll();
	}

	private Set<Reminder> populateReminderSet(final Note noteA, String reminder1, String reminder2) {
		Set<Reminder> remASet = new HashSet<>();
		Reminder rem1 = new Reminder();
		// No ID set as genetrated automatically
		rem1.setReminderName(reminder1);
		rem1.setNoteNew(noteA);// Link with Note

		Reminder rem2 = new Reminder();
		// No ID set as genetrated automatically
		rem2.setReminderName(reminder2);
		rem2.setNoteNew(noteA);// Link with Note

		remASet.add(rem1);
		remASet.add(rem2);
		return remASet;
	}

}
