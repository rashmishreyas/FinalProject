package com.servicenow.genericlibraries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Capabilities {
	
	public static String getPropertyValue(String propertyKey) throws IOException{
		String value = "";
		File deviceProperty = new File(".\\properties\\Constants.properties");
		BufferedReader reader = new BufferedReader(new FileReader(deviceProperty));
		String line;
		Iterator<String> lines = reader.lines().iterator();
		while(lines.hasNext()){
			line=lines.next();
			if(line.contains(propertyKey)){
				value = line.split("=")[1];
			}
		}
		return value;
	}
	
}
