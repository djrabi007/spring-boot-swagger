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
@Table(name = "BOOK_NEW")
public class BookNew {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "BOOK_CATEGORY_ID")
	@JsonIgnore
	private BookCategoryNew bookCategoryNew;

	    
	    public BookNew() {

	    }

	    public BookNew(String name) {
	        this.name = name;
	    }

	    public BookNew(String name, BookCategoryNew bookCategory) {
	        this.name = name;
	        this.bookCategoryNew = bookCategory;
	    }
	    
	    
	    
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public void setId(int id) {
			this.id = id;
		}


		public int getId() {
			return id;
		}

		public BookCategoryNew getBookCategoryNew() {
			return bookCategoryNew;
		}

		public void setBookCategoryNew(BookCategoryNew bookCategoryNew) {
			this.bookCategoryNew = bookCategoryNew;
		}

		
	    
	
}
