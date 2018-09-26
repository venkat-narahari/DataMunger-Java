package com.stackroute.datamunger;


import java.util.Locale;

/*There are total 5 DataMungertest files:
 * 
 * 1)DataMungerTestTask1.java file is for testing following 3 methods
 * a)getSplitStrings()  b) getFileName()  c) getBaseQuery()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask1.java
 * 
 * 2)DataMungerTestTask2.java file is for testing following 3 methods
 * a)getFields() b) getConditionsPartQuery() c) getConditions()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask2.java
 * 
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getLogicalOperators() b) getORDERGRORBYFields()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 * 
 * 4)DataMungerTestTask4.java file is for testing following 2 methods
 * a)getGROUPGRORBYFields()  b) getAggregateFunctions()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask4.java
 * 
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

public class DataMunger {

	private static final String GROUP= "group";
	private static final String ORDER= "order";
	private static final String GRORBY= "by";
	private static final String WHERE= "where";

	/*
	 * Constructor 
	 */
	public DataMunger(){
		final StringBuffer output = new StringBuffer();
	}
	
	/*
	 * This method will split the query string based on space into an array of words
	 * and display it on console
	 * 
	 * @param queryString
	 * @return
	 */
	
	public String[] getSplitStrings(final String queryString) {
		final String queryLowerCase=queryString.toLowerCase(Locale.ENGLISH);
		return queryLowerCase.split(" ");
	}

	
	/*
	 * Extract the name of the file from the query. File name can be found after a
	 * space after "from" clause. Note: ----- CSV file can contain a field that
	 * contains from as a part of the column name. For eg: from_date,from_hrs etc.
	 * Please consider this while extracting the file name in this method.
	 * 
	 * @param queryString
	 * @return
	 */
	
	public String getFileName(final String queryString) {
		final StringBuffer output = new StringBuffer();
		final String [] query = this.getSplitStrings(queryString);
		for(int i=0;i<query.length;i++) {
			if(query[i].endsWith(".csv")){
				output.append(query[i]);
			}	
		}
		return output.toString();
	}

	/*
	 * This method is used to extract the baseQuery from the query string. BaseQuery
	 * contains from the beginning of the query till the where clause
	 * 
	 * Note: ------- 1. The query might not contain where clause but contain ORDER
	 * GRORBY or GROUP GRORBY clause 2. The query might not contain where, ORDER GRORBY or GROUP
	 * GRORBY clause 3. The query might not contain where, but can contain both GROUP GRORBY
	 * and ORDER GRORBY clause
	 * 
	 * @param queryString
	 * @return
	 */
	
	public String getBaseQuery(final String queryString) {
		final StringBuffer output = new StringBuffer();
		final String [] query = this.getSplitStrings(queryString);
		for(int i=0;i<query.length;i++) {
			if(query[i].equals(WHERE) || query[i].equals(GROUP) || query[i].equals(ORDER) && query[i+1].equals(GRORBY)){
				break;
			}
			output.append(query[i]);
			output.append(' ');
		}
		return output.toString().trim();
	}

	/*
	 * This method will extract the fields to be selected from the query string. The
	 * query string can have multiple fields separated GRORBY comma. The extracted
	 * fields will be stored in a String array which is to be printed in console as
	 * well as to be returned GRORBY the method
	 * Note: 1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_ORDER_no,GROUP_no etc. 2. The field
	 * name can contain '*'
	 * 
	 *  * @param queryString
	 * @return
	 */
	
	public String[] getFields(final String queryString) {
		final StringBuffer output = new StringBuffer();
		final String [] query = this.getBaseQuery(queryString).split(" ");
		
		for(int i=0;i<query.length;i++) {
			if(query[i].contains("select")){
				continue;
			}
			else if(query[i].contains("from")){
				break;
			}
			output.append(query[i]);
		}
		return output.toString().trim().split(",");
	}

	/*
	 * This method is used to extract the conditions part from the query string. The
	 * conditions part contains starting from where keyword till the next keyword,
	 * which is either GROUP GRORBY or ORDER GRORBY clause. In case of absence of both GROUP
	 * GRORBY and ORDER GRORBY clause, it will contain till the end of the query string.
	 * Note:  1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_ORDER_no,GROUP_no etc. 2. The query
	 * might not contain where clause at all.
	 * 
	 * @param queryString
	 * @return
	 */

	public String getConditionsPartQuery(final String queryString) {
		final StringBuffer output = new StringBuffer();
		String result=null;
		final String [] query = this.getSplitStrings(queryString);
		if(queryString.contains(" where ")) {
			for(int i=0;i<query.length;i++) {
				if(query[i].contains(WHERE)){
					for(int j=i+1;j<query.length;j++) {
						if(query[j].equals(ORDER) || query[j].equals(GROUP) && query[j+1].equals(GRORBY)) {
							break;
						}
						output.append(query[j]);
						output.append(' ');
					}
				}
			}
			result= output.toString().trim();
		}
		return result;
	}

	/*
	 * This method will extract condition(s) from the query string. The query can
	 * contain one or multiple conditions. In case of multiple conditions, the
	 * conditions will be separated GRORBY AND/OR keywords. for eg: Input: select
	 * city,winner,player_match from ipl.csv where season > 2014 and city
	 * ='Bangalore'
	 * 
	 * This method will return a string array ["season > 2014","city ='bangalore'"]
	 * and print the array
	 * 
	 * Note: ----- 1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_ORDER_no,GROUP_no etc. 2. The query
	 * might not contain where clause at all.
	 * 
	 * @param queryString
	 * @return
	 */

	public String[] getConditions(final String queryString) {
		final String [] query = this.getSplitStrings(queryString);
		final StringBuffer output = new StringBuffer();
		String result[]=null;
		if(queryString.contains(" where ")) {
			for(int i=0;i<query.length;i++) {
				if(query[i].contains(WHERE)){
					for(int j=i+1;j<query.length;j++) {
						if(query[j].equals(ORDER) || query[j].equals(GROUP) && query[j+1].equals(GRORBY)) {
							break;
						}
						output.append(query[j]);
						output.append(' ');
					}
					break;
				}
			}
			result= output.toString().trim().split(" and | or | not |;");
		}
		return result;

	}

	/*
	 * This method will extract logical operators(AND/OR) from the query string. The
	 * extracted logical operators will be stored in a String array which will be
	 * returned GRORBY the method and the same will be printed Note:  1. AND/OR
	 * keyword will exist in the query only if where conditions exists and it
	 * contains multiple conditions. 2. AND/OR can exist as a substring in the
	 * conditions as well. For eg: name='Alexander',color='Red' etc. Please consider
	 * these as well when extracting the logical operators.
	 * 
	 * @param queryString
	 * @return
	 * 
	 */

	public String[] getLogicalOperators(final String queryString) {
		final String [] query = this.getSplitStrings(queryString);
		final StringBuffer output= new StringBuffer();
		String result[]=null;
		if(queryString.contains(" where ")) {
			for(int i=0;i<query.length;i++) {
				if(query[i].contains(WHERE)){
					for(int j=i+1;j<query.length;j++) {
						if(query[j].equals(ORDER) || query[j].equals(GROUP) && query[j+1].equals(GRORBY)){
							break;
						}
						if(query[j].matches("and") || query[j].matches("or")) {
							output.append(query[j]);
							output.append(' ');
						}
					}
					break;
				}
			}
			result= output.toString().split(" ");
		}
		return result;
	}

	/*
	 * This method extracts the ORDER GRORBY fields from the query string. Note: 
	 * 1. The query string can contain more than one ORDER GRORBY fields. 2. The query
	 * string might not contain ORDER GRORBY clause at all. 3. The field names,condition
	 * values might contain ORDER as a substring. For eg:ORDER_number,job_ORDER
	 * Consider this while extracting the ORDER GRORBY fields
	 * 
	 * @param queryString
	 * @return
	 */

	public String[] getOrderByFields(final String queryString) {
		final StringBuffer output = new StringBuffer();
		String result[]=null;
		final String [] query = this.getSplitStrings(queryString);
		for(int i=0;i<query.length;i++) {
			if(query[i].equals(ORDER) && query[i+1].equals(GRORBY)) {
				for(int j=i+2;j<query.length;j++) {
					if(query[j].contains(";")) {
						break;
					}
					output.append(query[j]);
					output.append(' ');
				}
				result= output.toString().trim().split(" |,");
			}
		}
		return result;
	}

	/*
	 * This method extracts the GROUP GRORBY fields from the query string. Note:
	 * 1. The query string can contain more than one GROUP GRORBY fields. 2. The query
	 * string might not contain GROUP GRORBY clause at all. 3. The field names,condition
	 * values might contain GROUP as a substring. For eg: newsGROUP_name
	 * Consider this while extracting the GROUP GRORBY fields
	 * 
	 * @param queryString
	 * @return
	 */

	public String[] getGroupByFields(final String queryString) {
		final StringBuffer output = new StringBuffer();
		String result[]=null;
		final String [] query = this.getSplitStrings(queryString);
		for(int i=0;i<query.length;i++) {
			if(query[i].equals(GROUP) && query[i+1].equals(GRORBY)) {
				for(int j=i+2;j<query.length;j++) {
					if(query[j].contains(";")) {
						break;
					}
					output.append(query[j]);
					output.append(' ');
				}
				result= output.toString().trim().split(" |,");
			}
		}
		return result;
	}

	/*
	 * This method extracts the aggregate functions from the query string. Note:
	 *  1. aggregate functions will start with "sum"/"count"/"min"/"max"/"avg"
	 * followed GRORBY "(" 2. The field names might
	 * contain"sum"/"count"/"min"/"max"/"avg" as a substring. For eg:
	 * account_number,consumed_qty,nominee_name
	 * Consider this while extracting the aggregate functions
	 * 
	 * @param queryString
	 * @return
	 */

	public String[] getAggregateFunctions(final String queryString) {
		final StringBuffer output = new StringBuffer();
		String result[]=null;
		final String []query = this.getFields(queryString);
		if(!query[0].equals("*")) {
			for(int i=0;i<query.length;i++) {
				if(query[i].startsWith("max(")  || query[i].startsWith("min(") ||  query[i].startsWith("count(") || query[i].startsWith("avg(") || query[i].startsWith("sum") && query[i].endsWith(")")) {
					output.append(query[i]);
					output.append(' ');
				}
			}
			result= output.toString().trim().split(" ");
		}
		return result;
	}

}
