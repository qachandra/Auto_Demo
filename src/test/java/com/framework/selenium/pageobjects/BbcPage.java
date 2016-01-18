package com.framework.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framework.core.UIElement;

public class BbcPage extends BasePageObject{
	

	private static WebElement element;
	private static UIElement image=new UIElement("textbox",".//*[@id='orb-header']/div[1]/div[1]/a/img");
	public static WebElement getImage(WebDriver driver)
	{
		
	    element=driver.findElement(By.xpath(image.getXpath()));
	    return element;
		
	}

}
