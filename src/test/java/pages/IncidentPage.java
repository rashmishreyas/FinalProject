package pages;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IncidentPage {
	

	public static WebElement element;
	
	public static WebElement getCreateNewIncidentlnk(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//a[text()='Incident Management']/following::a[text()='Create New']"));
		return element;
	}
	
	public static WebElement getAllIncidentslnk(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//a[text()='Incident Management']/following::a[text()='All']"));
		return element;
	}
	
	public static String getIncidentNumber(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.number']"));
		String incidentNumber=element.getAttribute("value");
		return incidentNumber;
	}
	
	public static WebElement getStateDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.state']"));
		return element;
	}
	
	public static WebElement getAssignmentGroupEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.assignment_group']"));
		return element;
	}
	
	public static WebElement getAssignmentGroupReadOnly(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.assignment_group_label"));
		return element;
	}
	
	public static WebElement getAssignedToEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.assigned_to']"));
		return element;
	}
	
	public static WebElement getOpenedByGroupEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_opened_by_group']"));
		return element;
	}
	
	public static WebElement getRequestedByEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_requested_by']"));
		return element;
	}
	
	public static WebElement getRequestedForEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_requested_for']"));
		return element;
	}
	
	public static WebElement getBusinessServiceEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_business_service']"));
		return element;
	}
	
	public static WebElement getBusinessServiceReadOnly(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_business_service_label"));
		return element;
	}
	
	public static WebElement getImpactDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.impact']"));
		return element;
	}
	
	public static WebElement getUrgencyDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		return element;
	}
	
	public static WebElement getPriorityValue(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.priority']"));
		return element;
	}
	
	public static WebElement getUserImpactDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//select[@id='incident.u_user_impact']"));
		return element;
	}
	
	public static WebElement getIncidentModelEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.u_template']"));
		return element;
	}
	
	public static WebElement getConfigurationItemEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_display.incident.cmdb_ci']"));
		return element;
	}
	
	public static String getCIEnvironment(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.u_ci_environment']"));
		String ciEnvironment=element.getAttribute("value");
		return ciEnvironment;
	}
	
	
	public static String getOpenedAt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_readonly.incident.opened_at']"));
		String openedAt=element.getAttribute("value");
		return openedAt;
	}
	
	public static String getStartTime(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.u_incident_start_time']"));
		String startTime=element.getAttribute("value");
		return startTime;
	}
	
	public static WebElement getIncidentManagerRequiredChkbox(WebDriver driver) throws Exception
	{
		
		//WebDriverWait wait2 = new WebDriverWait(driver, 10);
		//wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("xpath_of_element_to_be_invisible")));
		//driver.findElement(By.xpath("xpath_element_to_be_clicked")).click();
		//WebDriverWait wait2 = new WebDriverWait(driver, 30);
		//wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id='label.ni.incident.u_incident_manager_required']")));
		//wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//label[@id='label.ni.incident.u_incident_manager_required']")));
		System.out.println("start");
		//element=driver.findElement(By.xpath("//label[@id='label.ni.incident.u_incident_manager_required']"));
		//element=driver.findElement(By.className(" checkbox-label"));
		//element=driver.findElement(By.cssSelector("input[id$='label.ni.incident.u_incident_manager_required']"));
		element=driver.findElement(By.id("label.ni.incident.u_incident_manager_required"));
		element.click();
		System.out.println("end");
	
//wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.id("label.ni.incident.u_incident_manager_required")));
//element=driver.findElement(By.id("label.ni.incident.u_incident_manager_required"));
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return element;
	}
	
	public static WebElement getReasonForIncidentManagerDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_reason_for_incident_manager"));
		return element;
	}
	
	public static WebElement getKnowledgeChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='sys_original.incident.knowledge']"));
		return element;
	}
	
	public static WebElement getMitigationTime(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.u_mitigation_time']"));
		return element;
	}
	
	public static WebElement getResolvedTime(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.u_mitigation_time']"));
		return element;
	}
	
	public static WebElement getShortDescriptionEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//input[@id='incident.short_description']"));
		return element;
	}
	
	public static WebElement getShortDescriptionReadOnly(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.short_description"));
		return element;
	}
	
	public static WebElement getDescriptionEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//textarea[@id='incident.description']"));
		return element;
	}
	
	public static WebElement getNotesTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Notes']"));
		return element;
	}
	
	public static WebElement getWorkNotesEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.work_notes"));
		return element;
	}
	
	public static WebElement getAdditionalCommentsEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.comments"));
		return element;
	}
	
	public static WebElement getRelatedRecordsTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[contains(text(),'Related') and contains(text(),'Record')]"));
		return element;
	}
	
	public static WebElement getParentIncidentEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.parent_incident"));
		return element;
	}
	
	public static WebElement getAlertNumberEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.u_alert_number"));
		return element;
	}
	
	public static WebElement getProblemEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.problem_id"));
		return element;
	}
	
	public static WebElement getKbArticleEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.u_kb_article"));
		return element;
	}
	
	public static WebElement getResolvingChangeEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.rfc"));
		return element;
	}
	
	public static WebElement getCausedByChangeEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_display.incident.caused_by"));
		return element;
	}
	
	public static WebElement getActivityTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Activity']"));
		return element;
	}
	
	public static WebElement getUpdatedByEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.sys_updated_by"));
		return element;
	}
	
	public static WebElement getUpdatedEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.sys_updated_on"));
		return element;
	}
	
	public static WebElement getResolvedByEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.resolved_by_label"));
		return element;
	}
	
	public static WebElement getGovernanceTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Governance']"));
		return element;
	}
	
	public static WebElement getLateralAssignmentChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_lateral_assignment"));
		return element;
	}
	
	public static WebElement getCreatedByTier1Chkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_created_by_tier_1"));
		return element;
	}	
	
	public static WebElement getFirstLineResolvedChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_first_line_resolved"));
		return element;
	}

	public static WebElement getFirstCallResolutionChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_first_call_resolution"));
		return element;
	}
	
	public static WebElement getReopenedChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_reopened"));
		return element;
	}
	
	public static WebElement getEscalationChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_escalation"));
		return element;
	}
	
	public static WebElement getProtocolNotFollowedChkbox(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("label.ni.incident.u_protocol_not_followed"));
		return element;
	}
	
	public static WebElement getReasonForCancellationEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_reason_for_cancellation"));
		return element;
	}
	
	public static WebElement getReassignmentCountEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.reassignment_count"));
		return element;
	}
	
	public static WebElement getUpdatesEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.sys_mod_count"));
		return element;
	}
	public static WebElement getReopenCountEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.reopen_count"));
		return element;
	}
	public static WebElement getIncidentStateCountEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_incident_state_count"));
		return element;
	}
	public static WebElement getRelatedCallsCountEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_related_calls_count"));
		return element;
	}
	public static WebElement getSetToP1Edt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_set_to_p1"));
		return element;
	}
	
	
	
	public static WebElement getExternalReferenceTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[contains(text(),'External') and contains(text(),'Reference')]"));
		return element;
	}
	
	public static WebElement getTicketSourceEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_ticket_source"));
		return element;
	}
	
	public static WebElement getTicketComponentEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_ticket_component"));
		return element;
	}
	
	public static WebElement getEventManagementIdEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_event_management_id"));
		return element;
	}
	
	public static WebElement getFingerprintEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_fingerprint"));
		return element;
	}
	
	public static WebElement getATTTicketIdEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_at_t_ticket_id"));
		return element;
	}
	
	public static WebElement getServiceCloudIdEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_service_cloud_id"));
		return element;
	}
	
	public static WebElement getExternalCustomerAlertIdEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("sys_readonly.incident.u_customer_zone_alert_id"));
		return element;
	}
	
	public static WebElement getNumberOfCustomerCallsEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_number_of_customer_calls"));
		return element;
	}
	
	public static WebElement getEstimatedResolutionTimeEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_estimated_resolution_time"));
		return element;
	}
	
	public static WebElement getClosureTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Closure']"));
		return element;
	}
	
	public static WebElement getCauseCodeDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_cause_code"));
		return element;
	}
	
	public static WebElement getSubCauseCodeDropdown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.u_sub_cause_code"));
		return element;
	}
	
	public static WebElement getMitigationAndSolutionStepsEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.id("incident.close_notes"));
		return element;
	}
	
	public static WebElement getSaveBtn(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//button[text()='Save']"));
		return element;
	}
	
	public static WebElement getUpdateBtn(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//button[@id='sysverb_update']"));
		return element;
	}	
	
	public static WebElement getAttachedKnowledgeTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[contains(text(),'Attached') and contains(text(),'Knowledge')]"));
		return element;
	}
	
	public static WebElement getBackBtn(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Back']"));
		return element;
	}
	
	public static WebElement getAdditionalActionsBtn(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//span[text()='Additional actions']"));
		return element;
	}
	
	public static WebElement getSearchDropDown(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//div[@class='input-group']//select"));
		return element;
	}
	
	public static WebElement getSearchIncidentEdt(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
		return element;
	}
	
	public static WebElement getIncidentStatusfromQueue(WebDriver driver, String incidentNumber) throws Exception
	{
		//element=driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+incidentNumber+"']/following::td"));
		element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+incidentNumber+"']/following::td"));
		//element=driver.findElement(By.xpath("/html/body/div[1]/div[1]/span/div/div[5]/table/tbody/tr/td/div/table/tbody/tr[1]/td[5]"));
		System.out.println(element);
		return element;
	}
	
	public static WebElement getIncidentNumberfromQueue(WebDriver driver, String incidentNumber) throws Exception
	{
		element=driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+incidentNumber+"']"));
		return element;
	}
	
	public static WebElement getIncidentManagerRequiredValueFromNotesTab(WebDriver driver) throws Exception
	{
		element=driver.findElement(By.xpath("//ul[@class='h-card-wrapper activities-form']//span[text()='Incident Manager Required']//following-sibling::span//span"));
		return element;
	}
	
	public static WebElement getIncidentSearchTicketEdt(WebDriver driver){
    	element = driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
    	return element;
    }
    
    }
