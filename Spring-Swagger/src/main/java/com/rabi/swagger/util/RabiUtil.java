package com.rabi.swagger.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.rabi.swagger.exception.ValidationException;

public class RabiUtil {
	
	
	private static final String SLASH = "/";
	private static final String ERROR = ".ERROR";
	private static final String COLON = ":";
	private static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd-HH.mm.ss.S";
	private static final String TXT_ONLY = ".txt";
	private static final String PDF_ONLY = ".pdf";
	private static final String DOCX_ONLY = ".docx";

	public static void main(String[] args) 
	{
		exampleDecimal();
		exampleLeftPad();
		exampleFileProcessingNIO();
		
		
	}
	
	private static void exampleFileProcessingNIO()
	{
		String folderLocation="C:\\WORKSPACE\\Raja\\WORK";
		boolean isFileOnly=false;
		System.out.println("******************FOLDER ONLY *******");
		isFileOnly=false;
		List<String> folderList=getFileOrDirectoryList(folderLocation,isFileOnly);
		folderList.forEach(System.out::println);
		System.out.println("******************FILE ONLY *******");
		isFileOnly=true;
		List<String> fileList=getFileOrDirectoryList(folderLocation,isFileOnly);
		fileList.forEach(System.out::println);
		System.out.println("******************TEXT FILE ONLY *******");
		List<String> textFileList=getTextOrPDForDOCFileOnly(fileList,TXT_ONLY);
		textFileList.forEach(System.out::println);
		System.out.println("******************PDF FILE ONLY *******");
		List<String> pdfFileList=getTextOrPDForDOCFileOnly(fileList,PDF_ONLY);
		pdfFileList.forEach(System.out::println);
		
		System.out.println("******************Document FILE ONLY *******");
		List<String> documentFileList=getTextOrPDForDOCFileOnly(fileList,DOCX_ONLY);
		documentFileList.forEach(System.out::println);
		
		System.out.println("******************List<String> to List<File>  with +without Java8*******");
		System.out.println("******************Text File after rename*******");
		List<File> finalTextfileList=listOfFilePrepareWithJava8(textFileList);
		
		List<File> renameTextfileList=appendTimeStampToFileNameWithJava8(finalTextfileList);
		renameTextfileList.forEach(f->System.out.println(f.getName()));
		
		System.out.println("******************Write to Error File*******");
		String basePath="C:\\WORKSPACE\\Raja\\ERROR";
		String fileName ="Mohonbagan";
		String CorrelationId="678Rabi:ffh5555:6722";
		StringBuffer errorRecord =new StringBuffer();
		errorRecord.append("EEEEEEEEEEEEEEEEEE");
		writeToErrorFile(basePath,fileName,CorrelationId,errorRecord);
		
System.out.println("******************DELETe file+Folder from Directory*******");
		
		//Delete  "1" directory and all the file inside it 
		//deleteFilesAndFolderFromDirectory(folderLocation+"\\1");//f.delete()
		
		
		
		
	}
	
	public static void deleteFilesAndFolderFromDirectory(String rootDirPath)
	{
		
		Path rootPath= Paths.get(rootDirPath);
		
		try
		{
			Files
			.walk(rootPath,FileVisitOption.FOLLOW_LINKS)
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach(
					f->
					{
						if(!f.getAbsolutePath().equals(rootPath))
						{
							f.delete();
						}
					}
					
					);
			
		}
		catch (IOException e) {
		}
		
	}
	
	
	
	
	public static synchronized void 
	writeToErrorFile(String basePath,String fileName
			,String CorrelationId,StringBuffer errorRecord)
	{
		File errorFile=null;
		String[] correlationArray= CorrelationId.split(COLON);
		String fileNamewithBusinessDate= fileName+"_"+correlationArray[0]+ERROR;
		
		try
		{
			errorFile =new File(basePath+SLASH+fileNamewithBusinessDate);
			if(!errorFile.exists())
			{
				errorFile.createNewFile();
			}
		}
		catch (IOException e)
		{
		}
		
		try(BufferedWriter bw= new BufferedWriter(new FileWriter(errorFile,true)))
		{
			bw.write(errorRecord.toString());
		}
		catch (IOException e)
		{
		}
	}
	
