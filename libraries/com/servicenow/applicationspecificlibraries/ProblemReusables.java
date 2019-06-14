package com.servicenow.applicationspecificlibraries;

import java.awt.Checkbox;

import org.apache.poi.sl.usermodel.TextBox;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

import pages.ChangePage;
import pages.HomePage;
import pages.IncidentPage;
import pages.ProblemPage;
import pages.ProblemTaskPage;

public class ProblemReusables {
       

       static String problemId=null;
       static String problemState=null;
       static String source=null;
       static String impact=null;
       static String complexity=null;
       static String assignmentGroup = null;
       static String configurationItem = null;
       static String shortDescription = null;
       static String description = null;
       static String priority = null;
       static String initialPriority = null;
       static String impactValue = null;
       static String complexityValue = null;
       static String updatedPriority = null;
       static String actualStateOfTicket = null;
       static String assignedTo = null;
       static String problemTaskNum = null;
       static String actualProblemTask = null;
       static String workaround = null;
       static String rootcauseCategory = null;
       static String rootcauseSubCategory = null;
       static String rootcauseDetail = null;
       static String rootcauseCI = null;
       static String resolution = null;
       static String state = null;
       static String workNotes = null;
       
       public static String createProblem(WebDriver driver, int sNum, int cellNum) throws Exception{
           
           try{
                 WaitUtils.waitForPageToLoad(driver, 30);
                 //Thread.sleep(3000);
                 source=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 5);
                 assignmentGroup=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 6);
                 configurationItem=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 7);
                 shortDescription=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 10);
                 description=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 11);
                  
                 WaitUtils.waitForIdPresent(driver, "sys_readonly.problem.number");
                 problemId=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
                 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, problemId);
                 //ExtentReport.reportLog(LogStatus.INFO, "Problem number captured: "+problemId);
                // ReporterLogs.log("Problem number captured: "+problemId, "info");     
                 WaitUtils.waitForIdPresent(driver, "problem.contact_type");
                 DropDowns.selectDropdownByVisibleText(ProblemPage.getSourceDropdown(driver), source, "Source");
                 //Thread.sleep(3000);                                 
                 WaitUtils.waitForIdPresent(driver, "sys_display.problem.assignment_group");
                 TextBoxes.enterTextValue(ProblemPage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignment Group");                    
                 ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
                 ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.DOWN);
                 ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
                 Thread.sleep(5000);
                 ReporterLogs.log("Entering Assignment group field value "+assignmentGroup, "info");                 
                 TextBoxes.enterTextValue(ProblemPage.getShortdescriptionEdt(driver), shortDescription, "Short Description");
                 Thread.sleep(2000);
                 TextBoxes.enterTextValue(ProblemPage.getDescriptionEdt(driver), description, "Description");
                 Thread.sleep(2000);
                 WaitUtils.waitForIdPresent(driver, "sys_display.problem.cmdb_ci"); 
                 TextBoxes.enterTextValue(ProblemPage.getConfigurationitemEdt(driver), configurationItem, "Configuration Item");
                 ProblemPage.getConfigurationitemEdt(driver).sendKeys(Keys.ENTER);
                 ReporterLogs.log("Entering Configuration Item field value "+configurationItem, "info");
                 Thread.sleep(5000);
                 ProblemPage.getSubmitBtn(driver).click();
                 try {
                	 Alert alert = driver.switchTo().alert();
                	 String alertText = alert.getText();
                	 ReporterLogs.log("Alert message: " + alertText, "error");
                	 //ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
                	 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
                	 Assert.fail("Unexpected alert !!!! ");
                    } 
                 catch (Exception e) {
                	System.out.println("handled");
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
                  return problemId;                     
       }
 public static String createProblem2(WebDriver driver, int sNum, int cellNum) throws Exception{
           
           try{
                 WaitUtils.waitForPageToLoad(driver, 30);
                 //Thread.sleep(3000);
                 source=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 5);
                 assignmentGroup=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 6);
                 configurationItem=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 7);
                 shortDescription=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 10);
                 description=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 11);
                  
                 WaitUtils.waitForIdPresent(driver, "sys_readonly.problem.number");
                 problemId=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
                 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, problemId);
                 //ExtentReport.reportLog(LogStatus.INFO, "Problem number captured: "+problemId);
                // ReporterLogs.log("Problem number captured: "+problemId, "info");     
                 WaitUtils.waitForIdPresent(driver, "problem.contact_type");
                 DropDowns.selectDropdownByVisibleText(ProblemPage.getSourceDropdown(driver), source, "Source");
                 //Thread.sleep(3000);                                 
                 WaitUtils.waitForIdPresent(driver, "sys_display.problem.assignment_group");
                 TextBoxes.enterTextValue(ProblemPage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignment Group");                    
                 ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
                 ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.DOWN);
                 ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
                 Thread.sleep(5000);
                 ReporterLogs.log("Entering Assignment group field value "+assignmentGroup, "info");                 
                 TextBoxes.enterTextValue(ProblemPage.getShortdescriptionEdt(driver), shortDescription, "Short Description");
                 Thread.sleep(2000);
                 TextBoxes.enterTextValue(ProblemPage.getDescriptionEdt(driver), description, "Description");
                 Thread.sleep(2000);
                 WaitUtils.waitForIdPresent(driver, "sys_display.problem.cmdb_ci"); 
                 TextBoxes.enterTextValue(ProblemPage.getConfigurationitemEdt(driver), configurationItem, "Configuration Item");
                 ProblemPage.getConfigurationitemEdt(driver).sendKeys(Keys.ENTER);
                 ReporterLogs.log("Entering Configuration Item field value "+configurationItem, "info");
                 Thread.sleep(5000);
                 ProblemPage.getSaveBtn(driver).click();
                 try {
                	 Alert alert = driver.switchTo().alert();
                	 String alertText = alert.getText();
                	 ReporterLogs.log("Alert message: " + alertText, "error");
                	// ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
                	 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
                	 Assert.fail("Unexpected alert !!!! ");
                    } 
                 catch (Exception e) {
                	System.out.println("handled");
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
                  return problemId;                     
       }
       
       public static String createProblem1(WebDriver driver, int sNum, int cellNum) throws Exception{
    	   try{
               WaitUtils.waitForPageToLoad(driver, 30);
               source=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 5);
               assignmentGroup=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 6);
               configurationItem=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 7);
               shortDescription=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 10);
               description=ExcelUtils.getData("Problem_Management_TestData.xlsx","Smoke_Suite", 1, 11);
                
               WaitUtils.waitForIdPresent(driver, "sys_readonly.problem.number");
               problemId=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
              // ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, problemId);
              // ExtentReport.reportLog(LogStatus.INFO, "Problem number captured: "+problemId);
               ReporterLogs.log("Problem number captured: "+problemId, "info");     
               WaitUtils.waitForIdPresent(driver, "problem.contact_type");
               DropDowns.selectDropdownByVisibleText(ProblemPage.getSourceDropdown(driver), source, "Source");
               //Thread.sleep(3000);                                 
               WaitUtils.waitForIdPresent(driver, "sys_display.problem.assignment_group");
               Thread.sleep(1000);
               TextBoxes.enterTextValue(ProblemPage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignment Group");                    
               ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
               ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.DOWN);
               ProblemPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
               Thread.sleep(5000);
               ReporterLogs.log("Entering Assignment group field value "+assignmentGroup, "info");                 
               TextBoxes.enterTextValue(ProblemPage.getShortdescriptionEdt(driver), shortDescription, "Short Description");
               Thread.sleep(2000);
               TextBoxes.enterTextValue(ProblemPage.getDescriptionEdt(driver), description, "Description");
               Thread.sleep(2000);
               WaitUtils.waitForIdPresent(driver, "sys_display.problem.cmdb_ci"); 
               TextBoxes.enterTextValue(ProblemPage.getConfigurationitemEdt(driver), configurationItem, "Configuration Item");
               ProblemPage.getConfigurationitemEdt(driver).sendKeys(Keys.ENTER);
               ReporterLogs.log("Entering Configuration Item field value "+configurationItem, "info");
               Thread.sleep(5000);
               ProblemPage.getSubmitBtn(driver).click();
    	   }
    	   catch(Exception e){
    		  
    		   System.out.println("handled");
    	   }
    	   
    	   return problemId; 
       }

       public static void verifyProblemCreation1(WebDriver driver, String problemId) throws Exception
		{
				WaitUtils.waitForPageToLoad(driver, 30);
				WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
		        if(!ProblemPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
		        {
		           WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			       DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        
		        ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
		       // problemState=ProblemPage.getProblemStatusfromQueue(driver, problemId).getText();
		        ReporterLogs.log("Current state of the problem ticket "+problemId+" is "+problemState, "info");
		       // ExtentReport.reportLog(LogStatus.INFO, "Current state of the problem ticket "+problemId+" is "+problemState);
		        
	            
	           
		  	}    
       
       public static void verifyProblemCreation(WebDriver driver, String problemId) throws Exception
		{
				WaitUtils.waitForPageToLoad(driver, 30);
				WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
		        if(!ProblemPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
		        {
		           WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			       DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
				}
		        
		        ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
		        problemState=ProblemPage.getProblemStatusfromQueue(driver, problemId).getText();
		        ReporterLogs.log("Current state of the problem ticket "+problemId+" is "+problemState, "info");
		       // ExtentReport.reportLog(LogStatus.INFO, "Current state of the problem ticket "+problemId+" is "+problemState);
	            
	            if(problemState.equalsIgnoreCase("New"))
	            {  
	               Assert.assertEquals(problemState, "New");
	         	   ReporterLogs.log("Successfully created problem ticket with Id "+problemId, "info");
	         	   ExtentReport.reportLog(LogStatus.PASS, "Successfully created problem ticket with Id "+problemId);
	         	   ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Passed");
	         	   ExtentReport.reportLog(LogStatus.INFO, "Status of the problem id "+ problemId +" is "+ problemState);
	         	   ReporterLogs.log("Status of the problem id "+ problemId +" is "+ problemState, "info");
	         }else{        	   	
	         	   ReporterLogs.log("Problem ticket status is not new. Actual status is "+ problemState, "error");
	         	 //  ExtentReport.reportLog(LogStatus.FAIL, "Unable to create a problem ticket with Status as New");
	         	   ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 4, "Failed");
	         	   //ExtentReport.reportLog(LogStatus.INFO, "Status of the problem id "+ problemId +" is "+ problemState);
	         	   ReporterLogs.log("Status of the problem id "+ problemId +" is "+ problemState, "info");
	         	   Assert.assertEquals(problemState, "New");
	         	 }
		  	} 
       
       public static void searchProblemTicketFromQueue(WebDriver driver, String ticketNumber) throws Exception{
   			try{
   				   Thread.sleep(2000);
   				   WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
   				   TextBoxes.enterTextValue(ProblemPage.getSearchProblemEdt(driver), ticketNumber, "Search Ticket");
   				   ProblemPage.getSearchProblemEdt(driver).sendKeys(Keys.ENTER);
   				}
   			catch (Exception e) {
   				   ReporterLogs.log(e.getMessage(), "info");
   				}
   			}
       
       public static void clickProblemTicketFromQueue(WebDriver driver, String ticketNumber) throws Exception{
      		try{
      			   Thread.sleep(2000);
      			   ProblemPage.getProblemTicketfromQueue(driver, ticketNumber).click();      		
      		   }
      		catch (Exception e) {
      			   ReporterLogs.log(e.getMessage(), "info");
      			}
      		}
       
       public static void verifyStateOfProblemTicket(WebDriver driver, String expectedStateOfTicket,String prNum,int sNum, int cellNum) {
   			try{
   					actualStateOfTicket = DropDowns.getFirstSelectedOptionName(ProblemPage.getProblemStateEdtDropDown(driver), "State Drop Down");
   					ReporterLogs.log("State of the Problem is :"+actualStateOfTicket);
   					if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket))
   				    {
   						Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
   						//ExtentReport.reportLog(LogStatus.PASS, "Successfully updated Problem "+prNum+" with state as "+actualStateOfTicket);
   						ReporterLogs.log("Successfully updated Problem "+prNum+" with state as "+actualStateOfTicket, "info");
   						ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, cellNum, prNum);
   						ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, 3, actualStateOfTicket);
   						ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, 4, "Passed");
   				    }else{
   				    	//ExtentReport.reportLog(LogStatus.FAIL, "State of the Problem ticket is not : "+actualStateOfTicket);
   				    	ReporterLogs.log("Unable to update Problem "+prNum+" with state as "+expectedStateOfTicket, "error");
   				    	ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, cellNum, prNum);
   				    	ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, 3, actualStateOfTicket);
   				    	ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", sNum, 4, "Failed");
   				    	Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
   				    }
   			}catch(Exception e)
   			{
   				ReporterLogs.log("Exception :"+e.getMessage(),"error");
   			}
   		}
       
       
       public static void updateProblemTicket(WebDriver driver, String problemId, int sNum, int cellNum) throws Exception
		{
		   		impact=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 8);
		   		complexity=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 9);
		   		WaitUtils.waitForPageToLoad(driver, 30);
		   		Thread.sleep(3000);
		   		WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
		   		if(!ProblemPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
		   		{
		   			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
		   			DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
		   		}
		   		ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx","Smoke_Suite", sNum, cellNum, problemId);
		   		ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
		   		initialPriority=ProblemPage.getProblemPriorityfromQueue(driver, problemId).getText();
		   		ReporterLogs.log("Initial Priority value for problem ticket "+problemId+ " is "+ initialPriority, "info");
		   		//ExtentReport.reportLog(LogStatus.INFO, "Initial Priority value for problem ticket "+problemId+ " is "+ initialPriority);
		   		ProblemReusables.clickProblemTicketFromQueue(driver, problemId);
		   		WaitUtils.waitForTitleIs(driver, problemId+" | Problem | ServiceNow");
		   		DropDowns.selectDropdownByVisibleText(ProblemPage.getImpactDropdown(driver), impact, "Impact");
		   		DropDowns.selectDropdownByVisibleText(ProblemPage.getComplexityDropdown(driver), complexity, "Complexity");
		   		//Thread.sleep(3000);
		   		ProblemPage.getUpdateBtn(driver).click();
	        try {
           	 		Alert alert = driver.switchTo().alert();
           	 		String alertText = alert.getText();
           	 		ReporterLogs.log("Alert message: " + alertText, "error");
           	 		//ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
           	 		ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
           	 		Assert.fail("Unexpected alert !!!! ");
               	} 
            catch (Exception e) 
            {
             System.out.println("handeled");
            }	
	           	WaitUtils.waitForPageToLoad(driver, 30);
            	WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
            	updatedPriority=ProblemPage.getProblemPriorityfromQueue(driver, problemId).getText();
            	System.out.println(initialPriority);
            	System.out.println(updatedPriority);
            	ReporterLogs.log("Priority value for problem ticket "+problemId+ " after updating is "+ updatedPriority, "info");
            	//ExtentReport.reportLog(LogStatus.INFO, "Priority value for problem ticket "+ problemId +" after updating is "+ updatedPriority);
            	if (!updatedPriority.equalsIgnoreCase(initialPriority)) {
				 	Assert.assertTrue(!updatedPriority.equalsIgnoreCase(initialPriority), "Initial Priority and Updated priority are same, Problem ticket has not been updated");
					ReporterLogs.log("Problem ticket "+problemId+ " has been updated.", "pass");
					ReporterLogs.log("Priority of the ticket has been updated from "+initialPriority+ " to "+updatedPriority, "pass");
					ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Passed");
					//ExtentReport.reportLog(LogStatus.PASS, "Problem ticket "+problemId+ " has been updated. Priority of the ticket has been updated from "+initialPriority+ " to "+updatedPriority );
				} else {
					ReporterLogs.log("Problem ticket "+problemId+ " has not been updated.", "error");
					ReporterLogs.log("Priority of the ticket has not been updated from "+initialPriority+ " to "+updatedPriority, "error");
					//ExtentReport.reportLog(LogStatus.FAIL, "Problem ticket "+problemId+ " has not been updated. Priority of the ticket has not been updated from "+initialPriority+ " to "+updatedPriority );
					ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 4, "Failed");
					Assert.fail("Initial Priority and Updated priority are same, Problem ticket has not been updated");
				}
			}
       
       public static void moveProblemTicketToAcceptedPhase(WebDriver driver, String problemId) throws Exception{
     		try{
     			System.out.println("pass3");
     				WaitUtils.waitForPageToLoad(driver, 30);
     				WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
     				if(!ProblemPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
     				{
     					WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
     					DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
     				}   
     				System.out.println("pass4");
     				ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
     				problemState=ProblemPage.getProblemStatusfromQueue(driver, problemId).getText();
     				System.out.println(problemState);
    		        //ReporterLogs.log("Current state of the problem ticket "+problemId+" is "+problemState, "info");
    		       // ExtentReport.reportLog(LogStatus.INFO, "Current state of the problem ticket "+problemId+" is "+problemState);
     				ProblemReusables.clickProblemTicketFromQueue(driver, problemId);  
     				System.out.println("pass5");
     				//Frames.switchToDefaultContent(driver);
                    /*String loggedinUser = HomePage.getLoggedInUserInfo(driver).getText();
                    System.out.println("Logged in user :"+loggedinUser); */
                    Thread.sleep(2000);
                    //Frames.switchToFrameById("gsft_main", driver);
                   System.out.println("pass6");
                    WaitUtils.waitForPageToLoad(driver, 20);
                    //WaitUtils.waitForTitleIs(driver, problemId+"Problem | ServiceNow");
                    Thread.sleep(3000);
                    ChangePage.getUpdateBtn(driver);
                    ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
     				String loggedinUser=Capabilities.getPropertyValue("LoggedInUser");
     				System.out.println(loggedinUser);
     				
                    ProblemPage.getProblemApproversTab(driver).click();
                   // ReporterLogs.log("Clicking on Approvers tab", "info");
                    Thread.sleep(3000);
                    System.out.println("tab");
                    ProblemPage.getProblemPendingApproverLnk(driver, loggedinUser).click();
                    
                    //ReporterLogs.log("Clicking on the 'Requested' link for the user "+ loggedinUser, "info");
                    //Thread.sleep(3000);
                    WaitUtils.waitForPageToLoad(driver, 20);
                    WaitUtils.waitForTitleIs(driver, "Approval | ServiceNow");
                    ProblemPage.getProblemApproveBtn(driver).click();
                    ReporterLogs.log("Approving the problem ticket "+ problemId, "info");
                    WaitUtils.waitForPageToLoad(driver, 20);
                    WaitUtils.waitForTitleIs(driver, problemId+ " | ServiceNow");
                    ProblemReusables.verifyStateOfProblemTicket(driver, "Accepted", problemId, 3, 2);
                  }
     		catch (Exception e) {
     			   ReporterLogs.log(e.getMessage(), "info");
     			}
     		}
       
       
       
       
       public static void moveProblemTicketToRejectedPhase(WebDriver driver, String problemId) throws Exception{
    		try{
    			System.out.println("pass3");
    				WaitUtils.waitForPageToLoad(driver, 30);
    				WaitUtils.waitForTitleIs(driver, "Problems | ServiceNow");
    				if(!ProblemPage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number"))
    				{
    					WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
    					DropDowns.selectDropdownByVisibleText(IncidentPage.getSearchDropDown(driver), "Number", "Search Drop Down");
    				}   
    				System.out.println("pass4");
    				ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
    				problemState=ProblemPage.getProblemStatusfromQueue(driver, problemId).getText();
    				System.out.println(problemState);
   		        //ReporterLogs.log("Current state of the problem ticket "+problemId+" is "+problemState, "info");
   		       // ExtentReport.reportLog(LogStatus.INFO, "Current state of the problem ticket "+problemId+" is "+problemState);
    				ProblemReusables.clickProblemTicketFromQueue(driver, problemId);  
    				System.out.println("pass5");
    				//Frames.switchToDefaultContent(driver);
                   /*String loggedinUser = HomePage.getLoggedInUserInfo(driver).getText();
                   System.out.println("Logged in user :"+loggedinUser); */
                   Thread.sleep(2000);
                   //Frames.switchToFrameById("gsft_main", driver);
                  System.out.println("pass6");
                   WaitUtils.waitForPageToLoad(driver, 20);
                   //WaitUtils.waitForTitleIs(driver, problemId+"Problem | ServiceNow");
                   Thread.sleep(3000);
                   ChangePage.getUpdateBtn(driver);
                   ProblemReusables.searchProblemTicketFromQueue(driver, problemId);
    				String loggedinUser=Capabilities.getPropertyValue("LoggedInUser");
    				System.out.println(loggedinUser);
    				
                   ProblemPage.getProblemApproversTab(driver).click();
                  // ReporterLogs.log("Clicking on Approvers tab", "info");
                   Thread.sleep(3000);
                   System.out.println("tab");
                   ProblemPage.getProblemPendingApproverLnk(driver, loggedinUser).click();
                   
                   //ReporterLogs.log("Clicking on the 'Requested' link for the user "+ loggedinUser, "info");
                   //Thread.sleep(3000);
                   WaitUtils.waitForPageToLoad(driver, 20);
                   WaitUtils.waitForTitleIs(driver, "Approval | ServiceNow");
                  // ProblemPage.getProblemApproveBtn(driver).click();
                  // TextBoxes.enterTextValue(ProblemPage.getComments(driver), "Testing", "Comments"); 
                  // ChangePage.getReasonForReject(driver).sendKeys("test");
                   ProblemPage.getComments(driver).sendKeys("test");
                   ProblemPage.getRejectBtn(driver).click();
                   
               //WebElement comment=driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[6]/div[2]/div/div[3]/table/tbody/tr/td/div[1]/div/div/div/div[1]/div/div/div[2]/label"));
                  // TextBoxes.enterTextValue(ProblemPage.getComments(driver), "Testing", "Comments");   
                   
                   
                   ReporterLogs.log("Approving the problem ticket "+ problemId, "info");
                   WaitUtils.waitForPageToLoad(driver, 20);
                   WaitUtils.waitForTitleIs(driver, problemId+ " | ServiceNow");
                   ProblemReusables.verifyStateOfProblemTicket(driver, "Closed", problemId, 3, 2);
                 }
    		catch (Exception e) {
    			   ReporterLogs.log(e.getMessage(), "info");
    			}
    		}
     //---------------------------------------Problem Task------------------------------------------------
       
       public static String moveProblemTicketToInProgressPhase(WebDriver driver, String problemId) throws Exception{
    		try{
    			System.out.println("pass7");
    			ProblemTaskPage.getProblemTasksTab(driver).click();
                ReporterLogs.log("Clicking on Problem Tasks Tab", "info");
                ProblemTaskPage.getProblemTasksCreateBtn(driver).click();
                ReporterLogs.log("-----Creating New Problem Tasks-----", "info");
                WaitUtils.waitForPageToLoad(driver, 20);
                problemTaskNum = ProblemTaskPage.getProblemTaskNumberEdt(driver).getAttribute("value");
                System.out.println(problemTaskNum);
               // WaitUtils.waitForTitleIs(driver, problemTaskNum+" | ServiceNow");
                Thread.sleep(2000);
                ReporterLogs.log("Problem Task Number Captured is " +problemTaskNum, "info");
                //ExtentReport.reportLog(LogStatus.INFO, "Problem Task Number Captured is " +problemTaskNum);
                configurationItem=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 1);
                assignmentGroup=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 3);
                priority=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 2);
                assignedTo=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 4);
                shortDescription=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 5);
                description=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 6);
                
                TextBoxes.enterTextValue(ProblemTaskPage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
                ProblemTaskPage.getConfigurationItemEdt(driver).sendKeys(Keys.ENTER);
                
                TextBoxes.enterTextValue(ProblemTaskPage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignment Group");
                ProblemTaskPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
                
                DropDowns.selectDropdownByVisibleText(ProblemTaskPage.getPriorityDropdown(driver), priority, "Priority");
                
                TextBoxes.enterTextValue(ProblemTaskPage.getAssignedToEdt(driver), assignedTo, "Assigned To");
                Thread.sleep(2000);
                ProblemTaskPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
                
                TextBoxes.enterTextValue(ProblemTaskPage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
                
                TextBoxes.enterTextValue(ProblemTaskPage.getDescriptionEdt(driver), description, "Description");
                ProblemTaskPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
                Thread.sleep(3000);
               String DueDate= Utils.getDesiredDateAndTime(1);
               String ActuallDate=Utils.getCurrentDateTime();
               System.out.println(DueDate);
               System.out.println(ActuallDate);
               TextBoxes.enterTextValue(ProblemPage.getDueDate(driver), DueDate, "Due date");
               Thread.sleep(2000);
               
              // TextBoxes.enterTextValue(ProblemPage.getActuallDueDate(driver),ActuallDate, "Actual Due date");
                
                ProblemPage.getSubmitBtn(driver).click();   
                /*try {
               	 if (ProblemTaskPage.getErrorValidationTxt(driver).isDisplayed()) {
               		 ReporterLogs.log("Field Validation error message: " + ProblemTaskPage.getErrorValidationTxt(driver).getText(), "error");
               		 ExtentReport.reportLog(LogStatus.FAIL, "Field Validation error message: " + ProblemTaskPage.getErrorValidationTxt(driver).getText());
                  	 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
                  	 Assert.fail("Field Validation Error Message !!!! "); 
               	 	} 
               	 } 
                catch (Exception e) {
               	 e.printStackTrace();
                }*/
                
                WaitUtils.waitForPageToLoad(driver, 20);
                ProblemTaskPage.getProblemTasksTab(driver).click();
                

          	   	 actualProblemTask=ProblemReusables.getProblemTaskNumberFromTaskTable(driver);
          	   	 /*if (actualProblemTask.equalsIgnoreCase(problemTaskNum)) {
          	   		 ReporterLogs.log("Successfully created Problem task "+actualProblemTask+" for the problem "+problemId, "pass");
          	   		 ExtentReport.reportLog(LogStatus.PASS, "Successfully created Problem task "+actualProblemTask+" for the problem "+problemId);				
          	   	}else {
          	   		 ReporterLogs.log("Created Problem Task "+actualProblemTask+" is different from the expected problem task "+problemTaskNum, "error");
          	   		 ExtentReport.reportLog(LogStatus.FAIL, "Created Problem Task "+actualProblemTask+" is different from the expected problem task "+problemTaskNum);
          	   	}  */
          	   	 	 ProblemReusables.verifyStateOfProblemTicket(driver, "Work in Progress", problemId, 3, 2);
      	}
       		
       		   catch (Exception e) {
       			   ReporterLogs.log(e.getMessage(), "info");
       			}
  			return problemTaskNum;
    			
    }      
       
       
       
       
       public static String moveProblemTicketToInProgressPhase1(WebDriver driver, String problemId) throws Exception{
   		try{
   			System.out.println("pass7");
   			ProblemTaskPage.getProblemTasksTab(driver).click();
               ReporterLogs.log("Clicking on Problem Tasks Tab", "info");
               ProblemTaskPage.getProblemTasksCreateBtn(driver).click();
               ReporterLogs.log("-----Creating New Problem Tasks-----", "info");
               WaitUtils.waitForPageToLoad(driver, 20);
               problemTaskNum = ProblemTaskPage.getProblemTaskNumberEdt(driver).getAttribute("value");
               System.out.println(problemTaskNum);
              // WaitUtils.waitForTitleIs(driver, problemTaskNum+" | ServiceNow");
               Thread.sleep(2000);
               ReporterLogs.log("Problem Task Number Captured is " +problemTaskNum, "info");
              // ExtentReport.reportLog(LogStatus.INFO, "Problem Task Number Captured is " +problemTaskNum);
               configurationItem=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 1);
               assignmentGroup=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 3);
               priority=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 2);
               assignedTo=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 4);
               shortDescription=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 5);
               description=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 6);
               
               TextBoxes.enterTextValue(ProblemTaskPage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
               ProblemTaskPage.getConfigurationItemEdt(driver).sendKeys(Keys.ENTER);
               
               TextBoxes.enterTextValue(ProblemTaskPage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignment Group");
               ProblemTaskPage.getAssignmentGrpEdt(driver).sendKeys(Keys.ENTER);
               
               DropDowns.selectDropdownByVisibleText(ProblemTaskPage.getPriorityDropdown(driver), priority, "Priority");
               
               TextBoxes.enterTextValue(ProblemTaskPage.getAssignedToEdt(driver), assignedTo, "Assigned To");
               Thread.sleep(2000);
               ProblemTaskPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
               
               TextBoxes.enterTextValue(ProblemTaskPage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
               
               TextBoxes.enterTextValue(ProblemTaskPage.getDescriptionEdt(driver), description, "Description");
               ProblemTaskPage.getAssignedToEdt(driver).sendKeys(Keys.ENTER);
               Thread.sleep(3000);
              String DueDate= Utils.getDesiredDateAndTime(1);
              String ActuallDate=Utils.getCurrentDateTime();
              System.out.println(DueDate);
              System.out.println(ActuallDate);
              TextBoxes.enterTextValue(ProblemPage.getDueDate(driver), DueDate, "Due date");
              Thread.sleep(2000);
              
             // TextBoxes.enterTextValue(ProblemPage.getActuallDueDate(driver),ActuallDate, "Actual Due date");
               
               ProblemPage.getSubmitBtn(driver).click();   
               /*try {
              	 if (ProblemTaskPage.getErrorValidationTxt(driver).isDisplayed()) {
              		 ReporterLogs.log("Field Validation error message: " + ProblemTaskPage.getErrorValidationTxt(driver).getText(), "error");
              		 ExtentReport.reportLog(LogStatus.FAIL, "Field Validation error message: " + ProblemTaskPage.getErrorValidationTxt(driver).getText());
                 	 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
                 	 Assert.fail("Field Validation Error Message !!!! "); 
              	 	} 
              	 } 
               catch (Exception e) {
              	 e.printStackTrace();
               }*/
               
               WaitUtils.waitForPageToLoad(driver, 20);
               ProblemTaskPage.getProblemTasksTab(driver).click();
               

         	   	 actualProblemTask=ProblemReusables.getProblemTaskNumberFromTaskTable(driver);
         	   	 /*if (actualProblemTask.equalsIgnoreCase(problemTaskNum)) {
         	   		 ReporterLogs.log("Successfully created Problem task "+actualProblemTask+" for the problem "+problemId, "pass");
         	   		 ExtentReport.reportLog(LogStatus.PASS, "Successfully created Problem task "+actualProblemTask+" for the problem "+problemId);				
         	   	}else {
         	   		 ReporterLogs.log("Created Problem Task "+actualProblemTask+" is different from the expected problem task "+problemTaskNum, "error");
         	   		 ExtentReport.reportLog(LogStatus.FAIL, "Created Problem Task "+actualProblemTask+" is different from the expected problem task "+problemTaskNum);
         	   	}  */
         	   	 	 ProblemReusables.verifyStateOfProblemTicket(driver, "Work in Progress", problemId, 3, 2);
     	}
      		
      		   catch (Exception e) {
      			   ReporterLogs.log(e.getMessage(), "info");
      			}
 			return problemTaskNum;
   			
   }   
       
       public static String getProblemTaskNumberFromTaskTable(WebDriver driver) throws Exception{
     		try{
     			   Thread.sleep(2000);
     			   actualProblemTask=ProblemPage.getProblemTaskFromTaskTable(driver, problemTaskNum).getText();     		
     		   }
     		catch (Exception e) {
     			   ReporterLogs.log(e.getMessage(), "info");
     			}
			return actualProblemTask;
     		}
       
       public static void moveProblemTicketToKnownErrorPhase(WebDriver driver, String problemId) throws Exception{
    		try{
    			Thread.sleep(2000);
    			ProblemPage.getInvestigationDiagnosisTab(driver).click();
    			Thread.sleep(2000);
    			ProblemPage.getRootCauseAnalysisCompleteChkbox(driver).click();
    			workaround=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 12);
    			rootcauseCategory=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 13);
    			rootcauseSubCategory=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 14);
    		    rootcauseDetail=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 15);
    		    rootcauseCI=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 16);
    		    resolution=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 17);
    		    WaitUtils.waitForXpathPresent(driver, "//textarea[@id='problem.u_work_around_str']");
    			TextBoxes.enterTextValue(ProblemPage.getWorkaroundEdt(driver), workaround, "Workaround");
    			DropDowns.selectDropdownByVisibleText(ProblemPage.getRootCauseCategoryDropDown(driver), rootcauseCategory, "Root cause Category");
    			DropDowns.selectDropdownByVisibleText(ProblemPage.getRootCauseSubCategoryDropDown(driver), rootcauseSubCategory, "Root cause SubCategory");
    			TextBoxes.enterTextValue(ProblemPage.getRootCauseDetailsEdt(driver), rootcauseDetail, "Root cause Detail");
    			TextBoxes.enterTextValue(ProblemPage.getRootCauseCIEdt(driver), rootcauseCI, "Root cause CI");
    			ProblemPage.getRootCauseCIEdt(driver).sendKeys(Keys.ENTER);
    			TextBoxes.enterTextValue(ProblemPage.getResolutionEdt(driver), resolution, "Resolution");
    			Thread.sleep(2000);
    			ProblemPage.getSaveBtn(driver).click();
    			System.out.println("test");
    			try {
                  	 Alert alert = driver.switchTo().alert();
                  	 String alertText = alert.getText();
                  	 ReporterLogs.log("Alert message: " + alertText, "error");
                  	// ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
                  	 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
                  	 Assert.fail("Unexpected alert !!!! ");
                      } 
                   catch (Exception e) {
                  	System.out.println("handled1");
                   }
    			WaitUtils.waitForPageToLoad(driver, 20);
    			ProblemReusables.verifyStateOfProblemTicket(driver, "Known Error", problemId, 3, 2);
    		}
    		catch (Exception e) {
  			   ReporterLogs.log(e.getMessage(), "info");
  			}
    		}
       
      /* public static void closeProblemTask(WebDriver driver, String problemId) throws Exception{
   		try{
   			ProblemTaskPage.getProblemTasksTab(driver).click();
   			Thread.sleep(5000);
   			ProblemPage.getProblemTaskLnk(driver).click();
   			state=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 8);
   			workNotes=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask",1 , 7);
   			DropDowns.selectDropdownByVisibleText(ProblemTaskPage.getProblemTasksSateDropdown(driver), state, "State");
   			Thread.sleep(3000);
   			TextBoxes.enterTextValue(ProblemTaskPage.getWorkNotesEdt(driver), workNotes, "Work Notes");
   			ProblemPage.getUpdateBtn(driver).click();
   			WaitUtils.waitForPageToLoad(driver, 20);
   		}
   		catch (Exception e) {
 			   ReporterLogs.log(e.getMessage(), "info");
 			}
   		}*/
       
       
       public static void closeProblemTask(WebDriver driver, String problemId) throws Exception{
    	     try{
    	        Thread.sleep(5000);
    	        ProblemPage.getProblemTasksTab(driver).click();
    	        String problemTaskState=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask", 1, 8);
    	        String workNotes=ExcelUtils.getData("Problem_Management_TestData.xlsx", "ProblemTask", 1, 7);
    	        Thread.sleep(2000);
    	        int rowCount = ProblemPage.getNumberOfTasksFromProblemTaskTab(driver).size();
    	        System.out.println(rowCount);
    	        ReporterLogs.log("Number of Tasks for the problem :"+problemId+" is "+rowCount/2, "info");
    	        for (int c = 1; c <= rowCount; c=c+2) {
    	        ProblemPage.getMultipleProblemTaskLnk(driver, c).click();
    	        WaitUtils.waitForPageToLoad(driver, 10);
    	        DropDowns.selectDropdownByVisibleText(ProblemTaskPage.getProblemTasksSateDropdown(driver),problemTaskState , "Problem Task State");
    	        Thread.sleep(1000);
    	        TextBoxes.enterTextValue(ProblemTaskPage.getWorkNotesEdt(driver), workNotes, "Work Notes");
    	        ProblemTaskPage.getSaveBtn(driver).click();
    	        WaitUtils.waitForPageToLoad(driver, 10);
    	        ProblemTaskPage.getProblemTaskBackBtn(driver).click();
    	        WaitUtils.waitForPageToLoad(driver, 10);
    	        //Thread.sleep(5000);     
    	        }
    	     }
    	     catch (Exception e) {
    	        ReporterLogs.log(e.getMessage(), "info");
    	     }

    	     }
       
       
       public static void closeProblemTicket(WebDriver driver, String problemId) throws Exception{
      		try{
      			WaitUtils.waitForPageToLoad(driver, 10);
      			//WaitUtils.waitForTitleIs(driver, problemId+" | ServiceNow");
      			Thread.sleep(2000);
      			state=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 18);
      			DropDowns.selectDropdownByVisibleText(ProblemPage.getProblemStateDropdown(driver), state, "State");
      			Thread.sleep(3000);
      			ProblemPage.getSaveBtn(driver).click();
      			try {
                  	 Alert alert = driver.switchTo().alert();
                  	 String alertText = alert.getText();
                  	 ReporterLogs.log("Alert message: " + alertText, "error");
                  	// ExtentReport.reportLog(LogStatus.FAIL, "Alert message: " + alertText);
                  	 ExcelUtils.writeDataIntoCell("Problem_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
                  	 Assert.fail("Unexpected alert !!!! ");
                      } 
                   catch (Exception e) {
                  	System.out.println("handled2");
                   }
      			WaitUtils.waitForPageToLoad(driver, 20);
      			ProblemReusables.verifyStateOfProblemTicket(driver, "Closed", problemId, 3, 2);
      		}
      		catch (Exception e) {
    			   ReporterLogs.log(e.getMessage(), "info");
    			}
      		}
}
