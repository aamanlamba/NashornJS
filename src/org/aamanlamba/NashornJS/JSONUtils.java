package org.aamanlamba.NashornJS;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author aamanlamba
 * JSON utils to read JSON Object and Array from file
 * Uses org.json.simple
 */
public class JSONUtils {
	
	JSONArray jArr = new JSONArray();
	
	public void readJSONObjectFromFile(String file, List<JSONObject> jObj)   {
		
		//load json object from array
			
				JSONParser parser = new JSONParser();
				try {
					 jArr = (JSONArray)parser.parse(new FileReader("src/blockchainTest.json"));
					 for(Object o: jArr)
						 jObj.add((JSONObject) o);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
	
	public void readJSONArrayFromFile(String file, JSONArray jArr) {
		JSONParser parser = new JSONParser();
		try{
		     jArr = (JSONArray) parser.parse(new FileReader(file));
		    // TODO do something with jObj
		} catch (IOException|ParseException e) {
		    System.err.println(e.getMessage());
		}
	}
	
	
}
