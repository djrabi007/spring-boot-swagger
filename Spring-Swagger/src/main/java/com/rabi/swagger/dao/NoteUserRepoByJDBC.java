package com.rabi.swagger.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rabi.swagger.model.Book;
import com.rabi.swagger.model.Note;
@Repository
public class NoteUserRepoByJDBC {
	@Autowired
   private JdbcTemplate template;
	
    private String GET_QUERY_SEARCH_BY_NOTE_ID = "SELECT * FROM NOTE  WHERE NOTE_ID=?";	//noteId
    private String GET_QUERY_SEARCH_BY_USER_ID = "SELECT * FROM NOTE  WHERE USER_NOTE_ID=?";	
    private String GET_QUERY_SEARCH_BY_NOTE_ID_USER_ID = "SELECT * FROM NOTE WHERE NOTE_ID=? AND USER_NOTE_ID=?";
    private String UPDATE_QUERY_NOTE_USER = "UPDATE NOTE SET NOTE_TITLE = ? WHERE NOTE_ID=? AND USER_NOTE_ID=?";//noteTitle
    private String DELETE_QUERY_NOTE_USER = "DELETE FROM  NOTE WHERE USER_NOTE_ID=?";
    private String DELETE_QUERY_NOTE_NOTE_ID_USER_ID = "DELETE FROM  NOTE WHERE NOTE_ID=? AND USER_NOTE_ID=?";
    
    //NOTE_ID,NOTE_TITLE

    //  JDBC ----GET
	public Note searchNoteByNoteId(int noteId) {
		return template.queryForObject(GET_QUERY_SEARCH_BY_NOTE_ID,
				new Object[]{noteId},new BeanPropertyRowMapper<>(Note.class));
	}
//	public List<Note> searchNoteByUserId(int userId) {
//		return template.queryForObject(GET_QUERY_SEARCH_BY_USER_ID,
//				new Object[]{userId},new BeanPropertyRowMapper<>(Note.class));
//	}
	
	public List<Note> searchNoteByUserId(String userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_USER_ID,new Object[]{userId},
                (rs, rowNum) ->
                        new Note(
                                rs.getInt("NOTE_ID"),  //NOTE_ID,NOTE_TITLE
                                rs.getString("NOTE_TITLE")
                        		)
        );
    }
	
	
	public List<Note> searchNoteByNoteIdUserId(int noteId,String userId) {
        return template.query(
        		GET_QUERY_SEARCH_BY_NOTE_ID_USER_ID,new Object[]{noteId,userId},
                (rs, rowNum) ->
                        new Note(
                                rs.getInt("NOTE_ID"),   //NOTE_ID,NOTE_TITLE
                                rs.getString("NOTE_TITLE")
                        		)
        );
    }
	
	
	
	public List<Note> updateAllNotesByNoteIdUserId(Note note,int noteId,String userId) {
		  template.update(UPDATE_QUERY_NOTE_USER,note.getNoteTitle(),noteId,userId);
		 return searchNoteByNoteIdUserId(noteId,userId);
		 
    }
	
	public List<Note> deleteAllNotesByUserId(String userId) {
		  template.update(DELETE_QUERY_NOTE_USER,userId);
		 return searchNoteByUserId(userId);
		 
  }
	
	public List<Note> deleteAllNotesByNoteIdUserId(int noteId,String userId) 
	{
		  template.update(DELETE_QUERY_NOTE_NOTE_ID_USER_ID,noteId,userId);
		 return searchNoteByUserId(userId);

	}
	
	
//	public Note searchNoteByNoteIdUserId(int noteId,int userId) {
//		return template.queryForObject(GET_QUERY_SEARCH_BY_NOTE_ID_USER_ID,
//				new Object[]{noteId,userId},new BeanPropertyRowMapper<>(Note.class));
//	}
	

}
