package com.servicenow.applicationspecificlibraries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import pages.ChangePage;
import pages.ChangeTaskPage;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.ScreenShot;
import com.servicenow.genericlibraries.TextBoxes;

import freemarker.core.ReturnInstruction.Return;

public class ChangeReusables {

	static String changeId=null;
	static String assignmentGroup = null;
	static String configurationItem = null;
	static String shortDescription = null;
	static String description = null;
	static String reasonForChange = null;
	static String customerImpactDuringChange = null;
	static String implementationPlan= null;
	static String testPlan = null;
	static String backoutPlan = null;
	static String plannedStartDate = null;
	static String plannedEndtDate=null;
	static String crNumber=null;
	static String assignedTo=null;
	static String configItem=null;
	static String ManTestCase=null;
	static String tara=null;
	public static String createChange(WebDriver driver, int serialnum,int cnum){	
		try{
			
				WaitUtils.waitForXpathPresent(driver, "//a[contains(text(),'Normal: Planned')]");
				ChangePage.getNormalLnk(driver).click();
				//ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
				assignmentGroup = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				shortDescription = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
				description = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
				reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				//ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				ReporterLogs.log("Assignment Group field is entered successfully "+configurationItem, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.short_description");
				TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
				ReporterLogs.log("Assignment Group field is entered successfully "+ shortDescription, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.description");
				TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
				ReporterLogs.log("Assignment Group field is entered successfully "+ description, "info");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
				
				//driver.findElement(By.xpath("//button[text()='Save']")).click();
				/*crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2);
				ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
				ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
				ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
				WaitUtils.waitForPageToLoad(driver, 10);*/
				//ChangePage.getSubmitForPlanningBtn(driver).click();
				//Thread.sleep(50000);
				boolean b=driver.findElement(By.xpath("//button[text()='Update']")).isDisplayed();
				System.out.println(b);
				
				}
		catch(Exception e){
			e.getMessage();
			//System.out.println("handeled");

			
		}
		 System.out.println("Final"+changeId);
		return changeId;
	
	
	}
	public static String createEmerChange(WebDriver driver, int serialnum,int cnum){	
		try{
			
				WaitUtils.waitForXpathPresent(driver, "//a[contains(text(),'Emergency: Changes')]");
				ChangePage.getEmerChangeLnk(driver).click();
				//ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
				assignmentGroup = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				shortDescription = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
				description = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
				reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				//ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), "tara", "Configuration Item");
				ReporterLogs.log("Assignment Group field is entered successfully "+"tara", "info");
				WaitUtils.waitForIdPresent(driver, "change_request.short_description");
				TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
				ReporterLogs.log("Assignment Group field is entered successfully "+ shortDescription, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.description");
				TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
				ReporterLogs.log("Assignment Group field is entered successfully "+ description, "info");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
				
				//driver.findElement(By.xpath("//button[text()='Save']")).click();
				/*crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2);
				ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
				ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
				ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
				WaitUtils.waitForPageToLoad(driver, 10);*/
				//ChangePage.getSubmitForPlanningBtn(driver).click();
				//Thread.sleep(50000);
				boolean b=driver.findElement(By.xpath("//button[text()='Update']")).isDisplayed();
				System.out.println(b);
				
				}
		catch(Exception e){
			e.getMessage();
			//System.out.println("handeled");

			
		}
		 System.out.println("Final"+changeId);
		return changeId;
	
	
	}
	
	public static void createChange1(WebDriver driver, int serialnum,int cnum){	
		try{
			
			WaitUtils.waitForXpathPresent(driver, "//a[contains(text(),'Emergency: Changes')]");
			ChangePage.getEmerChangeLnk(driver).click();
				//ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
				assignmentGroup = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				shortDescription = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
				description = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
				reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				//ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				ReporterLogs.log("Assignment Group field is entered successfully "+configurationItem, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.short_description");
				TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
				ReporterLogs.log("Assignment Group field is entered successfully "+ shortDescription, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.description");
				TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
				ReporterLogs.log("Assignment Group field is entered successfully "+ description, "info");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				//WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				//TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
		}
		catch(Exception e){
			e.getMessage();
			//System.out.println("handeled");

			
		}
	}
	
	public static void creatChange2(WebDriver driver, int serialnum,int cnum){	
		try{
			
				WaitUtils.waitForXpathPresent(driver, "//a[contains(text(),'Normal: Planned')]");
				ChangePage.getNormalLnk(driver).click();
				
				//ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
				assignmentGroup = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				shortDescription = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
				description = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
				reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				//ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				ReporterLogs.log("Assignment Group field is entered successfully "+configurationItem, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.short_description");
				TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
				ReporterLogs.log("Assignment Group field is entered successfully "+ shortDescription, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.description");
				TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
				ReporterLogs.log("Assignment Group field is entered successfully "+ description, "info");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				//WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				//TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
		}
		catch(Exception e){
			e.getMessage();
			//System.out.println("handeled");

			
		}
	}
	public static String createStandardChange(WebDriver driver, int serialnum,int cnum){	
		try{
			
			
			
		
			WaitUtils.waitForXpathPresent(driver, "//a[contains(text(),'Standard: Pre-approved')]");
			ChangePage.getStandardChangeLnk(driver).click();
			ChangePage.getdatabaseLnk(driver).click();
			ChangePage.getAddSpace(driver).click();
			//ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
			assignmentGroup = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
			configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
			//shortDescription = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
			//description = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
			//reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
			changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
			ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
			//ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
			TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
			ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup, "info");
			Thread.sleep(5000);
			TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
			ReporterLogs.log("Assignment Group field is entered successfully "+configurationItem, "info");
			WaitUtils.waitForIdPresent(driver, "change_request.short_description");
			//TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
			//ReporterLogs.log("Assignment Group field is entered successfully "+ shortDescription, "info");
			//WaitUtils.waitForIdPresent(driver, "change_request.description");
			//TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
			//ReporterLogs.log("Assignment Group field is entered successfully "+ description, "info");
			WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
			ChangePage.getPlanningTab(driver).click();
			//WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
			//TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
			//ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");		
			WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
			ChangePage.getScheduleTab(driver).click();
			String requestedByDate = Utils.getCurrentDateTime();
			TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
			ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
			WaitUtils.waitForIdPresent(driver, "sysverb_insert");
			ChangePage.getSubmitBtn(driver).click();
			
			//driver.findElement(By.xpath("//button[text()='Save']")).click();
			/*crNumber = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 2, 2);
			ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "change");
			ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);*/
			//ChangePage.getSubmitForPlanningBtn(driver).click();
			//Thread.sleep(50000);
			boolean b=driver.findElement(By.xpath("//button[text()='Update']")).isDisplayed();
			System.out.println(b);
			
			}
		catch(Exception e){
			e.getMessage();
			//System.out.println("handeled");

			
		}
		System.out.println("Final"+changeId);
		return changeId;
	
	}
		
	
	public static void verifyChangeCreation(WebDriver driver, String crNumber, int snum,int cellnum) throws FileNotFoundException, IOException{
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForTitleIs(driver, "Change Requests | ServiceNow");
		if(ChangePage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
		WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']/label[text()='Search']/following-sibling::input");
			TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), crNumber, "Search Change ");
			ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
			WaitUtils.waitForPageToLoad(driver, 10);
			if(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText().equalsIgnoreCase("Draft")){
				Assert.assertEquals(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "Draft");
				ExtentReport.reportLog(LogStatus.PASS, "Successfully created change : "+crNumber);
				ReporterLogs.log("Successfully created Change with Id "+crNumber, "info");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", snum, cellnum, "Passed");
			}else{
				ReporterLogs.log("Actual Status displayed is "+ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "error");
				ReporterLogs.log("Unable to create a Change Ticket "+crNumber, "error");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", snum, cellnum, "Failed");
				ExtentReport.reportLog(LogStatus.FAIL, "Unable to create change : "+crNumber);
				Assert.assertEquals(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "Draft");
			}	
			
		}else{
			WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
			DropDowns.selectDropdownByVisibleText(ChangePage.getSearchDropDown(driver), "Number", "Search Drop Down");
			TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), crNumber, "Search Change ");
			ReporterLogs.log("Entering Change Id in the Search Text "+crNumber, "info");
			ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
			if(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText().equalsIgnoreCase("Draft")){
				Assert.assertEquals(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "Draft");
				ExtentReport.reportLog(LogStatus.PASS, "Successfully created change : "+crNumber);
				ReporterLogs.log("Successfully created Change with Id "+crNumber, "info");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", snum, cellnum, "Passed");	
			}else{
				ReporterLogs.log("Actual Status displayed is "+ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "error");
				ReporterLogs.log("Unable to create a Change Ticket "+crNumber, "error");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", snum, cellnum, "Failed");
				ExtentReport.reportLog(LogStatus.FAIL, "Unable to create change : "+crNumber);
				Assert.assertEquals(ChangePage.getChangeStatusFromQueue(driver, crNumber).getText(), "Draft");
			}		
		}
	}
	
	public static void moveToAssessmentState(WebDriver driver){
		
		try{
				customerImpactDuringChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 10);
				implementationPlan= ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 11);
				testPlan = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 12);
				backoutPlan = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 13);
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_customer_impact");
				TextBoxes.enterTextValue(ChangePage.getCustomerImpactDuringChangeEdt(driver), customerImpactDuringChange, "Customer Impact During Change");	
				ReporterLogs.log("Assignment Group field is entered successfully "+ customerImpactDuringChange, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.change_plan");
				TextBoxes.enterTextValue(ChangePage.getImplementationPlanEdt(driver), implementationPlan, "Implementation Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ implementationPlan, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.test_plan");
				TextBoxes.enterTextValue(ChangePage.getTestPlanEdt(driver), testPlan, "Test Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ testPlan, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.backout_plan");
				TextBoxes.enterTextValue(ChangePage.getBackoutPlanEdt(driver), backoutPlan, "Backout Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ backoutPlan, "info");
				ChangePage.getRiskAndImpactTab(driver).click();
				DropDowns.selectDropdownByIndex(ChangePage.getEnvironmentInWhichChangeIsToBeExecutedDropDown(driver), 1, "---Environment In Which Change Is To Be Executed Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getExpectedServiceImpactDuringExecutionOfTheChangeDropDown(driver), 1, "---Expected Service Impact During Execution Of The Change Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getPotentialServiceImpactDuringExecutionOfTheChangeDropDown(driver), 1, "---Potential Service Impact During Execution Of The Change Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getUserSupportedByTheAssetDropDown(driver), 1, "---Users Supported By The Asset Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getBackOutRecoveryComplexityDropDown(driver), 1, "---Back Out Recovery Complexity Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getFamilarityWithChangeDropDown(driver), 1, "---Familarity With Change Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getRedundantServiceDropDown(driver), 1, "---Redundant Service Drop Down---");
				ChangePage.getSubmitForAssessmentBtn(driver).click();
				Thread.sleep(10000);
		}catch(Exception e){
		ReporterLogs.log("Exception encountred "+ e.getMessage(), "error");
	}
	
}
	
