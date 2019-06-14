package testcase;
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

@Listeners(value=SnowReporter.class)
public class problemManagement extends SuperTestNG{
              
       static String prNumber=null;
       static String prTaskNumber=null;
       static String State=null;
       
       @Test(priority=0,description="-----Create Problem Test Case-----",enabled=true, groups="Problems")
       public void testCreateProblemTicket() throws Exception{
    	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName(driver, "Problem");
    	   prNumber=ProblemReusables.createProblem(driver,1,2);
    	  ProblemReusables.verifyProblemCreation(driver, prNumber); 
    	   driver.close();
       } 
       @Test(priority=1,description="-----update Problem Test Case-----",enabled=true)
       public void testUpdateProblemTicket() throws Exception{
    	 ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test CreateProblem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName(driver, "Problem");
    	   prNumber=ProblemReusables.createProblem(driver,1,2);
    	      ProblemReusables.updateProblemTicket(driver, prNumber,2,2);
    	      driver.close();
       } 
       @Test(priority=0,description="-----Different Phases and Status of Problem Ticket-----",enabled=true, groups="Problems")
       public void testDifferentPhasesOfProblemTicket() throws Exception{
    	 ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
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
    	// ProblemReusables.verifyProblemCreation(driver, prNumber);          
       } 
       
       @Test(priority=0,description="-----Different Phases and Status of Problem Ticket-----",enabled=true, groups="Problems")
       public void testMultipleStateValidationsProblemTicket() throws Exception{
    	 ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
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
    
    	//driver.close();
    	// ProblemReusables.verifyProblemCreation(driver, prNumber);          
       } 
       
       
       @Test(priority=0,description="-----testknownerror-----",enabled=true, groups="Problems")
       public void testKnownError() throws Exception{
    	 ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
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
       
       }
       @Test(priority=0,description="-----Different Phases and Status of Problem Ticket-----",enabled=true, groups="Problems")
       public void testStateWIPTask() throws Exception{
    	 ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
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
    	 
    	//driver.close();
    	// ProblemReusables.verifyProblemCreation(driver, prNumber);          
       } 
       
       
       
