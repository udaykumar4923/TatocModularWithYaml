package action;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import util.WebElementLocators;

public class DragAndDropActions {
    WebDriver driver;
    WebElementLocators dragAndDropElementLocators;
    
    public DragAndDropActions(WebDriver driver) throws IOException {
 	   this.driver = driver;
 	   dragAndDropElementLocators = new WebElementLocators(driver);
    }
    
    private  WebElement getWebElementToDrop() {
 	   return dragAndDropElementLocators.getWebElement("Dragbox_element");
    }
    
    private WebElement getWebElemntDropBox() {
 	   return dragAndDropElementLocators.getWebElement("Dropbox_element");
    }
    
    public void verifyisDragBoxPresent() {
       System.out.println("checking that dragbox is present");
 	   assertTrue(dragAndDropElementLocators.getWebElement("Dragbox_element").isDisplayed());
 	  System.out.println("found dragbox element");
    }
    
    public void verifyisDropBoxPresent() {
       System.out.println("checking that dropbox is present");
 	   assertTrue(dragAndDropElementLocators.getWebElement("Dropbox_element").isDisplayed(),"dropbox is not found");
 	  System.out.println("found dropbox element");
    }
    
    public void proceedingWithoutDragAndDropTakesToErrorPage() {
    	System.out.println("founding proceed link");
    	dragAndDropElementLocators.getWebElement("DragAndDrop_Proceed_button").click();
    	System.out.println("clicked proceed link");
		String expectedUrl = "http://10.0.1.86/tatoc/error";
		System.out.println("navigated to error page");
		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
		driver.navigate().back();
		System.out.println("navigated from error page");
    }
    
    public void proceedingWithDragAndDropTakesToPopupWindowsPage() {
		Actions act = new Actions(driver);
		act.dragAndDrop(getWebElementToDrop(),getWebElemntDropBox()).build().perform();
		System.out.println("completed drag and drop task");
		driver.findElement(By.linkText("Proceed")).click();
		System.out.println("clicked proceed link");
		String expectedUrl = "http://10.0.1.86/tatoc/basic/windows";
		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
		System.out.println("navigated to PopupWindows page");
    }
}
