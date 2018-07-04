package action;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import util.WebElementLocators;

public class FrameAndDungeonActions {
	 WebDriver driver;
	 WebElementLocators frameAndDungeonElementLocators;
	
	public FrameAndDungeonActions(WebDriver driver) throws IOException {
		this.driver = driver;
		frameAndDungeonElementLocators = new WebElementLocators(driver);
	}
	
	private String getColorOfBox1() {
		driver.switchTo().frame("main");
		String color_of_box1 = frameAndDungeonElementLocators.getWebElement("First_box_color").getAttribute("class");
		driver.switchTo().defaultContent();
		return color_of_box1;
	}
	
	private String getColorOfBox2() {
		driver.switchTo().frame("main");
		driver.switchTo().frame("child");
		String color_of_box_2 = frameAndDungeonElementLocators.getWebElement("Second_box_color").getAttribute("class");
		driver.switchTo().defaultContent();
		return color_of_box_2;
	}
	
	private void repaint() {
		driver.switchTo().frame("main");
		frameAndDungeonElementLocators.getWebElement("Repaint_button").click();
		driver.switchTo().defaultContent();
	}
	
	
	private void matchColor() {
		String box1Color = getColorOfBox1();
		System.out.println("getting color of box1");
		String box2Color = getColorOfBox2();
		System.out.println("getting color of  box2");
		System.out.println("repainting until color is different");
		while(box1Color.equals(box2Color) != true) {
			repaint();
			box2Color = getColorOfBox2();
		}
	}
	
	
	public void proceed() {
		System.out.println("switching to main frame");
		driver.switchTo().frame("main");
		System.out.println("finding proceed button to click");
		frameAndDungeonElementLocators.getWebElement("Proceed_button").click();
		System.out.println("clicked on proceed button");
	}
	
	
	public void isclickingWithoutColorMatchingTakesToErrorPage() {
		proceed();
		String expectedUrl = "http://10.0.1.86/tatoc/error";
		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
		System.out.println("navigated to error page");
		driver.navigate().back();
		System.out.println("navigating back from error page");
	}
	
	public void isclickingWithmatchingColorsTakesToDragAndDropBoxPage() {
		matchColor();
		System.out.println("color matched");
		proceed();
		String expectedUrl = "http://10.0.1.86/tatoc/basic/drag";
		assertTrue(expectedUrl.contains(driver.getCurrentUrl()));
		System.out.println("navigating to DragAndDropPage");
	}
}
