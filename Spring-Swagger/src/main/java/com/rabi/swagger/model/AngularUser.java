package com.rabi.swagger.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AngularUser {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long Id;
	private String name;
	private String email;
	private String experience;
	private String domain;
	
	public AngularUser() {}

	public Long getId() {
		return Id;
	}
/*
	public void setId(Long id) {
		Id = id;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	
	}

