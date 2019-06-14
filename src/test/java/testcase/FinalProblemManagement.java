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
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProblemPage;
import pages.ProblemTaskPage;

import com.servicenow.applicationspecificlibraries.ProblemReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;



public class FinalProblemManagement{
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	static String prNumber=null;
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
	
	
		
	@Test(priority=0,description="-----Create Problem Test Case-----",enabled=true)
	public void testCreatedProblemRequest() throws Exception{
		
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
		
		ServiceNowUtils.navigateToModuleName(driver, "Problem");
 	   String prNumber = ProblemReusables.createProblem(driver,1,2);
 	  ProblemReusables.verifyProblemCreation(driver, prNumber); 
 	   
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creation of a CR Ticket", ExtentColor.GREEN));
	}
	@Test(priority=1,description="-----update Problem Test Case-----",enabled=true)
	public void testUpdateProblemTicket() throws Exception
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
		
		ServiceNowUtils.navigateToModuleName(driver, "Problem");
 	   prNumber=ProblemReusables.createProblem(driver,1,2);
 	      ProblemReusables.updateProblemTicket(driver, prNumber,2,2);
		 driver.close();
		 Assert.assertTrue(true);
		 logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is -----update Problem Test Case----- ", ExtentColor.GREEN));
		
		 
	}
	@Test(priority=2,description="Different Phases and Status of Problem Ticket",enabled=true)
	public void testDifferentPhasesOfProblemTicket() throws Exception{
		//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Cancel Change Ticket Report");
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
		ServiceNowUtils.navigateToModuleName(driver, "Problem");
 	   prNumber=ProblemReusables.createProblem(driver,1,2);
 	   Thread.sleep(3000);
 	   System.out.println("pass1");
 	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
 	   System.out.println("pass2");
 	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
 	 ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
 	 ProblemReusables.closeProblemTask(driver, prNumber);
 ProblemReusables.closeProblemTicket(driver, prNumber);
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Different Phases and Status of Problem Ticket", ExtentColor.GREEN));
		
}
	@Test(priority=3,description="Multiple state validations",enabled=true)
	public void testMultipleStateValidationsProblemTicket() throws Exception{
		//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Reject Change Ticket Report");
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
		
		 ServiceNowUtils.navigateToModuleName(driver, "Problem");
  	   prNumber=ProblemReusables.createProblem(driver,1,2);
  	   Thread.sleep(3000);
  	  // System.out.println("pass1");
  	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
  	   Select statea = new Select(driver.findElement(By.id("problem.state")));
  	   System.out.println(statea.getFirstSelectedOption().getText());
  	   String state1=statea.getFirstSelectedOption().getText();
  	   if(state1.equalsIgnoreCase("Accepted"))
  	   {
  	      System.out.println("Accepted state");
  	   }
  	   else 
  	   {
  		   System.out.println("invalid");
  	   }
  	  
  	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
  	   Select stateb = new Select(driver.findElement(By.id("problem.state")));
  	   System.out.println(stateb.getFirstSelectedOption().getText());
  	   String state2=stateb.getFirstSelectedOption().getText();
  	   if(state2.equalsIgnoreCase("Work in Progress"))
  	   {
  	      System.out.println("Work in Progress phase");
  	   }
  	   else 
  	   {
  		   System.out.println("invalid");
  	   }
  	 ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
  	 Select statec = new Select(driver.findElement(By.id("problem.state")));
  	 System.out.println(statec.getFirstSelectedOption().getText());
	   String state3=statec.getFirstSelectedOption().getText();
	   if(state3.equalsIgnoreCase("Known Error"))
	   {
	      System.out.println("Known error phase");
	   }
	   else 
	   {
		   System.out.println("invalid");
	   }
  	 ProblemReusables.closeProblemTask(driver, prNumber);
  ProblemReusables.closeProblemTicket(driver, prNumber);
  Select stated = new Select(driver.findElement(By.id("problem.state")));
  System.out.println(stated.getFirstSelectedOption().getText());
	   String state4=stated.getFirstSelectedOption().getText();
	   if(state4.equalsIgnoreCase("Closed"))
	   {
	      System.out.println("Closed phase");
	      driver.close();
	   }
	   else 
	   {
		   System.out.println("invalid");
	   }
  
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Different Phases and Status of Problem Ticket", ExtentColor.GREEN));
				
	}
	@Test(priority=4,description="testknownerror",enabled=true)
	public void testKnownError() throws Exception{
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
		ServiceNowUtils.navigateToModuleName(driver, "Problem");
 	   prNumber=ProblemReusables.createProblem(driver,1,2);
 	   Thread.sleep(3000);
 	  // System.out.println("pass1");
 	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
 	   Select statea = new Select(driver.findElement(By.id("problem.state")));
 	   System.out.println(statea.getFirstSelectedOption().getText());
 	   String state1=statea.getFirstSelectedOption().getText();
 	   if(state1.equalsIgnoreCase("Accepted"))
 	   {
 	      System.out.println("Accepted state");
 	   }
 	   else 
 	   {
 		   System.out.println("invalid");
 	   }
 	  
 	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
 	   Select stateb = new Select(driver.findElement(By.id("problem.state")));
 	   System.out.println(stateb.getFirstSelectedOption().getText());
 	   String state2=stateb.getFirstSelectedOption().getText();
 	   if(state2.equalsIgnoreCase("Work in Progress"))
 	   {
 	      System.out.println("Work in Progress phase");
 	   }
 	   else 
 	   {
 		   System.out.println("invalid");
 	   }
 	 ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
 	 Select statec = new Select(driver.findElement(By.id("problem.state")));
 	 System.out.println(statec.getFirstSelectedOption().getText());
	   String state3=statec.getFirstSelectedOption().getText();
	   if(state3.equalsIgnoreCase("Known Error"))
	   {
	      System.out.println("Known error phase");
	      driver.close();
	   }
	   else 
	   {
		   System.out.println("invalid");
		   
	   }
		
		
		Assert.assertTrue(true);
	    logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is testknownerror", ExtentColor.GREEN));
}
	@Test(priority=5,description="testStateWIPTask",enabled=false)
	public void testStateWIPTask() throws Exception{
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		//Thread.sleep(3000);
		 prNumber=ProblemReusables.createProblem(driver,1,2);
  	   Thread.sleep(3000);
  	  // System.out.println("pass1");
  	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
  	   Select statea = new Select(driver.findElement(By.id("problem.state")));
  	   System.out.println(statea.getFirstSelectedOption().getText());
  	   String state1=statea.getFirstSelectedOption().getText();
  	   if(state1.equalsIgnoreCase("Accepted"))
  	   {
  	      System.out.println("Accepted state");
  	   }
  	   else 
  	   {
  		   System.out.println("invalid");
  	   }
  	  
  	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
  	   
  	   Select stateb = new Select(driver.findElement(By.id("problem.state")));
  	   System.out.println(stateb.getFirstSelectedOption().getText());
  	   String state2=stateb.getFirstSelectedOption().getText();
  	   if(state2.equalsIgnoreCase("Work in Progress"))
  	   {
  	      System.out.println("Work in Progress phase");
  	   }
  	   else 
  	   {
  		   System.out.println("invalid");
  	   }
  	 
	      driver.close();
	     Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("testStateWIPTask ", ExtentColor.GREEN));
	
	
	}
