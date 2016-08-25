package com.framework.selenium.pageobjects;

import com.gkola.framework.core.PageObject;
import com.gkola.framework.core.UIDriver;
import com.gkola.framework.core.UIElement;
import com.gkola.framework.util.UIType;

public class LoginPage extends BasicPageObject implements PageObject{

	private UIElement userName=createElement(UIType.ID, "0_uid_field");
	private UIElement passWord=createElement(UIType.ID, "0_pwd_field");
	private UIElement loginButton=createElement(UIType.ID,"0_loginButton");
	private UIElement logOutButton=createElement(UIType.ID,"logout_menu");
	public LoginPage(UIDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public LoginPage setUserName()
	{
		if(userName.isDisplayed())
		userName.clearAndSendKeys("//Chandra.Liyanage");
		
		return this;
		
		
	}
	
	public LoginPage setPassword()
	{
		if(passWord.isDisplayed())
		passWord.clearAndSendKeys("$Chan1170");
		
		return this;
		
		
	}
	
	public LoginPage login()
	
	{
		
		setUserName();
		setPassword();
		if(loginButton.isDisplayed())
			loginButton.click();
		this.sleep(config_element_timeout);
		return this;
		
		
	}
	
public LoginPage logOut()
	
	{
		
		
		if(logOutButton.isDisplayed())
			logOutButton.click();
		
		return this;
		
		
	}

}
