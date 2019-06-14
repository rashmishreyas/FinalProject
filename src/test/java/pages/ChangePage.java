package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.servicenow.genericlibraries.ReporterLogs;

public class ChangePage {

	private static WebElement element=null;
	private static List<WebElement> elements =null;
	
    public static WebElement getNormalLnk(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[contains(text(),'Normal: Planned')]"));
        return element;
    }	
    
    public static WebElement getStandardChangeLnk(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[contains(text(),'Standard: Pre-approved')]"));
    	return element;
    }
    
    public static WebElement getEmerChangeLnk(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[contains(text(),'Emergency: Changes')]"));
    	return element;
    }
    
    public static WebElement getdatabaseLnk(WebDriver driver){
    	element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[1]/table/tbody[3]/tr/td/div/table/tbody/tr/td[2]/table/tbody/tr/td/a/div/strong"));
    	return element;
    }
    public static WebElement getAddSpace(WebDriver driver){
    	element = driver.findElement(By.linkText("Add Space"));
    	return element;
    }
    
    
    public static WebElement getEmergencyChangeLnk(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[contains(text(),'Emergency: Changes')]"));
    	return element;
    }
    
    public static WebElement getConfigurationItemEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_display.change_request.cmdb_ci"));
    	return element;
    }
    
    public static WebElement getAssignmentGrpEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_display.change_request.assignment_group"));
    	return element;
    }
    
    public static WebElement getShortDescriptionEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.short_description"));
    	return element;
    }
    
    public static WebElement getDescriptionEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.description"));
    	return element;
    }
    
    
    public static WebElement getPlanningTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[contains(text(),'Planning') and @class='tab_caption_text']"));
    	return element;
    }
    
    public static WebElement getReasonForChangeEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_reason_for_change"));
    	return element;
    }
    
    public static WebElement getChangeNumberEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_readonly.change_request.number"));
    	return element;
    }
     
    public static WebElement getCustomerImpactDuringChangeEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_customer_impact"));
    	return element;
    } 
    
    public static WebElement getImplementationPlanEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.change_plan"));
    	return element;
    } 
    
    public static WebElement getTestPlanEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.test_plan"));
    	return element;
    } 
    
    public static WebElement getBackoutPlanEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.backout_plan"));
    	return element;
    } 
    
    public static WebElement getExpectedServiceImpactDuringTheExecutionofChangeDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_knowledge_of_impacted_config"));
    	return element;
    }
  
    public static WebElement getScheduleTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[contains(text(),'Schedule')]"));
    	return element;
    }
    
    public static WebElement getRequestedByDateCalenderBtn(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[@id='change_request.requested_by_date.ui_policy_sensitive']/span[@class='icon icon-calendar']"));
    	return element;
    }
    
    public static WebElement getRequestedByMonthLabel(WebDriver driver){
    	element = driver.findElement(By.id("GwtDateTimePicker_month"));
    	return element;
    }
    
    public static WebElement getRequestedByDateEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.requested_by_date"));
    	return element;
    }
    
    
    public static WebElement getSearchDropDown(WebDriver driver){
    	element = driver.findElement(By.xpath("//div[@class='input-group']//select"));
    	return element;
    }
  
    public static WebElement getSearchChangeEdt(WebDriver driver){
    	element = driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
    	return element;
    }
    
    public static WebElement getTaskChangeEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_readonly.change_task.number"));
    	return element;
    }
    public static WebElement getcreatedChangeLnk(WebDriver driver, String changeNumber){
    	element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+changeNumber+"']"));
    	return element;
    }
    
    public static WebElement getSubmitBtn(WebDriver driver){
    	element = driver.findElement(By.id("sysverb_insert"));
    	return element;
    }
    public static WebElement getUpdateBtn(WebDriver driver)
    {
    	element=driver.findElement(By.xpath("//button[text()='Update']"));
    	return element;
    }
    public static WebElement getRequestImplementationBtn(WebDriver driver)
    {
    	element=driver.findElement(By.xpath("//button[text()='Request  Implementation']"));
    	return element;
    }
    
    
    public static WebElement getApproveBtn(WebDriver driver)
    {
    	element=driver.findElement(By.xpath("//button[text()='Approve']"));
    	return element;
    }
    public static WebElement getUpdateCIBtn(WebDriver driver)
    {
    	element=driver.findElement(By.xpath("//button[text()='Update CI']"));
    	return element;
    }
    
    public static WebElement getCancelTaskButton(WebDriver driver)
    {
    	element=driver.findElement(By.id("cancel_task"));
    	return element;
    }
    public static WebElement getFollowUpBtn(WebDriver driver)
    {
    	element=driver.findElement(By.xpath("//button[text()='Follow Up']"));
    	return element;
    }
    public static WebElement getStateField(WebDriver driver)
    {
    	element=driver.findElement(By.id("change_request.state"));
    	return element;
    }
    
    public static WebElement getChangeStatusFromQueue(WebDriver driver, String changeNumber){
    	element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+changeNumber+"']/following::td"));
    	System.out.println(element);
    	return element;
    }
  
    public static WebElement getChangeNumberFromQueue(WebDriver driver, String changeNumber){
    	element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+changeNumber+"']"));
    	return element;
    }

    public static WebElement getSubmitForPlanningBtn(WebDriver driver){
    	element = driver.findElement(By.xpath("//button[text()='Submit for Planning']"));
    	//element = driver.findElement(By.xpath("//*[@id="2dfba240382be1005ac6cf3404629ac8"]"));
    	//element=driver.findElement(By.id("2dfba240382be1005ac6cf3404629ac8"));
    	return element;
    }
    
    public static WebElement getChangeStateEdtDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.state"));
    	System.out.println(element);
    	return element;
    }
    
    public static WebElement getChangeCancelBtn(WebDriver driver){
    	element = driver.findElement(By.id("cancel_change"));
    	return element;
    }
  
    public static WebElement getActivityTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[text()='Activity']"));
    	return element;
    }
    
    public static WebElement getReasonForCancellationEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_reason_for_cancellation"));
    	return element;
    }
    
    public static WebElement getSubmitForAssessmentBtn(WebDriver driver){
    	element = driver.findElement(By.xpath("//button[text()='Submit For Assessment']"));
    	return element;
    }
    
    public static WebElement getEnvironmentInWhichChangeIsToBeExecutedDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_technology_maturity"));
    	return element;
    }
    
    public static WebElement getExpectedServiceImpactDuringExecutionOfTheChangeDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_knowledge_of_impacted_config"));
    	return element;
    }
    
    public static WebElement getPotentialServiceImpactDuringExecutionOfTheChangeDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_service_impact"));
    	return element;
    }
    
    public static WebElement getUserSupportedByTheAssetDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_business_user_impact"));
    	return element;
    }
    
    public static WebElement getBackOutRecoveryComplexityDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_backout___remediation_capabi"));
    	return element;
    }
    
    public static WebElement getFamilarityWithChangeDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_change_conflict"));
    	return element;
    }
    
    public static WebElement getRedundantServiceDropDown(WebDriver driver){
    	element = driver.findElement(By.id("change_request.u_service_availability"));
    	return element;
    }
    
    public static WebElement getRiskAndImpactTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[contains(text(),'Risk') and contains(text(),'Impact')]"));
    	return element;
    }
    
    public static WebElement getPlannedStartDateEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.start_date"));
    	return element;
    }
    
    public static WebElement getPlannedEndDateEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_request.end_date"));
    	return element;
    }
    public static WebElement getPlannedStartEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_task.expected_start"));
    	return element;
    }
    
    public static WebElement getPlannedEndEdt(WebDriver driver){
    	element = driver.findElement(By.id("change_task.due_date"));
    	return element;
    }
    
    
    public static WebElement getRequestImpementationApprovalBtn(WebDriver driver){
    	element = driver.findElement(By.xpath("//button[contains(text(),'Request') and contains(text(),'Implementation') and contains(text(),'Approval')]"));
    	return element;
    }
    public static WebElement getRequestImpementationApprovalBtn1(WebDriver driver){
    	element = driver.findElement(By.xpath("//button[contains(text(),'Request') and contains(text(),'Implementation') and contains(text(),'Reject')]"));
    	return element;
    }
    
    public static WebElement getGroupApprovalTab(WebDriver driver){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	//element=driver.findElement(By.xpath("/html/body/div[2]/div[1]/span[2]/span/span[2]"));
    	element = driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Group') and contains(text(),'Approvals')]"));
    	//js.executeScript("arguments[0].scrollIntoView();", element);
    	//element=driver.findElement(By.className("tabs2_tab tabs2_active"));
    	return element;
    	
    }
    
    public static WebElement getChangeRequestedLnk(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[text()='Requested']"));
    	return element;
    }
    
    public static WebElement getChangeApproveBtn(WebDriver driver){
    	element = driver.findElement(By.id("approve"));
    	return element;
    }
    
    public static WebElement getChangeTaskLnk(WebDriver driver, String changeNumber){
    	element = driver.findElement(By.xpath("//a[text()='"+changeNumber+"']"));
    	return element;
    }
  
    public static WebElement getChangeTaskTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Change') and contains(text(),'Tasks')]"));
    	return element;
    }
        
    public static List<WebElement> getChangeTaskLnkFromChangeQueue(WebDriver driver){
    	elements = driver.findElements(By.xpath("//table[@list_name='change_request.change_task.change_request']//tbody//tr"));
    	return elements;
    }
   public static WebElement getChangeTaskLink(WebDriver driver)
   {
	  element=driver.findElement(By.xpath("//table[@list_name='change_request.change_task.change_request']//tbody//tr"));
	  return element;
   }
    
    public static WebElement getChangeTaskLnk(WebDriver driver, int i){
    	element = driver.findElement(By.xpath("//table[@list_name='change_request.change_task.change_request']//tbody//tr["+i+"]//a[starts-with(text(),'CTASK')]"));
    	return element;
    }
    
    public static WebElement getChangeRequestedByEdt(WebDriver driver){
    	element = driver.findElement(By.id("sys_display.change_request.requested_by"));
    	return element;
    }
  
    public static List<WebElement> getPendingApproversFromGroupApprovalsQueue(WebDriver driver){
    	elements = driver.findElements(By.xpath("//table[@list_name='change_request.sysapproval_group.parent']//tbody//tr"));
    	return elements;
    }
    
    public static WebElement getChangeRequestedLnk(WebDriver driver, int k){
    	element = driver.findElement(By.xpath("//table[@list_name='change_request.sysapproval_group.parent']//tbody//tr["+k+"]//a[starts-with(text(),'Requested')]"));
    	return element;
    }
    public static WebElement getGroupApprovalsTab(WebDriver driver){
    	element = driver.findElement(By.xpath("//span[contains(text(),'Group Approvals')]"));
    	return element;
    }
    public static WebElement getRequestedLink(WebDriver driver)
    {
    	element=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/span/div[2]/div[4]/table/tbody/tr/td/div/table/tbody/tr/td[3]/a"));
    	return element;
    }
    public static WebElement geChangeTasksTab(WebDriver driver){
    	//JavascriptExecutor js = (JavascriptExecutor) driver;
    	//element=driver.findElement(By.xpath("/html/body/div[2]/div[1]/span[2]/span/span[2]"));
    	element = driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Change') and contains(text(),'Tasks')]"));
    	//js.executeScript("arguments[0].scrollIntoView();", element);
    	//element=driver.findElement(By.className("tabs2_tab tabs2_active"));
    	return element;
    	
    }
    public static WebElement getExpectedStartBtn(WebDriver driver)
    {
    	element=driver.findElement(By.id("change_task.expected_start"));
    	return element;
    }
    
    public static WebElement getExpectedEndBtn(WebDriver driver)
    {
    	element=driver.findElement(By.id("change_task.due_date"));
    	return element;
    }
    public static WebElement getActualStartBtn(WebDriver driver)
    {
    	element=driver.findElement(By.id("change_task.u_actual_start"));
    	return element;
    }
    public static WebElement getActualEndBtn(WebDriver driver)
    {
    	element=driver.findElement(By.id("change_task.u_actual_end"));
    	return element;
    }
  public static WebElement getCompleteImplementationBtn(WebDriver driver)
  {
	  element=driver.findElement(By.xpath("//button[text()='Complete Implementation']"));
	  return element;
  }
  public static WebElement getImplementationResult(WebDriver driver)
  {
	  element=driver.findElement(By.id("change_task.u_implementation_result"));
	  return element;
  }
  public static WebElement getImplementationSubcode(WebDriver driver)
  {
	  element=driver.findElement(By.id("change_task.u_closure_subcode"));
	  return element;
  }
  public static WebElement getSaveBtn(WebDriver driver){
  	element = driver.findElement(By.xpath("//button[text()='Save']"));
  	
  	return element;
  }
  
  public static WebElement getCloseTaskBtn(WebDriver driver){
	  	element = driver.findElement(By.xpath("//button[text()='Close Task']"));
	  	
	  	return element;
	  }
  public static WebElement getCreateTaskBtn(WebDriver driver){
	  	element = driver.findElement(By.xpath("//button[text()='New']"));
	  	
	  	return element;
	  }
  public static WebElement getTaskAssignmentGroup(WebDriver driver)
  {
	  element=driver.findElement(By.id("sys_display.change_task.assignment_group"));
	  return element;
  }
  public static WebElement getTaskSubmitBtn(WebDriver driver){
	  	element = driver.findElement(By.xpath("//button[text()='Submit']"));
	  	
	  	return element;
	  } 
  public static WebElement getReasonForCancel(WebDriver driver)
  {
	  element=driver.findElement(By.id("change_task.u_reason_for_cancel"));
	  return element;
  }

  
  public static WebElement getRejectBtn(WebDriver driver){
	  	element = driver.findElement(By.xpath("//button[text()='Reject']"));
	  	
	  	return element;
	  }
  public static WebElement getReasonForReject(WebDriver driver)
  {
	  element=driver.findElement(By.id("sysapproval_group.u_reason_for_rejection"));
	  return element;
  }
	
  
    
    
    
    
}
