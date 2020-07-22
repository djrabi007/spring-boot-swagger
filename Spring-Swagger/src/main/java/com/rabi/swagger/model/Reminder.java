package com.rabi.swagger.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
	private int reminderId; //REMINDER_ID
	//private String name;
	
	private String reminderName;

	@NotBlank(message = "reminderDescription is mandatory")
	private String reminderDescription;

	// @NotNull
	// @NotEmpty
	@NotBlank(message = "reminderType is mandatory")
	private String reminderType;
	
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

		
		public Reminder(int reminderId, String reminderName) {
			super();
			this.reminderId = reminderId;
			this.reminderName = reminderName;
		}

		
		

		public int getReminderId() {
			return reminderId;
		}


		public void setReminderId(int reminderId) {
			this.reminderId = reminderId;
		}


		public String getReminderName() {
			return reminderName;
		}

		public void setReminderName(String reminderName) {
			this.reminderName = reminderName;
		}

		public String getReminderDescription() {
			return reminderDescription;
		}

		public void setReminderDescription(String reminderDescription) {
			this.reminderDescription = reminderDescription;
		}

		public String getReminderType() {
			return reminderType;
		}

		public void setReminderType(String reminderType) {
			this.reminderType = reminderType;
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
