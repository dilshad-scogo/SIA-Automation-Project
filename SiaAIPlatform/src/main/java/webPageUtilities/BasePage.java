package webPageUtilities;

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

import webpages.AgentStorePage;
import webpages.KnowledgeBasePage;
import webpages.SiaHomePage;
import webpages.SiaLoginPage;
import webpages.TeamPage;

public class BasePage {
	
	static WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	// WebElements For Profile Tab
	@FindBy(xpath="//div[@class='aspect-square size-5 cursor-pointer overflow-hidden rounded-full md:size-6 lg:size-7 xl:size-7']")
	private  WebElement profileTab;
	
	@FindBy(xpath="//span[text()='Manage']")
	private  WebElement manage;
	
	@FindBy(xpath="//span[text()='Log Out']")
	private  WebElement logOut;
	
	//WebElMents To navigateDifferentPAges
	@FindBy(xpath="//span[text()='Home']")
	private  WebElement homeTab;
	
	@FindBy(xpath= "//span[text()='Ticket']")
	private  WebElement ticketsTab;
	
	@FindBy(xpath="//span[text()='Chats']")
	private  WebElement chatsTab;
	
	@FindBy(xpath="//*[text()='Teams']")
	private  WebElement teamsTab;
	
	@FindBy(xpath="//*[text()='Knowledge Hub']")
	private  WebElement knowledgeHubTab;
	
	@FindBy(xpath="//*[text()='Agent Store']")
	private  WebElement agentStoreTab;
	
	
	
	public  void navigateToProfile()
	{
		profileTab.click();
		manage.click();
	}
	
	public  void logOut()
	{
		profileTab.click();
		logOut.click();
	}

	
	//Methods To perform Navigate Action
	public  void navigateToHome()
	{
		homeTab.click();
	}
	
	public  void navigateToTickets()
	{
		ticketsTab.click();
	}
	
	public void navigateToChats()
	{
		chatsTab.click();
	}
	
	public TeamPage navigateToTeams()
	{
		teamsTab.click();
		TeamPage teamPage = new TeamPage(driver);
		return teamPage;
	}
	
	public KnowledgeBasePage navigateToKnowledgeHub()
	{
		knowledgeHubTab.click();
		KnowledgeBasePage knowledgeBasePage = new KnowledgeBasePage(driver);
		return knowledgeBasePage;
	}
	
	public  AgentStorePage navigateToAgentStore()
	{
		agentStoreTab.click();
		AgentStorePage agentStorePage = new AgentStorePage(driver);
		return agentStorePage;
	}

	//Wait
	public static void waitUntilVisible(WebDriver driver, WebElement element)
	{
		new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.visibilityOfAllElements(element));
		
	}
	
	//ScrollTo Element
	public static void scrollToElement(WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
	}
	
	public static void waitUntilVislibleListElements(WebDriver driver, List<WebElement> listOfElement)
	{
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.visibilityOfAllElements(listOfElement));
	}
}




















































////Login WebElements
//	@FindBy(xpath="//input[@type='text']")
//	private static WebElement email;
//	
//	@FindBy(xpath="//button[text()='Login with Password']")
//	private static WebElement loginWithPassBtn;
//	
//	@FindBy(xpath="//input[@type='password']")
//	private static WebElement password;
//	
//	@FindBy(xpath="//button[text()='Login']")
//	private static WebElement loginBtn;
//
//
//
//
