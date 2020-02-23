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
	private int categoryId; //CATEGORY_ID,CATEGORY_NAME
	
	// private int id;
	//private String name;
	
	private String categoryName;//CATEGORY_NAME
	private String categoryDescription;//CATEGORY_DESCRIPTION

	@ManyToOne
	@JoinColumn(name = "USER_CAT_ID")
	@JsonIgnore
	private USER userCatNew;

	public CATEGORY() {

	}



	public CATEGORY(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}



	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getCategoryDescription() {
		return categoryDescription;
	}



	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}



	public USER getUserCatNew() {
		return userCatNew;
	}

	public void setUserCatNew(USER userCatNew) {
		this.userCatNew = userCatNew;
	}
	
	

}
