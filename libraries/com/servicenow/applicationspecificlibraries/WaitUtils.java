package com.servicenow.applicationspecificlibraries;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicenow.genericlibraries.ReporterLogs;

public class WaitUtils {

	public static void waitForPageToLoad(WebDriver driver, int timeout){
		
		ReporterLogs.log("Wait for page to load", "info");
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			

		
	}
	
	public static void waitForIdPresent(WebDriver driver, String wbId){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(wbId)));	
	}
	
	public static void waitForNamePresent(WebDriver driver, String wbName){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(wbName)));
	}
	
	public static void waitForCSSPresent(WebDriver driver, String wbCss){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(wbCss)));
	}
	
	public static void waitForXpathPresent(WebDriver driver, String wbXpath){
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(wbXpath)));
	}
	
	public static void waitForTitleIs(WebDriver driver, String wbTitle) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleIs(wbTitle));
	}
	
	public static void waitForElementToBeClickable(WebDriver driver, WebElement wbLocator){
        WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(wbLocator));
        }
	
	public static void waitForElementToBeVisible(WebDriver driver, WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        }
	
	public static void waitForTitleContains(WebDriver driver, String wbTitle){
        WebDriverWait wait=new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleContains(wbTitle));
        }


	
}