public static void moveToAssessmentStatestand(WebDriver driver){
		
		try{
				//customerImpactDuringChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 10);
				implementationPlan= ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 11);
				testPlan = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 12);
				backoutPlan = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 13);
				ChangePage.getPlanningTab(driver).click();
				//WaitUtils.waitForIdPresent(driver, "change_request.u_customer_impact");
				//TextBoxes.enterTextValue(ChangePage.getCustomerImpactDuringChangeEdt(driver), customerImpactDuringChange, "Customer Impact During Change");	
				//ReporterLogs.log("Assignment Group field is entered successfully "+ customerImpactDuringChange, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.change_plan");
				TextBoxes.enterTextValue(ChangePage.getImplementationPlanEdt(driver), implementationPlan, "Implementation Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ implementationPlan, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.test_plan");
				TextBoxes.enterTextValue(ChangePage.getTestPlanEdt(driver), testPlan, "Test Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ testPlan, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.backout_plan");
				TextBoxes.enterTextValue(ChangePage.getBackoutPlanEdt(driver), backoutPlan, "Backout Plan");
				ReporterLogs.log("Assignment Group field is entered successfully "+ backoutPlan, "info");
				//ChangePage.getRiskAndImpactTab(driver).click();
				/*DropDowns.selectDropdownByIndex(ChangePage.getEnvironmentInWhichChangeIsToBeExecutedDropDown(driver), 1, "---Environment In Which Change Is To Be Executed Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getExpectedServiceImpactDuringExecutionOfTheChangeDropDown(driver), 1, "---Expected Service Impact During Execution Of The Change Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getPotentialServiceImpactDuringExecutionOfTheChangeDropDown(driver), 1, "---Potential Service Impact During Execution Of The Change Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getUserSupportedByTheAssetDropDown(driver), 3, "---Users Supported By The Asset Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getBackOutRecoveryComplexityDropDown(driver), 1, "---Back Out Recovery Complexity Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getFamilarityWithChangeDropDown(driver), 1, "---Familarity With Change Drop Down---");
				DropDowns.selectDropdownByIndex(ChangePage.getRedundantServiceDropDown(driver), 1, "---Redundant Service Drop Down---");*/
				ChangePage.getSubmitForAssessmentBtn(driver).click();
				System.out.println("test");
				Thread.sleep(10000);
		}catch(Exception e){
		ReporterLogs.log("Exception encountred "+ e.getMessage(), "error");
	}
	
}
	
	
	
	public static void moveToCancelledState(WebDriver driver,String crNumber) {
		try{
			ChangePage.getActivityTab(driver).click();
			ChangePage.getReasonForCancellationEdt(driver).sendKeys("test Cancel");
			ChangePage.getChangeCancelBtn(driver).click();
			WaitUtils.waitForPageToLoad(driver, 20);
			ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
			WaitUtils.waitForPageToLoad(driver, 10);
			String stateofChange = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
			System.out.println(stateofChange);
			ReporterLogs.log("State of the Change is :Cancelled"+stateofChange);
			/*if(stateofChange.equalsIgnoreCase("")){
					Assert.assertEquals(stateofChange, "Cancelled");
					ExtentReport.reportLog(LogStatus.PASS, "Successfully Change change : "+crNumber);
					ReporterLogs.log("Successfully updated Change with Id "+crNumber, "info");
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 2, crNumber);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Passed");
				}else{
					ExtentReport.reportLog(LogStatus.FAIL, "Unable to Cancel change : "+crNumber);
					ReporterLogs.log("Unable to update Change with Id "+crNumber, "error");
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 2, crNumber);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", 3, 4, "Failed");
					Assert.assertEquals(stateofChange, "Planning");
		}*/
		}catch(Exception e){
			ReporterLogs.log("Exception :"+e.getMessage(),"error");
		}		
	}
	
	public static void moveToApprovalState(WebDriver driver) {
		try{
				/*plannedStartDate = Utils.getDesiredDateAndTime(1);
				plannedEndtDate= Utils.getDesiredDateAndTime(2);
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				Actions act = new Actions(driver);
				act.sendKeys(Keys.PAGE_DOWN).build().perform();
				WaitUtils.waitForIdPresent(driver, "change_request.start_date");
				//TextBoxes.enterTextValue(ChangePage.getPlannedStartDateEdt(driver), plannedStartDate, "Planned Start Date");
				TextBoxes.enterTextValue(ChangePage.getPlannedStartDateEdt(driver), "2018-07-07 07:17:19", "Planned Start Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ plannedStartDate, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.end_date");
				//TextBoxes.enterTextValue(ChangePage.getPlannedEndDateEdt(driver), plannedEndtDate, "Planned End Date");
				TextBoxes.enterTextValue(ChangePage.getPlannedEndDateEdt(driver), "2018-07-14 07:17:25", "Planned End Date");
				
				ReporterLogs.log("Requested By Date field is entered successfully "+ plannedEndtDate, "info");*/
				//ChangePage.getGroupApprovalTab(driver).click();
				//TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), "exam", "Short Description");
				ChangePage.getRequestImpementationApprovalBtn(driver).click();
				Thread.sleep(5000);
			//	ChangePage.getGroupApprovalTab(driver).click();
				
				
				Thread.sleep(5000);
				//ChangePage.getGroupApprovalTab(driver).click();
				
				System.out.println("a");
				//driver.switchTo().frame("gsft_main");
				//WebElement el =driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Group') and contains(text(),'Approvals')]"));
				//WebElement el=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table/tbody/tr/td/div/table/tbody/tr/td[3]/a"));
				
				//Utils.scrollingToElementofAPage(el, driver,"Requested");
				//JavascriptExecutor exe = (JavascriptExecutor) driver;
			//	Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
			//	System.out.println("Number of iframes on the page are " + numberOfFrames);
				//driver.findElement(By.id("sys_display.change_request.requested_by")).sendKeys("changed");
				//driver.findElement(By.xpath("")).sendKeys("example");
				//TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), "exam", "Short Description");
				//Thread.sleep(5000);
				
				
				//System.out.println("sleeping");
				Thread.sleep(5000);
				//TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), "exam", "Short Description");
				//Thread.sleep(5000);
				//WebElement el=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table/tbody/tr/td/div/table/tbody/tr/td[3]/a"));
			
				//Utils.scrollingToElementofAPage(el, driver,"Requested");
				
				//driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table/tbody/tr/td/div/table/tbody/tr/td[2]/a")).click();
				//act.sendKeys(Keys.PAGE_DOWN).build().perform();
				
				
				Thread.sleep(5000);
				
				
				
				/*JavascriptExecutor js = (JavascriptExecutor) driver;
				
				js.executeScript("window.scrollBy(0, 200)");
				
				
				WebElement el =driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Group') and contains(text(),'Approvals')]"));
				js.executeScript("arguments[0].scrollIntoView(true)", el);
				
				
				WaitUtils.waitForXpathPresent(driver, "//div[@id='tabs2_list']//span[contains(text(),'Group') and contains(text(),'Approvals')]");
				int x=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Group') and contains(text(),'Approvals')]")).getLocation().x;
				int y=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Group') and contains(text(),'Approvals')]")).getLocation().y;
				System.out.println(x+"     "+y);
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo("+x+","+y+")");*/
				//ChangePage.getGroupApprovalTab(driver).click();
				
				//WebElement scroll = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table/tbody/tr/td/div/table/tbody/tr/td[3]/a"));
				//scroll.sendKeys(Keys.PAGE_DOWN);
				
				//driver.findElement(By.partialLinkText("Requested")).click();
			
	//driver.findElement(By.linkText("Requested")).click();
				//ChangePage.getGroupApprovalTab(driver).click();
				Thread.sleep(10000);
		}catch(Exception e){
			ReporterLogs.log("Exception :"+e.getMessage(),"error");
		}	
	}
	public static void moveToApprovalState1(WebDriver driver) {
		try{
				plannedStartDate = Utils.getDesiredDateAndTime(1);
				plannedEndtDate= Utils.getDesiredDateAndTime(2);
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.start_date");
				TextBoxes.enterTextValue(ChangePage.getPlannedStartDateEdt(driver), plannedStartDate, "Planned Start Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ plannedStartDate, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.end_date");
				TextBoxes.enterTextValue(ChangePage.getPlannedEndDateEdt(driver), plannedEndtDate, "Planned End Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ plannedEndtDate, "info");
				ChangePage.getRequestImpementationApprovalBtn1(driver).click();
				
				Thread.sleep(10000);
		}catch(Exception e){
			ReporterLogs.log("Exception :"+e.getMessage(),"error");
		}	
	}
	
	
	public static void verifyStateOfChangeTicket(WebDriver driver, String expectedStateOfTicket,String crNum,int reqRow, int reqcol) {
		try{
				String actualStateOfTicket = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
				System.out.println(actualStateOfTicket);
				ReporterLogs.log("State of the Change is :"+actualStateOfTicket);
				if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket)){
					Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
					ExtentReport.reportLog(LogStatus.PASS, "Actual State of the Change Ticket : "+actualStateOfTicket);
					ReporterLogs.log("Successfully updated Change with Id "+crNum, "info");
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Passed");
				}else{
					ExtentReport.reportLog(LogStatus.FAIL, "State of the Change ticket are not same : "+actualStateOfTicket);
					ReporterLogs.log("Unable to update Change with Id "+crNum, "error");
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
					ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Failed");
					Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
	}
		}catch(Exception e){
			ReporterLogs.log("Exception :"+e.getMessage(),"error");
		}
	}
	
	public static void searchDesiredChangeTicket(WebDriver driver,String ticketNumber) throws InterruptedException{
		
		if(ChangePage.getSearchDropDown(driver).getAttribute("value").equalsIgnoreCase("number")){
			Thread.sleep(5000);
			TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), ticketNumber, "Searching for change "+ticketNumber);
			ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
			WaitUtils.waitForPageToLoad(driver, 10);
		}else{
				WaitUtils.waitForXpathPresent(driver, "//div[@class='input-group']//select");
				DropDowns.selectDropdownByVisibleText(ChangePage.getSearchDropDown(driver), "Number", "Search Drop Down");
				TextBoxes.enterTextValue(ChangePage.getSearchChangeEdt(driver), ticketNumber, "Search Change ");
				ReporterLogs.log("Entering change in the Search Text "+ticketNumber, "info");
				ChangePage.getSearchChangeEdt(driver).sendKeys(Keys.ENTER);
				WaitUtils.waitForPageToLoad(driver, 10);
		}
		
}
	
	public static void moveToImplementationState(WebDriver driver) {
		try{
			WaitUtils.waitForXpathPresent(driver, "//div[@id='tabs2_list']//span[contains(text(),'Group') and contains(text(),'Approvals')]");
			ChangePage.getGroupApprovalTab(driver).click();
			int pendingApprovalCount = ChangePage.getPendingApproversFromGroupApprovalsQueue(driver).size();
			ReporterLogs.log("Number of Pedning Approvals :"+pendingApprovalCount, "info");
			for (int c = 1; c <= pendingApprovalCount; c++) {
				ChangePage.getChangeRequestedLnk(driver, c).click();
				WaitUtils.waitForPageToLoad(driver, 10);
				ChangePage.getChangeApproveBtn(driver).click();
				Thread.sleep(5000);
			}
		}catch(Exception e){
			ReporterLogs.log("Exception :"+e.getMessage(),"error");
		}	
	}
	
	public static void moveToClosedState(WebDriver driver) {
		try{
				WaitUtils.waitForXpathPresent(driver, "//div[@id='tabs2_list']//span[contains(text(),'Change') and contains(text(),'Tasks')]");
				ChangePage.getChangeTaskTab(driver).click();
				int taskCount = ChangePage.getChangeTaskLnkFromChangeQueue(driver).size();
				ReporterLogs.log("Number of Tasks :"+taskCount, "info");
				for (int j = 1; j <= taskCount; j++) {
					String ChangeTaskAssignedTo = ChangePage.getChangeRequestedByEdt(driver).getAttribute("value");
					ChangePage.getChangeTaskLnk(driver, j).click();
					ChangeTaskPage.getChangeTaskCompleteBtn(driver).click();
					WaitUtils.waitForIdPresent(driver, "change_task.u_implementation_result");
					DropDowns.selectDropdownByVisibleText(ChangeTaskPage.getImplementationResultDropDown(driver), "Successful", "---Implementation Result Drop Down---");
					WaitUtils.waitForIdPresent(driver, "change_task.u_actual_start");
					TextBoxes.enterTextValue(ChangeTaskPage.getActualStartEdt(driver), Utils.getCurrentDateTime(), "---Actual Start Date---");
					WaitUtils.waitForIdPresent(driver, "change_task.u_actual_end");
					TextBoxes.enterTextValue(ChangeTaskPage.getActualEndEdt(driver), Utils.getCurrentDateTime(), "---Actual End Date---");
					WaitUtils.waitForIdPresent(driver, "sys_display.change_task.assigned_to");
					TextBoxes.enterTextValue(ChangeTaskPage.getChangeTaskAssignedToEdt(driver), ChangeTaskAssignedTo, "---Change Task Assigned To---");
					ChangeTaskPage.getChangeTaskAssignedToEdt(driver).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					ChangeTaskPage.getChangeTaskCloseBtn(driver).click();
					Thread.sleep(2000);
					ChangeTaskPage.getChangeTaskBackBtn(driver).click();
					Thread.sleep(5000);
				}
		}catch(Exception e){
			ReporterLogs.log("Exception :"+e.getMessage(),"error");
		}	
	}
	public static void groupApp(WebDriver driver)
	{
		//ChangePage.getGroupApprovalTab(driver).click();
		WaitUtils.waitForXpathPresent(driver,"/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table/tbody/tr/td/div/table/tbody/tr/td[2]/a");
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table/tbody/tr/td/div/table/tbody/tr/td[2]/a")).click();
		
	}
	public static String approveChange(WebDriver driver, int serialnum,int cnum){	
		try{
				WaitUtils.waitForXpathPresent(driver, "//a[contains(text(),'Normal: Planned')]");
				ChangePage.getNormalLnk(driver).click();
				ExtentReport.reportLog(LogStatus.INFO, "Creating Normal Change Ticket");
				assignmentGroup = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
				configurationItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
				shortDescription = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
				description = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
				reasonForChange = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 9);
				changeId=ChangePage.getChangeNumberEdt(driver).getAttribute("value");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", serialnum, cnum, changeId);
				ExtentReport.reportLog(LogStatus.INFO, "Change Id : "+changeId);
				TextBoxes.enterTextValue(ChangePage.getAssignmentGrpEdt(driver), assignmentGroup, "Assignement Group Field");
				ReporterLogs.log("Assignment Group field is entered successfully "+assignmentGroup, "info");
				Thread.sleep(5000);
				TextBoxes.enterTextValue(ChangePage.getConfigurationItemEdt(driver), configurationItem, "Configuration Item");
				ReporterLogs.log("Assignment Group field is entered successfully "+configurationItem, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.short_description");
				TextBoxes.enterTextValue(ChangePage.getShortDescriptionEdt(driver), shortDescription, "Short Description");
				ReporterLogs.log("Assignment Group field is entered successfully "+ shortDescription, "info");
				WaitUtils.waitForIdPresent(driver, "change_request.description");
				TextBoxes.enterTextValue(ChangePage.getDescriptionEdt(driver), description, "Description");	
				ReporterLogs.log("Assignment Group field is entered successfully "+ description, "info");
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Planning') and @class='tab_caption_text']");
				ChangePage.getPlanningTab(driver).click();
				WaitUtils.waitForIdPresent(driver, "change_request.u_reason_for_change");
				TextBoxes.enterTextValue(ChangePage.getReasonForChangeEdt(driver), reasonForChange, "Reason For Change");
				ReporterLogs.log("Assignment Group field is entered successfully "+ reasonForChange, "info");		
				WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
				ChangePage.getScheduleTab(driver).click();
				String requestedByDate = Utils.getCurrentDateTime();
				TextBoxes.enterTextValue(ChangePage.getRequestedByDateEdt(driver), requestedByDate, "Requested By Date");
				ReporterLogs.log("Requested By Date field is entered successfully "+ requestedByDate, "info");
				WaitUtils.waitForIdPresent(driver, "sysverb_insert");
				ChangePage.getSubmitBtn(driver).click();
				}catch(Exception e){
			e.getMessage();
		}
		return changeId;
	}
	public static void schedule(WebDriver driver)
	{
		plannedStartDate = Utils.getDesiredDateAndTime(1);
		plannedEndtDate= Utils.getDesiredDateAndTime(2);
		WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
		ChangePage.getScheduleTab(driver).click();
		WaitUtils.waitForIdPresent(driver, "change_request.start_date");
		TextBoxes.enterTextValue(ChangePage.getPlannedStartDateEdt(driver), plannedStartDate, "Planned Start Date");
		ReporterLogs.log("Requested By Date field is entered successfully "+ plannedStartDate, "info");
		WaitUtils.waitForIdPresent(driver, "change_request.end_date");
		TextBoxes.enterTextValue(ChangePage.getPlannedEndDateEdt(driver), plannedEndtDate, "Planned End Date");
		
		
	}
	public static void AssignedTo(WebDriver driver) throws IOException
	{
		assignedTo = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 14);
		driver.findElement(By.id("sys_display.change_task.assigned_to")).sendKeys(assignedTo);
		
	}
	
	public static void ConfigItem(WebDriver driver) throws IOException
	{
		configItem = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 6);
		System.out.println(configItem);
		driver.findElement(By.id("sys_display.change_task.cmdb_ci")).sendKeys(configItem);
		
	}
