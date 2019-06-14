package com.servicenow.applicationspecificlibraries;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ScreenShot;

public class SuperTestNG {
	
	
	 static{
		 
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
	        System.setProperty("current.date.time", dateFormat.format(new Date()));
	    }
	
	/*
	 * Author : Samujjal Das Choudhury
	 * Objective : BeforeMethod launches the browser and presents user with Login Page
	 * 
	 */
	public static WebDriver driver = null;
	String browserType;
	@BeforeMethod
	public void preCondition() throws IOException
	{	
		browserType = Capabilities.getPropertyValue("Browser");
		ReporterLogs.log("Browser Set Up:::","info");
		ReporterLogs.log("WebDriver Instance inside the setup():::" + browserType,"info");
		if(browserType.equalsIgnoreCase("Chrome")){
			ReporterLogs.log("Inside Chrome Class Set Up method","info");
			ChromeOptions options = new ChromeOptions();
           options.addArguments("test-type");
           options.addArguments("chrome.switches", "--disable-extensions");
           options.addArguments("--disable-extensions");
           options.addArguments("start-maximized");
           options.addArguments("--enable-popup-blocking");
           options.addArguments("--disable-user-media-security=true");
           java.util.HashMap<String, Object> chromePrefs = new java.util.HashMap<String, Object>();
           chromePrefs.put("profile.default_content_settings.popups", 0);
           chromePrefs.put("download.default_directory", Capabilities.getPropertyValue("FileDownload"));
           HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
           chromePrefs.put("profile.default_content_settings.popups", 0);
           //Turns off download prompt
           chromePrefs.put("download.prompt_for_download", false);
           chromeOptionsMap.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
           options.setExperimentalOption("prefs", chromePrefs);
           options.addArguments("--test-type");
           DesiredCapabilities chromecapabilities = DesiredCapabilities.chrome();
           chromecapabilities.setBrowserName("chrome");
           chromecapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
           chromecapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
           chromecapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
           chromecapabilities.setCapability(ChromeOptions.CAPABILITY, options);
           chromecapabilities.setCapability(CapabilityType.PROXY, true);
           String ChromeDrivers = Capabilities.getPropertyValue("ChromeDrivers");
			System.setProperty("webdriver.chrome.driver", ChromeDrivers);
			driver = new ChromeDriver(chromecapabilities);
		}else if(browserType.equalsIgnoreCase("IE")){
			ReporterLogs.log("Inside IE Class Set Up method","info");
	        String IEDrivers = Capabilities.getPropertyValue("IEDrivers");
			System.setProperty("webdriver.ie.driver", IEDrivers);
			driver = new InternetExplorerDriver();
			
		}else{
			ReporterLogs.log("FF Browser","info");
			String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
			System.setProperty("webdriver.gecko.driver", FirefoxDriver);
			driver = new FirefoxDriver();
		} 
		ReporterLogs.log("Browser Launched", "info");
		driver.manage().window().maximize();
		ReporterLogs.log("Window Maximised", "info");
		driver.get(Capabilities.getPropertyValue("URL"));
		ReporterLogs.log("Navigate to the mentioned URL", "info");
		
	}
	
	
	
	/*
	 * Author : Sathyanarayanan V
	 * AfterMethod captures screenshots on failure. Also in case of Pass it logsout of the application
	 * 
	 */
	@AfterMethod
	public void postCondition(ITestResult result) throws InterruptedException{  
		try {
			//Here will compare if test is failing then only it will enter into if condition
		       if(ITestResult.FAILURE==result.getStatus()){
		             try {
		                    Thread.sleep(3000);
		                  	String filename=ScreenShot.takeFullScreenShot(result.getName());
		                    String screenshot=ExtentReport.attachScreenshotInReport(filename);
		                    ExtentReport.reportLog(LogStatus.FAIL, result.getThrowable()+screenshot);
		                    ReporterLogs.log("Exception encountered "+result.getThrowable(), "error");
		             } catch (Exception e) {
		                    e.printStackTrace();
		             }  
		       	} 
		     }
	     finally {
	               Frames.switchToDefaultContent(driver);
	  	    	   //SafeLogin.logOut(driver);
	  	    	   ReporterLogs.log("Test Case Executed: "+result.getName(), "info");
		           ReporterLogs.log("                                                                                                                                      ", "info");
		           ReporterLogs.log("**************************************************************************************************************************************","info");
	  		       //driver.close();
	               ExtentReport.endReport();
	  		}
	                  
	         
	       
	       
	}
}