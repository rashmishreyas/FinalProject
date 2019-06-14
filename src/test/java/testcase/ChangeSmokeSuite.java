package testcase;



import java.io.IOException;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExtentReport;
import pages.ChangePage;


public class ChangeSmokeSuite {
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
	public void testCreateChnageRequest() throws IOException, InterruptedException{
		
		logger = extent.createTest("Creation of a CR Ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
	    driver.get("http://thomsonreutersqa.service-now.com");
	    SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
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
		logger = extent.createTest("Change Request approval");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
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
		logger = extent.createTest("Cancel Change ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
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
		Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Cancel Change ticket", ExtentColor.GREEN));
		
}
	@Test(priority=3,description="Reject CR ticket",enabled=true)
	public void testChangeTicketReject() throws IOException, InterruptedException{
		//ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest1", "Reject Change Ticket Report");
		logger = extent.createTest("Reject CR ticket");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
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
	@Test(priority=4,description="Moving the Change to Multiple phases",enabled=true)
	public void testMovingtoMultiplePhases() throws Exception{
		logger = extent.createTest("Moving the Change to Multiple phases");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
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
		Assert.assertTrue(true);
	    logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Moving the Change to Multiple phases ", ExtentColor.GREEN));
}
	@Test(priority=5,description="Creating and updating  of a Change Task",enabled=true)
	public void testChangeCreateTask() throws Exception{
		logger = extent.createTest("Creating and updating  of a Change Task");
		String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",FirefoxDriver);
		WebDriver driver=new FirefoxDriver();
		driver.get("http://thomsonreutersqa.service-now.com");
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
	     Assert.assertTrue(true);
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is Creating and updating  of a Change Task ", ExtentColor.GREEN));
	
	
	}
@Test(priority=6,description="moving the change task to different state and validating the closure of change task",enabled=true)
	
	public void testTaskMovingtoMultiplePhases() throws Exception{
	logger = extent.createTest("moving the change task to different state and validating the closure of change task");
	String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
	System.setProperty("webdriver.gecko.driver",FirefoxDriver);
	 WebDriver driver=new FirefoxDriver();
	 driver.get("http://thomsonreutersqa.service-now.com");
	
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
			 Assert.assertTrue(true);
				logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is moving the change task to different state and validating the closure of change task ", ExtentColor.GREEN));
			
			
				
}
	
	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			//logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
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
	                InternetAddress.parse("rp00506732@TechMahindra.com"));
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