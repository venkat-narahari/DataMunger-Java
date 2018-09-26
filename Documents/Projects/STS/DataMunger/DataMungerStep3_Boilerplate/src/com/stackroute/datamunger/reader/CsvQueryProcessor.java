package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	private String fileName;
	private String dataRow[] = null;
	BufferedReader br = null;

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		br = new BufferedReader(new FileReader(fileName));
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file. Note: Return type of the method will be
	 * Header
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
	 * getDataRow() method will be used in the upcoming assignments
	 */

	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. Note: Return Type of the method will be
	 * DataTypeDefinitions
	 */

	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		this.getSecondDataRow();
		DataTypeDefinitions dataTypeDefinitions = null;
		Integer matchInt;
		Double matchDob;
		String matchStr;
		StringBuffer data = new StringBuffer();
		String dataTypes[] = null;
		if (dataRow.length != 0) {
			for (int i = 0; i < dataRow.length; i++) {
				if (dataRow[i] == " ") {
					matchStr = dataRow[i];
					dataRow[i] = null;
					data.append(matchStr.getClass().getName().toString()).append('-');
					continue;
				} else if (!isStringInt(dataRow[i]) && !isStringDouble(dataRow[i])) {
					matchStr = dataRow[i];
					data.append(matchStr.getClass().getName().toString()).append('-');
					continue;
				} else if (isStringInt(dataRow[i])) {
					matchInt = Integer.parseInt(dataRow[i].toString());
					data.append(matchInt.getClass().getName().toString()).append('-');
					continue;
				} else if (isStringDouble(dataRow[i])) {
					matchDob = Double.parseDouble(dataRow[i].toString());
					data.append(matchDob.getClass().getName().toString()).append('-');
					continue;
				}
			}
			String result = data.toString();
			dataTypes = result.split("-");
			dataTypeDefinitions = new DataTypeDefinitions(dataTypes);
		}
		return dataTypeDefinitions;
	}

	/**
	 * Method to initialize dataRow with one value of each column
	 */
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

}