public static void AssignmentGroup(WebDriver driver) throws IOException
{
	assignmentGroup = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 5);
	driver.findElement(By.id("sys_display.change_task.assignment_group")).sendKeys(assignmentGroup);
	
	
	
}
public static void ShortDis(WebDriver driver) throws IOException
{
	shortDescription = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 7);
	driver.findElement(By.id("change_task.short_description")).sendKeys(shortDescription);
}

public static void Dis(WebDriver driver) throws IOException
{
	description = ExcelUtils.getData("Change_Management_TestData.xlsx", "Smoke_Suite", 1, 8);
	
	driver.findElement(By.id("change_task.description")).sendKeys(description);
}



public static void FinalReport(WebDriver driver, String expectedStateOfTicket,String crNum,int reqRow, int reqcol) throws FileNotFoundException, IOException {
	
		try{
			String actualStateOfTicket = DropDowns.getFirstSelectedOptionName(ChangePage.getChangeStateEdtDropDown(driver), "State Drop Down");
			System.out.println(actualStateOfTicket);
			ReporterLogs.log("State of the Change is :"+actualStateOfTicket);
			  ManTestCase  = ExcelUtils.getData("SmokeTestReport.xlsx", "Change", 1, 3);
			if(actualStateOfTicket.equalsIgnoreCase(expectedStateOfTicket)){
				Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
				ExtentReport.reportLog(LogStatus.PASS, "Actual State of the Change Ticket : "+actualStateOfTicket);
				ReporterLogs.log("Successfully updated Change with Id "+crNum, "info");
				
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Passed");
                System.out.println(ManTestCase);
				
				
					ExcelUtils.writeDataIntoCell("SmokeTestReport.xlsx", "Change", reqRow, 4, "Passed");
				
			}else{
				ExtentReport.reportLog(LogStatus.FAIL, "State of the Change ticket are not same : "+actualStateOfTicket);
				ReporterLogs.log("Unable to update Change with Id "+crNum, "error");
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, reqcol, crNum);
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 3, actualStateOfTicket);
				ExcelUtils.writeDataIntoCell("Change_Management_TestData.xlsx", "Smoke_Suite", reqRow, 4, "Failed");
				ExcelUtils.writeDataIntoCell("SmokeTestReport.xlsx", "Change", reqRow, 4, "Failed");
				Assert.assertEquals(actualStateOfTicket, expectedStateOfTicket);
				//ExcelUtils.writeDataIntoCell("SmokeTestReport.xlsx", "Change", 1, 4, "Failed");
			}
		}
			catch(Exception e)
			{
				System.out.println("handled");
			}
				
				
}
	
}
