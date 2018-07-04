package action;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import util.WebElementLocators;

public class GridBoxActions {
	WebDriver driver;
	WebElementLocators locateGridBoxElements;
	
	public GridBoxActions(WebDriver driver) throws IOException {
		this.driver = driver;
		 locateGridBoxElements = new WebElementLocators(driver);
	}
	
	
    public void clickGridBox(String colorOfBox) {
    	if(colorOfBox == "greenbox") {
    		System.out.println("finding green gridbox to click");
    		assertTrue(locateGridBoxElements.getWebElement("Green_gridbox").isDisplayed(),"green grid box is not found");
    		System.out.println("green gridbox is found");
    		locateGridBoxElements.getWebElement("Green_gridbox").click();
    		System.out.println("cliked on green gridbox");
    	}
    	else {
    		System.out.println("finding red gridbox to click");
    		assertTrue(locateGridBoxElements.getWebElement("Red_gridbox").isDisplayed(),"red grid box is not found");
    		System.out.println("red gridbox is found");
    		locateGridBoxElements.getWebElement("Red_gridbox").click();
    		System.out.println("cliked on red gridbox");
    	}
    }
    
  public void isclickingRedBoxTakesToErrorPage() {
		clickGridBox("redbox");
		String expectedUrl = "http://10.0.1.86/tatoc/error";
		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
		System.out.println("navigated to error page");
		driver.navigate().back();
		System.out.println("navigating back from error page");
	}

  public void isclickingGreenBoxTakesToFrameAndDungeonPage() {
		clickGridBox("greenbox");
		String expectedUrl = "http://10.0.1.86/tatoc/basic/frame/dungeon";
		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
		System.out.println("navigated to FrameAndDungeonPage page");
		
	}
    
}
