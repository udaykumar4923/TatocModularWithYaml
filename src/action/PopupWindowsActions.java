package action;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import util.WebElementLocators;

public class PopupWindowsActions {
	WebDriver driver;
	WebElementLocators popupWindowsElementLocators;
	
	public PopupWindowsActions(WebDriver driver) throws IOException {
		this.driver = driver;
		popupWindowsElementLocators = new WebElementLocators(driver);
	}
	
	
	private WebElement getLaunchWindow() {
		return popupWindowsElementLocators.getWebElement("Popup_windows_button");
	}
	
	
	private String getParentWindow() {
		ArrayList windowHandlers = new ArrayList(driver.getWindowHandles());
		String parentWindow = (String)(windowHandlers.get(0));
		return parentWindow;
	}
	
	
	private String getChildWindow() {
		ArrayList windowHandlers = new ArrayList(driver.getWindowHandles());
		String childWindow = ((String)windowHandlers.get(1));
		return childWindow;
	}
	
	
	public void isLaunchWindowButtonPresent() { 
		System.out.println("finding launch window link");
		assertTrue(getLaunchWindow().isDisplayed());
		System.out.println("launch window link is found");
	}
	
	
	public void  isClickingLaunchOpensNewWinDow() {
		System.out.println("clicking getlaunchwindow link");
		getLaunchWindow().click();
		System.out.println("switching to child window");
		driver.switchTo().window(getChildWindow());
		System.out.println("the child window is opened");
		String ExpectedUrl = "http://10.0.1.86/tatoc/basic/windows/popup";
		assertTrue(ExpectedUrl.contains(driver.getCurrentUrl()));
	}
	
	public void proceedingWithoutOpeningNewWindowTakesToErrorPage() {
		System.out.println("clicking proceed button without opening new window");
		popupWindowsElementLocators.getWebElement("Proceed_button").click();
   		String expectedUrl = "http://10.0.1.86/tatoc/error";
   		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
   		System.out.println("navigated to error page");
   		driver.navigate().back();
   		System.out.println("navigating back from error page");
	}
	
	public void proceedWithoutTypingInTextBoxTakesToErrorPage() {
		popupWindowsElementLocators.getWebElement("Submit_button").click();
		System.out.println("submitting without typing in inputbox");
		driver.switchTo().window(getParentWindow());
		System.out.println("switching to parent window");
		popupWindowsElementLocators.getWebElement("Proceed_button").click();
		System.out.println("clicking proceed");
   		String expectedUrl = "http://10.0.1.86/tatoc/error";
   		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
   		System.out.println("navigated to error page");
   		driver.navigate().back();
   		System.out.println("navigating back from error page");
   		
	}
	
	
	public void submittingAfterTypingInTextBoxTakesToPopupWindowsPage() {
		System.out.println("launching new window");
		getLaunchWindow().click();
		System.out.println("switching to child window");
		driver.switchTo().window(getChildWindow());
		System.out.println("typing in inputbox field");
		popupWindowsElementLocators.getWebElement("Input_field").sendKeys("uday");
		System.out.println("clicking the submit button");
		popupWindowsElementLocators.getWebElement("Submit_button").click();
		System.out.println("switching to parent window");
		driver.switchTo().window(getParentWindow());
   		String expectedUrl = "http://10.0.1.86/tatoc/basic/windows#";
   		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
   		System.out.println("navigated to popwindows page ");
	}
	
	
	public void proceedingCompletingThePopupWindowsTakesToTokenPage() {
		System.out.println("clicking the proceed link");
		popupWindowsElementLocators.getWebElement("Proceed_button").click();
   		String expectedUrl = "http://10.0.1.86/tatoc/basic/cookie";
   		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
   		System.out.println("navigated to token genration page");
	}
	
	
}
