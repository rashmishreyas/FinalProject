package testcase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.ChangePage;
import pages.HomePage;

import com.servicenow.applicationspecificlibraries.BrowserConfigurations;
import com.servicenow.applicationspecificlibraries.Frames;
import com.servicenow.applicationspecificlibraries.SnowReporter;
import com.servicenow.applicationspecificlibraries.SafeLogin;
import com.servicenow.applicationspecificlibraries.WaitUtils;
import com.servicenow.applicationspecificlibraries.SuperTestNG;
import com.servicenow.genericlibraries.DropDowns;
import com.servicenow.genericlibraries.ReporterLogs;
import com.servicenow.genericlibraries.ExcelUtils;
import com.servicenow.genericlibraries.ScreenShot;
import com.servicenow.genericlibraries.TextBoxes;

@Listeners(value=SnowReporter.class)
public class loginTest extends SuperTestNG
{		
		
		/*
		* SC001_Verify user login/ logout functionality in Service Now
		* TestValidLogInOut() covers TC001_Verify Login Functionality by providing valid login credentials
		* Verifies the Valid Login Functionality with all possible valid conditions
		*/
		@Test(priority=1,dataProvider="ValidCredentials")
		public void TestValidSignIn(String usrId,String pwd)throws IOException, InterruptedException{
			SafeLogin.logInSnow(driver, usrId, pwd);
			ReporterLogs.log("Title ="+ driver.getTitle(),"info");
			try {
				System.out.println( ExcelUtils.getData("LogInTestData.xlsx","login_credentials", 1, 4));
				String expectedOperatorName =  ExcelUtils.getData("LogInTestData.xlsx","login_credentials", 1, 4);
				String actualOperatorName = HomePage.getUserNameDropDown(driver).getText();
				Assert.assertEquals(actualOperatorName, expectedOperatorName);
				ReporterLogs.log("Logged in succesfully with user name " + usrId, "info");
				HomePage.getLogoutBtn(driver).click();
			}catch(Exception e) {
				ReporterLogs.log("Logged in unsuccesful with user name " + usrId, "error");
				ReporterLogs.log("Unable to login due to exception :"+e.getCause(),"error");
			}
			
		}
	
		@DataProvider(name="ValidCredentials")
		public Object[][] getValidLoginCredentials() throws Exception{
			Object[][] arrayObject = ExcelUtils.getExcelObjects("LogInTestData.xlsx","login_credentials",1,1,2,3);
			return arrayObject;
		}
}