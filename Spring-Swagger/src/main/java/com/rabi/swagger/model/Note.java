package com.rabi.swagger.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "NOTE")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;

	private int noteId;
	private String noteTitle;
	private String noteContent;
	private String noteStatus;

	@OneToMany(mappedBy = "noteNew", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	//private Set<Reminder> remNew;
	private Set<Reminder> reminder;

	@ManyToOne
	@JoinColumn(name = "USER_NOTE_ID")
	@JsonIgnore
	private USER userNew;

	public Note() {

	}

	public Note(String noteTitle) {
		super();
		this.noteTitle = noteTitle;
	}

	public Note(int noteId, String noteTitle) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
	}

	public Note(int noteId, String noteTitle, USER userNew) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.userNew = userNew;
	}

	public USER getUserNew() {
		return userNew;
	}

	public void setUserNew(USER userNew) {
		this.userNew = userNew;
	}


	public Set<Reminder> getReminder() {
		return reminder;
	}

	public void setReminder(Set<Reminder> reminder) {
		this.reminder = reminder;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	@Override
	public String toString() {
		String result = String.format("Note[id=%d, name='%s']%n", noteId, noteTitle);
		if (reminder != null) {
			for (Reminder rmOnly : reminder) {
				result += String.format("Reminder[id=%d, name='%s']%n", rmOnly.getReminderId(),
						rmOnly.getReminderName());
			}
		}
		return result;
	}

}