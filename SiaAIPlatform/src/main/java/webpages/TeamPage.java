package webpages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webPageUtilities.BasePage;

public class TeamPage extends BasePage{
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public TeamPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	//SearchBar WebElement
	@FindBy(xpath="//*[contains(@placeholder,'Search Teams')]")
	private WebElement searchTeamName;
	
	//CreateTeam Button 
	@FindBy(xpath="//*[contains(text(), 'Create Team')]")
	private WebElement createTeamButton;
	
	//Team Creation Form
	@FindBy(xpath="//*[contains(@placeholder, 'Team Name')]")
	private WebElement teamNameInput;
	
	@FindBy(xpath="//*[@id='search-input']")
	private WebElement searchUser;
	
	@FindBy(xpath="//*[contains(@class, 'overflow-y-auto rounded-lg')]")
	private List<WebElement> userList;
	
	@FindBy(xpath="//*[text()='Create Team']")
	private WebElement submitCreateTeam;
	
	//Error Message
	@FindBy(xpath="//*[contains(text(), 'Error')]")
	private WebElement errorMessage;
	
	//Team Scrollbar
	@FindBy(xpath="//div[contains(@class,'max-h-[calc(100vh-200px)]')]")
	private WebElement teamScrollBar;
	
	//Team List
	@FindBy(xpath="//div[contains(@class,'max-h-[calc(100vh-200px)]')]")
	private List<WebElement> teamList;
	
	//Craete Team
	public void createTeam(String teamName, String searchUserName, String userNameToSelect)
	{
		createTeamButton.click();
		teamNameInput.clear();
		teamNameInput.sendKeys(teamName);
		searchUser.clear();
		searchUser.sendKeys(searchUserName);
		WebElement targetUser = driver.findElement(By.xpath("//span[contains(text(),'" +userNameToSelect+ "')]"));
		
		targetUser.click();
		submitCreateTeam.click();
		
		BasePage.waitUntilVisible(driver, searchTeamName);	
	}

	
	public boolean searchIfTeamExist(String teamNameExist) throws InterruptedException // use nameOfAgent, because when agent gets create at that time Team with same name of agent gets create
	{
		searchTeamName.click();
		searchTeamName.sendKeys(teamNameExist);
		boolean teamExist = false;
//		if(driver.findElement(By.xpath("//div[@role='button' and contains(., '" +teamName+ "')]")) != null)
//		{
//			teamExist = true;
//			System.out.println("is Team Exist : "+teamExist);
//		}
//		searchTeamName.clear();
		
		List<WebElement> teams = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//span[contains(text(),'"+teamNameExist+"')])[2]")));
		for(WebElement team : teams)
		{
			if(team.getText().equals(teamNameExist))
			{
				teamExist= true;
				System.out.println("A Team With Name : " +teamNameExist+ " Exist.");
			}
			else {
				System.out.println("A Team With Name : " +teamNameExist+ " Does Not Exist.");
			}
		}
	
		return teamExist;
		
	}
	
	public boolean selectTeam(String teamNameToSelect) throws InterruptedException
	{
	
		boolean isTeamSelected = false;
		for(WebElement team : teamList)
		{
			Actions action = new Actions(driver);
			if(team.getText().equalsIgnoreCase(teamNameToSelect));
			{
				action.moveToElement(team).click().build().perform();
				isTeamSelected=true;
				
				Thread.sleep(2000);
			}
		}
		System.out.println("is Team Selected : The " +teamNameToSelect+ " has been selected");
		return 	isTeamSelected;
		
	}
}
