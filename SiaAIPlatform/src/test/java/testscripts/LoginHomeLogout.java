package testscripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testutilities.SiaBaseTest;
import webPageUtilities.BasePage;
import webpages.SiaHomePage;
import webpages.SiaLoginPage;

public class LoginHomeLogout extends SiaBaseTest{

	//public static WebDriver driver;
	@Test(dataProvider="loginData")
	public void loginToLogoutTest(String uEmail, String uPassword) throws IOException, InterruptedException {
		
		WebDriver driver = openBrowser();
		
		//Login
		SiaLoginPage siaLoginPage = new SiaLoginPage(driver);
		siaLoginPage.siaLoginAction(uEmail,uPassword);
		
		//Without DataProvider
		//siaLoginPage.siaLoginAction("anjali@scogo.in", "Anjali@910");
		
		//Logout
		BasePage basePage = new BasePage(driver);
		basePage.logOut();
		
		driver.quit();
	}
	
	
	@DataProvider(name="loginData")
	public Object[][] getData()
	{
		return new Object [][] {
			{"anjali+siau3@scogo.in", "Scogo@1234"}
		};
	}
}
