package com.rabi.swagger.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rabi.swagger.model.Book;
import com.rabi.swagger.model.Note;
import com.rabi.swagger.model.Reminder;
@Repository
public class ReminderRepoByJDBC {
	@Autowired
   private JdbcTemplate template;
	
    
	//It is JPA Object Not Database Table!!! Be Careful for Column!!!
	private String UPDATE_QUERY_REMINDER = "UPDATE REMINDER SET NAME = ? WHERE ID=? ";
    private String GET_QUERY_SEARCH_BY_REMINDER_ID = "SELECT * FROM REMINDER  WHERE ID=?";
    private String GET_QUERY_SEARCH_BY_USER_ID = "SELECT * FROM REMINDER  WHERE USER_REM_ID=?";
    private String GET_QUERY_SEARCH_BY_REM_ID_USER_ID = "SELECT * FROM REMINDER  WHERE ID=? AND USER_REM_ID=?";	
    private String DELETE_QUERY_REMINDER = "DELETE FROM  REMINDER WHERE ID=? ";
    
    
    
    public List<Reminder> updateReminderByRemId(Reminder reminder,int remId) {
		  template.update(UPDATE_QUERY_REMINDER,reminder.getName(),remId);
		 return getReminderByRemId(remId);
		 
  }
    
    
    public List<Reminder> deleteReminderByRemId(int remId) {
		  template.update(DELETE_QUERY_REMINDER,remId);
		 return getReminderByRemId(remId);
		 
}


    
    
	
	public List<Reminder> getReminderByRemId(int remId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_REMINDER_ID,new Object[]{remId},
                		(rs, rowNum) ->
                        new Reminder(rs.getInt("id"),rs.getString("name"))
                        		
        				);
    }
	
	
	public List<Reminder> getReminderByUserId(int userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_USER_ID,new Object[]{userId},
                		(rs, rowNum) ->
                        new Reminder(rs.getInt("id"),rs.getString("name"))
                        		
        				);
    }
	
	public List<Reminder> getReminderByRemIdUserId(int remId,int userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_REM_ID_USER_ID,new Object[]{remId,userId},
                		(rs, rowNum) ->
                        new Reminder(rs.getInt("id"),rs.getString("name"))
                        		
        				);
    }
	
	
	
	
}
