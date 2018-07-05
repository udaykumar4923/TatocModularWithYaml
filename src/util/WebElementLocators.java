package util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import action.YamlReader;

public class WebElementLocators {
	YamlReader yamlReader;
	WebDriver driver;
	
	public WebElementLocators(WebDriver driver) throws IOException {
		yamlReader = new YamlReader();
		this.driver = driver;
	}
	
	
	public WebElement getWebElement(String name) {	
		 String typeOfElement = yamlReader.getLocatorType(name);
		 String valueOfElement = yamlReader.getLocatorValue(name);
		 		
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