	public static List<File> appendTimeStampToFileNameWithJava8(List<File> fileList)
	{
		List<File> newFileList= new ArrayList<>();
		SimpleDateFormat dateFormat= new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_S);
		fileList.forEach(f->
				{
					f.renameTo(new File(f.getAbsolutePath()+"_"+dateFormat.format(new Date())));
					newFileList.add(f);
					
				}
		);
		
		return newFileList;
		
	}
	public static List<File> appendTimeStampToFileNameWithoutJava8(List<File> fileList)
	{
		List<File> newFileList= new ArrayList<>();
		SimpleDateFormat dateFormat= new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_S);
		
		for(File f:fileList)
		{
					f.renameTo(new File(f.getAbsolutePath()+"_"+dateFormat));
					newFileList.add(f);
					
		}
		
		return newFileList;
		
	}
	
	
	public static List<File> listOfFilePrepareWithJava8(List<String> fileList)
	{
		List<File> finalfileList=new ArrayList<>();
		fileList.forEach(f->finalfileList.add(new File(f)));
		return finalfileList;
	}
	public static List<File> listOfFilePrepareWithoutJava8(List<String> fileList)
	{
		List<File> finalfileList=new ArrayList<>();
		for(String st:fileList)
		{
			finalfileList.add(new File(st));
		}
		return finalfileList;
	}
	
	public static List<String> getTextOrPDForDOCFileOnly(List<String> fileList,String fileExtension)
	{
		return fileList.stream().filter(f->f.endsWith(fileExtension)).collect(Collectors.toList());
	}
	
	
	
	public static List<String> getFileOrDirectoryList(String folderLocation,boolean isFileOnly)
	{
		List<String> fileList= new ArrayList<>();
		
		try(Stream<Path> walk=Files.walk(Paths.get(folderLocation)))
		{
			if(isFileOnly)
			{
			fileList=walk.filter(Files::isRegularFile)
					.map(x->x.toString()).collect(Collectors.toList());
			}
			else
			{
				fileList=walk.filter(Files::isDirectory)
						.map(x->x.toString()).collect(Collectors.toList());
				
			}
			
		}
		catch (IOException e)
		{
		e.printStackTrace();
		}
		
		return fileList;
	}
	
	
	private static void exampleDecimal()
	{
		
		String st1=String.format("My answer is %.3f", 47.65734);//47.657
		String st2=String.format("My answer is %15.4f", 47.65734);//47.6573
		//Used in CIN format
		String st3=String.format("My answer is %011d", 56789);//00000056789
		System.out.println("st1 ="+st1);
		System.out.println("st2 ="+st2);
		System.out.println("st3 ="+st3);
		
		String accNo="1151111111111111111111111222222222";
		String formatAcctNumber=accNo.substring(0,4);//last 4 digit
		System.out.println("formatAcctNumber ="+formatAcctNumber);//1151
		
	}

	
	private static void exampleLeftPad()
	{
	
		String routerNumber="0000000000000000004545678900";
		int digit=2;
		//Remove Leading Zero
		String formattedRouterNumb=StringUtils.leftPad(routerNumber, digit,"0");
		System.out.println("formattedRouterNumb ="+formattedRouterNumb);//No change
		
		System.out.println(StringUtils.leftPad("0123456789", 10,"9"));//No Change/padding
		System.out.println(StringUtils.leftPad("789", 10,"9"));//filled/padding with 9 and make 10 digit  i.e. 9999999789
		System.out.println(StringUtils.leftPad("789", 10,"X"));//filled/padding with X  and make 10 digit  i.e. XXXXXXX789
		System.out.println(StringUtils.leftPad("789", 3,"X"));//NO Padding
		
	}
	
	
	
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
