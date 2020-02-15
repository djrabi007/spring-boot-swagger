package com.rabi.swagger.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.rabi.swagger.exception.ValidationException;

public class RabiUtil {
	
	
	
	public static void closeRsPs(PreparedStatement ps,ResultSet rs)
			throws SQLException
	{
		if(rs!=null && !rs.isClosed())
		{
			rs.close();
		}
		if(ps!=null && !ps.isClosed())
		{
			ps.close();
		}
		
	}

	public static String convertJavaToSQLDate(final String stringDate) throws ParseException {
		String sqlDateStr="";
		
		try {
		java.sql.Date sqlDate=java.sql.Date.valueOf(stringDate);
		sqlDateStr= new SimpleDateFormat(RabiConstants.DD_MMM_YY).format(sqlDate);
		}
		catch(RuntimeException re) {
			throw new ValidationException();
			
		}
		
		return sqlDateStr;
		
	}

}
