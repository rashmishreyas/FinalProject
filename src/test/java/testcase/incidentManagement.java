package testcase;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.applicationspecificlibraries.ChangeReusables;
import com.servicenow.applicationspecificlibraries.Frames;
import com.servicenow.applicationspecificlibraries.IncidentReusables;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.ServiceNowUtils;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;

import pages.HomePage;
import pages.IncidentPage;

@Listeners(value=SnowReporter.class)
public class incidentManagement extends SuperTestNG {

	static String incNumber=null;
	@Test(priority=0,description="Create standalone incident ticket", groups="Incidents",enabled=true)
	public void testCreateStandAloneIncidentTicket() throws Exception{
		
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Standalone Incident Ticket","Create Standalone Incident Ticket");
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 10);			
		ServiceNowUtils.navigateToModuleName1(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 1, 2);
		System.out.println("pass");
		IncidentReusables.verifyIncidentCreation(driver, incNumber);
	}
	

	@Test(priority=1,description="Create managed incident ticket",groups="Incidents",enabled=true)
	public void testCreateManagedIncidentTicket() throws Exception{
		
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"),"Test Create Managed Incident Ticket","Create Managed Incident");		
		SafeLogin.logInUser(driver);			
		WaitUtils.waitForPageToLoad(driver, 30);			
		ServiceNowUtils.navigateToModuleName1(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, true,2,2);
		IncidentReusables.verifyManagedIncidentCreation(driver, incNumber);
	}
	
	
	/*@Test(priority=2,description="Progression of Incident ticket to Resolved state",enabled=true,groups="Incidents",dependsOnMethods={"testCreateStandAloneIncidentTicket"})
	public void testResolveIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Resolve Incident Ticket", "Progress Incident ticket to Resolved state");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToAllQueueForDesiredModule(driver, "Incident");	
		IncidentReusables.resolveIncident(driver);	
	}
	*/
	/*@Test(priority=2,description="resolve incident",groups="incidents",enabled=true)
	public void testResolveIncidentTicket()throws Exception
	
	{
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver,10);
		//ServiceNowUtils.navigateToAllQueueForDesiredModule(driver,"Incident");
		ServiceNowUtils.navigateToModuleName1(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 4, 2);
		
	}*/
	@Test(priority=2,description="resolving Incident Ticket",groups="Incidents", enabled=true)
	public void testResolveIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Cancel Incident Ticket", "Cancelling the Incident Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName1(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 4, 2);
		IncidentReusables.resolve1Incident(driver, incNumber);
	
		//IncidentReusables.resolveIncident(driver);	
	}
	
	
	@Test(priority=3,description="Cancelling Incident Ticket",groups="Incidents", enabled=true)
	public void testCancelIncidentTicket() throws Exception{
		ExtentReport.startReport(Capabilities.getPropertyValue("IncidentReports"), "Test Cancel Incident Ticket", "Cancelling the Incident Ticket");
		SafeLogin.logInUser(driver);
		WaitUtils.waitForPageToLoad(driver, 10);
		ServiceNowUtils.navigateToModuleName1(driver,"incident");
		incNumber=IncidentReusables.createIncident(driver, false, 4, 2);
		IncidentReusables.cancelIncident(driver, incNumber);		
	}
}