@Test(priority=6,description="testClosePhase",enabled=true)
	
	public void testClosePhase() throws Exception{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "change");
	 prNumber=ProblemReusables.createProblem(driver,1,2);
	   Thread.sleep(3000);
	   System.out.println("pass1");
	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
	   System.out.println("pass2");
	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
	 ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
	 ProblemReusables.closeProblemTask(driver, prNumber);
ProblemReusables.closeProblemTicket(driver, prNumber);
WebElement phase=driver.findElement(By.id("problem.state"));
System.out.println(phase.getText());
if((phase.getText()).equalsIgnoreCase("Closed"))
{
	driver.close();
}
else 
{
 System.out.println("invalid");
}
	// ProblemReusables.verifyProblemCreation(driver, prNumber);          
 
			 
			
			 Assert.assertTrue(true);
				logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is moving the change task to different state and validating the closure of change task ", ExtentColor.GREEN));
			
			
				
}
	
@Test(priority=7,description="Cancel change task",enabled=true)

public void testWOrkFlowOfTask() throws Exception{

	logger = extent.createTest("testWOrkFlowOfTask");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	 prNumber=ProblemReusables.createProblem(driver,1,2);
	   Thread.sleep(3000);
	  // System.out.println("pass1");
	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
	  // System.out.println("pass2");
	  ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
	  System.out.println("work in progress");
	  ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
	 ProblemReusables.closeProblemTask(driver, prNumber);
ProblemReusables.closeProblemTicket(driver, prNumber);
System.out.println("closed");
		driver.close();	
		 Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel(" testWOrkFlowOfTask ", ExtentColor.GREEN));
		
		
			
}

@Test(priority=8,description="-----deffered state-----",enabled=true)
public void testDifferedState() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver, "Problem");
	   prNumber=ProblemReusables.createProblem(driver,1,2);
	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
	   ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
	  //State=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 8);
	  DropDowns.selectDropdownByVisibleText(ProblemPage.getStateDropdown(driver),"Deferred" , "State");
	  DropDowns.selectDropdownByVisibleText(ProblemPage.getStateCodeDropdown(driver),"Cost","State code");
