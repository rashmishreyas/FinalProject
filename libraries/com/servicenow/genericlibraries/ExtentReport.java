
package com.servicenow.genericlibraries;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport {
	
	public static ExtentReports report;
	public static ExtentTest logger;
	
	/* Author - Sathyanarayanan V
	 * 
	 * Start of the extent report
	 * 
	 * Parameters: 
	 * @reportPath - Path where report needs to be saved (Eg: .\\Reports\\Incident Management\\)
	 * @reportName - Name of the report that gets saved in the above mentioned path
	 * @reportTitle - Title that gets displayed in the report
	 * eg: ExtentReport.startReport(".\\Reports\\Incident Management\\", "TC01", "TC01_IncidentCreation");
	 */	
	public static void startReport(String reportPath, String reportName, String reportTitle){
	
		report=new ExtentReports(reportPath+reportName+".html", false);
		logger=report.startTest(reportTitle);
		report.flush();

	}
	
	
	/* Author - Sathyanarayanan V
	 * Logging in the report
	 * Parameters:
	 * @logstatus - INFO/PASS/FAIL/SKIP/ERROR   (Eg: LogStatus.INFO)
	 * @loginfo   - Message to be displayed in the report log
	 * eg: ExtentReport.reportLog(LogStatus.INFO, "Safe login Page");
	 */	
	public static void reportLog(LogStatus logstatus, String loginfo){
	
		logger.log(logstatus, loginfo);
		
	}
	
	/* Author - Sathyanarayanan V
	 * Attaching screenshot in the report
	 * Parameters:
	 * @screenshot_path - path of the screenshot that needs to be attached in the report
	 * This returns the complete path of the screenshot with its extension (Eg: C:\Users\Public\Pictures\Sample Pictures\image.jpg)
	 *  Eg: String screenshot=ExtentReport.attachScreenshotInReport(filename);
	 *	ExtentReport.reportLog(LogStatus.INFO, "Safe login Page"+screenshot);
	 */	
	public static String attachScreenshotInReport(String screenshot_path)
	{
		String screenshot=logger.addScreenCapture(screenshot_path);
		return screenshot;
	}
		
	/* Author - Sathyanarayanan V
	 * End of the extent report
	 * Eg: ExtentReport.endReport();
	 */	
	public static void endReport(){
		report.endTest(logger);
		report.flush();
	
	}


	public static void endTest() {
		// TODO Auto-generated method stub
		
	}
	}