package com.stackroute.datamunger.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter {
	/*
	 * This method will write the resultSet object into a JSON file. On successful
	 * writing, the method will return true, else will return false
	 */
	@SuppressWarnings("rawtypes")
	public boolean writeToJson(Map resultSet) {

		Boolean bool=false;
		BufferedWriter buffer=null;
		/*
		 * Gson is a third party library to convert Java object to JSON. We will use
		 * Gson to convert resultSet object to JSON
		 */
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String result = gson.toJson(resultSet);
			/*
			 * write JSON string to data/result.json file. As we are performing File IO,
			 * consider handling exception
			 */
		
			buffer = new BufferedWriter(new FileWriter("data/result.json"));
			buffer.write(result); 
			/* close BufferedWriter object */
			bool=true;
			/* return true if file writing is successful */
			buffer.close();  
		} catch (Exception e) {
			/* return false if file writing is failed */
			bool=false;
			e.printStackTrace();
		}  
		
		return bool;
	}

}
