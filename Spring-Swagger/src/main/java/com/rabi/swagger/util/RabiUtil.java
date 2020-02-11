package com.rabi.swagger.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.rabi.swagger.exception.ValidationException;

public class RabiUtil {
	
	
	

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
