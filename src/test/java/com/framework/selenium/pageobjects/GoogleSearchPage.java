package com.framework.selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GoogleSearchPage extends BasePageObject{
	private static WebElement element = null;
	private static WebDriver driver=null;
	private static WebElement searchItem=null;
	//private UIElement textbox=new UIElement(UiType,".//*[@id='sb_ifc0");
	//private static UIElement Googletextbox=new UIElement("textbox",".//*[@id='sb_ifc0']");
	//private static UIElement GoogleSearchButton=new UIElement("textbox","//*[@id=\"sblsbb\"]");

	
	public static WebElement getTextBox(WebDriver driver)
	{
		
	    element=driver.findElement(By.xpath(".//*[@id='sb_ifc0']"));
		//element=driver.findElement(By.xpath(Googletextbox.getXpath()));
		
	    return element;
		
	}
	
	public static WebElement getSearchButton(WebDriver driver)
	{
		element=driver.findElement(By.xpath("//*[@id=\"sblsbb\"]"));
		//element=driver.findElement(By.xpath(GoogleSearchButton.getXpath()));
		return element;
		
	}
	
	
	public static boolean Search(WebDriver driver,String xpath)
	{
		boolean searchItem=driver.findElement(By.xpath(xpath)).isDisplayed();
		return searchItem;
	}
	
	

}
