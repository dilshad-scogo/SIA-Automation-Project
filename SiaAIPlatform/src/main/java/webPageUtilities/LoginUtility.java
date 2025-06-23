package webPageUtilities;

import org.openqa.selenium.WebDriver;

import webpages.SiaHomePage;
import webpages.SiaLoginPage;

public class LoginUtility {
	
	//Login Method
	public static SiaHomePage logIn(WebDriver driver, String uEmail, String uPassword) throws InterruptedException
	{
		SiaLoginPage siaLoginPage= new SiaLoginPage(driver);
		siaLoginPage.siaLoginAction(uEmail, uPassword);
		
		return new SiaHomePage(driver);

	}

}
