package com.servicenow.applicationspecificlibraries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.ScreenShot;
import com.servicenow.genericlibraries.TextBoxes;

import pages.HomePage;
import pages.IncidentPage;

public class IncidentReusables {	
	
	static String incidentState = null;
	static String businessServceValue = null;
	static String assignmentGroupValue = null;
	static String openedByGroupValue = null;
	static String userImpact = null;
	static String shortDescription = null;
	static String description = null;
	static String reasonForIncidentManager = null;
	static String incidentNumber = null;
	static String incMgrReqdValue = null;
	static String incNumber = null;
	static String assignedTo = null;
	static String incidentStateWIP = null;
	static String configurationItem = null;
	static String causeCode = null;
	static String subCauseCode = null;
	static String mitigationSolutionSteps = null;
	static String incidentStateResolved = null;
	static String reasonForCancellation = null;
	static String incidentStateCancelled = null;
	
	
	/*
	 
	 * Objective: To create Standalone/Managed Incident Ticket
	 * @Param- boolean ManagedIncident: If true, a managed incident ticket is created. Else, Standalone incident ticket is created
	 * @Param- serialNum, cellNum - sno and cell number to store the incident ticket number in Excel
	*/
	
	public static String createIncident(WebDriver driver, boolean ManagedIncident, int sNum, int cellNum) throws Exception
	{
		
		try{
		WaitUtils.waitForPageToLoad(driver, 30);
				
		//if ManagedIncident is true, creates managed Incident ticket
		if (ManagedIncident) {
			
			ExtentReport.reportLog(LogStatus.INFO, "New Incident Creation page");
			ReporterLogs.log("New Incident Creation page", "info");
			
			//Reading values from Excel file for Managed Incident Test Case
			businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 5);
			System.out.println(businessServceValue);
			assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 6);
			openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 8);
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 9);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 10);
			description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 11);
			reasonForIncidentManager=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 2, 12);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
			ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
			ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);
			ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			
			//Business Service Field
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			Thread.sleep(5000);
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Assignment group value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
			ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
					
			//OpenedByGroup value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
					
			//User Impact Field			
			WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
			DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
			
			//Incident Manager Required Checkbox
			if (IncidentPage.getIncidentManagerRequiredChkbox(driver).isEnabled()) {
				IncidentPage.getIncidentManagerRequiredChkbox(driver).sendKeys(Keys.SPACE);
				WaitUtils.waitForIdPresent(driver, "incident.u_reason_for_incident_manager");
				DropDowns.selectDropdownByVisibleText(IncidentPage.getReasonForIncidentManagerDropdown(driver), reasonForIncidentManager, "Reason for Incident Manager");
			}else{
				ReporterLogs.log("Incident Manager Required Checkbox not enabled", "error");
				}	
		
			//Short Description field			
			WaitUtils.waitForIdPresent(driver, "incident.short_description");
			IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
			
			//Description field
			WaitUtils.waitForIdPresent(driver, "incident.description");
			IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
			
			Thread.sleep(2000);
			
			//Click on Submit button
			WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();	
			/*try {5
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        ReporterLogs.log("Alert message: " + alertText, "error");
		        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
		        Assert.fail("Unexpected alert !!!! ");
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }	*/
		}
		
		else {			
						
			//Reading values from Excel file for Standalone Incident Test Case
			businessServceValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 5);
			assignmentGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 6);
			openedByGroupValue=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 8);
			userImpact=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 9);
			shortDescription=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 10);
			description=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 11);			
			
			//Store the Incident number in a variable
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForIdPresent(driver, "sys_readonly.incident.number");
			incidentNumber=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']")).getAttribute("value");
			ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, incidentNumber);
			ReporterLogs.log("Incident number captured: "+incidentNumber, "info");
			ExtentReport.reportLog(LogStatus.INFO, "Incident number captured: "+incidentNumber);			
			
			//Business Service Field
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(businessServceValue);
			ReporterLogs.log("Business Service value selected: "+businessServceValue, "info");
			Thread.sleep(5000);
			IncidentPage.getBusinessServiceEdt(driver).sendKeys(Keys.ENTER);
			
			//Assignment group value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.assignment_group");
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(assignmentGroupValue);
			IncidentPage.getAssignmentGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Entering Assignment Group field value "+assignmentGroupValue, "info");
					
			//OpenedByGroup value			
			WaitUtils.waitForIdPresent(driver, "sys_display.incident.u_opened_by_group");
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(openedByGroupValue);
			IncidentPage.getOpenedByGroupEdt(driver).sendKeys(Keys.ENTER);
			ReporterLogs.log("Opened By Group value selected: "+openedByGroupValue, "info");
					
			//User Impact Field			
			WaitUtils.waitForIdPresent(driver, "incident.u_user_impact");
			DropDowns.selectDropdownByVisibleText(IncidentPage.getUserImpactDropdown(driver), userImpact, "User impact");
			ReporterLogs.log("User Impact selected: "+userImpact, "info");
					
			//Short Description field			
			WaitUtils.waitForIdPresent(driver, "incident.short_description");
			IncidentPage.getShortDescriptionEdt(driver).sendKeys(shortDescription+ incidentNumber);
			
			//Description field
			WaitUtils.waitForIdPresent(driver, "incident.description");
			IncidentPage.getDescriptionEdt(driver).sendKeys(description+ incidentNumber);
			
			Thread.sleep(2000);			
			
			//Click on Submit button
			WaitUtils.waitForXpathPresent(driver, "//button[text()='Submit']");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
			/*try {
		        Alert alert = driver.switchTo().alert();
		        String alertText = alert.getText();
		        ReporterLogs.log("Alert message: " + alertText, "error");
		        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
		        Assert.fail("Unexpected alert !!!! ");
		        } 
		    catch (NoAlertPresentException e) {
		        e.printStackTrace();
		    }*/
		}
	}catch (UnhandledAlertException f) {
	    try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        System.out.println("Alert data: " + alertText);
	        Assert.fail("Unhandled alert");
	        
	        } 
	    catch (NoAlertPresentException e) {
	        e.printStackTrace();
	    }		
	}	
		return incidentNumber;
}
		
		public static void verifyManagedIncidentCreation(WebDriver driver, String incNumber) throws Exception
		{
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
			 if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
				 	Thread.sleep(1000);
			        WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			        TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
			        IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);			            
			        WaitUtils.waitForPageToLoad(driver, 30);            
			        IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
			        WaitUtils.waitForPageToLoad(driver, 30);
			        WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
			        ReporterLogs.log("Title of the page after searching the incident: "+driver.getTitle(), "info");
			        incMgrReqdValue=IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver).getText();
			        if (incMgrReqdValue.equalsIgnoreCase("true")) {
			            	Assert.assertEquals(incMgrReqdValue, "true");
			            	ReporterLogs.log("Successfully created Managed incident with Id "+incNumber, "info");
			                ExtentReport.reportLog(LogStatus.PASS, "Successfully created Managed incident with Id "+incNumber);
			                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
			                ExtentReport.reportLog(LogStatus.INFO, "Incident Manager Required value for the incident "+ incNumber +" is "+ incMgrReqdValue);
			            	Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver, "Incident Manager Required");
			      }else {
							ReporterLogs.log("Unable to create a Managed incident Ticket. Incident Manager Required value from Notes tab displayed is "+ incMgrReqdValue, "error");
			                ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a managed incident ticket");
			                ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
			                Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver,"Incident Manager Required");
			                Assert.assertEquals(incMgrReqdValue, "true");
						}
			        }            
			   else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
				   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
				   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
				   ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
				   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
				   WaitUtils.waitForPageToLoad(driver, 30);            
				   IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
				   WaitUtils.waitForPageToLoad(driver, 30);
				   WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
				   incMgrReqdValue=IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver).getText();
				   ReporterLogs.log("Incident Manager Required value is: "+incMgrReqdValue, "info");
				   if (incMgrReqdValue.equalsIgnoreCase("true")) {
					   Assert.assertEquals(incMgrReqdValue, "true");
					   ReporterLogs.log("Successfully created Managed incident with Id "+incNumber, "info");
					   ExtentReport.reportLog(LogStatus.PASS, "Successfully created Managed incident with Id "+incNumber);
					   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
					   ExtentReport.reportLog(LogStatus.INFO, "Incident Manager Required value for the incident "+ incNumber +" is "+ incMgrReqdValue);
					   Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver,"Incident Manager Required");
				   }else{
					   ReporterLogs.log("Unable to create a Managed incident Ticket. Incident Manager Required value from Notes tab displayed is "+ incMgrReqdValue, "error");
					   ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a managed incident ticket");
					   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
					   Utils.scrollingToElementofAPage(IncidentPage.getIncidentManagerRequiredValueFromNotesTab(driver), driver, "Incident Manager Required");
					   Assert.assertEquals(incMgrReqdValue, "true");
				   }
			   }				
			}
		
		public static void verifyIncidentCreation(WebDriver driver, String incNumber) throws Exception
		{
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
	        if(IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
	        {
	        	Thread.sleep(2000);
	            WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
	            TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver),incNumber,"Search Incident");
	            IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);
	            System.out.println("clicked");
	            //Thread.sleep(3000);
	            WaitUtils.waitForPageToLoad(driver, 30);
	            incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
	        System.out.println(incidentState);    
	            if(incidentState.equalsIgnoreCase("New"))
	            {
	         	   Assert.assertEquals(incidentState, "New");
	         	   ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
	         	   ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
	         	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
	         	   ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
	         	   ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	         }else{
	        	   	
	         	   ReporterLogs.log("Unable to create a Standalone incident Ticket. Actual status is "+ incidentState, "error");
	         	   ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
	         	   ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
	         	   ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
	         	   ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	         	   Assert.assertEquals(incidentState, "New");
	         	 }  	       
	        }      
	        else if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
	        	   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
	        	   DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
	        	   TextBoxes.enterTextValue(IncidentPage.getSearchIncidentEdt(driver), incNumber, "Search Incident ");
	        	   ReporterLogs.log("Entering Incident Id in the Search Text "+incNumber, "info");
	        	   IncidentPage.getSearchIncidentEdt(driver).sendKeys(Keys.ENTER);	         
	        	   if(incidentState.equalsIgnoreCase("New"))
	        	   {
		         	Assert.assertEquals(incidentState, "New");
		         	ReporterLogs.log("Successfully created Standalone incident with Id "+incNumber, "info");
		         	ExtentReport.reportLog(LogStatus.PASS, "Successfully created Standalone incident with Id "+incNumber);
		            ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed"); 
		            ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
		            ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
	        	   }else{        	    
				    ReporterLogs.log("Unable to create a Standalone incident Ticket ", "error");
				    ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a incident ticket with Status as New");
				    ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
				    ExtentReport.reportLog(LogStatus.INFO, "Status of the Standalone incident id "+ incNumber +" is "+ incidentState);
				    ReporterLogs.log("Status of the Standalone incident id "+ incNumber +" is "+ incidentState, "info");
				    Assert.assertEquals(incidentState, "New");
			       }      
		  		}
			}
		
		public static void resolveIncident(WebDriver driver) throws Exception
			{
			try{
				WaitUtils.waitForPageToLoad(driver, 30);
				
				//Search for the Incident Ticket
				incNumber=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 1, 2);
				assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 7);
				ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 2, incNumber);
				ReporterLogs.log("Incident Number is "+incNumber, "info");
				ExtentReport.reportLog(LogStatus.INFO, "Incident Number is "+incNumber);
				WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");			
				if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
					 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        if (incidentState.equalsIgnoreCase("New")) {
		        	Thread.sleep(3000);
		        	IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	WaitUtils.waitForPageToLoad(driver, 30);
		        	WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentState, "info");
		        	ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	 
		        	//Update the status from New to Work in Progress
		        	DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Work in Progress", "State");
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
		        	IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
		        	Thread.sleep(2000);
		        	IncidentPage.getUpdateBtn(driver).click();						
		        	/*try {
				        Alert alert = driver.switchTo().alert();
				        String alertText = alert.getText();
				        ReporterLogs.log("Alert message: " + alertText, "error");
				        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
				        Assert.fail("Unexpected alert !!!! ");
				        } 
				    catch (NoAlertPresentException e) {
				        e.printStackTrace();
				    }*/
		        	WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		        	 
		        	//Search for the incident ticket
		        	DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		        	IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        	WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		        	incidentStateWIP=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        	ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentStateWIP, "info");
		        	ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentStateWIP);
		        	 
		        	//Update the incident ticket from Work in Progress to Resolved State
		        	if (incidentStateWIP.equalsIgnoreCase("Work in Progress")) {					
		        		Thread.sleep(3000);
						IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
						WaitUtils.waitForPageToLoad(driver, 30);
						WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
						DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Resolved", "State");					
						configurationItem=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 13);
						causeCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 14);
						subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 15);
						mitigationSolutionSteps=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 16);
						System.out.println("output");
						IncidentPage.getConfigurationItemEdt(driver).sendKeys(configurationItem);
						IncidentPage.getConfigurationItemEdt(driver).sendKeys(Keys.ENTER);
						
						//Fill closure tab fields
						IncidentPage.getClosureTab(driver).click();
						DropDowns.selectDropdownByVisibleText(IncidentPage.getCauseCodeDropdown(driver), causeCode, "Cause Code");
						DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver), subCauseCode, "Sub Cause Code");
						Thread.sleep(4000);
						TextBoxes.enterTextValue(IncidentPage.getMitigationAndSolutionStepsEdt(driver), mitigationSolutionSteps + incNumber, "Mitigation and Solution Steps");
						IncidentPage.getUpdateBtn(driver).click();	
						try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        ReporterLogs.log("Alert message: " + alertText, "error");
					        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
					        Assert.fail("Unexpected alert !!!! ");
					        } 
					    catch (NoAlertPresentException e) {
					        e.printStackTrace();
					    }
						WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
			        	 
			        	//Search for the incident ticket
						DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
						IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
						WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
						incidentStateResolved=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
						ReporterLogs.log("Current State of Incident " + incNumber + " is "+incidentStateResolved, "info");
						ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentStateResolved);
			        	 
						//Update the incident ticket from Work in Progress to Resolved State
						if (incidentStateResolved.equalsIgnoreCase("Resolved")) {
							Assert.assertEquals(incidentStateResolved, "Resolved");
							ExtentReport.reportLog(LogStatus.PASS, "Incident ticket " + incNumber +" has been successfully moved to Resolved State");
							ReporterLogs.log("Incident ticket " + incNumber +" has been successfully moved to Resolved State", "pass");
							ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Passed");
			        	 }
						else{
			        		ReporterLogs.log("Incident ticket " + incNumber +" has not been moved to Resolved State", "error");
			        		ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" has not been moved to Resolved State");
			        		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
			        		Assert.assertEquals(incidentStateResolved, "Resolved");
			        	 	}
		        	 	}
		        	 
		        	else{
		        		 ReporterLogs.log("Incident ticket " + incNumber +" is not Work in Progress", "error");
		        		 ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" is not Work in Progress");
		        		 ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
		        		 Assert.assertEquals(incidentStateWIP, "Work in Progress");
		        	 }
		         	}	
		         else {
		        	 	ReporterLogs.log("Incident ticket " + incNumber +" status is not New", "error");
						ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" status is not New");
						ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
		        		Assert.assertEquals(incidentState, "New");
				}         
			}		
			catch (UnhandledAlertException f) {
			    try {
			        Alert alert = driver.switchTo().alert();
			        String alertText = alert.getText();
			        System.out.println("Alert data: " + alertText);
			         Assert.fail("Unhandled alert");
			        
			        } 
			    catch (NoAlertPresentException e) {
			        e.printStackTrace();
			    }		
			}		
		}
	
		
		public static void cancelIncident(WebDriver driver, String incNumber) throws Exception
		{
			
			try{
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");	
			
			//Search for the Incident Ticket
			reasonForCancellation=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 4, 17);
			subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 15);
					
			if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
				 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
		         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
			}
					IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		         	 WaitUtils.waitForPageToLoad(driver, 30);
		         	 WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		         	 System.out.println("executed");
		         	 incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		         	 System.out.println(incidentState);
		         	 if (!incidentState.equalsIgnoreCase("Cancelled")) {					
		             Thread.sleep(3000);
		        	 IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	 WaitUtils.waitForPageToLoad(driver, 30);
		        	 WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	 ReporterLogs.log("Current State of Incident " + incNumber + " is "+incidentState, "info");
		        	 ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	 
		        	 //Update the status to Cancelled
		        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Cancelled", "State");
		        	 WaitUtils.waitForXpathPresent(driver, "//span[text()='Governance']");
		        	 IncidentPage.getGovernanceTab(driver).click();
		        	 //WaitUtils.waitForElementToBeVisible(driver, IncidentPage.getReasonForCancellationEdt(driver));
		        	 WaitUtils.waitForXpathPresent(driver, "//textarea[@id='incident.u_reason_for_cancellation']");
		        	 IncidentPage.getReasonForCancellationEdt(driver).sendKeys(reasonForCancellation);		        	 
		        	 WaitUtils.waitForXpathPresent(driver, "//span[text()='Closure']");
		        	 Thread.sleep(2000);
		        	 IncidentPage.getClosureTab(driver).click();
		        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver),subCauseCode,"Sub Cause Code");		        	 
		        	 Thread.sleep(2000);
		        	 IncidentPage.getUpdateBtn(driver).click();						
		        	 /*try {
		 		        Alert alert = driver.switchTo().alert();
		 		        String alertText = alert.getText();
		 		        ReporterLogs.log("Alert message: " + alertText, "error");
		 		        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
		 		        Assert.fail("Unexpected alert !!!! ");
		 		        } 
		 		    catch (NoAlertPresentException e) {
		 		        e.printStackTrace();
		 		    }*/
		        	 WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		        	 
		        	 //Search for the incident ticket
		        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		        	 IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		        	 WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		        	 incidentStateCancelled=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		        	 ReporterLogs.log("Current State of Incident " + incNumber + " is "+ incidentStateCancelled, "info");
		        	 ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " +incNumber+ " is "+ incidentStateCancelled);
		        	 
		        	 //Update the incident ticket from Work in Progress to Resolved State
		        	 if (incidentStateCancelled.equalsIgnoreCase("Cancelled")) {					
						Thread.sleep(3000);
						IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
						WaitUtils.waitForPageToLoad(driver, 30);
						WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
						if ((!IncidentPage.getStateDropdown(driver).isEnabled()) || (!IncidentPage.getBusinessServiceReadOnly(driver).isEnabled())
								|| (!IncidentPage.getShortDescriptionReadOnly(driver).isEnabled())) {
							Assert.assertEquals(IncidentPage.getStateDropdown(driver).getAttribute("readonly"), "true");
							Assert.assertEquals(IncidentPage.getBusinessServiceReadOnly(driver).getAttribute("readonly"), "true");
							Assert.assertEquals(IncidentPage.getShortDescriptionReadOnly(driver).getAttribute("readonly"), "true");
							ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 4, "Passed");
							ReporterLogs.log("Incident Ticket " + incNumber + " is greyed out/non-editable as the ticket is in "+incidentStateCancelled+ " state", "pass");
							ExtentReport.reportLog(LogStatus.PASS, "Incident Ticket " + incNumber + " is greyed out/non-editable as the ticket is in "+incidentStateCancelled+ " state");
						} else {
							ReporterLogs.log("Incident Ticket " + incNumber + " is not greyed out as the ticket is in "+incidentStateCancelled+ " state", "error");
							ExtentReport.reportLog(LogStatus.FAIL, "Incident Ticket " + incNumber + " is greyed out/non-editable as the ticket is in "+incidentStateCancelled+ " state");	
							ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 4, "Failed");
							Assert.assertEquals(IncidentPage.getStateDropdown(driver).getAttribute("readonly"), "true");
							Assert.assertEquals(IncidentPage.getBusinessServiceReadOnly(driver).getAttribute("readonly"), "true");
							Assert.assertEquals(IncidentPage.getShortDescriptionReadOnly(driver).getAttribute("readonly"), "true");
						}
		        	 }			        	
			         else{
			        	 	ReporterLogs.log("Incident ticket " + incNumber +" has not been moved to Cancelled State", "error");
			        	 	ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" has not been moved to Cancelled State");
			        	 	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 4, "Failed");
			        	 	Assert.assertEquals(incidentStateCancelled, "Cancelled");
			        	 }
		         	 }	
				  else{
					  	ReporterLogs.log("Incident ticket " + incNumber +" is already in Cancelled State. Choose correct TestData", "error");
		        	 	ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" is already in Cancelled State. Choose correct TestData");
		        	 	ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 4, "Failed");
		        	 	Assert.assertNotEquals(incidentState, "Cancelled");
		        	 }
		         }

			
			catch (UnhandledAlertException f) {
			    try {
			        Alert alert = driver.switchTo().alert();
			        String alertText = alert.getText();
			        System.out.println("Alert data: " + alertText);
			         Assert.fail("Unhandled alert");
			        
			        } 
			    catch (NoAlertPresentException e) {
			        e.printStackTrace();
			    }		
			}		
		}
		public static void resolve1Incident(WebDriver driver, String incNumber) throws Exception
		{
			
			try{
			WaitUtils.waitForPageToLoad(driver, 30);
			WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");	
			
			//Search for the Incident Ticket
			reasonForCancellation=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 4, 17);
			subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx", "Smoke_Suite", 4, 15);
					
			if(!IncidentPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
				 WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
		         DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
			}
					IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
		         	 WaitUtils.waitForPageToLoad(driver, 30);
		         	 WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
		         	 System.out.println("executed");
		         	 incidentState=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
		         	 System.out.println(incidentState);
		         	 if (!incidentState.equalsIgnoreCase("Cancelled")) {					
		             Thread.sleep(3000);
		        	 IncidentPage.getIncidentNumberfromQueue(driver, incNumber).click();
		        	 WaitUtils.waitForPageToLoad(driver, 30);
		        	 WaitUtils.waitForTitleIs(driver, incNumber+" | ServiceNow");
		        	 ReporterLogs.log("Current State of Incident " + incNumber + " is "+incidentState, "info");
		        	 ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentState);
		        	 
		        	 //Update the status to Cancelled
		        	 DropDowns.selectDropdownByVisibleText(IncidentPage.getStateDropdown(driver), "Resolved", "State");
		        	 assignedTo=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 7);
		        	 IncidentPage.getAssignedToEdt(driver).sendKeys(assignedTo);
			         IncidentPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
		        	 configurationItem=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 13);
		        	 System.out.println(configurationItem);
		        	 Thread.sleep(2000);
		        	 IncidentPage.getConfigurationItemEdt(driver).sendKeys(configurationItem);
					IncidentPage.getConfigurationItemEdt(driver).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
		        	 
						causeCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 14);
						subCauseCode=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 15);
						mitigationSolutionSteps=ExcelUtils.getData("Incident_Management_TestData.xlsx","Smoke_Suite", 3, 16);
						
						
						IncidentPage.getClosureTab(driver).click();
						DropDowns.selectDropdownByVisibleText(IncidentPage.getCauseCodeDropdown(driver), causeCode, "Cause Code");
						DropDowns.selectDropdownByVisibleText(IncidentPage.getSubCauseCodeDropdown(driver), subCauseCode, "Sub Cause Code");
						Thread.sleep(4000);
						TextBoxes.enterTextValue(IncidentPage.getMitigationAndSolutionStepsEdt(driver), mitigationSolutionSteps + incNumber, "Mitigation and Solution Steps");
		        	
		        	 IncidentPage.getUpdateBtn(driver).click();						
		        	 try {
					        Alert alert = driver.switchTo().alert();
					        String alertText = alert.getText();
					        ReporterLogs.log("Alert message: " + alertText, "error");
					        ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
					        Assert.fail("Unexpected alert !!!! ");
					        } 
					    catch (NoAlertPresentException e) {
					        e.printStackTrace();
					    }
						WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
			        	 
			        	//Search for the incident ticket
						DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
						IncidentReusables.searchIncidentTicketFromQueue(driver, incNumber);
						WaitUtils.waitForTitleIs(driver, "Incidents | ServiceNow");
						incidentStateResolved=IncidentPage.getIncidentStatusfromQueue(driver, incNumber).getText();
						ReporterLogs.log("Current State of Incident " + incNumber + " is "+incidentStateResolved, "info");
						ExtentReport.reportLog(LogStatus.INFO, "Current State of Incident " + incNumber + " is "+ incidentStateResolved);
			        	 
						//Update the incident ticket from Work in Progress to Resolved State
						if (incidentStateResolved.equalsIgnoreCase("Resolved")) {
							Assert.assertEquals(incidentStateResolved, "Resolved");
							ExtentReport.reportLog(LogStatus.PASS, "Incident ticket " + incNumber +" has been successfully moved to Resolved State");
							ReporterLogs.log("Incident ticket " + incNumber +" has been successfully moved to Resolved State", "pass");
							ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Passed");
			        	 }
						else{
			        		ReporterLogs.log("Incident ticket " + incNumber +" has not been moved to Resolved State", "error");
			        		ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" has not been moved to Resolved State");
			        		ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
			        		Assert.assertEquals(incidentStateResolved, "Resolved");
			        	 	}
		        	 	}
		        	 
		        	else{
		        		 ReporterLogs.log("Incident ticket " + incNumber +" is not Work in Progress", "error");
		        		 ExtentReport.reportLog(LogStatus.FAIL, "Incident ticket " + incNumber +" is not Work in Progress");
		        		 ExcelUtils.writeDataIntoCell("Incident_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
		        		 Assert.assertEquals(incidentStateWIP, "Work in Progress");
		        	 }
		         	}	
		        
			    catch (NoAlertPresentException e) {
			        e.printStackTrace();
			    }		
			}		
		
		
		public static void searchIncidentTicketFromQueue(WebDriver driver, String ticketNumber) throws Exception{
			try{
			Thread.sleep(2000);
			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			TextBoxes.enterTextValue(IncidentPage.getIncidentSearchTicketEdt(driver), ticketNumber, "Search Ticket");
			IncidentPage.getIncidentSearchTicketEdt(driver).sendKeys(Keys.ENTER);
			
		}
			catch (Exception e) {
				ReporterLogs.log(e.getMessage(), "info");
			}
		}
	}


