package com.servicenow.applicationspecificlibraries;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicenow.genericlibraries.ReporterLogs;



/*
 * Author- Sathyanarayanan V
 * Objective - To accept the alert
 * @Param - WebDriver Instance
 */
public class Alerts {

	public static void checkForAlertPresence(WebDriver driver){
        WebDriverWait wait=new WebDriverWait(driver, 10);
        if(wait.until(ExpectedConditions.alertIsPresent())==null)
           ReporterLogs.log("alert was not present", "info");	
        else{
            Alert alert=driver.switchTo().alert();
            String alertText = alert.getText();
        	alert.accept();
        	ReporterLogs.log("Alert "+alertText +" was handled", "info");
        }	
}

}
