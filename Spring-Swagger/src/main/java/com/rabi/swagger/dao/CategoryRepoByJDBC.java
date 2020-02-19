package com.rabi.swagger.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rabi.swagger.model.Book;
import com.rabi.swagger.model.CATEGORY;
import com.rabi.swagger.model.Note;
import com.rabi.swagger.model.Reminder;
@Repository
public class CategoryRepoByJDBC {
	@Autowired
   private JdbcTemplate template;
	
    
	//It is JPA Object Not Database Table!!! Be Careful for Column!!!
	private String UPDATE_QUERY_CATEGORY = "UPDATE CATEGORY SET NAME = ? WHERE ID=? ";
    private String GET_QUERY_SEARCH_BY_CATEGORY_ID = "SELECT * FROM CATEGORY  WHERE ID=?";
    private String GET_QUERY_SEARCH_BY_USER_ID = "SELECT * FROM CATEGORY  WHERE USER_REM_ID=?";
    private String GET_QUERY_SEARCH_BY_CAT_ID_USER_ID = "SELECT * FROM CATEGORY  WHERE ID=? AND USER_CAT_ID=?";	
    private String DELETE_QUERY_CATEGORY = "DELETE FROM  CATEGORY WHERE ID=? ";
    
    
    
    public List<CATEGORY> updateCategoryByCatId(CATEGORY category,int catId) {
		  template.update(UPDATE_QUERY_CATEGORY,category.getName(),catId);
		 return getCategoryByCatId(catId);
		 
  }
    
    
    public List<CATEGORY> deleteCategoryByCatId(int catId) {
		  template.update(DELETE_QUERY_CATEGORY,catId);
		 return getCategoryByCatId(catId);
		 
}


    
    
	
	public List<CATEGORY> getCategoryByCatId(int catId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_CATEGORY_ID,new Object[]{catId},
                		(rs, rowNum) ->
                        new CATEGORY(rs.getInt("id"),rs.getString("name"))
                        		
        				);
    }
	
	
	public List<CATEGORY> getCategoryByUserId(int userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_USER_ID,new Object[]{userId},
                		(rs, rowNum) ->
                        new CATEGORY(rs.getInt("id"),rs.getString("name"))
                        		
        				);
    }
	
	public List<CATEGORY> getCategoryByCatIdUserId(int catId,int userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_CAT_ID_USER_ID,new Object[]{catId,userId},
                		(rs, rowNum) ->
                        new CATEGORY(rs.getInt("id"),rs.getString("name"))
                        		
        				);
    }
	
	
	
	
}
