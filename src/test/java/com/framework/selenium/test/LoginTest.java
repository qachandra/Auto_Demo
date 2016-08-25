package com.framework.selenium.test;

import org.testng.annotations.Test;

import com.framework.selenium.DataProviders.DataProviders;
import com.framework.selenium.launcher.BaseTestClass;

public class LoginTest  extends BaseTestClass{

	@Test(dataProvider = "Login", dataProviderClass = DataProviders.class)
	public void Login(String userName,String passWord)
	{
		try
		{
			loginPage.login();
			
		}
		catch(RuntimeException e)
		{}
		
		
	}
	
	@Test
	public void LogOut()
	{
		try
		{
			loginPage.logOut();
			
		}
		catch(RuntimeException e)
		{}
		
		
	}
}
