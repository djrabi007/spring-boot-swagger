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
    private int id;
	
    private String name;

    @OneToMany(mappedBy = "noteNew",fetch=FetchType.LAZY,
    		cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Reminder> remNew;
    
    
	@ManyToOne
	@JoinColumn(name = "USER_NOTE_ID")
	@JsonIgnore
	private USER userNew;

    public Note(){

    }

	public Note(String name) {
        this.name = name;
    }

	
	public Note(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Note(int id, String name, USER userNew) {
		super();
		this.id = id;
		this.name = name;
		this.userNew = userNew;
	}

	public USER getUserNew() {
		return userNew;
	}

	public void setUserNew(USER userNew) {
		this.userNew = userNew;
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

	public Set<Reminder> getRemNew() {
		return remNew;
	}

	public void setRemNew(Set<Reminder> remNew) {
		this.remNew = remNew;
	}


	@Override
    public String toString() {
        String result = String.format(
                "Note[id=%d, name='%s']%n",
                id, name);
        if (remNew != null) {
            for(Reminder rmOnly : remNew) {
                result += String.format(
                        "Reminder[id=%d, name='%s']%n",
                        rmOnly.getId(), rmOnly.getName());
            }
        }    
        return result;
    }
	
}