       @Test(priority=0,description="-----Different Phases and Status of Problem Ticket-----",enabled=true, groups="Problems")
       public void testClosePhase() throws Exception{
    	 ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
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
       }
       
       
       @Test(priority=0,description="-----Different Phases and Status of Problem Ticket-----",enabled=true, groups="Problems")
       public void testWOrkFlowOfTask() throws Exception{
    	 ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName(driver, "Problem");
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
    	       
       }
       @Test(priority=0,description="-----deffered state-----",enabled=true, groups="Problems")
       public void testDifferedState() throws Exception{
     	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "DifferedState", "DifferedState");
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
     	   //ProblemReusables.closeProblemTask(driver, prNumber);
     	  // ProblemReusables.closeProblemTicket(driver, prNumber);
     	 //  driver.close();
     	  // ProblemReusables.verifyProblemCreation(driver, prNumber);          
        } 
       
       
       
       @Test(priority=0,description="-----deffered state-----",enabled=true, groups="Problems")
       public void testCancelledCOdeState() throws Exception{
     	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "DifferedState", "DifferedState");
     	   SafeLogin.logInUser(driver);
     	   WaitUtils.waitForPageToLoad(driver, 10);
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
	   //ProblemReusables.closeProblemTask(driver, prNumber);
	  // ProblemReusables.closeProblemTicket(driver, prNumber);
	 //  driver.close();
	  // ProblemReusables.verifyProblemCreation(driver, prNumber);          
  } 
     	  //driver.close();
     	   //ProblemReusables.closeProblemTask(driver, prNumber);
     	  // ProblemReusables.closeProblemTicket(driver, prNumber);
     	 //  driver.close();
     	  // ProblemReusables.verifyProblemCreation(driver, prNumber);          
        
       
       
       @Test(priority=0,description="-----deffered state-----",enabled=true, groups="Problems")
       public void testDifferedPhase() throws Exception{
     	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "DifferedState", "DifferedState");
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
     	   //ProblemReusables.closeProblemTask(driver, prNumber);
     	  // ProblemReusables.closeProblemTicket(driver, prNumber);
     	 //  driver.close();
     	  // ProblemReusables.verifyProblemCreation(driver, prNumber);          
        } 
       
       
       @Test(priority=0,description="-----Create Problem Test Case-----",enabled=true, groups="Problems")
       public void testValidateMandatoryFields() throws Exception{
    	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName(driver, "Problem");
    	   Thread.sleep(3000);
    	   ProblemPage.getSaveBtn(driver).click();
    	   if(driver.findElement(By.xpath("/html/body/div[2]/form/span[1]/span/div[2]/div/div/span[2]")).isDisplayed())
    	   {
    		   driver.close();
    	   }
    	   else
    	   {
    		   System.err.println("failed");
    	   }
    	  // prNumber=ProblemReusables.createProblem(driver,1,2);
    	 // ProblemReusables.verifyProblemCreation(driver, prNumber); 
    	  // driver.close();
       } 
       
       
       @Test(priority=0,description="-----Create Problem Test Case-----",enabled=true, groups="Problems")
       public void testPStateClosedReject() throws Exception{
    	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName(driver, "Problem");
    	   prNumber=ProblemReusables.createProblem(driver,1,2);
     	 //  ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
     	  ProblemReusables.moveProblemTicketToRejectedPhase(driver,prNumber);
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
     	  
    	   
       } 
       @Test(priority=0,description="-----Create Problem Test Case-----",enabled=true, groups="Problems")
       public void testNotestab() throws Exception{
    	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName(driver, "Problem");
    	   prNumber=ProblemReusables.createProblem2(driver,1,2);
    	   
    	   if(ProblemPage.getNotesTab(driver).isDisplayed())
    	   {
    		   driver.close();
    	   }
    	   else
    	   {
    		   System.err.println("failed");
    	   }
    	  // prNumber=ProblemReusables.createProblem(driver,1,2);
    	 // ProblemReusables.verifyProblemCreation(driver, prNumber); 
    	  // driver.close();
       } 
       @Test(priority=0,description="-----Create Problem Test Case-----",enabled=true, groups="Problems")
       public void testActivitytab() throws Exception{
    	  ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Create Problem Ticket", "Create Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToModuleName(driver, "Problem");
    	   prNumber=ProblemReusables.createProblem2(driver,1,2);
    	   
    	   if(ProblemPage.getActivityTab(driver).isDisplayed())
    	   {
    		   driver.close();
    	   }
    	   else
    	   {
    		   System.err.println("failed");
    	   }
    	  // prNumber=ProblemReusables.createProblem(driver,1,2);
    	 // ProblemReusables.verifyProblemCreation(driver, prNumber); 
    	  // driver.close();
       } 
       
      /* @Test(priority=1,description="-----Update Problem Test Case-----",enabled=false,dependsOnMethods={"testCreateProblemTicket"}, groups="Problems")
       public void testUpdateProblemTicket() throws Exception{
    	   ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Update Problem Ticket", "Update Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Problem");
    	   prNumber=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 2);
    	   ProblemReusables.updateProblemTicket(driver, prNumber,2,2);
    	  }
       
       @Test(priority=2,description="-----Different Phases and Status of Problem Ticket-----",enabled=true, groups="Problems")
       public void testDifferentPhasesOfProblemTicket() throws Exception{
    	   ExtentReport.startReport(Capabilities.getPropertyValue("ProblemReports"), "Test Different Phases and Status of Problem Ticket", "Different Phases and Status of Problem Ticket");
    	   SafeLogin.logInUser(driver);
    	   WaitUtils.waitForPageToLoad(driver, 10);
    	   ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Problem");
    	   prNumber=ExcelUtils.getData("Problem_Management_TestData.xlsx", "Smoke_Suite", 1, 2);    	   
    	   ProblemReusables.moveProblemTicketToAcceptedPhase(driver, prNumber);
    	   ProblemReusables.moveProblemTicketToInProgressPhase(driver, prNumber);
    	   ProblemReusables.moveProblemTicketToKnownErrorPhase(driver, prNumber);
    	   ProblemReusables.closeProblemTask(driver, prNumber);
    	   ProblemReusables.closeProblemTicket(driver, prNumber);
    	   
    	  }
*/
}
