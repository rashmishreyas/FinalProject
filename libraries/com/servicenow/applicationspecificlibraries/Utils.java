package com.servicenow.applicationspecificlibraries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.servicenow.genericlibraries.ReporterLogs;

public class Utils {
	
	public static String getCurrentDateTime() throws Exception {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	 
	 //get current date time with Date()
	 Date date = new Date();
	 // Now format the date
	 String date1= dateFormat.format(date);
	 // Print the Date
	 ReporterLogs.log("Current date and time in yyyy-MM-dd HH:mm:ss is "+date1, "info");
	 return date1;			
}
	
	/*
	 * Author : Samujjal Das Choudhury
	 * Objective : Scroll to the required web element
	 */
	public static void scrollingToElementofAPage(WebElement element, WebDriver driver,String elementName)
	{
	   try
	      {
		       ReporterLogs.log("Scrolling to element " + elementName, "info");  
			   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
	         }
	   catch(Exception e){
		   ReporterLogs.log("Unable to Scroll to element " + elementName, "error");
		   ReporterLogs.log("Exception " + e.getMessage(), "error");
		  
	   }
}
	
	/*
	 * Author : Samujjal Das Choudhury
	 * Objective : Get future date and time
	 */
	public static String getDesiredDateAndTime(int days){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
	        c.add(Calendar.MONTH, days);
	        c.add(Calendar.DATE, days);
	        c.add(Calendar.HOUR, days);
	        c.add(Calendar.MINUTE, days);
	        c.add(Calendar.SECOND, days);
	        // convert calendar to date
	        Date currentDatePlusDays = c.getTime();     
		return dateFormat.format(currentDatePlusDays);	
	}

}