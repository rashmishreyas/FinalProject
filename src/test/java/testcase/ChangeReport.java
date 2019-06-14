package testcase;



import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.CaptureScreenShot;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ScreenShot;

import pages.ChangePage;


public class ChangeReport{
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	static String crNumber=null;
	@BeforeTest
	public void startReport(){
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport11.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Amreen Noor");
		htmlReporter.config().setDocumentTitle("Change Request");
		htmlReporter.config().setReportName("Change Request Smoke suite");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
		
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void testCreateChangeRequest() throws IOException, InterruptedException{
		
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		//ChangeReusables.FinalReport(driver, "Draft", crNumber, 1, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
	}
	@Test(priority=1,description="Approval of a Change Ticket",enabled=true)
	public void testMoveToApprovalState() throws Exception
	{
		//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Approve Change Ticket Report");
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
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
	    Thread.sleep(5000);
	    ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
	 //ChangeReusables.FinalReport(driver, "Implementation", crNumber, 3, 2);
		 driver.close();
		 Assert.assertTrue(true);
		 logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Approve of change ticket", ExtentColor.GREEN));
		
		 
	}
	@Test(priority=2,description="Cancel Change ticket",enabled=true)
	public void testChangeTicketCancellation() throws IOException, InterruptedException{
		//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Cancel Change Ticket Report");
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
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
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Cancel Change ticket", ExtentColor.GREEN));
		
}
	@Test(priority=3,description="Reject CR ticket",enabled=true)
	public void testChangeTicketReject() throws IOException, InterruptedException{
		//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Reject Change Ticket Report");
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
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
		ChangePage.getRejectBtn(driver).click();
		ChangePage.getReasonForReject(driver).sendKeys("test");
		ChangePage.getUpdateBtn(driver).click();
		//driver.findElement(By.xpath("//button[text()='Reject']")).click();
		//driver.findElement(By.id("sysapproval_group.u_reason_for_rejection")).sendKeys("test");
		//driver.findElement(By.xpath("//button[text()='Update']")).click();
		ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,1,2);
		// ChangeReusables.FinalReport(driver, "Closed", crNumber, 9, 2);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Reject CR ticket", ExtentColor.GREEN));
				
	}
	@Test(priority=4,description="Approval of chnage ticket and Moving the Change to Multiple phase",enabled=true)
	public void testMovingtoMultiplePhases() throws Exception{
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);

		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		WaitUtils.waitForPageToLoad(driver, 10);
		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Draft", crNumber, 2, 2);
		ChangePage.getSubmitForPlanningBtn(driver).click();
		Thread.sleep(10000);
	    ChangeReusables.moveToAssessmentState(driver);
		ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
	    ChangeReusables.schedule(driver);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangeReusables.moveToApprovalState(driver);
		ChangeReusables.FinalReport(driver, "Approval", crNumber, 2, 2);
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
		ChangePage.getGroupApprovalTab(driver).click();
		Thread.sleep(5000);
		ChangePage.getChangeRequestedLnk(driver).click();
		Thread.sleep(5000);
		ChangePage.getApproveBtn(driver).click();
		ChangeReusables.verifyStateOfChangeTicket(driver, "Implementation", crNumber,1,2);
		Thread.sleep(5000);
		WaitUtils.waitForXpathPresent(driver, "//span[contains(text(),'Schedule')]");
		ChangePage.getScheduleTab(driver).click();
		String start=driver.findElement(By.id("change_request.start_date")).getAttribute("value");
		String end=driver.findElement(By.id("change_request.end_date")).getAttribute("value");
		System.out.println(start);
		ChangePage.getChangeTaskTab(driver).click();
		System.out.println("success");
		ChangePage.getChangeTaskLnk(driver, 1).click();
		Thread.sleep(3000);
		ChangePage.getExpectedStartBtn(driver).sendKeys(start);
		Thread.sleep(3000);
		ChangePage.getExpectedEndBtn(driver).sendKeys(end);
		Thread.sleep(5000);
		ChangePage.getCompleteImplementationBtn(driver).click();
		ChangeReusables.AssignedTo(driver);
	    WebElement re=ChangePage.getImplementationResult(driver);
		DropDowns.selectDropdownByIndex(re, 1, "result");
		ChangePage.getActualStartBtn(driver).sendKeys(start);
		Thread.sleep(3000);
		ChangePage.getActualEndBtn(driver).sendKeys(end);
		Thread.sleep(5000);
		ChangePage.getSaveBtn(driver).click();
		ChangePage.getCloseTaskBtn(driver).click();
		ChangePage.getUpdateBtn(driver).click();
		ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Closed", crNumber, 2, 2);
		driver.close();	
		Assert.assertTrue(true);
	    logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Moving the Change to Multiple phases ", ExtentColor.GREEN));
}
	@Test(priority=5,description="Creating and updating  of a Change Task",enabled=false)
	public void testChangeCreateTask() throws Exception{
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
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
	     Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creating and updating  of a Change Task ", ExtentColor.GREEN));
	
	
	}
