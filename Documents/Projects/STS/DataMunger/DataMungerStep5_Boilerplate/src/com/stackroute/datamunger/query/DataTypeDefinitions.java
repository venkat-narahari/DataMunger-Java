package com.stackroute.datamunger.query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/*
 * Implementation of DataTypeDefinitions class. This class contains a method getDataTypes() 
 * which will contain the logic for getting the datatype for a given field value. This
 * method will be called from QueryProcessors.   
 * In this assignment, we are going to use Regular Expression to find the 
 * appropriate data type of a field. 
 * Integers: should contain only digits without decimal point 
 * Double: should contain digits as well as decimal point 
 * Date: Dates can be written in many formats in the CSV file. 
 * However, in this assignment,we will test for the following date formats('dd/mm/yyyy',
 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
 */
public class DataTypeDefinitions {

	//method stub
	public static Object getDataType(final String input) {
		Integer matchInt;
		// checking for Integer
		Double matchDob;
		// checking for floating point numbers
		String matchStr;
		// checking for strings
		Date matchDate;
		// checking for date format dd/mm/yyyy | mm/dd/yyyy | dd-mon-yy | dd-mon-yyyy | dd-month-yy | dd-month-yyyy | yyyy-mm-dd

		final StringBuffer dataType = new StringBuffer();
		if (input!=null) {
			if (isStringNull(input)==" ") {
				final Object matchObj=new Object();
				dataType.append(matchObj.getClass().getName());
			} 
			else if((matchDate=isStringDate(input))!=null) {
				dataType.append(matchDate.getClass().getName().toString());
			}
			
			else if (!isStringInt(input) && !isStringDouble(input) && isStringDate(input)==null && isStringNull(input)==null) {
				matchStr = input;
				dataType.append(matchStr.getClass().getName().toString());
			} 
			else if (isStringInt(input)) {
				matchInt = Integer.parseInt(input.toString());
				dataType.append(matchInt.getClass().getName().toString());
			} 
			else if (isStringDouble(input)) {
				matchDob = Double.parseDouble(input.toString());
				dataType.append(matchDob.getClass().getName().toString());
			}
		}
		else if(input==null) {
			if (isStringNull(input)==null) {
				final Object matchObj=new Object();
				dataType.append(matchObj.getClass().getName());
			} 
		}
		return dataType.toString();
	}
	
	/**
	 * Method to check if a string is can be parseable by int
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isStringInt(final String s) {
		boolean bool = false;
		try {
			Integer.parseInt(s);
			bool = true;
		} catch (NumberFormatException nfe) {
			bool = false;
		}
		return bool;
	}

	/**
	 * Method to check if a string is can be parseable by double
	 * 
	 * @param s
	 * @return
	 */

	public static boolean isStringDouble(final String s) {
		boolean bool = false;
		try {
			Double.parseDouble(s);
			bool = true;

		} catch (NumberFormatException ex) {
			bool = false;
		}
		return bool;
	}
	
	public static Date isStringDate(final String s) {
		DateFormat df=null;
		Date date=null;
		if(Pattern.matches("\\d{4}-\\d{2}-\\d{2}",s)) {
			df= new SimpleDateFormat("yyy-MM-dd");
			try {
				date= df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}/\\d{2}/\\d{4}",s)) {
			df= new SimpleDateFormat("dd/MM/yyyy");
			try {
				date= df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}/\\d{2}/\\d{4}",s)) {
			df= new SimpleDateFormat("MM/dd/yyyy");
			try {
				date= df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}-\\w{3}-\\d{2}",s)) {
			df= new SimpleDateFormat("dd-mon-yy");
			try {
				date= df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}-\\d{3}-\\d{4}",s)) {
			df= new SimpleDateFormat("dd-mon-yyyy");
			try {
				date= df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}-\\w{5}-\\d{2}",s)) {
			df= new SimpleDateFormat("dd-month-yy");
			try {
				date= df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}-\\w{5}-\\d{4}",s)) {
			df= new SimpleDateFormat("dd-month-yyyy");
			try {
				date= df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				date=null;
			}
		}
		
		
		return date;
	}	
	
	public static Object isStringNull(final String s) {
		Object obj=null;
		if(s.equals(" ")) {
			obj = new Object();
		}
		return obj;
	}
	

	
}
