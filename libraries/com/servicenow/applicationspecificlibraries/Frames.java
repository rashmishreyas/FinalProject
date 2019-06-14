package com.servicenow.applicationspecificlibraries;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.servicenow.genericlibraries.ReporterLogs;

public class Frames {

	/**This method is used to switched to the recently opened Tab Automatically
	 * Designer : Samujjal Das Choudhury
	 * @param driver
	 * @throws InterruptedException
	 * This method is used to switched to the recently opened Tab Automatically
	 */

	//Switching to frame using Index
	public static void switchToFrameByIndex(int index, WebDriver driver){
		
			try{
					ReporterLogs.log("Switching to frame by index : "+ index, "info");
					driver.switchTo().frame(index);
			}
			catch (NoSuchFrameException e){
				ReporterLogs.log("Unable to switch to frame with index " + index, "error");
				ReporterLogs.log("Error encountered "+ e.getCause(),"error");
			}
			catch (Exception e)
			{
				ReporterLogs.log("Unable to locate frame with index " + index, "error");
				ReporterLogs.log("Error encountered "+ e.getCause(),"error");
			}
		}

	/*
	 * Switching to frame with Name
	 */
	public static void switchToFrameByName(String fname, WebDriver driver)
		{
			try{
				ReporterLogs.log("Switching to the frame with Name : "+ fname, "info");
				driver.switchTo().frame(fname);
			}
			catch (NoSuchFrameException e){
				ReporterLogs.log("Unable to locate a Frame with Name: " + fname, "error");
				ReporterLogs.log("Error encountered "+ e.getCause(),"error");
			}
			catch (Exception e){
				ReporterLogs.log("Unable to locate frame with index " + fname, "error");
				ReporterLogs.log("Error encountered "+ e.getCause(),"error");
			}
		}
	

	/*
	 * Switching to frame with ID
	 */
	
	public static void switchToFrameById(String id, WebDriver driver)
	{
		try{
			ReporterLogs.log("Switching to the frame with ID : "+ id, "info");
			driver.switchTo().frame(id);
		}
		catch (NoSuchFrameException e){
			ReporterLogs.log("Unable to locate a Frame with ID : " + id, "error");
			ReporterLogs.log("Error encountered "+ e.getCause(),"error");
		}
		catch (Exception e){
			ReporterLogs.log("Unable to locate frame with ID " + id, "error");
			ReporterLogs.log("Error encountered "+ e.getCause(),"error");
		}
	}
		
		/*
		 * Switching out of a frame
         */
          public static void switchToDefaultContent(WebDriver driver){
            
        	  try{
                     ReporterLogs.log("Switching out of the frame", "info");
                     driver.switchTo().defaultContent();
        	  }catch (NoSuchFrameException e){
                     ReporterLogs.log("Error encountered "+ e.getCause(),"error");
        	  }catch (Exception e){
                     ReporterLogs.log("Error encountered "+ e.getCause(),"error");
              }
   }	

}