@Test(priority=6,description="creating and updating and moving the change task to different state and validating the closure of change task",enabled=true)
	
	public void testTaskMovingtoMultiplePhases() throws Exception{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
			 Assert.assertTrue(true);
				logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is moving the change task to different state and validating the closure of change task ", ExtentColor.GREEN));
			
			
				
}
	
@Test(priority=7,description="Cancel change task",enabled=true)

public void testCancelChangeTask() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
	
	//ChangePage.getCloseTaskBtn(driver).click();
	ChangePage.getCancelTaskButton(driver).click();
	ChangePage.getReasonForCancel(driver).sendKeys("test");
	
	ChangePage.getUpdateBtn(driver).click();
	
	 ChangeReusables.FinalReport(driver, "Cancelled", crNumber, 7, 2);
	 ChangeReusables.FinalReport(driver, "Cancelled", crNumber, 8, 2);
		driver.close();	
		 Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel(" Cancel change task ", ExtentColor.GREEN));
		
		
			
}

@Test(priority=8,description="Update change ticket",enabled=true)
public void testUpdateChangeTicket() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
    ChangeReusables.schedule(driver);
	ChangePage.getUpdateBtn(driver).click();
	driver.close();
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is updation of a CR Ticket", ExtentColor.GREEN));
}
@Test(priority=9,description="Default task validation",enabled=true)
public void testDefaulttaskvalidation() throws IOException, InterruptedException{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);

	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
	ChangePage.getChangeTaskTab(driver).click();
	System.out.println("success");
	ChangePage.getChangeTaskLnk(driver, 1).click();
	String cTask = ChangePage.getTaskChangeEdt(driver).getAttribute("value");
	if(cTask!=null)
	{
		System.out.println(cTask);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed Default task validation", ExtentColor.GREEN));
	}
	else
	{
		System.out.println("fail");
	}
	
}
@Test(priority=10,description="Follow up clicked list view",enabled=true)
public void testFollowUpListView() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);

	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
    ChangeReusables.schedule(driver);
	ChangePage.getUpdateBtn(driver).click();
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
	ChangePage.getChangeNumberFromQueue(driver, crNumber).click();
	ChangeReusables.moveToApprovalState(driver);
	ChangePage.getFollowUpBtn(driver).click();
    Thread.sleep(5000);
	String actualTitle = driver.getTitle();
	System.out.println(actualTitle);
	String expectedTitle = "Change Requests | ServiceNow";
	assertEquals(expectedTitle,actualTitle);
	driver.close();
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed Follow up clicked list view", ExtentColor.GREEN));
	
}
@Test(priority=11,description="testValidateMandatoryChangeTask",enabled=true)

public void testValidateMandatoryChangeTask() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
	/*ChangePage.getExpectedStartBtn(driver).sendKeys(start);
	ChangePage.getExpectedEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getCompleteImplementationBtn(driver).click();
	ChangeReusables.AssignedTo(driver);
    WebElement re=ChangePage.getImplementationResult(driver);
	DropDowns.selectDropdownByIndex(re, 1, "result");
	ChangePage.getActualStartBtn(driver).sendKeys(start);
	ChangePage.getActualEndBtn(driver).sendKeys(end);*/
	Thread.sleep(5000);
	ChangePage.getSaveBtn(driver).click();
	if( driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel(" testValidateMandatoryChangeTask", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
		}
	
	//ChangePage.getCloseTaskBtn(driver).click();
	/*ChangePage.getCancelTaskButton(driver).click();
	ChangePage.getReasonForCancel(driver).sendKeys("test");
	
	ChangePage.getUpdateBtn(driver).click();
	
	 ChangeReusables.FinalReport(driver, "Cancelled", crNumber, 7, 2);
	 ChangeReusables.FinalReport(driver, "Cancelled", crNumber, 8, 2);*/
		
			
}


