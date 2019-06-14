package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private static WebElement element;
	
	public static WebElement gettrLogo(WebDriver driver) {
        element = driver.findElement(By.xpath("//input[@id='home_title"));
        return element;
    }  
    
    public static WebElement getUserNameDropDown(WebDriver driver){
       element = driver.findElement(By.xpath("//button[@id='user_info_dropdown']"));
       return element;
    }
    
    public static WebElement getfilterEdt(WebDriver driver){
       element = driver.findElement(By.id("filter"));
       return element;
    }
    
    public static WebElement getCreateNewBtn(WebDriver driver){
    //   element = driver.findElement(By.xpath("/html/body/div/div/div/nav/div/div[3]/div/div/concourse-application-tree/ul/li[12]/ul/li[1]/div/ul/li/a/div[1]/div"));
    	element=driver.findElement(By.id("323bb07bc611227a018aea9eb8f3b35e"));
    	//element=driver.findElement(By.xpath("//a[text()='Create New']"));
    	
       return element;
    }
    //new code for incident
    
    public static WebElement getCreateNewBtn1(WebDriver driver){
element = driver.findElement(By.xpath("/html/body/div/div/div/nav/div/div[3]/div/div/concourse-application-tree/ul/li[90]/ul/li[1]/div/ul/li/div/a/div/div"));
    	/*element=driver.findElement(By.className("sn-widget-list-title ng-binding"));*/
    	//element=driver.findElement(By.xpath("//a[text()='Create New']"));
    	//element=driver.findElement(By.linkText("Create New"));
        return element;
     }
    //new code for problem
    public static WebElement getCreateNewBtn2(WebDriver driver){
       // element = driver.findElement(By.xpath("/html/body/div/div/div/nav/div/div[3]/div/div/magellan-favorites-list/div/div[2]/div/div[2]/div[1]/a/div/div[2]/span"));
    	//element=driver.findElement(By.xpath("/html/body/div/div/div/nav/div/div[3]/div/div/concourse-application-tree/ul/li[13]/ul/li[1]/div/ul/li/a/div[1]/div"));
    	//element=driver.findElement(By.xpath("//a[text()='Create New']"));
    	//element=driver.findElement(By.xpath("//a[normalize-space(.)='Create New']"));
    	element=driver.findElement(By.linkText("Create New"));
        return element;
     }

  
    public static WebElement getAssignedToMyGroupsLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Assigned to my groups']"));
       return element;
    }
    
    public static WebElement getOpenedByMyGroupsLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Opened by my groups']"));
       return element;
    }
    
    public static WebElement getAssignedToMeLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Assigned to me']"));
       return element;
    }
    
    public static WebElement getOpenedByMeLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Opened by me']"));
       return element;
    }
    
    public static WebElement getAllLnk(WebDriver driver){
        //element = driver.findElement(By.xpath("//a[text()='All']"));
    	element = driver.findElement(By.xpath("/html/body/div/div/div/nav/div/div[3]/div/div/concourse-application-tree/ul/li[12]/ul/li[4]/div/ul/li/a/div[1]/div"));
        return element;
     }
    public static WebElement getAllLnk1(WebDriver driver){
       // element = driver.findElement(By.xpath("//a[text()='All']"));
    	element = driver.findElement(By.xpath("/html/body/div/div/div/nav/div/div[3]/div/div/concourse-application-tree/ul/li[14]/ul/li[4]/div/ul/li/a/div[1]/div"));
        return element;
     }
    public static WebElement getAllLnk2(WebDriver driver){
        // element = driver.findElement(By.xpath("//a[text()='All']"));
     	element = driver.findElement(By.xpath("/html/body/div/div/div/nav/div/div[3]/div/div/concourse-application-tree/ul/li[13]/ul/li[9]/div/ul/li/a/div[1]/div"));
         return element;
      }
     
    
    
    public static WebElement getOverviewLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Overview']"));
       return element;
    }
    
    public static WebElement getIncidentTasksAssignedToMyGroupsLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Incident Tasks']//following-sibling::ul//a[text()='Assigned to my groups']"));
       return element;
    }
    
    public static WebElement getIncidentTasksAssignedToMeLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Incident Tasks']//following-sibling::ul//a[text()='Assigned to me']"));
       return element;
    }
    
    public static WebElement getIncidentTasksAllLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Incident Tasks']//following-sibling::ul//a[text()='All']"));
       return element;
    }
    
    public static WebElement getOpenLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Open']"));
       return element;
    }
    
    public static WebElement getClosedLnk(WebDriver driver){
       element = driver.findElement(By.xpath("//a[text()='Closed']"));
       return element;
    }    
    
    public static WebElement getLogoutBtn(WebDriver driver){
       element = driver.findElement(By.xpath("//button[@id='user_info_dropdown']/following::a[text()='Logout']"));
       return element;
    }
  
    public static WebElement getLoggedInUserInfo(WebDriver driver){
    	element = driver.findElement(By.xpath("//button[@id='user_info_dropdown']//span[2]"));
    	return element;
    }
   
    public static WebElement getChangesAssignedToMeQueue(WebDriver driver){
    	element = driver.findElement(By.xpath("//a[contains(text(),'Changes Assigned')]"));
    	return element;
    }

}