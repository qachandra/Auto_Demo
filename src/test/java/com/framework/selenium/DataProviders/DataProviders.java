package com.framework.selenium.DataProviders;

import org.testng.annotations.DataProvider;

import com.framework.selenium.launcher.BaseTestClass;

public class DataProviders extends BaseTestClass{

	 @DataProvider(name = "Login")
	    public static Object[][] Login() {
	        return new Object[][]{{config.getValue("userName"),
	        	config.getValue("passWord")
	        }};
	    }

}
