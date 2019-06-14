package com.servicenow.applicationspecificlibraries;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import pages.ChangePage;
import pages.HomePage;
import pages.IncidentPage;
import pages.ProblemPage;

import com.relevantcodes.extentreports.LogStatus;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ExtentReport;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.TextBoxes;

public class ServiceNowUtils {

	public static void navigateToModuleName(WebDriver driver, String moduleName) throws InterruptedException {
		
		WaitUtils.waitForIdPresent(driver, "filter");
		TextBoxes.enterTextValue(HomePage.getfilterEdt(driver), moduleName, "Filter Edit box for searching : "+moduleName);
		//WaitUtils.waitForXpathPresent(driver,"//a[text()='Create New']");
		Thread.sleep(3000);
		WaitUtils.waitForPageToLoad(driver, 10);
		HomePage.getCreateNewBtn2(driver).click();

		Frames.switchToFrameById("gsft_main", driver);	
		
	}
	
	//new code for incident
	public static void navigateToModuleName1(WebDriver driver, String moduleName) throws InterruptedException {
		WaitUtils.waitForIdPresent(driver, "filter");
		WaitUtils.waitForPageToLoad(driver, 10);
		TextBoxes.enterTextValue(HomePage.getfilterEdt(driver), moduleName, "Filter Edit box for searching : "+moduleName);
		//WaitUtils.waitForXpathPresent(driver,"/html/body/div/div/div/nav/div/div[3]/div/div/concourse-application-tree/ul/li[11]/ul/li[1]/div/ul/li[1]/a/div[1]/div");
		WaitUtils.waitForPageToLoad(driver, 10);
		HomePage.getCreateNewBtn1(driver).click();
		Frames.switchToFrameById("gsft_main", driver);	
	}
	//new code for problem
	public static void navigateToModuleName2(WebDriver driver, String moduleName) throws InterruptedException {
		WaitUtils.waitForIdPresent(driver, "filter");
		TextBoxes.enterTextValue(HomePage.getfilterEdt(driver), moduleName, "Filter Edit box for searching : "+moduleName);
		//WaitUtils.waitForXpathPresent(driver,"//a[text()='Create New']");
		WaitUtils.waitForPageToLoad(driver, 30);
		HomePage.getCreateNewBtn(driver).click();
		Frames.switchToFrameById("gsft_main", driver);	
	}
	
	
	
	public static void navigateToAllQueueForDesiredModule(WebDriver driver, String moduleName){
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForIdPresent(driver, "filter");
		HomePage.getfilterEdt(driver).sendKeys(moduleName);
		WaitUtils.waitForPageToLoad(driver, 10);
		HomePage.getAllLnk1(driver).click();
		Frames.switchToFrameById("gsft_main", driver);
		WaitUtils.waitForPageToLoad(driver, 10);
	}
	public static void navigateToAllQueueForDesiredModule1(WebDriver driver, String moduleName){
		WaitUtils.waitForPageToLoad(driver, 10);
		WaitUtils.waitForIdPresent(driver, "filter");
		HomePage.getfilterEdt(driver).sendKeys(moduleName);
		WaitUtils.waitForPageToLoad(driver, 10);
		HomePage.getAllLnk2(driver).click();
		Frames.switchToFrameById("gsft_main", driver);
		WaitUtils.waitForPageToLoad(driver, 10);
	}
	
}