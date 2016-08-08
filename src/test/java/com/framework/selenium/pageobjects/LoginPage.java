package com.framework.selenium.pageobjects;



import com.gkola.framework.core.PageObject;
import com.gkola.framework.core.UIDriver;
import com.gkola.framework.core.UIElement;
import com.gkola.framework.util.UIType;

public class LoginPage extends BasicPageObject implements PageObject{

	private UIElement userName=createElement(UIType.ID, "");
    
	public LoginPage(UIDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public LoginPage setUserName()
	{
		userName.clearAndSendKeys("username");
		
		return this;
		
		
	}

}
