package testcase;

import org.testng.annotations.Test;

import com.servicenow.genericlibraries.JSONReadFromFile;

public class readjson {

	@Test
	public void Test(){
		JSONReadFromFile.getJSONData();
	}
	
}
