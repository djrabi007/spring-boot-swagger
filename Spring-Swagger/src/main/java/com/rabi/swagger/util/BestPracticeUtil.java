package com.rabi.swagger.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class BestPracticeUtil {

	private static final String DSATABASE_NOT_WORKING_DURING_EXECUION = "Dsatabase NOT Working during execuion";
	private static final String FAILURE = "FAILURE";
	private static final String STATUS = "STATUS";
	private static final String SUCCESS = "SUCCESS";
	private static final String DATA_NOT_FOUND = "Data Not Found!!";
	private static final String TWELEVE = "12";
	public  List<String>   errorList =new ArrayList<>();

	public static void main(String[] args) throws Exception {
		//Conditional Breakpoint
		executeConditionalBreakpoint();
		executeWhiteSpaceOrEmptyCheck();
		
		BestPracticeUtil bu=new BestPracticeUtil();
		bu.executeSwitchCase("Customer");
		
		executeJDBC();
	}

	private static void executeJDBC() throws ClassNotFoundException {
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/EMP";
		// Database credentials
		final String USER = "username";
		final String PASS = "password";
		final String SQL_QUERY="SELECT fname,EMP_NAME, age FROM Employees WHERE empId=?";
		PreparedStatement ps = null;
		ResultSet rs=null;
		String emp_id="345";
		String value_str="";
		List<String> errorList =new ArrayList<>();
		Map<String, String> outputValueMap=new HashMap<>();
		List<Map<String, String>> mapList=  new ArrayList<>();
		BestPracticeResponse response=new BestPracticeResponse();
		Class.forName(JDBC_DRIVER);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) 
		{
			ps=conn.prepareStatement(SQL_QUERY);
			ps.setString(1,String.valueOf(emp_id));
			rs=ps.executeQuery();
			
			//Troubleshooting...suppose you want need to know the URL ,Username from Connection!!!
			checkUserNameURLFromConnection(conn);
			
			boolean isDataPresent=false;
			while(rs.next())
			{
				//M-1
				// M1- GOOD -
				value_str=rs.getString("EMP_NAME");
				// M1- BAD
				value_str=rs.getString(2);
				
				//M-2  (should be with while(rs.next() )  ---------Start
				Map<String, String> newMap =decodeValueByMetadata(rs);
				//M-2  (should be with while(rs.next() )  ---------End
				
				
				if(StringUtils.isEmpty(value_str))
				{
					errorList.add(DATA_NOT_FOUND+"for this iteration!!");
					continue;//if Any record fail..it will iterate again from Loop!!
				}
				outputValueMap.put(STATUS, SUCCESS);
				mapList.add(outputValueMap);
				isDataPresent=true;
			}
			if(!isDataPresent) 
			{
				errorList.add(DATA_NOT_FOUND);
				outputValueMap.put(STATUS, FAILURE);
			}
			response.setCount(mapList.size());
			response.setMapList(mapList);
		}
		catch(SQLException sq)
		{	
			errorList.add(DSATABASE_NOT_WORKING_DURING_EXECUION);
			
		}
		catch(Exception e)
		{
			errorList.add(DSATABASE_NOT_WORKING_DURING_EXECUION+"Connection Issue");
		}
		finally 
		{
			//Try/Catch within FinallY!!
			try
			{
				RabiUtil.closeRsPs(ps, rs);
			}
			catch(SQLException sq)
			{
				
			}
		}
	}

	private static void checkUserNameURLFromConnection(Connection conn) throws SQLException {
		String usernameFromConn=conn.getMetaData().getUserName();
		String urlFromConn=conn.getMetaData().getURL();
	}

	private  String executeSwitchCase(String wType) {
		if(!StringUtils.isEmpty(wType)) {
			wType= wType.toLowerCase();
		}
		else {
			errorList.add(RabiConstants.W_TYPE_NOT_VALID);
		}
		
		switch(wType) {
			case RabiConstants.CUSTOMER:
						break;
			case RabiConstants.ACCOUNT:
				break;
			default:
				errorList.add(RabiConstants.W_TYPE_NOT_VALID);
				break;
		}
		
		return wType;
	}
	private static Map<String, String> decodeValueByMetadata(ResultSet rs) throws SQLException {
		int cloumnCount=rs.getMetaData().getColumnCount();
		Map<String, String> outputValueMap =new HashMap<>();
		for(int z=1;z<=cloumnCount;z++) 
		{
			String columnHeader=rs.getMetaData().getColumnName(z).toString();
			String columnValue=String.valueOf(rs.getObject(z));
			if(columnHeader.startsWith("VARIABLE_DATA"))
			{
				//Decode value
				columnValue= columnValue+"XXXXXXXXXXXXXXX";
				
			}
			outputValueMap.put(columnHeader, columnValue);
		 	
		}
		return outputValueMap;
	}

	private static void executeWhiteSpaceOrEmptyCheck() {
		String st="           ";
		//Bad Code
		if(TWELEVE.equals(st)||"".equals(st))
		{
			
		}
		//Good Code (care of null,Zero length,whitespace
		if(TWELEVE.equals(st)||StringUtils.isBlank(st))
		{
			
		}
	}

	private static void executeConditionalBreakpoint() {
		for(int i=0;i<10;i++) 
		{
			
			if(i==5) {
				System.out.println("Let's C!!");
			}
		}
	}
}
