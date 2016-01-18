package com.framework.selenium.modules;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.framework.selenium.pageobjects.BasePageObject;
import com.framework.selenium.pageobjects.BbcPage;
import com.framework.selenium.pageobjects.GoogleSearchPage;

public class SearchAction extends BasePageObject{
	final static Logger logger = Logger.getLogger(SearchAction.class);

	public static void Execute(WebDriver driver) throws InterruptedException
	{
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		GoogleSearchPage.getTextBox(driver).sendKeys("bbc");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if(GoogleSearchPage.Search(driver,".//*[@id='rso']/div[1]/div/div/h3/a"))
		{
			driver.findElement(By.xpath(".//*[@id='rso']/div[1]/div/div/h3/a")).click();
			if(BbcPage.getImage(driver).isDisplayed());		
			logInstruction("image displayed");
			
		}
		else
		{   
			
			logInstruction("element not found");
			
		}
		
	}
}

