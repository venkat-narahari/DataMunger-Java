package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	
	private String fileName;
	private String dataRow[] = null;
	BufferedReader br = null;

	/*
	 * parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		br = new BufferedReader(new FileReader(fileName));
	}

	/*
	 * implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 */
	@Override
	public Header getHeader() throws IOException {
		br.mark(1);
		String firstLine = null;
		String headerArgs[] = null;
		String str = null;
		Header header = null;
		
		if ((str = br.readLine()) != null) {
			// read the first line
			firstLine = str;
			headerArgs = firstLine.split("[,]+");

			header = new Header(headerArgs);
		}
		br.reset();
		// populate the header object with the String array containing the header names
		return header;
	}
	

	/**
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {

	}

	/*
	 * implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
	 */
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		this.getSecondDataRow();
		DataTypeDefinitions dataTypeDefinitions = null;
		Integer matchInt;
		Double matchDob;
		String matchStr;
		Date matchDate;
		StringBuffer data = new StringBuffer();
		String dataTypes[] = null;
		if (dataRow.length != 0) {
			for (int i = 0; i < dataRow.length; i++) {
				if (isStringNull(dataRow[i])!=null) {
					dataRow[i] = null;
					Object matchObj=new Object();
					data.append(matchObj.getClass().getName()).append("@");
					continue;
				} 
				else if((matchDate=isStringDate(dataRow[i]))!=null) {
					data.append(matchDate.getClass().getName().toString()).append("@");
					System.out.println(dataRow[i]);
					continue;
				}
				
				else if (!isStringInt(dataRow[i]) && !isStringDouble(dataRow[i])) {
					matchStr = dataRow[i];
					data.append(matchStr.getClass().getName().toString()).append("@");
					continue;
				} 
				else if (isStringInt(dataRow[i])) {
					matchInt = Integer.parseInt(dataRow[i].toString());
					data.append(matchInt.getClass().getName().toString()).append("@");
					continue;
				} 
				else if (isStringDouble(dataRow[i])) {
					matchDob = Double.parseDouble(dataRow[i].toString());
					data.append(matchDob.getClass().getName().toString()).append("@");
					continue;
				}
			}
			String result = data.toString();
			dataTypes = result.split("@");
			dataTypeDefinitions = new DataTypeDefinitions(dataTypes);
		}
		return dataTypeDefinitions;
		
	}
	
	public void getSecondDataRow() {
		StringBuffer line = new StringBuffer();
		String secondLine = null;
		String str = null;
		try {
			br.mark(1);
			int count = 1;
			while ((str = br.readLine()) != null && count < 3) {
				// read the second line
				if (count == 2) {
					line.append(str).append(" ,");
				}
				count++;
			}
			// populate the header object with the String array containing the header names
			secondLine = line.toString();
			dataRow = secondLine.toString().split("[,]+");
			br.reset();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	/**
	 * Method to check if a string is can be parseable by int
	 * 
	 * @param s
	 * @return
	 */
	public boolean isStringInt(String s) {
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

	public boolean isStringDouble(String s) {
		boolean bool = false;
		try {
			Double.parseDouble(s);
			bool = true;

		} catch (NumberFormatException ex) {
			bool = false;
		}
		return bool;
	}
	
	public Date isStringDate(String s) {
		DateFormat df=null;
		Date date=null;
		if(Pattern.matches("\\d{4}-\\d{2}-\\d{2}",s)) {
			df= new SimpleDateFormat("yyy-MM-dd");
			try {
				date= df.parse(s);
				System.out.println(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}/\\d{2}/\\d{4}",s)) {
			df= new SimpleDateFormat("dd/MM/yyyy");
			try {
				date= df.parse(s);
				System.out.println(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}/\\d{2}/\\d{4}",s)) {
			df= new SimpleDateFormat("MM/dd/yyyy");
			try {
				date= df.parse(s);
				System.out.println(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}-\\w{3}-\\d{2}",s)) {
			df= new SimpleDateFormat("dd-mon-yy");
			try {
				date= df.parse(s);
				System.out.println(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}-\\d{3}-\\d{4}",s)) {
			df= new SimpleDateFormat("dd-mon-yyyy");
			try {
				date= df.parse(s);
				System.out.println(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}-\\w{5}-\\d{2}",s)) {
			df= new SimpleDateFormat("dd-month-yy");
			try {
				date= df.parse(s);
				System.out.println(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				e.printStackTrace();
			}
		}
		
		else if(Pattern.matches("\\d{2}-\\w{5}-\\d{4}",s)) {
			df= new SimpleDateFormat("dd-month-yyyy");
			try {
				date= df.parse(s);
				System.out.println(date.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch blocks
				date=null;
			}
		}
		
		
		return date;
	}	
	
	public Object isStringNull(String s) {
		Object obj=null;
		if(s.equals(" ")) {
			obj = new Object();
		}
		return obj;
	}
	

}
