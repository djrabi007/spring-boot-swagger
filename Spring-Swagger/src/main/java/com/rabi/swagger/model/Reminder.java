package com.rabi.swagger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*@Getter
@Setter
@ToString
@NoArgsConstructor
*/
@Entity
@Table(name = "REMINDER")
public class Reminder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "REM_NOTE_ID")
	@JsonIgnore
	private Note noteNew;

	@ManyToOne
	@JoinColumn(name = "USER_REM_ID")
	@JsonIgnore
	private USER userRemNew;
	
	    public Reminder() {

	    }
	    
	    


		public Reminder(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}




		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public Note getNoteNew() {
			return noteNew;
		}


		public void setNoteNew(Note noteNew) {
			this.noteNew = noteNew;
		}
		public USER getUserRemNew() {
			return userRemNew;
		}
		public void setUserRemNew(USER userRemNew) {
			this.userRemNew = userRemNew;
		}

	    
	    
}
