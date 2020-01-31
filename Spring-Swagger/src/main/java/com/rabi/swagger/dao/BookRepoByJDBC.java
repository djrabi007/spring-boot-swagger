package com.rabi.swagger.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rabi.swagger.model.Book;
@Repository
public class BookRepoByJDBC {
	@Autowired
   private JdbcTemplate template;
	
    private String GET_QUERY_SEARCH = "SELECT * FROM Book WHERE bookid=?";	
	private String INSERT_QUERY_ADD = "INSERT INTO Book VALUES(?,?,?)";
	private String DELETE_QUERY_REMOVE = "DELETE FROM Book WHERE bookid =?";
	
	//  JDBC ----GET
	public Book searchBookByJdbc(int bookId) {
		return template.queryForObject(GET_QUERY_SEARCH,
				new Object[]{bookId},new BeanPropertyRowMapper<>(Book.class));
	}
	
	//  JDBC ----POST
	public int saveBookByJdbc(Book bb){
        return template.update(INSERT_QUERY_ADD,bb.getBookid(),bb.getBookname(),bb.getPrice());
	}
	
	//	JDBC ----DELETE
	public int deleteBookByJdbc(int bookId){
    return template.update(DELETE_QUERY_REMOVE,bookId);
    }

}
