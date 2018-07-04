package action;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.WebElementLocators;

public class CookieActions {
	WebDriver driver;
	WebElementLocators tokenPageElementLocators;
	
	public CookieActions(WebDriver driver) throws IOException {
		this.driver = driver;
		tokenPageElementLocators = new WebElementLocators(driver);
	}
	
	private WebElement getGenerateTokenButton() {
		return tokenPageElementLocators.getWebElement("Generate_token_link");
	}
	
	private String getToken() {
		getGenerateTokenButton().click();
		String tokenText = tokenPageElementLocators.getWebElement("Token").getText();
		String tokenValue = tokenText.substring(7, tokenText.length());
		System.out.println("generated token");
		return tokenValue;
	}
	
	private void setCookie() {
		System.out.println("adding cookie");
		Cookie cookie = new Cookie("Token",getToken());
		driver.manage().addCookie(cookie);
	}
	
	public void isgenerateTokenButtonIsPresent() {
		assertTrue(getGenerateTokenButton().isDisplayed());
	}
	
	public void proceedingWithoutAddingCookieTakesToErrorPage() {
		System.out.println("clicking on proceed button");
		tokenPageElementLocators.getWebElement("Proceed_button").click();
   		String expectedUrl = "http://10.0.1.86/tatoc/error";
   		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
   		System.out.println("navigated to error page");
      	driver.navigate().back();
      	System.out.println("navigating back from error page");
	}
	
	public void proceedingAfterAddingCookieTakesToEndPage() {
		setCookie();
		tokenPageElementLocators.getWebElement("Proceed_button").click();
		System.out.println("clicking the proceed button");
   		String expectedUrl = "http://10.0.1.86/tatoc/end";
   		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
   		System.out.println("navigated to end course page");
	}
	

}
