package com.servicenow.applicationspecificlibraries;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ReporterLogs;

import pages.HomePage;
import pages.loginPage;

public class SafeLogin {
	
	/*
	 
	 * Objective : Login to the application and credential taken from Property Files
	 */
	public static void logInUser(WebDriver driver) throws IOException{
		
		
				WaitUtils.waitForPageToLoad(driver, 10);
				WaitUtils.waitForIdPresent(driver, "USER");
				if(driver.getTitle().equalsIgnoreCase("Sign in to SAFE")){
					loginPage.getsafeUserIDEdt(driver).sendKeys(Capabilities.getPropertyValue("UserId"));
					loginPage.getsafePasswordEdt(driver).sendKeys(Capabilities.getPropertyValue("Password"));
					loginPage.getsafeLoginbtn(driver).click();	
					ReporterLogs.log("Logged in successfully", "info");
				}	
			
}
	
	
	/*
	
	 * Objective : Login to the application and credential can be passed from the script
	 */
	public static void logInSnow(WebDriver driver, String usrID, String pwd){
		if(driver.getTitle().equalsIgnoreCase("Sign in to SAFE")){
			loginPage.getsafeUserIDEdt(driver).sendKeys(usrID);
			loginPage.getsafePasswordEdt(driver).sendKeys(pwd);
			loginPage.getsafeLoginbtn(driver).click();
			ReporterLogs.log("Logged in successfuly", "info");
	}
	}
	
	/*
	
	 * Objective : Log out of the application and verifes successful logout
	 */
	public static void logOut(WebDriver driver) throws InterruptedException{
		try {
				Thread.sleep(3000);
				WaitUtils.waitForXpathPresent(driver, "//button[@id='user_info_dropdown']");
		        String username=HomePage.getLoggedInUserInfo(driver).getText(); 
		        HomePage.getUserNameDropDown(driver).click();
		        WaitUtils.waitForXpathPresent(driver, "//button[@id='user_info_dropdown']/following::a[text()='Logout']"); 
		        HomePage.getLogoutBtn(driver).click();
		        WaitUtils.waitForPageToLoad(driver, 10);
		        WaitUtils.waitForXpathPresent(driver, "//div[@class='field-head']/following::p[contains(text(),'SUCCESS! You are now logged out of your application.')]");
		        if(driver.getTitle().equalsIgnoreCase("SAFE Logout")){
		               ReporterLogs.log("The user "+username+" has succesfully logged out of the application", "info");
		        }
		        else {
		         ReporterLogs.log("The user "+username+" has not been logged out of the application", "error");
		         ReporterLogs.log("Title page :"+driver.getTitle(),"info");
		        }
			}catch (Exception e) {
			e.printStackTrace();

			}	
}

}