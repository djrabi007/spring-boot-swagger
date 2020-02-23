package com.rabi.swagger.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/noteservice")
@CrossOrigin("http://localhost:4200")
public class NoteRabiController {
	@Autowired
	NoteRepository noteRepo;
	@Autowired
	ReminderRepository reminderRepo;
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	NoteUserRepoByJDBC noteRepoJdbc;
	

	
	@GetMapping("/api/v1/note/{userId}")
	//@GetMapping("/getNoteByUserId/{userId}")
	public List<Note> getAllNotesByUserId(@PathVariable String userId) {
		return noteRepoJdbc.searchNoteByUserId(userId);
	}
	
	@GetMapping("/api/v1/note/{userId}/{noteId}")
	//@GetMapping("/getNoteByNoteIdUserId/{noteId}/{userId}")
	public List<Note> getAllNotesByNoteIdUserId(@PathVariable int noteId,@PathVariable String userId) {
		return noteRepoJdbc.searchNoteByNoteIdUserId(noteId,userId);
	}
	
	@PutMapping(path = "/api/v1/note/{userId}/{noteId}")
	//@PutMapping("/updateNoteByNoteIdUserId/{noteId}/{userId}")
	@ApiOperation(value="UPDATE User(1) Note(M)- by Service ")
	@Transactional
	public List<Note> updateAllNotesByNoteIdUserId(@RequestBody Note note,@PathVariable int noteId,@PathVariable String userId) {
		return noteRepoJdbc.updateAllNotesByNoteIdUserId(note,noteId,userId);
	}
	
	@DeleteMapping("/api/v1/note/{userId}")
	//@DeleteMapping("/deleteNoteByUserId/{userId}")
	@ApiOperation(value="DELETE User(1) Note(M)- by Service ")
	@Transactional
	public List<Note> deleteAllNotesByUserId(@PathVariable String userId) {
		return noteRepoJdbc.deleteAllNotesByUserId(userId);
	}
	
	@DeleteMapping("/api/v1/note/{userId}/{noteId}")
	//@DeleteMapping("/deleteNoteByUserId/{noteId}/{userId}")
	@ApiOperation(value="DELETE User(1) Note(M) (Note and User ID)- by Service ")
	@Transactional
	public List<Note> deleteAllNotesByNoteIdUserId(@PathVariable int noteId,@PathVariable String userId) {
		return noteRepoJdbc.deleteAllNotesByNoteIdUserId(noteId,userId);
	}

	
	@PostMapping("/api/v1/note/{userName}")
	@ApiOperation(value="Save Note +username - by Service ")
	@Transactional
	public Note saveReminderByUserId(@RequestBody Note note ,@PathVariable String userName) {
        
		USER user=new USER(userName);
		user.setId(userName); //Manually Assign!!!!!!!!!!!!!!! as No @GeneratedValue at @ID
		
		Set<Note> noteFinalSet= new HashSet<>();
		note.setUserNew(user);
		noteFinalSet.add(note);
		 
		user.setNoteSet(noteFinalSet);
		 
		user=userRepo.save(user);
		//Save Note into Database
		return  noteRepo.save(note);
	}
	
	
	@PostMapping("/api/v1/note11111111111111111111111")
	@ApiOperation(value="Save NOTE ONLY!!!- by Service ")
	@Transactional
	public Note createNote(@RequestBody Note note) {
		return noteRepo.save(note);
	}

		@PostMapping("/api/v1/noteEEEEEEEEEEEEEEEEEEEEEEEEEE/{userName}")
		//@PostMapping("/saveUserNoteByService/{userName}")
		@ApiOperation(value="Save User(1) Note(M)- by Service ")
		@Transactional
		public USER saveUserNote1111111111111111111111111111(@RequestBody Set<Note> noteSet 
											, @PathVariable String userName ) {
			USER user=new USER(userName);
			Set<Note> noteFinalSet= new HashSet<>();
			 user.setNoteSet(noteSet);
			 user=userRepo.save(user);
			//Update Note with Info of User (ID..i.e Object Save)
	        for(Note noteNew:noteSet)
	        {
	        	noteNew.setUserNew(user);
	        	noteFinalSet.add(noteNew);
	        }
	        //Save BookNew into Database
	        noteRepo.saveAll(noteFinalSet);
	        return user;
		}
		

	
	@PostMapping("/saveReminderNoteByService/{noteName}")
	@ApiOperation(value="Save Note(1) Reminder(M)- by Service ")
	@Transactional
	public Note saveNoteReminder(@RequestBody Set<Reminder> remSet 
										, @PathVariable String noteName ) {
		Note note=new Note(noteName);
		Set<Reminder> remFinalSet= new HashSet<>();
		//Update Note with Set<Reminder>
		 note.setReminder(remSet);
		 note= noteRepo.save(note);
		//Update Reminder with Info of Note (ID..i.e Object Save)
        for(Reminder remNew:remSet)
        {
        	remNew.setNoteNew(note);
        	remFinalSet.add(remNew);
        }
        //Save BookNew into Database
        reminderRepo.saveAll(remFinalSet);
        return note;
	}
	
	
	@GetMapping("/saveNoteReminderByHardCode")
	@ApiOperation(value="Save Reminder Note- by HardCode")
	@Transactional
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
		
		return noteRepo.saveAll(Arrays.asList(noteA,noteB,noteC));
			
	}

	private Set<Reminder> populateReminderSet(final Note noteA, String reminder1, String reminder2) {
		Set<Reminder> remASet = new HashSet<>();
				Reminder rem1 =new Reminder();
				//No ID set as genetrated automatically
				rem1.setReminderName(reminder1);
				rem1.setNoteNew(noteA);//Link with Note
				
				Reminder rem2 =new Reminder();
				//No ID set as genetrated automatically
				rem2.setReminderName(reminder2);
				rem2.setNoteNew(noteA);//Link with Note
		
				remASet.add(rem1);
				remASet.add(rem2);
		return remASet;
	}
	
	@GetMapping("/allNote")
	 public List<Note> allNote() {
			return noteRepo.findAll();
		}
	
}
