package com.servicenow.genericlibraries;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class CaptureScreenShot {
	
	
	
private static final DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd SSS");
	
	public static String captureScreen(WebDriver driver, String screenName) throws IOException{
		
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		
		String dest = System.getProperty("user.dir")+"//test-output//"+screenName+".png";
		
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		
		return dest;
	}
	
	public static String generateFileName(ITestResult result){
		Date date = new Date();
		String fileName = result.getName()+ "_" + dateFormat.format(date);
		return fileName;
		
	}
	

}
