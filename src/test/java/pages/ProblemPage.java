package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProblemPage {


     private static WebElement element;
     private static List<WebElement> elements=null;
                
     public static WebElement getAssignmentGrpEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem.assignment_group"));
        return element;
       }
     public static WebElement getSourceDropdown(WebDriver driver) {
        element = driver.findElement(By.id("problem.contact_type"));
        return element;
       }
     public static WebElement getImpactDropdown(WebDriver driver) {
        element = driver.findElement(By.id("problem.impact"));
        return element;
       }
     public static WebElement getStateDropdown(WebDriver driver) {
         element = driver.findElement(By.id("problem.state"));
         return element;
        }
     public static WebElement getStateCodeDropdown(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_state_code"));
         return element;
        }
     
     
     public static WebElement getComplexityDropdown(WebDriver driver) {
        element = driver.findElement(By.id("problem.urgency"));
        return element;
       }
     public static WebElement getConfigurationitemEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem.cmdb_ci"));
        return element;
       }
     public static WebElement getShortdescriptionEdt(WebDriver driver) {
        element = driver.findElement(By.id("problem.short_description"));
        return element;
       }
     public static WebElement getDescriptionEdt(WebDriver driver) {
        element = driver.findElement(By.id("problem.description"));
        return element;
       }
     public static WebElement getSubmitBtn(WebDriver driver) {
        element = driver.findElement(By.id("sysverb_insert"));
        return element;
       }
     public static WebElement getSaveBtn(WebDriver driver) {
    	 element=driver.findElement(By.xpath("//button[text()='Save']"));
     	return element;
        }
     public static WebElement getRejectBtn(WebDriver driver) {
    	 element=driver.findElement(By.xpath("//button[text()='Reject']"));
     	return element;
        }
     public static WebElement getUpdateBtn(WebDriver driver) {
         element = driver.findElement(By.id("sysverb_update"));
         return element;
        }
     public static WebElement getComments(WebDriver driver)
     {
    	 element=driver.findElement(By.id("activity-stream-textarea"));
    	 return element;
     }
     
     public static WebElement getMajorProblemChkbox(WebDriver driver) {
    	element = driver.findElement(By.id("label.ni.problem.u_major_problem"));
    	return element;
       }
     public static WebElement getSearchDropDown(WebDriver driver) throws Exception {
    	 element=driver.findElement(By.xpath("//div[@class='input-group']//select"));
    	 return element;
       }
     public static WebElement getSearchProblemEdt(WebDriver driver) throws Exception {
    	 element=driver.findElement(By.xpath("//div[@class='input-group']/label[text()='Search']/following-sibling::input"));
    	 return element;
       }
     public static WebElement getProblemStatusfromQueue(WebDriver driver, String problemId) throws Exception{
 		element=driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+problemId+"']/following::td[2]"));
 		return element;
 	   }
     public static WebElement getProblemPriorityfromQueue(WebDriver driver, String problemId) throws Exception{
  		element=driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+problemId+"']/following::td[1]"));
  		return element;
  	   }
     public static WebElement getProblemTicketfromQueue(WebDriver driver, String problemId) throws Exception{
  		element=driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+problemId+"']"));
  		return element;
  	   }
     public static WebElement getProblemTaskFromTaskTable(WebDriver driver, String problemTaskId) throws Exception{
   		element=driver.findElement(By.xpath("//table[@id='problem.problem_task.problem_table']//a[text()='"+problemTaskId+"']"));
   		return element;
   	   }
      public static WebElement getServiceForumTab(WebDriver driver) throws Exception {
    	 element=driver.findElement(By.xpath("//span[contains(text(),'Service') and contains(text(),'Forum')]"));
    	 return element;
       }
     public static WebElement getDivisionDropDown(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_division"));
    	 return element;
       }
     public static WebElement getForumDropDown(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_forum"));
    	 return element;
       }
     public static WebElement getSeverityDropDown(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_severity"));
    	 return element;
       }
     public static WebElement getSFUpdateEdt(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_sf_update"));
    	 return element;
       }
       public static WebElement getImpactAndAnalysisTab(WebDriver driver) throws Exception {
    	 element=driver.findElement(By.xpath("//span[contains(text(),'Impact') and contains(text(),'Analysis')]"));
    	 return element;
       }
       public static WebElement getNumberOfCustomerCallsEdt(WebDriver driver) {
    	 element = driver.findElement(By.id("problem.u_number_of_customer_calls"));
    	 return element;
       }
       public static WebElement getIncidentStartTimeEdt(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_incident_start_time"));
         return element;
       }
       public static WebElement getIncidentDetectedTimeEdt(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_incident_detected_time"));
         return element;
       }
       public static WebElement getIncidentMitigationTimeEdt(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_incident_mitigation_time"));
         return element;
       }
       public static WebElement getIncidentResolvedTimeEdt(WebDriver driver) {
         element = driver.findElement(By.id("problem.u_incident_resolved_time"));
         return element;
       }
       public static WebElement getImpactedServicesTab(WebDriver driver) throws Exception {
         element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Impacted') and contains(text(),'Services')]"));
         return element;
       }
       public static WebElement getProblemTasksTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Problem') and contains(text(),'Tasks')]"));
    	   return element;
       }
       public static WebElement getIncidentsTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Incidents')]"));
    	   return element;
       }
       public static WebElement getChildProblemsTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Child') and contains(text(),'Problems')]"));
    	   return element;
       }
       public static WebElement getKnowledgeTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Knowledge')]"));
    	   return element;
       }
       public static WebElement getApproversTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Approvers')]"));
    	   return element;
       }
       public static WebElement getMetricsTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Metrics')]"));
    	   return element;
       }
       public static WebElement getServiceForumsTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Service') and contains(text(),'Forums')]"));
    	   return element;
       }
       
       //Problem Approvers is pending
       
       public static WebElement getProblemTaskNewBtn(WebDriver driver) throws Exception {
    	   element = driver.findElement(By.id("sysverb_new"));
    	   return element;
       }
               
       //In Progress
       
       public static WebElement getRootCauseAnalysisCompleteChkbox(WebDriver driver) {
    	   element = driver.findElement(By.id("label.ni.problem.u_root_cause_analysis_complete"));
    	   return element;
       }
       public static WebElement getWorkaroundEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_work_around_str"));
    	   return element;
       }
       public static WebElement getTriggerCategoryDropDown(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_trigger_category"));
    	   return element;
       }
       public static WebElement getTriggerDetailsEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_trigger_details"));
    	   return element;
       }
       public static WebElement getRootCauseCategoryDropDown(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_root_cause_category"));
    	   return element;
       }
       public static WebElement getRootCauseSubCategoryDropDown(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_root_cause_subcategory"));
    	   return element;
       }
       public static WebElement getRootCauseDetailsEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_root_cause"));
    	   return element;
       }
       public static WebElement getRootCauseCIEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("sys_display.problem.u_root_cause_ci"));
    	   return element;
       }
       
       public static WebElement getAvoidanceFailureCategoryDropDown(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_avoidance_failure_category"));
    	   return element;
       }
       public static WebElement getAvoidanceFailureDetailsEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_avoidance_failure_details"));
    	   return element;
       }
       public static WebElement getResolutionEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.u_resolution"));
    	   return element;
       }
       
        //Known Error
       public static WebElement getNotesTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//span[contains(text(),'Notes') and contains(text(),'Notes')]"));
    	   return element;
       }
       
       public static WebElement getActivityTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//span[contains(text(),'Activity') and contains(text(),'Activity')]"));
    	   return element;
       }
       public static WebElement getStakeholderListUnlockBtn(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.watch_list_unlock"));
    	   return element;
       }
       public static WebElement getStakeholderListEdt(WebDriver driver) {
    	   element = driver.findElement(By.id("sys_display.problem.watch_list"));
    	   return element;
       }
       public static WebElement getStakeholderListLockBtn(WebDriver driver) {
    	   element = driver.findElement(By.id("problem.watch_list_lock"));
    	   return element;
       }
       public static WebElement getProblemNumberFromQueue(WebDriver driver, String problemNumber){
       	element = driver.findElement(By.xpath("//tbody[@class='list2_body']//a[text()='"+problemNumber+"']"));
       	return element;
       }
       public static WebElement getProblemApproversTab(WebDriver driver){
       	element = driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Approvers')]"));
       	return element;
       }
       public static WebElement getProblemPendingApproverLnk(WebDriver driver,String usrName){
          	element = driver.findElement(By.xpath("//table[@id='problem.sysapproval_approver.sysapproval_table']//a[text()='"+usrName+"']//parent::td//preceding-sibling::td//a[text()='Requested']"));
          	return element;
          }
       public static WebElement getProblemApproveBtn(WebDriver driver){
       	element = driver.findElement(By.id("approve"));
       	return element;
       }
       public static WebElement getProblemStateEdtDropDown(WebDriver driver){
       	element = driver.findElement(By.id("problem.state"));
       	return element;
       } 
       public static WebElement getInvestigationDiagnosisTab(WebDriver driver) throws Exception {
    	   element=driver.findElement(By.xpath("//span[contains(text(),'Investigation') and contains(text(),'Diagnosis')]"));
    	   return element;
       }
       
       //Problem Task Closure
       public static WebElement getProblemTaskLnk(WebDriver driver){
    	   element = driver.findElement(By.xpath("//table[@id='problem.problem_task.problem_table']//a[starts-with(text(),'PTASK')]"));
    	   return element;
       }
       
       //Problem Closure
       public static WebElement getProblemStateDropdown(WebDriver driver){
           element = driver.findElement(By.id("problem.state"));
           return element;
          }
       
       
       
       


       public static List<WebElement> getNumberOfTasksFromProblemTaskTab(WebDriver driver){
    	   elements = driver.findElements(By.xpath("//table[@id='problem.problem_task.problem_table']//tbody//tr"));
    	   return elements;
       	}


       public static WebElement getMultipleProblemTaskLnk(WebDriver driver, int k){
    	   element = driver.findElement(By.xpath("//table[@id='problem.problem_task.problem_table']//tbody//tr["+k+"]//a[starts-with(text(),'PTASK')]"));
    	   return element;
       }
       public static WebElement getDueDate(WebDriver driver) {
    	   element = driver.findElement(By.id("problem_task.due_date"));
    	   return element;
       }
       public static WebElement getActuallDueDate(WebDriver driver) {
    	   element = driver.findElement(By.id("problem_task.u_actual_due_date"));
    	   return element;
       }
       public static WebElement getMenu(WebDriver driver)
       {
    	 //button[text()='Approve']
       	element=driver.findElement(By.xpath("/html/body/div[1]/span/span/nav/div/div[1]/button[2]"));
       	return element;
       }
       public static WebElement getChangeLink(WebDriver driver)
       {
    	   element=driver.findElement(By.xpath("/html/body/div[8]/div[5]"));
    	   return element;
       }
       
     
}
