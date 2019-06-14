package com.servicenow.genericlibraries;

	import java.io.FileReader;
	import java.util.Iterator;
	 
	import org.json.simple.JSONArray;
	import org.json.simple.JSONObject;
	import org.json.simple.parser.JSONParser;
	 
	/**
	 * @author Sam
	 */
	 
	public class JSONReadFromFile {
	 
	    @SuppressWarnings("unchecked")
	    public static void getJSONData() {
	        JSONParser parser = new JSONParser();
	 
	        try {
	 
	            Object obj = parser.parse(new FileReader("C:\\Users\\u6035997\\Desktop\\file1.txt"));
	 
	            JSONObject jsonObject = (JSONObject) obj;
	            String Name = (String) jsonObject.get("Name");
	            String Author = (String) jsonObject.get("Author");
	            JSONArray CompanyList = (JSONArray) jsonObject.get("Company List");
	            ReporterLogs.log("Name: " + Name, "info");
	            ReporterLogs.log("Author: " + Author, "info");
	            ReporterLogs.log("\n Company List :", "info");
	            Iterator<String> iterator = CompanyList.iterator();
	            while (iterator.hasNext()) {
	            	ReporterLogs.log(iterator.next());
	            }
	 
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

