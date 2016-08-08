package com.framework.selenium.launcher;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.gkola.framework.core.Browser;
import com.gkola.framework.core.Config;
import com.gkola.framework.core.DefaultConfig;
import com.framework.selenium.pageobjects.BbcPage;
import com.framework.selenium.pageobjects.GoogleSearchPage;
import com.framework.selenium.pageobjects.LoginPage;

public class BaseTestClass extends BaseTestObject{
	//private static WebDriver driver=null;
	//public BbcPage bbcPage=new BbcPage(driver);
	//public GoogleSearchPage googleSearchPage= new GoogleSearchPage(driver);
	public LoginPage loginPage =new LoginPage(uiDriver);
	public static String CONFIGPATH_QA = "/config/configQA.cfg";
	public static String CONFIGPATH_PROD = "/config/configPROD.cfg";
	public static String qaURL="";
	public static String prodURL="";	
	public static Config config; 
	
	
	
	
	
	
	public void setConfigFileAndStartPages(String type,String browser) {
        switch (type.toLowerCase()) {
        
        case "qa" :
        	 config = new DefaultConfig(CONFIGPATH_QA);
        	 //environment_type = qaURL;
             loginPage = (LoginPage) getStartPages(qaURL,  config.getValue("start_class"),browser);
        	 break;
        	 
        case "prod" :
       	 	config = new DefaultConfig(CONFIGPATH_PROD);
       	 	loginPage = (LoginPage) getStartPages(prodURL,  config.getValue("start_class"),browser);
       }
	}
	
	
@BeforeClass(alwaysRun = true, groups = {"BVT",
            "Regression"})
           
@Parameters({"envType","browser"})
public void beforeClass(
@Optional("qa") String env_type,
@Optional("CHROME") String browser )
{

setConfigFileAndStartPages(env_type,browser);

}
        
}
