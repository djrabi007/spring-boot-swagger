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
	
    
	//It is JPA Object Not Database Table!!! Be Careful for Column... reminderName(JAVA POJO )==>REMINDER_NAME (JDBC)==>REM_NAME (DATABASE)!!!
	private String UPDATE_QUERY_REMINDER = "UPDATE REMINDER "
											+ "SET REMINDER_NAME = ? ,"
											+ "REMINDER_Type= ?, REMINDER_DESCRIPTION=? "
											+ "WHERE REMINDER_ID=? ";
    private String GET_QUERY_SEARCH_BY_REMINDER_ID = "SELECT * FROM REMINDER  WHERE REMINDER_ID=?";
    private String GET_QUERY_SEARCH_BY_USER_ID = "SELECT * FROM REMINDER  WHERE USER_REM_ID=?";
    private String GET_QUERY_SEARCH_BY_REM_ID_USER_ID = "SELECT * FROM REMINDER  WHERE REMINDER_ID=? AND USER_REM_ID=?";	
    private String DELETE_QUERY_REMINDER = "DELETE FROM  REMINDER WHERE REMINDER_ID=? ";
    private String DELETE_QUERY_REMINDER_USER = "DELETE FROM  REMINDER WHERE REMINDER_ID=? AND USER_REM_ID=? ";
    
    
    
    public List<Reminder> updateReminderByRemId(Reminder reminder,int remId) {
		  template.update(UPDATE_QUERY_REMINDER,reminder.getReminderName(),reminder.getReminderType(),reminder.getReminderDescription(), remId);
		 return getReminderByRemId(remId);
		 
  }
    
    
    public List<Reminder> deleteReminderByRemId(int remId)
    {
		  template.update(DELETE_QUERY_REMINDER,remId);
		 return getReminderByRemId(remId);
    }
    
    public List<Reminder> deleteReminderByRemIdUserId(String userId,int remId)
    {
		  template.update(DELETE_QUERY_REMINDER_USER,remId,userId);
		 return getReminderByRemId(remId);
    }


    
    
	
	public List<Reminder> getReminderByRemId(int remId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_REMINDER_ID,new Object[]{remId},
                		(rs, rowNum) ->
                        new Reminder(rs.getInt("REMINDER_ID"),rs.getString("REMINDER_NAME"))
                        		
        				);
    }
	
	
	public List<Reminder> getReminderByUserId(String userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_USER_ID,new Object[]{userId},
                		(rs, rowNum) ->
                        new Reminder(rs.getInt("REMINDER_ID"),rs.getString("REMINDER_NAME"))
                        		
        				);
    }
	
	public List<Reminder> getReminderByRemIdUserId(int remId,String userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_REM_ID_USER_ID,new Object[]{remId,userId},
                		(rs, rowNum) ->
                        new Reminder(rs.getInt("REMINDER_ID"),rs.getString("REMINDER_NAME"))
                        		
        				);
    }
	
	
	
	
}
