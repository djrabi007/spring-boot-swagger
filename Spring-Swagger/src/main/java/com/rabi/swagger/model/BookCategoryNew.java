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
@Table(name = "BOOK_CATEGORY")
public class BookCategoryNew {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private String name;

    @OneToMany(mappedBy = "bookCategoryNew",fetch=FetchType.LAZY,
    		cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BookNew> booksNew;

    public BookCategoryNew(){

    }

	public BookCategoryNew(String name) {
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

	public Set<BookNew> getBooks() {
		return booksNew;
	}

	public void setBooks(Set<BookNew> books) {
		this.booksNew = books;
	}

	@Override
    public String toString() {
        String result = String.format(
                "Category[id=%d, name='%s']%n",
                id, name);
        if (booksNew != null) {
            for(BookNew bookNew : booksNew) {
                result += String.format(
                        "Book[id=%d, name='%s']%n",
                        bookNew.getId(), bookNew.getName());
            }
        }    
        return result;
    }
}