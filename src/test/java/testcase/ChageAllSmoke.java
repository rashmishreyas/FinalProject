package testcase;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;

import pages.ChangePage;

public class ChageAllSmoke extends SuperTestNG {
	static String crNumber=null;
	static String plannedStartDate = null;
	static String plannedEndtDate=null;
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
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	/*@BeforeSuite
	public void startReport() throws IOException
	{
		
		extent=new ExtentReports("C://Users//UX011746//Desktop//ServiceNowQAAutomation//ServiceNowQAAutomation//SNOWQA//Reports//Change Management//SmokeChangeTest.html", true); //Provide Desired Report Directory Loca)
	}
	@BeforeTest
	public void beforeTest()
	{
	System.out.println(" in Before Test ");
	}*/
	
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void testCreateChangeRequest()throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Create Change Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		
		WaitUtils.waitForPageToLoad(driver, 10);
		

		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Draf", crNumber, 1, 2);
		driver.close();
		
		
		}

	
	@Test(priority=1,description="Moving the Change to Multiple phases",enabled=true)
	public void testMovingtoMultiplePhases() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Multiple Change Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Draft", crNumber, 1, 2);
		 
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		
		ChangeReusables.moveToAssessmentState(driver);
		//ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
		
		ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		//ChangeReusables.FinalReport(driver, "Approva", crNumber, 2, 2);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangePage.getGroupApprovalTab(driver).click();
		Thread.sleep(5000);
		ChangePage.getChangeRequestedLnk(driver).click();
		Thread.sleep(5000);
		ChangePage.getApproveBtn(driver).click();
		ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Approval", crNumber, 2, 2);
		ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		Thread.sleep(5000);
		WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
		ChangePage.getScheduleTab(driver).click();
		String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
		String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
		ChangePage.getChangeTaskTab(driver).click();
		System.out.println("success");
		ChangePage.getChangeTaskLnk(driver, 1).click();
		ChangePage.getExpectedStartBtn(driver).sendKeys(start);
		ChangePage.getExpectedEndBtn(driver).sendKeys(end);
		Thread.sleep(5000);
		ChangePage.getCompleteImplementationBtn(driver).click();
		ChangeReusables.AssignedTo(driver);
	    WebElement re=ChangePage.getImplementationResult(driver);
		DropDowns.selectDropdownByIndex(re, 1, "result");
		ChangePage.getActualStartBtn(driver).sendKeys(start);
		ChangePage.getActualEndBtn(driver).sendKeys(end);
		Thread.sleep(5000);
		ChangePage.getSaveBtn(driver).click();
		ChangePage.getCloseTaskBtn(driver).click();
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Closed", crNumber, 2, 2);
		driver.close();	
		ExtentReport.endTest();
		
			
				
}
	@Test(priority=2,description="Approval of a Change Ticket",enabled=false)
	public void testMoveToApprovalState() throws Exception
	{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Approve Change Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		 
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		
		ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangePage.getGroupApprovalTab(driver).click();
		Thread.sleep(5000);
		//ChangePage.getChangeRequestedLnk(driver).click();
		ChangePage.getChangeRequestedLnk(driver);
		Thread.sleep(5000);
		ChangePage.getApproveBtn(driver).click();
		ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
		 ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		 driver.close();
		
		 
	}
	@Test(priority=3,description="Cancel Change ticket",enabled=true)
	public void testChangeTicketCancellation() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Cancel Change Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Planning", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Planning", crNumber, 4, 2);
		ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Assessment", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Assessment", crNumber, 4, 2);
		ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		ChangeReusables.moveToCancelledState(driver, crNumber);
		 ChangeReusables.verifyStateOfChangeTicket(driver, "Cancelled", crNumber,1,2);
		 ChangeReusables.FinalReport(driver, "Cancelled", crNumber, 4, 2);
		 driver.close();
		
}
	@Test(priority=4,description="Creating and updating  of a Change Task",enabled=true)
	public void testChangeCreateTask() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", " Change Task Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangePage.getGroupApprovalTab(driver).click();
		Thread.sleep(5000);
		ChangePage.getChangeRequestedLnk(driver).click();
		Thread.sleep(5000);
		ChangePage.getApproveBtn(driver).click();
	    Thread.sleep(5000);
		WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
		ChangePage.getScheduleTab(driver).click();
		String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
		String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
		ChangePage.getChangeTaskTab(driver).click();
		ChangePage.getCreateTaskBtn(driver).click();
		ChangeReusables.ConfigItem(driver);
		ChangePage.getExpectedStartBtn(driver).sendKeys(start);
		ChangePage.getExpectedEndBtn(driver).sendKeys(end);
		ChangeReusables.AssignedTo(driver);
		ChangeReusables.AssignmentGroup(driver);
		ChangeReusables.ShortDis(driver);
		ChangeReusables.Dis(driver);
		Thread.sleep(10000);
		ChangePage.getSaveBtn(driver).click();
		ChangePage.getCompleteImplementationBtn(driver).click();
		WebElement re=ChangePage.getImplementationResult(driver);
		DropDowns.selectDropdownByIndex(re, 1, "result");
		ChangePage.getActualStartBtn(driver).sendKeys(start);
		ChangePage.getActualEndBtn(driver).sendKeys(end);
		ChangePage.getSaveBtn(driver).click();
		//ChangePage.getTaskSubmitBtn(driver).click();
		ChangePage.getUpdateBtn(driver).click();
		 ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
		 ChangeReusables.FinalReport(driver, "Implementation", crNumber, 5, 2);
		 ChangeReusables.FinalReport(driver, "Implementation", crNumber, 6, 2);
	driver.close();
	
	}
	@Test(priority=5,description="moving the change task to different state and validating the closure of change task",enabled=true)
	
	public void testTaskMovingtoMultiplePhases() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Multiple Change Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		 
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		
		ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangePage.getGroupApprovalTab(driver).click();
		Thread.sleep(5000);
		ChangePage.getChangeRequestedLnk(driver).click();
		Thread.sleep(5000);
		ChangePage.getApproveBtn(driver).click();
		 ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
		 ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
	    
	    Thread.sleep(5000);
		WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
		ChangePage.getScheduleTab(driver).click();
		String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
		String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
		ChangePage.getChangeTaskTab(driver).click();
		System.out.println("success");
		ChangePage.getChangeTaskLnk(driver, 1).click();
		ChangePage.getExpectedStartBtn(driver).sendKeys(start);
		ChangePage.getExpectedEndBtn(driver).sendKeys(end);
		Thread.sleep(5000);
		ChangePage.getCompleteImplementationBtn(driver).click();
		ChangeReusables.AssignedTo(driver);
	    WebElement re=ChangePage.getImplementationResult(driver);
		DropDowns.selectDropdownByIndex(re, 1, "result");
		ChangePage.getActualStartBtn(driver).sendKeys(start);
		ChangePage.getActualEndBtn(driver).sendKeys(end);
		Thread.sleep(5000);
		ChangePage.getSaveBtn(driver).click();
		ChangePage.getCloseTaskBtn(driver).click();
		ChangePage.getUpdateBtn(driver).click();
		
		 ChangeReusables.FinalReport(driver, "Closed", crNumber, 7, 2);
		 ChangeReusables.FinalReport(driver, "Closed", crNumber, 8, 2);
			driver.close();	
			
				
}
	@Test(priority=6,description="Reject CR ticket",enabled=true)
	public void testChangeTicketApproval() throws IOException, InterruptedException{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Reject Change Ticket Report");
	
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
		//ChangeReusables.verifyStateOfChangeTicket(driver, "Planning", crNumber,1,2);
		ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangePage.getGroupApprovalTab(driver).click();
		Thread.sleep(5000);
		ChangePage.getChangeRequestedLnk(driver).click();
		Thread.sleep(5000);
		
				driver.findElement(By.xpath("//button[text()='Reject']")).click();
				driver.findElement(By.id("sysapproval_group.u_reason_for_rejection")).sendKeys("test");
				driver.findElement(By.xpath("//button[text()='Update']")).click();
				 ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,1,2);
				 ChangeReusables.FinalReport(driver, "Closed", crNumber, 9, 2);
				 driver.close();
				 
				
	}
	
	
	/*@AfterTest
	public void endReport1()
	{
		report.endTest(logger);
		report.flush();
		report.close();
	}
	*/
	
	

	
	

}
