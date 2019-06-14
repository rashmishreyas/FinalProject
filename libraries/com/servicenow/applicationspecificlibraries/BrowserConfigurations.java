package com.servicenow.applicationspecificlibraries;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicenow.genericlibraries.Capabilities;
import com.servicenow.genericlibraries.ReporterLogs;

/*
 * Designed by Sam
 * Objective : Browser setup and invoke before Test Script Execution
 */
public class BrowserConfigurations {

	public static WebDriver driver;

	public static synchronized WebDriver getDriverInstance(){
        return driver;
    }
	
	/*
	 * Code for selection of Browser Type
	 */
	public static void setup() throws Throwable{
		String browserType = Capabilities.getPropertyValue("Browser");
		ReporterLogs.log("Browser Set Up:::","info");
		ReporterLogs.log("WebDriver Instance inside the setup():::" + browserType,"info");
		if(browserType.equalsIgnoreCase("Chrome")){
			ReporterLogs.log("Inside Chrome Class Set Up method","info");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
            options.addArguments("chrome.switches", "--disable-extensions");
            options.addArguments("--disable-extensions");
            options.addArguments("start-maximized");
            options.addArguments("--enable-popup-blocking");
            options.addArguments("--disable-user-media-security=true");
            java.util.HashMap<String, Object> chromePrefs = new java.util.HashMap<String, Object>();
            HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            //Turns off download prompt
            chromePrefs.put("download.prompt_for_download", false);
            chromeOptionsMap.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--test-type");
            DesiredCapabilities chromecapabiities = DesiredCapabilities.chrome();
            chromecapabiities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
            chromecapabiities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            chromecapabiities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromecapabiities.setCapability(ChromeOptions.CAPABILITY, options);
            String ChromeDrivers = Capabilities.getPropertyValue("IEDrivers");
            System.setProperty("webdriver.chrome.driver", ChromeDrivers);
            driver = new ChromeDriver(chromecapabiities);
		}else if(browserType.equalsIgnoreCase("IE")){
			ReporterLogs.log("Inside IE Class Set Up method","info");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setBrowserName("iexplorer");
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        String IEDrivers = Capabilities.getPropertyValue("IEDrivers");
			System.setProperty("webdriver.ie.driver", IEDrivers);
			driver = new InternetExplorerDriver(ieCapabilities);
		}else{
//			DesiredCapabilities ffCapabilities = DesiredCapabilities.firefox();
//			ffCapabilities.setBrowserName("firefox");
//			ffCapabilities.setCapability("ignoreZoomSetting", true);
//			ffCapabilities.setCapability(FirefoxProfile.ALLOWED_HOSTS_PREFERENCE, true);
//			ffCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, true);
//			ffCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ReporterLogs.log("Inside Chrome Class Set Up method","info");
			String FirefoxDriver = Capabilities.getPropertyValue("FirefoxDriver");
			System.setProperty("webdriver.gecko.driver", FirefoxDriver);
			driver = new InternetExplorerDriver();
		}
		ReporterLogs.log("Window Maximised", "info");
		driver.manage().window().maximize();
		driver.get(Capabilities.getPropertyValue("URL"));
		ReporterLogs.log("Navigate to the mentioned URL", "info");
	}
	   public static void ClosingAll() throws Throwable {
		   driver.manage().deleteAllCookies();
		   driver.quit();
	    
	    }

	   public class MyChromeDriver extends ChromeDriver{
	        public WebDriverWait getWait() {
	            return wait;
	        }

	        private WebDriverWait wait;
	        public MyChromeDriver()
	        {
	            super();
	            this.manage().window().maximize();
	            wait = new WebDriverWait(this,500);
	        }
	    }
}