ProblemPage.getSaveBtn(driver).click();
	  driver.close();
	
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is testDifferedState", ExtentColor.GREEN));
}
@Test(priority=9,description="testCancelledCOdeState",enabled=true)
public void testCancelledCOdeState() throws Exception{
	
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	 ServiceNowUtils.navigateToModuleName(driver, "Problem");
	   prNumber=ProblemReusables.createProblem(driver,1,2);
	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
	  
	  //State=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 8);
	 DropDowns.selectDropdownByVisibleText(ProblemPage.getStateDropdown(driver),"Closed" , "State");
	 // DropDowns.selectDropdownByVisibleText(ProblemPage.getStateDropdown(driver),"Cancelled","State code");
ProblemPage.getSaveBtn(driver).click();

//WebElement phase=driver.findElement(By.id("problem.u_state_code"));
Select phase = new Select(driver.findElement(By.id("problem.u_state_code")));
 System.out.println(phase.getFirstSelectedOption().getText());
 String phase1=phase.getFirstSelectedOption().getText();
 if(phase1.equalsIgnoreCase("Cancelled"))
 {
  	driver.close();
 }
 else 
 {
	   System.out.println("invalid");
 }
	
}
@Test(priority=10,description="testDifferedPhase",enabled=true)
public void testDifferedPhase() throws Exception{
	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	ServiceNowUtils.navigateToModuleName(driver, "Problem");
	   prNumber=ProblemReusables.createProblem(driver,1,2);
	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
	   ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
	  //State=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 2, 8);
	  DropDowns.selectDropdownByVisibleText(ProblemPage.getStateDropdown(driver),"Deferred" , "State");
	  DropDowns.selectDropdownByVisibleText(ProblemPage.getStateCodeDropdown(driver),"Cost","State code");
ProblemPage.getSaveBtn(driver).click();
	 // driver.close();
	  
	 WebElement phase=driver.findElement(By.id("problem.state"));
	   System.out.println(phase.getText());
	   if((phase.getText()).equalsIgnoreCase("Differed"))
	   {
	    	driver.close();
	   }
	   else 
	   {
		   System.out.println("invalid");
	   }
	driver.close();
	Assert.assertTrue(true);
	logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed Follow up clicked list view", ExtentColor.GREEN));
	
}
@Test(priority=11,description="testValidateMandatoryChangeTask",enabled=true)

public void testValidateMandatoryFields() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "Problem");
	   Thread.sleep(3000);
	   ProblemPage.getSaveBtn(driver).click();
	   if(driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed())
	   {
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


@Test(priority=12,description="testPStateClosedReject",enabled=true)

public void testPStateClosedReject() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "Problem");
	   prNumber=ProblemReusables.createProblem(driver,1,2);
	 //  ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
	  ProblemReusables.moveProblemTicketToRejectedPhase(driver,prNumber);
	 WebElement phase=driver.findElement(By.id("problem.state"));
   System.out.println(phase.getText());
   if((phase.getText()).equalsIgnoreCase("Closed"))
   {
    	
   
		driver.close();	
		 Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is testPStateClosedReject ", ExtentColor.GREEN));
   }
   else
   {
	   System.out.println("invalid");
   }
		
			
}

@Test(priority=13,description="testNotestab",enabled=true)

public void testNotestab() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	ServiceNowUtils.navigateToModuleName(driver, "Problem");
	   prNumber=ProblemReusables.createProblem2(driver,1,2);
	   
	   if(ProblemPage.getNotesTab(driver).isDisplayed())
	   {
		driver.close();	
		 Assert.assertTrue(true);
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed testNotestab", ExtentColor.GREEN));
	   }
	   else
	   {
		   System.out.println("Invalid");
	   }
		
			
}
@Test(priority=14,description="testActivitytab",enabled=true)

public void testActivitytab() throws Exception{

	logger = extent.createTest("Creation of a CR Ticket");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	WebDriver driver=new FirefoxDriver();
	
    driver.get("http://thomsonreutersqa.service-now.com");
    SafeLogin.logInUser(driver);
	WaitUtils.waitForPageToLoad(driver, 10);
	//Thread.sleep(3000);
	 ServiceNowUtils.navigateToModuleName(driver, "Problem");
	   prNumber=ProblemReusables.createProblem2(driver,1,2);
	   
	   if(ProblemPage.getActivityTab(driver).isDisplayed())
	   {
		driver.close();
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case passed testActivitytab", ExtentColor.GREEN));
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