package testcase;

import org.testng.annotations.Test;

import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ExtentReport;

import pages.ChangePage;

public class ChangePractice extends SuperTestNG
{
	static String crNumber=null;
	@Test(priority=0,description="Creation of a CR Ticket",enabled=true)
	public void testCreateChangeRequest()throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("ChangeReports"), "SmokeChangeTest2", "Create Change Ticket Report");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName(driver, "change");
		crNumber = ChangeReusables.createChange(driver,1,2);
		
		ChangeReusables.searchDesiredChangeTicket(driver, crNumber);
		ChangePage.getChangeNumberFromQueue(driver, crNumber).click();	
		
		WaitUtils.waitForPageToLoad(driver, 10);
		

		ChangeReusables.verifyStateOfChangeTicket(driver, "Draft", crNumber,1,2);
		ChangeReusables.FinalReport(driver, "Draft", crNumber, 1, 2);
		driver.close();
		
		
		}
	public void endReport()
	{
		ExtentReport.endReport();
	}
	
	

	
}
