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
	private String UPDATE_QUERY_CATEGORY = "UPDATE CATEGORY SET CATEGORY_NAME = ?,CATEGORY_DESCRIPTION=? WHERE CATEGORY_ID=? "; //CATEGORY_ID,CATEGORY_NAME
    private String GET_QUERY_SEARCH_BY_CATEGORY_ID = "SELECT * FROM CATEGORY  WHERE CATEGORY_ID=?";
    private String GET_QUERY_SEARCH_BY_USER_ID = "SELECT * FROM CATEGORY  WHERE USER_CAT_ID=?";
    private String GET_QUERY_SEARCH_BY_CAT_ID_USER_ID = "SELECT * FROM CATEGORY  WHERE CATEGORY_ID=? AND USER_CAT_ID=?";	
    private String DELETE_QUERY_CATEGORY = "DELETE FROM  CATEGORY WHERE CATEGORY_ID=? ";
    private String DELETE_QUERY_CATEGORY_USER = "DELETE FROM  CATEGORY WHERE CATEGORY_ID=? AND USER_CAT_ID=?";
    
    
    
    public List<CATEGORY> updateCategoryByCatId(CATEGORY category,int catId) {
		  template.update(UPDATE_QUERY_CATEGORY,category.getCategoryName(),category.getCategoryDescription(),catId);
		 return getCategoryByCatId(catId);
		 
  }
    
    
    public List<CATEGORY> deleteCategoryByCatId(int catId)
    {
		  template.update(DELETE_QUERY_CATEGORY,catId);
		 return getCategoryByCatId(catId);
		 
    }
    
    public List<CATEGORY> deleteCategoryByCatIdUserId(int catId,String userId)
    {
		  template.update(DELETE_QUERY_CATEGORY_USER,catId,userId);
		 return getCategoryByCatId(catId);
		 
    }



    
    
	
	public List<CATEGORY> getCategoryByCatId(int catId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_CATEGORY_ID,new Object[]{catId},
                		(rs, rowNum) ->
                        new CATEGORY(rs.getInt("CATEGORY_ID"),rs.getString("CATEGORY_NAME"))  //CATEGORY_ID,CATEGORY_NAME
                        		
        				);
    }
	
	
	public List<CATEGORY> getCategoryByUserId(String userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_USER_ID,new Object[]{userId},
                		(rs, rowNum) ->
        		 new CATEGORY(rs.getInt("CATEGORY_ID"),rs.getString("CATEGORY_NAME"))  //CATEGORY_ID,CATEGORY_NAME
                        		
        				);
    }
	
	public List<CATEGORY> getCategoryByCatIdUserId(int catId,String userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_CAT_ID_USER_ID,new Object[]{catId,userId},
                		(rs, rowNum) ->
        		 new CATEGORY(rs.getInt("CATEGORY_ID"),rs.getString("CATEGORY_NAME"))  //CATEGORY_ID,CATEGORY_NAME
                        		
        				);
    }
	
	
	
	
}
