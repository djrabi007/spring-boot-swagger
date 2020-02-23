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

import com.rabi.swagger.model.Note;
import com.rabi.swagger.model.Reminder;
import com.rabi.swagger.model.USER;
import com.rabi.swagger.service.NoteService;

import io.swagger.annotations.ApiOperation;

/**
 * https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-hsql/
 * 
 * @author Rabi
 *
 */
@RestController
@RequestMapping("/noteservice")
@CrossOrigin("http://localhost:4200")
public class NoteRabiController {

	@Autowired
	NoteService noteService;

	@GetMapping("/api/v1/note/{userId}")
	public List<Note> getAllNotesByUserId(@PathVariable String userId) {
		return noteService.searchNoteByUserId(userId);
	}

	@GetMapping("/api/v1/note/{userId}/{noteId}")
	public List<Note> getAllNotesByNoteIdUserId(@PathVariable int noteId, @PathVariable String userId) {
		return noteService.searchNoteByNoteIdUserId(noteId, userId);
	}

	@PutMapping(path = "/api/v1/note/{userId}/{noteId}")
	@ApiOperation(value = "UPDATE User(1) Note(M)- by Service ")
	@Transactional
	public List<Note> updateAllNotesByNoteIdUserId(@RequestBody Note note, @PathVariable int noteId,
			@PathVariable String userId) {
		return noteService.updateAllNotesByNoteIdUserId(note, noteId, userId);
	}

	@DeleteMapping("/api/v1/note/{userId}")
	@ApiOperation(value = "DELETE User(1) Note(M)- by Service ")
	@Transactional
	public List<Note> deleteAllNotesByUserId(@PathVariable String userId) {
		return noteService.deleteAllNotesByUserId(userId);
	}

	@DeleteMapping("/api/v1/note/{userId}/{noteId}")
	@ApiOperation(value = "DELETE User(1) Note(M) (Note and User ID)- by Service ")
	@Transactional
	public List<Note> deleteAllNotesByNoteIdUserId(@PathVariable int noteId, @PathVariable String userId) {
		return noteService.deleteAllNotesByNoteIdUserId(noteId, userId);
	}

	@PostMapping("/api/v1/note/{userName}")
	@ApiOperation(value = "Save Note +username - by Service ")
	@Transactional
	public Note saveReminderByUserId(@RequestBody Note note, @PathVariable String userName) {
		return noteService.saveReminderByUserId(note, userName);
	}

	@PostMapping("/api/v1/note11111111111111111111111")
	@ApiOperation(value = "Save NOTE ONLY!!!- by Service ")
	@Transactional
	public Note createNote(@RequestBody Note note) {
		return noteService.createNote(note);
	}

	@PostMapping("/api/v1/noteEEEEEEEEEEEEEEEEEEEEEEEEEE/{userName}")
	// @PostMapping("/saveUserNoteByService/{userName}")
	@ApiOperation(value = "Save User(1) Note(M)- by Service ")
	@Transactional
	public USER saveUserNote(@RequestBody Set<Note> noteSet,
			@PathVariable String userName) {
		return noteService.saveUserNote(noteSet,userName);
	}

	@PostMapping("/saveReminderNoteByService/{noteName}")
	@ApiOperation(value = "Save Note(1) Reminder(M)- by Service ")
	@Transactional
	public Note saveNoteReminder(@RequestBody Set<Reminder> remSet, @PathVariable String noteName) {
		return noteService.saveNoteReminder(remSet,noteName);
	}

	@GetMapping("/saveNoteReminderByHardCode")
	@ApiOperation(value = "Save Reminder Note- by HardCode")
	@Transactional
	public List<Note> saveReminderNoteByHardcode() {
		return noteService.saveReminderNoteByHardcode();

	}

	@GetMapping("/allNote")
	public List<Note> allNote() {
		return noteService.allNote();
	}

}