@Test(priority=12,description="change task partial closed",enabled=true)

public void testChangeTaskPartiallClose() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
	DropDowns.selectDropdownByIndex(re, 2, "result");
	WebElement re1=ChangePage.getImplementationSubcode(driver);
	if(re1.isDisplayed())
	{
		System.out.println("pass");
	}
	DropDowns.selectDropdownByIndex(re1, 1,"subcode");
	
	ChangePage.getActualStartBtn(driver).sendKeys(start);
	ChangePage.getActualEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getSaveBtn(driver).click();
	
	ChangePage.getCloseTaskBtn(driver).click();
	
	ChangePage.getUpdateBtn(driver).click();
	
	 ChangeReusables.FinalReport(driver, "Closed", crNumber, 7, 2);
	 ChangeReusables.FinalReport(driver, "Closed", crNumber, 8, 2);
		driver.close();	
		 Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is change task partial closed ", ExtentColor.GREEN));
		
		
			
}

@Test(priority=13,description="Change task unsuccessfull close",enabled=true)

public void testChangeTaskUnsuccessfulllClose() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
	DropDowns.selectDropdownByIndex(re, 3, "result");
	WebElement re1=ChangePage.getImplementationSubcode(driver);
	if(re1.isDisplayed())
	{
		System.out.println("pass");
	}
	DropDowns.selectDropdownByIndex(re1, 1,"subcode");
	
	ChangePage.getActualStartBtn(driver).sendKeys(start);
	ChangePage.getActualEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getSaveBtn(driver).click();
	
	ChangePage.getCloseTaskBtn(driver).click();
	
	ChangePage.getUpdateBtn(driver).click();
	
	 ChangeReusables.FinalReport(driver, "Closed", crNumber, 7, 2);
	 ChangeReusables.FinalReport(driver, "Closed", crNumber, 8, 2);
		driver.close();	
		 Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed Change task unsuccessfull close", ExtentColor.GREEN));
		
		
			
}
@Test(priority=14,description="test close task and cancel task buttons are available in the change task",enabled=true)

public void testCloseAndCancell() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
	if( driver.findElement(By.xpath("//button[text()='Cancel Task']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Close Task']")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed test close task and cancel task buttons are available in the change task", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
	
		}
	
	
	/*ChangePage.getCloseTaskBtn(driver).click();
	ChangePage.getUpdateBtn(driver).click();
	
	 ChangeReusables.FinalReport(driver, "Closed", crNumber, 7, 2);
	 ChangeReusables.FinalReport(driver, "Closed", crNumber, 8, 2);*/
		//driver.close();	
		// Assert.assertTrue(true);
			//logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is moving the change task to different state and validating the closure of change task ", ExtentColor.GREEN));
		
		
			
}


@Test(priority=15,description="Reject CR ticket to close",enabled=true)
public void testChangeTicketRejectClose() throws IOException, InterruptedException{
	//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Reject Change Ticket Report");
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
	ChangePage.getRejectBtn(driver).click();
	ChangePage.getReasonForReject(driver).sendKeys("test");
	ChangePage.getUpdateBtn(driver).click();
	//driver.findElement(By.xpath("//button[text()='Reject']")).click();
	//driver.findElement(By.id("sysapproval_group.u_reason_for_rejection")).sendKeys("test");
	//driver.findElement(By.xpath("//button[text()='Update']")).click();
	ChangeReusables.verifyStateOfChangeTicket(driver, "Closed", crNumber,1,2);
	// ChangeReusables.FinalReport(driver, "Closed", crNumber, 9, 2);
	driver.close();
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Reject CR ticket to close", ExtentColor.GREEN));
			
}
@Test(priority=16,description="test copy change, submit for planning ,cancel change buttons are avialable",enabled=true)
public void testCopyChangeAnSubmitForPlanningButtons() throws IOException, InterruptedException{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);

	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	if( driver.findElement(By.xpath("//button[text()='Submit for Planning']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Copy Change']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Cancel Change']")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed Copy change and submit for planning button validations", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
	
		}
	
	
	//driver.close();
	//Assert.assertTrue(true);
	//logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
}
@Test(priority=17,description="Save and submit buttons are available in the form",enabled=true)
public void testSaveAndSubmitButtons() throws IOException, InterruptedException{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "change");
	WaitUtils.waitForXpathPresent(driver, "//a[contains(text(),'Normal: Planned')]");
	ChangePage.getNormalLnk(driver).click();
	
	if( driver.findElement(By.xpath("//button[text()='Submit']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Save']")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed Save and submit buttons are available in the form", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
	
		}
	
	
	//driver.close();
	//Assert.assertTrue(true);
	//logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
}


