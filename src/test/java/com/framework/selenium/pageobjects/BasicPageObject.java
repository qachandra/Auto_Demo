package com.framework.selenium.pageobjects;




import org.apache.log4j.Logger;
import org.testng.annotations.*;

import com.gkola.framework.core.BasicObject;
import com.gkola.framework.core.PageObject;
import com.gkola.framework.core.UIDriver;
import com.gkola.framework.locators.ElementLocator;
import com.gkola.framework.util.UIType;







public class BasicPageObject extends BasicObject implements PageObject{
	
	final static Logger logger = Logger.getLogger(BasicPageObject.class);
	private String pageUrl = "";
   
    
   
    protected BasicPageObject(UIDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
    
   
	
	
	  @AfterMethod
	    public void quitBrowser() throws Exception {
		  
	        
	    }
	  @BeforeMethod
	    public void openBrowser() throws Exception {
	  }
	
	  
	@Override
	public void setUrl(String url) {
		pageUrl = url;
		
	}
	@Override
	public String getUrl() {
		return pageUrl;
	}
	@Override
	public void start() {
		 if (uiDriver != null && !"".equals(pageUrl)) {
	            uiDriver.get(pageUrl);
	        } else
			try {
				throw new Exception("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }




	@Override
	public ElementLocator getLocator(UIType uiType, String value) {
		// TODO Auto-generated method stub
		return null;
	}
		
	


	
	
	  }


