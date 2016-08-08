package com.framework.selenium.launcher;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.gkola.framework.core.BasicTestObject;
import com.gkola.framework.core.PageObject;
import com.gkola.framework.core.UIDriver;

public class BaseTestObject extends BasicTestObject {
	
	 private static Logger logger = Logger.getLogger(BaseTestObject.class);
     public UIDriver uiDriver=null;
	    public BaseTestObject() {
	        super();
	    }
	 public PageObject getStartPages(
	            String pageurl,
	            String pageclass,
	            String browser
	           ) 
	         {
	      if (pageclass == null && "".equals(pageclass)) {
	            logger.error(new Exception(""));
	            return null;
	        }
	    
	        // print page URL to instruction log file
	        logger.debug("Go to URL: " + pageurl);
	        if (uiDriver == null)
	            uiDriver = new DefaultUIDrivers(browser);
	        uiDriver.get(pageurl);
	      
	        try {
	            @SuppressWarnings("unchecked")
	            Class<PageObject> clazz = (Class<PageObject>) Class.forName(pageclass);
	            PageObject pageObject = null;
				try {
					pageObject = clazz.getDeclaredConstructor(WebDriver.class).newInstance(uiDriver);
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            pageObject.setUrl(pageurl);
	            return pageObject;
	        } catch (ClassNotFoundException e) {
	           
	            logger.error(e);
	           
	        } catch (InstantiationException e) {
	            logger.error("InstantiationException", e);
	        } catch (IllegalAccessException e) {
	            logger.error("IllegalAccessException", e);
	        } catch (IllegalArgumentException e) {
	            logger.error("IllegalArgumentException", e);
	        } catch (SecurityException e) {
	            logger.error("SecurityException", e);
	        }
	        return null;
	    }
}
