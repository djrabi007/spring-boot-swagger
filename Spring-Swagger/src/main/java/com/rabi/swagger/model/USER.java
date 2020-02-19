package com.rabi.swagger.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class USER {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private String name;

    @OneToMany(mappedBy = "userNew",fetch=FetchType.LAZY,
    		cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Note> noteSet; //1 USER and M Note
    
    
    @OneToMany(mappedBy = "userRemNew",fetch=FetchType.LAZY,
    		cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reminder>  remSet;//1 USER and M Reminder
    
    
    @OneToMany(mappedBy = "userCatNew",fetch=FetchType.LAZY,
    		cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CATEGORY>  catSet;//1 USER and M Reminder
    

    public USER(){

    }

	public USER(String name) {
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

	public Set<Note> getNoteSet() {
		return noteSet;
	}

	public void setNoteSet(Set<Note> noteSet) {
		this.noteSet = noteSet;
	}

	public Set<Reminder> getRemSet() {
		return remSet;
	}

	public void setRemSet(Set<Reminder> remSet) {
		this.remSet = remSet;
	}

	public Set<CATEGORY> getCatSet() {
		return catSet;
	}

	public void setCatSet(Set<CATEGORY> catSet) {
		this.catSet = catSet;
	}
	
	
	
	
}