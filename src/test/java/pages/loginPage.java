package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.servicenow.applicationspecificlibraries.WaitUtils;


public class loginPage {
	
	private static WebElement element;
	
    public static WebElement getsafeUserIDEdt(WebDriver driver) {
        element = driver.findElement(By.id("USER"));
        return element;
    }
    
    public static WebElement getsafePasswordEdt(WebDriver driver) {
        element = driver.findElement(By.id("PASSWORD"));
        return element;
    }
	
    public static WebElement getsafeLoginbtn(WebDriver driver) {
        element = driver.findElement(By.id("safeLoginbtn"));
        return element;
    }
}
