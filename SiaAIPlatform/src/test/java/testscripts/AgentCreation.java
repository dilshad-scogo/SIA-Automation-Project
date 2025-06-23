package testscripts;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testutilities.SiaBaseTest;
import webPageUtilities.BasePage;
import webPageUtilities.LoginUtility;
import webpages.AgentStorePage;
import webpages.InteractPage;
import webpages.KnowledgeBasePage;
import webpages.SiaHomePage;
import webpages.SiaLoginPage;
import webpages.TeamPage;

public class AgentCreation extends SiaBaseTest{
	
	public static WebDriver driver;
	SiaLoginPage siaLoginPage;
	SiaHomePage siaHomePage;
	AgentStorePage agentStorePage;
	InteractPage interactPage;
	KnowledgeBasePage knowledgeBasePage;
	TeamPage teamPage;
	BasePage basePage;

	
	@Test(dataProvider= "allData")
	public void agentCraetionAndView(String uEmail, String uPassword, String nameOfAgent, String description, String logo, String url, String folderName, String teamName, String agentOldName, String agentNewName, String agentToDelete) throws InterruptedException, IOException
	{
		//Invoke Browser
		WebDriver driver = openBrowser();
		
		siaLoginPage = new SiaLoginPage(driver);
		siaHomePage = LoginUtility.logIn(driver,uEmail, uPassword);
		//Assertion[1]
		Assert.assertTrue(siaLoginPage.isLoginSuccessful());
		
		
		basePage= new BasePage(driver);
		agentStorePage = basePage.navigateToAgentStore();
		String agentStoreUrl = driver.getCurrentUrl();
		System.out.println("Current Url : "+ agentStoreUrl);
		//Assertion[2]
		Assert.assertEquals(agentStoreUrl, "https://sia.development.scogo.ai/agents");
		
		agentStorePage = new AgentStorePage(driver);
		agentStorePage.clickOnCreateAgent(); //Button
		agentStorePage.agentCreate(nameOfAgent, description, logo, url ); // Form
		//Assertion[3]
		Assert.assertTrue(agentStorePage.isAgentPresent(nameOfAgent)); // Created agent present
		
		knowledgeBasePage = basePage.navigateToKnowledgeHub(); // Navigate to Knowledge Hub
		knowledgeBasePage= new KnowledgeBasePage(driver);
		//Assertion[4]
		Assert.assertTrue(knowledgeBasePage.searchIffolderExist(folderName)); // If folder Exist
	
		
		teamPage = basePage.navigateToTeams();
		teamPage = new TeamPage(driver);
		//Assertion[5]
		Assert.assertTrue(teamPage.searchIfTeamExist(teamName)); // If Team Exist
	
		basePage.navigateToAgentStore();
		agentStorePage.editAgent(agentOldName, agentNewName);  // Edit
		//Assertion[6]
		//Assert.assertTrue(agentStorePage.deleteAgent(agentToDelete));   // Delete
		
		//Assertion[7]
		//agentStorePage.isAgentPresent(agentToDelete);  //IS delete successfully
		
		//Edit Agent
		
		driver.quit();
		
		
		
	}
	
	
	@DataProvider(name="allData")
	public Object [][] getAgentData()
	{
		return new Object[][] {
			{"anjali@scogo.in", "Anjali@910", "Apple India", "Apple India", "logo.png", "https://www.apple.com/in/", "Pranav", "Pranav", "Ind Store", "IND NEW STORE", "Abp News"}
		};
	}

	
}





























