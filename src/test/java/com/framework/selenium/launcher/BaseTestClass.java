package com.framework.selenium.launcher;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;




import com.framework.selenium.pageobjects.LoginPage;
import com.gkola.framework.core.Config;
import com.gkola.framework.core.DefaultConfig;

public class BaseTestClass extends BaseTestObject{

	public LoginPage loginPage =new LoginPage(uiDriver);
	public static String CONFIGPATH_QA = "/config/configQA.cfg";
	public static String CONFIGPATH_PROD = "/config/configPROD.cfg";
	public static String qaURL="";
	public static String prodURL="https://chandra-liyanage-demo.credit360.com/csr/site/login.acds?";	
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
@Optional("prod") String env_type,
@Optional("CHROME") String browser )
{
	/*IF It wants to run tests individually it can run by uncomment below variables and and changing the parameters here not using maven commands*/
	//env_type="qa";
	//browser="FIREFOX";
setConfigFileAndStartPages(env_type,browser);

}
        
}
