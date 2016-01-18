package com.framework.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.framework.core.Config;
import com.framework.core.DefaultConfig;
import com.framework.selenium.modules.SearchAction;

public class GoogleSearchTC  {
	 private static WebDriver driver = null;
	 
	 @Test
	 public void search() throws Exception
	 {
		 driver=new FirefoxDriver();
		 Config config = DefaultConfig.getConfig();
		 String strStartpageurl = config.getValue("startpage");
		 driver.get(strStartpageurl);    
	     SearchAction.Execute(driver);
	     driver.close();
		 
	 }
	 

}
