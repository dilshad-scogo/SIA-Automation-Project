package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webPageUtilities.BasePage;


public class SiaHomePage {
	
	private WebDriver driver;
	
	//Constructor
	public SiaHomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// To Find Agent Store WebElement
	@FindBy(xpath="//span[text()= 'Agent Store']")
	private WebElement agentStore;
	
	
	
	//Method to Click On AgentStorePage
	public AgentStorePage clickOnAgentStore() throws InterruptedException
	{
		agentStore.click();
		AgentStorePage agentStorePage = new AgentStorePage(driver);
		return agentStorePage;
		
	}
	

}
