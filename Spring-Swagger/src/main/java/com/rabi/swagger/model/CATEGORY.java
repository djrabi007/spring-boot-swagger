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
@Table(name = "CATEGORY")
public class CATEGORY {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "USER_CAT_ID")
	@JsonIgnore
	private USER userCatNew;

	public CATEGORY() {

	}

	public CATEGORY(int id, String name) {
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

	public USER getUserCatNew() {
		return userCatNew;
	}

	public void setUserCatNew(USER userCatNew) {
		this.userCatNew = userCatNew;
	}
	
	

}
