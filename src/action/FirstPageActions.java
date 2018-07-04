package action;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import util.WebElementLocators;

public class FirstPageActions {
	WebElementLocators locateWebELement;
	WebDriver driver;
	
	public FirstPageActions(WebDriver driver) throws IOException {
		this.driver = driver;
		locateWebELement = new WebElementLocators(driver);
	}
	
	private void clickBasicCourse() {
		assertTrue(locateWebELement.getWebElement("Basic_course_button").isDisplayed(),"basic course button is not present");
		System.out.println("Basic Course Link is Found");
		locateWebELement.getWebElement("Basic_course_button").click();
		System.out.println("clicked on Basic Course Link");
	}
	
	public void isClickingBasicCourseTakesToGridBoxPage() {
		clickBasicCourse();
		String expectedUrl = "http://10.0.1.86/tatoc/basic/grid/gate";
		assertTrue(expectedUrl.contains(driver.getCurrentUrl()),"navigation to gridbox page failed");
        System.out.println("navigating to gridbox page");
	}
}
