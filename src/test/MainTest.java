package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import action.CookieActions;
import action.DragAndDropActions;
import action.EndPageActions;
import action.FirstPageActions;
import action.FrameAndDungeonActions;
import action.GridBoxActions;
import action.PopupWindowsActions;

public class MainTest {
	WebDriver driver;
	FirstPageActions firstpage;
	GridBoxActions gridboxpage;
	FrameAndDungeonActions frameAndDungeonPage;
	DragAndDropActions dragAndDropPage;
	PopupWindowsActions popupWindowsPage;
	CookieActions  tokenGenrationpage;
	EndPageActions  tatocEndPage;
	
	
	@BeforeTest
	public void openTatocLink() throws IOException {
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		//System.out.println("STARTING----------------------------------------- launching browser ");	
    	firstpage = new FirstPageActions(driver);
		gridboxpage = new GridBoxActions(driver);
    	frameAndDungeonPage = new FrameAndDungeonActions(driver);
		dragAndDropPage = new DragAndDropActions(driver);
		popupWindowsPage = new PopupWindowsActions(driver);
		tokenGenrationpage = new CookieActions(driver);
		tatocEndPage = new EndPageActions(driver);
	}
	
	
	@Test
	public void attemptClickingBasicCourse() {
		System.out.println("finding Basic Course Link to Click");
		firstpage.isClickingBasicCourseTakesToGridBoxPage();
		System.out.println("navigated to gridbox page");
		
		//System.out.println("completed----- ----------------------------------- first page ");
	}
	
	
	@Test(dependsOnMethods = { "attemptClickingBasicCourse" })
	public void attemptGridBoxPage() {
		System.out.println("verifying that clicking on red gridbox takes to error page");
		gridboxpage.isclickingRedBoxTakesToErrorPage();
		System.out.println("verified that clicking on red gridbox takes to error page");

		System.out.println("verifying that clicking on green gridbox takes to FrameandDungeon page");
		gridboxpage.isclickingGreenBoxTakesToFrameAndDungeonPage();	
		System.out.println("verified that clicking on green gridbox takes to FrameAndDungeon page");
		
		//System.out.println("COMPLETED----- ----------------------------------- GridBox page ");
	}
	
	
	@Test(dependsOnMethods = { "attemptGridBoxPage" })
	public void attemptFrameAndDungeonPage() {
		System.out.println("verifying that clicking without same color takes to error page");
		frameAndDungeonPage.isclickingWithoutColorMatchingTakesToErrorPage();
		System.out.println("verified that clicking without same color takes to error page");
		
		System.out.println("verifying that clicking with same color takes to DragAndDropBox page");
		frameAndDungeonPage.isclickingWithmatchingColorsTakesToDragAndDropBoxPage();
		System.out.println("verified that clicking with same color takes to DragAndDropBox page");
		
		//System.out.println("COMPLETED----- ----------------------------------- frameAndDungeon page ");
	}
	
	
	@Test(dependsOnMethods = { "attemptFrameAndDungeonPage" })
	public void attemptDragAndDropPage() {
		
		dragAndDropPage.verifyisDragBoxPresent();
		
		dragAndDropPage.verifyisDropBoxPresent();
		
		System.out.println("verifying that proceeding without completing drag and drop takes to error page");
		dragAndDropPage.proceedingWithoutDragAndDropTakesToErrorPage();
		System.out.println("verified that proceeding without completing drag and drop takes to error page");
		
		System.out.println("verifying that proceeding after completing drag and drop takes to popup windows page");
		dragAndDropPage.proceedingWithDragAndDropTakesToPopupWindowsPage();	
		System.out.println("verified that proceeding without completing drag and drop takes to popup windows page");
		
		//System.out.println("COMPLETED----------------------------------------- DragAndDropBox page ");
	}
	
	
	@Test(dependsOnMethods = {"attemptDragAndDropPage"})
	public void attemptPopupWindowsPage() {
		
		System.out.println("checking that launch windows button is present");
		popupWindowsPage.isLaunchWindowButtonPresent();
		System.out.println("launch windows button is present");
		
		System.out.println("verifying that proceeding Without Opening New Window Takes To Error Page");
		popupWindowsPage.proceedingWithoutOpeningNewWindowTakesToErrorPage();
		System.out.println("verified that proceeding Without Opening New Window Takes To Error Page");
		
		System.out.println("verifying that clicking launchnewWindows Button opens new window");
		popupWindowsPage.isClickingLaunchOpensNewWinDow();
		System.out.println("verified that clicking launchnewWindows Button opens new window");
		
		System.out.println("verifying that proceeding Without Typing in Input box Takes To Error Page");
		popupWindowsPage.proceedWithoutTypingInTextBoxTakesToErrorPage();
		System.out.println("verified that proceeding Without Typing in Input box Takes To Error Page");
		
		System.out.println("verifying that submitting after typing in text box takes to popup windows page");
		popupWindowsPage.submittingAfterTypingInTextBoxTakesToPopupWindowsPage();
		System.out.println("verified that submitting after typing in text box takes to popup windows page");
		
		System.out.println("verifying that proceeding after typing in inputbox to token generation page");
		popupWindowsPage.proceedingCompletingThePopupWindowsTakesToTokenPage();
		System.out.println("verified that submitting after typing in inputbox takes to token generation page");
		
		//System.out.println("COMPLETED----------------------------------------- PopupWindows page ");
	}
	
	
	@Test(dependsOnMethods = {"attemptPopupWindowsPage"})
	public void attemptTokenGenerationPage() {
		
		System.out.println("verifying that proceeding without adding cookie takes to error page");
		tokenGenrationpage.proceedingWithoutAddingCookieTakesToErrorPage();
		System.out.println("verified that proceeding without adding cookie takes to error page");
		
		System.out.println("checking that token button is present");
		tokenGenrationpage.isgenerateTokenButtonIsPresent();
		System.out.println("token button is present");
		
		System.out.println("verifying that proceeding after adding cookies takes to end course page ");
		tokenGenrationpage.proceedingAfterAddingCookieTakesToEndPage();
		System.out.println("verified that proceeding after adding cookies takes to end course page ");
		
		//System.out.println("COMPLETED----------------------------------------- TokenGeneration page ");	
	}
	
	
	@Test(dependsOnMethods = {"attemptTokenGenerationPage"})
	public void verifyEndPage() {
		System.out.println("verifying that this is last page");
		tatocEndPage.isLastPageLink();
		System.out.println("verified that this is last page");
		
		System.out.println("verifying that course ending message is present");
		tatocEndPage.isCompletingMessagePresent();
		System.out.println("Message is verified");
		
		//System.out.println("COMPLETED----------------------------------------- Tatocbasiccouse ");	
	}
	
	
	@AfterTest
	public void closeBrowser() {
		
		driver.quit();
		
		//System.out.println("COMPLETED----------------------------------------- closing browser ");
	}
	
	
}