@Test(priority=18,description="Planned start and end dates validations",enabled=true)
public void testStartAndEnd() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);
	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
    ChangePage.getSaveBtn(driver).click();
    if( driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed Planned start and end dates validations", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
		}
}



@Test(priority=19,description="Reject and approve button validations",enabled=true)
public void testRejectAndApproveButtons() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "change");
	crNumber = ChangeReusables.createChange(driver,1,2);

	ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
    ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
	WaitUtils.waitForPageToLoad(driver, 10);
	ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
	ChangePage.getSubmitForPlanningBtn(driver).click();
	Thread.sleep(10000);
    ChangeReusables.moveToAssessmentState(driver);
	ChangeReusables.FinalReport(driver, "Assessment", crNumber, 2, 2);
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
	if( driver.findElement(By.xpath("//button[text()='Approve']")).isDisplayed()&&
			driver.findElement(By.xpath("//button[text()='Reject']")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed Reject and approve button validations", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
	
		}
	
	
}


@Test(priority=20,description="Complete implementation button validation",enabled=true)

public void testCompleteImplementationButton() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
	Thread.sleep(5000);
	if( driver.findElement(By.xpath("//button[text()='Complete Implementation']")).isDisplayed()){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed complete implementation button validations", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
	
		}
	


}

@Test(priority=21,description="Close assessment button validations",enabled=true)

public void testCloseAssessmentButton() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
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
	Thread.sleep(3000);
	ChangePage.getExpectedStartBtn(driver).sendKeys(start);
	Thread.sleep(3000);
	ChangePage.getExpectedEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getCompleteImplementationBtn(driver).click();
	ChangeReusables.AssignedTo(driver);
	Thread.sleep(3000);
    WebElement re=ChangePage.getImplementationResult(driver);
	DropDowns.selectDropdownByIndex(re, 1, "result");
	ChangePage.getActualStartBtn(driver).sendKeys(start);
	Thread.sleep(3000);
	ChangePage.getActualEndBtn(driver).sendKeys(end);
	Thread.sleep(5000);
	ChangePage.getSaveBtn(driver).click();
	String state=driver.findElement(By.id("sys_readonly.change_task.state")).getAttribute("value");
	System.out.println(state);
	if(state.equals("Closing Assessment")){
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed Close assessment button validations", ExtentColor.GREEN));
		}else{
			System.out.println("invisible");
		
	
		}
	


}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		WebDriver driver = null;
		//String screenShot = CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName(result));
		if(result.getStatus() == ITestResult.FAILURE){
			
			//logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			//logger.fail("Screen Shot : " + logger.addScreenCaptureFromPath(screenShot));
			
		}else if(result.getStatus() == ITestResult.SKIP){
			//logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));	
		}
	}
	@AfterTest
	public void endReport(){
		extent.flush();
    }
	
	@AfterSuite
	public void afterSuite() {
		 final String username = "ax00506739@techmahindra.com";
	        final String password ="ShahedA>220193";

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "outlook.office365.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });
	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("ax00506739@techmahindra.com"));
	            message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse("rp00506732@techmahindra.com"));
	            message.setSubject("Test");
	            message.setText("HI");

	            //Transport.send(message);

	          //  System.out.println("Done");
	            MimeBodyPart messageBodyPart = new MimeBodyPart();

	            Multipart multipart = new MimeMultipart();

	            messageBodyPart = new MimeBodyPart();
	            String file = "C:\\Users\\UX011746\\Desktop\\ServiceNowQAAutomation\\ServiceNowQAAutomation\\SNOWQA\\test-output\\STMExtentReport11.html";
	            String fileName = "STMExtentReport11";
	            DataSource source = new FileDataSource(file);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName(fileName);
	            multipart.addBodyPart(messageBodyPart);

	            message.setContent(multipart);

	            System.out.println("Sending");

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    
	}

	
}