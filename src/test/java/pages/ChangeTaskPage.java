package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangeTaskPage {
	
	private static WebElement elementOfTask=null;

	public static WebElement getChangeTaskCompleteBtn(WebDriver driver){
		elementOfTask = driver.findElement(By.xpath("//button[text()='Complete Implementation']"));
    	return elementOfTask;
    }
    
    public static WebElement getChangeTaskAssignedToEdt(WebDriver driver) {
    	elementOfTask = driver.findElement(By.id("sys_display.change_task.assigned_to"));
		return elementOfTask;
	}
    
    public static WebElement getImplementationResultDropDown(WebDriver driver) {
    	elementOfTask = driver.findElement(By.id("change_task.u_implementation_result"));
		return elementOfTask;
	}
    
    public static WebElement getActualStartEdt(WebDriver driver) {
    	elementOfTask = driver.findElement(By.id("change_task.u_actual_start"));
		return elementOfTask;
	}
    
    public static WebElement getActualEndEdt(WebDriver driver) {
    	elementOfTask = driver.findElement(By.id("change_task.u_actual_end"));
		return elementOfTask;
	}
    
    public static WebElement getChangeTaskCloseBtn(WebDriver driver){
    	elementOfTask = driver.findElement(By.xpath("//button[text()='Close Task']"));
    	return elementOfTask;
    }
    
    public static WebElement getChangeTaskBackBtn(WebDriver driver){
    	elementOfTask = driver.findElement(By.xpath("//button[@class='btn btn-default icon-chevron-left navbar-btn']"));
    	return elementOfTask;
    }
    

}
