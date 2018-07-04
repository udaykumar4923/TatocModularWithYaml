package util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import action.Locatersmap;

public class WebElementLocators {
	Locatersmap locatersMap;
	WebDriver driver;
	
	public WebElementLocators(WebDriver driver) throws IOException {
		locatersMap = new Locatersmap();
		this.driver = driver;
	}
	
	
	public WebElement getWebElement(String name) {	
		 String typeOfElement = locatersMap.getLocatorType(name);
		 String valueOfElement = locatersMap.getLocatorValue(name);
		 		
		 if(typeOfElement.equals("id") == true) {
			 return driver.findElement(By.id(valueOfElement)); 
		 }	  
		 else if(typeOfElement.equals("linkText") == true) {
			 return driver.findElement(By.linkText(valueOfElement));
		 }
		 else if(typeOfElement.equals("className") == true) {
			 return driver.findElement(By.className(valueOfElement));
		 }
		 else if(typeOfElement.equals("partialLinkText") == true) {
			 return driver.findElement(By.partialLinkText(valueOfElement));
		 }
		 else if(typeOfElement.equals("cssSelector") == true) {
			 return driver.findElement(By.cssSelector(valueOfElement));
		 }
		 else {
			 return driver.findElement(By.xpath(valueOfElement));
		 }
	}
}
