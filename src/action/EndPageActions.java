package action;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import util.WebElementLocators;

public class EndPageActions {
	WebDriver driver;
	WebElementLocators endPageElementLocators;
	
	public EndPageActions(WebDriver driver) throws IOException {
		this.driver = driver;
		endPageElementLocators = new WebElementLocators(driver);
	}
	
	private String getMessage() {
		System.out.println("getting message");
		String endMsg = endPageElementLocators.getWebElement("Message").getText();
		System.out.println("extracted message");
		return endMsg;
	}
	
	public void isLastPageLink() {
		String expectedUrl = "http://10.0.1.86/tatoc/end";
		assertTrue(expectedUrl.contains(driver.getCurrentUrl()) , "Not the End Page Link");
		System.out.println("checked that it is the last link");
	}
	
	public void isCompletingMessagePresent() {
		String expectedMessage = "Obstacle Course is Complete!";
		System.out.println("verifying that message is correct");
		assertTrue(expectedMessage.contains(getMessage()) , "Completing Message Is Not Present");
		System.out.println("verified end message");
	}
	
}
