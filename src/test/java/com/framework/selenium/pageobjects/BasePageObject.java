package com.framework.selenium.pageobjects;




import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.framework.core.Browser;
import com.framework.selenium.modules.SearchAction;
import com.framework.selenium.util.LoadDataProperties;





public class BasePageObject {
	final static Logger logger = Logger.getLogger(BasePageObject.class);
    private static Browser browser;
   
    
   
	
    
   
	public static Browser getBrowser() {
		return browser;
	}
	public static void setBrowser(Browser browser) {
		BasePageObject.browser = browser;
	}
	
	private static void setBrowser() {
		String browserName = System.getProperty("browser.NAME");
		if (browserName.equalsIgnoreCase("FIREFOX")) {
			BasePageObject.browser = Browser.FIREFOX;
		} else if (browserName.equalsIgnoreCase("CHROME")) {
			BasePageObject.browser = Browser.CHROME;
		} else if (browserName.equalsIgnoreCase("INTERNETEXPLORER")) {
			BasePageObject.browser = Browser.INTERNETEXPLORER;
		} else {
			// Falling back to default browser, for any other browser
			BasePageObject.browser = Browser.FIREFOX;
		}
	}
	
	  @AfterMethod
	    public void quitBrowser() throws Exception {
		  
	        
	    }
	  @BeforeMethod
	    public void openBrowser() throws Exception {
	  }
	
	  public static void logInstruction(String instruction) {
	        Reporter.log("&bull; " + instruction + "<br/>");
	        logger.info(instruction);
	    }
	
	
	  }


