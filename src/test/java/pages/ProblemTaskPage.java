package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProblemTaskPage {

	private static WebElement element;
	private static WebElement elementOfTask=null;
	
    public static WebElement getAssignmentGrpEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem_task.assignment_group"));
        return element;
    }
    
    public static WebElement getConfigurationItemEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem_task.cmdb_ci"));
        return element;
    }
    
    public static WebElement getAssignedToEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem_task.assigned_to"));
        return element;
    }
    
    public static WebElement getPriorityDropdown(WebDriver driver) {
        element = driver.findElement(By.id("problem_task.priority"));
        return element;
    }

    public static WebElement getProblemEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_display.problem_task.problem"));
        return element;
    }
    
    public static WebElement getShortDescriptionEdt(WebDriver driver) {
        element = driver.findElement(By.id("problem_task.short_description"));
        return element;
    }
    
    public static WebElement getDescriptionEdt(WebDriver driver) {
        element = driver.findElement(By.id("problem_task.description"));
        return element;
    }
    
    public static WebElement getWorkNotesEdt(WebDriver driver) {
        element = driver.findElement(By.id("activity-stream-work_notes-textarea"));
        return element;
    }  
    
    public static WebElement getProblemTaskNumberEdt(WebDriver driver) {
        element = driver.findElement(By.id("sys_readonly.problem_task.number"));
        return element;
    }  
    public static WebElement getProblemTasksTab(WebDriver driver) throws Exception {
        element=driver.findElement(By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Problem') and contains(text(),'Tasks')]"));
        return element;
       }
    public static WebElement getProblemTasksCreateBtn(WebDriver driver){
         element = driver.findElement(By.id("sysverb_new"));
         return element;
        }
    public static WebElement getProblemTasksSateDropdown(WebDriver driver){
        element = driver.findElement(By.id("problem_task.state"));
        return element;
       }
    public static WebElement getUpdateBtn(WebDriver driver) {
        element = driver.findElement(By.id("sysverb_update"));
        return element;
       }
    
    public static WebElement getProblemTaskBackBtn(WebDriver driver){
        elementOfTask = driver.findElement(By.xpath("//button[@class='btn btn-default icon-chevron-left navbar-btn']"));
        return elementOfTask;
       }
       
    public static WebElement getSaveBtn(WebDriver driver) {
           element = driver.findElement(By.id("sysverb_update_and_stay"));
           return element;
       }
    
    public static WebElement getErrorValidationTxt(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[contains(text(),'Match not found, reset to original')]"));
        return element;
       }
    
}
    
    
    
    