package testscripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testutilities.SiaBaseTest;
import webPageUtilities.BasePage;
import webPageUtilities.LoginUtility;
import webpages.SiaHomePage;
import webpages.SiaLoginPage;
import webpages.TeamPage;

public class TeamCreation extends SiaBaseTest{
	
	SiaLoginPage siaLoginPage;
	SiaHomePage siaHomePage;
	TeamPage teamPage;
	BasePage basePage;
	
	@Test(dataProvider="teamData")
	public void teamCreationTest(String uEmail, String uPassword, String teamName, String userToSearch, String userToSelect, String teamToSelect) throws IOException, InterruptedException
	{
		WebDriver driver = openBrowser();
		
		siaLoginPage = new SiaLoginPage(driver);
		siaHomePage = LoginUtility.logIn(driver, uEmail, uPassword);
		
		basePage = new BasePage(driver);
		basePage.navigateToTeams();
		
		teamPage = new TeamPage(driver);
		teamPage.createTeam(teamName, userToSearch, userToSelect);
		//teamPage.selectTeam(teamToSelect);
		
		Assert.assertTrue(teamPage.searchIfTeamExist(teamName));
		teamPage.selectTeam(teamToSelect);
		
		Thread.sleep(2000);
		driver.quit();	
	}
	@DataProvider(name="teamData")
	public Object[][] getTeamData()
	{
		return new Object[][]{
			{"anjali@scogo.in", "Anjali@910", "TestAgent", "pranav", "Pranav Ved", "Jira" }
		};
	}

}
