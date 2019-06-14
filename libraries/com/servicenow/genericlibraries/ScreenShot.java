package com.servicenow.genericlibraries;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenShot {
	private static Logger log = Logger.getLogger("Initiating ScreenShot of the application");

	public static String takeFullScreenShot(String name){
        try {
		       Robot robot = new Robot();
		       String fileName = System.getProperty("user.dir")+Capabilities.getPropertyValue("SnapShotDir")+name+"."+ Capabilities.getPropertyValue("SnapShotType");
		       System.out.println("Workspace path: "+System.getProperty("user.dir"));
		       System.out.println("Directory path: "+ fileName);
		       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		       Rectangle captureRect = new Rectangle(0, 0, screenSize.width, screenSize.height);
		       BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
		       ImageIO.write(screenFullImage, Capabilities.getPropertyValue("SnapShotType"), new File(fileName));
		       ReporterLogs.log("screenshot was saved in location : " + fileName, "info");
		       return fileName;
        	}
        		catch(Exception ex){
	   						ReporterLogs.log("Exception " + ex, "error");
					        return ex.getMessage();
        					}
        		}

	
	//will be used for taking screenshot during GUI or functionality testing
	public static void takeElementScreenShot(WebDriver driver, WebElement element){
		    try {
		    	//configure the name and location of screenshot here
		        String fileName = Capabilities.getPropertyValue("SnapShotDir") + element.getTagName() + "." + Capabilities.getPropertyValue("ElementShapShotType");
		        int pointX = element.getLocation().x;
		        int pointY = element.getLocation().y;
		        int elemWidth = element.getSize().width;
		        int elemHight = element.getSize().height;
		        File fullBrowser = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        BufferedImage  fullImg = ImageIO.read(fullBrowser);
		        BufferedImage eleScreenshot= fullImg.getSubimage(pointX, pointY, elemWidth, elemHight);
		        ImageIO.write(eleScreenshot, Capabilities.getPropertyValue("ElementShapShotType"), fullBrowser);
		        FileUtils.copyFile(fullBrowser, new File(fileName));
		        ReporterLogs.log("Element screenshot was saved in location : " + fileName, "info");
		        
		    } catch (IOException ex) {
		    	ReporterLogs.log("Exception " + ex, "error");
		    }
	}
	
	public static void takeSnapShot(WebDriver webdriver, String name) {

        try {
        
        	String fileWithPath = Capabilities.getPropertyValue("SnapShotDir") + name + "." + Capabilities.getPropertyValue("SnapShotType"); 
        	
        	//Convert web driver object to TakeScreenshot
        	TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        	//Call getScreenshotAs method to create image file
        	File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination
        	File DestFile=new File(fileWithPath);

            //Copy file at destination
        	FileUtils.copyFile(SrcFile, DestFile);

        }catch(IOException ex){
        	ReporterLogs.log("Exception " + ex, "error");
        }

    }
	//Creating a method getScreenshot and passing two parameters 
	//driver and screenshotName
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
	                //below line is just to append the date format with the screenshot name to avoid duplicate names		
	                String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
	                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
			String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
	                //Returns the captured file path
			return destination;
	}

}
