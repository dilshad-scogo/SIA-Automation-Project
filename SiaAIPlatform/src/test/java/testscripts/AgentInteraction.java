package testscripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testutilities.SiaBaseTest;
import webPageUtilities.BasePage;
import webPageUtilities.LoginUtility;
import webpages.AgentStorePage;
import webpages.InteractPage;
import webpages.SiaHomePage;
import webpages.SiaLoginPage;

public class AgentInteraction extends SiaBaseTest {

	//public static WebDriver driver;

	 SiaLoginPage siaLoginPage;
	 SiaHomePage siaHomePage;
	@Test(dataProvider="loginData", priority=1)
	public void siaLogin(String uEmail , String uPassword, String[] questions) throws IOException, InterruptedException
	{
			//Invoke WebDriver
			WebDriver driver = openBrowser();

		    // Login
			siaHomePage = LoginUtility.logIn(driver, uEmail, uPassword);
			BasePage basePage = new BasePage(driver);
			basePage.navigateToAgentStore();

		    // Navigate to Interact page
		    AgentStorePage agentStorePage = new AgentStorePage(driver);           //siaHomePage.clickOnAgentStore();
		    basePage.navigateToAgentStore();
		    agentStorePage.clickOnInteract();
		    
		    //Interact Page
		    InteractPage interactPage = new InteractPage(driver);
		    
		    for(String question : questions) {
		    	interactPage.askQuestion(question);
		    }
		    
		    //driver.quit();
			
	}
	
	
	@DataProvider(name="loginData")
	public Object[][] getAllData()
	{
		return new Object[][] {
			{"anjali+siau2@scogo.in", "Scogo@1234", 
				new String[] {
						"Tell me about yourself.",
						"What does your company do?",
						"can you tell me in Brief?"
				}
			}
		};
	}
	
	
}
