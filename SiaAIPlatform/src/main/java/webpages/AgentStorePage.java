package webpages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webPageUtilities.BasePage;

public class AgentStorePage extends BasePage{
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public AgentStorePage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	//Create Agent Form WebElements
	@FindBy(xpath="//button[contains(text(),'Create Agent')]")
	private WebElement createAgentBtn;
	
	//agent Creation Form
	@FindBy(xpath="//input[@id='name']")
	private WebElement agentName;
	
	@FindBy(xpath="//textarea[@id='description']")
	private WebElement agentDescription;
	
	@FindBy(xpath="//input[@id='logo']")
	private WebElement agentLogo;
	
	@FindBy(xpath="//input[@id='website']")
	private WebElement agentDomainUrl;
	
	@FindBy(xpath="//button[text()='Create']")
	private WebElement createBtn;
	
	//To Interact With agent
	@FindBy(xpath="//a[text()='Interact']")
	private WebElement interactBtn;
	
	//Agent Actions Menu
	@FindBy(xpath="//button[@aria-haspopup='menu']")
	private WebElement menuButton;
	
	//Action Delete
	@FindBy(xpath="//div[@role='menuitem' and contains(text(),'Delete Agent')]")
	private WebElement deleteAgent;
	
	@FindBy(xpath="//button[text()='Delete']")
	private WebElement confirmDelete;
	
	//Action Edit
	@FindBy(xpath="//div[@role='menuitem' and contains(text(),'Edit Agent')]")
	private WebElement editAgent;
	
	//Edit Form
	@FindBy(xpath="//input[@id='name']")
	private WebElement editName;
	
	@FindBy(xpath="//*[@id='description']")
	private WebElement editDescription;
	
	@FindBy(xpath="//*[@id='logo']")
	private WebElement editLogo;
	
	@FindBy(xpath="//*[@id='website']")
	private WebElement editUrl;
	
	@FindBy(xpath="//*[text()='Update']")
	private WebElement updateButton;
	
	//To navigate Knowledge Hub
	@FindBy(xpath="//span[text()='Knowledge Hub']")
	private WebElement knowledgeHubTab;
	
	//To find List Of Agents
	@FindBy(xpath="//h3[contains(@class, 'truncate text')]")
	private List<WebElement> agents;
	
	//To Search Agents
	@FindBy(xpath="(//input[@placeholder= 'Search Agents...'])[2]")
	private WebElement searchAgent;
	
	public void clickOnCreateAgent() {
		createAgentBtn.click(); // This reveals the form on same page
	}
	
	public void agentCreate(String name, String description, String logo, String url) {
		
		agentName.sendKeys(name);
		agentDescription.sendKeys(description);
		agentLogo.sendKeys(logo);
		agentDomainUrl.sendKeys(url);
		createBtn.click();
		
	}
	
	public InteractPage clickOnInteract()
	{
		interactBtn.click();
		
		InteractPage intractPage = new InteractPage(driver);
		return intractPage;
	}

	public boolean isAgentPresent(String agentNameSearch) 
	{
	    boolean isPresent = false;
	    
	    //System.out.println("Before Applying Wait To Agent Search Bar");
	    BasePage.waitUntilVisible(driver, searchAgent);
	    //System.out.println("After Applying Wait To Agent Search Bar");
	    
	    searchAgent.click();
	    searchAgent.sendKeys(agentNameSearch);
	    if(!driver.findElements(By.xpath("//h3[contains(text(), '" + agentNameSearch + "')]")).isEmpty())
	    {
	    	isPresent=true;
	    }
	    System.out.println("Is Agent " +agentNameSearch+ " Present : " + isPresent);
	    return isPresent;
	}
	
	public boolean deleteAgent(String agentToDelete)
	{
		
		boolean isDelete = false;
		for(WebElement agent : agents)
		{
			if(agent.getText().equalsIgnoreCase(agentToDelete))
			{
				WebElement menuBtnForEachAgent = agent.findElement(By.xpath("./ancestor::div[contains(@class,'border-gray-100')]//button[@aria-haspopup='menu']"));  ////button[@aria-haspopup='menu']/ancestor::div[contains(@class,'border-gray-100 bg-white')]
				System.out.println("Before Applying Wait To Menu Button.");
				BasePage.waitUntilVisible(driver, menuBtnForEachAgent);
				System.out.println("After Applying Wait To Menu Button.");
				menuBtnForEachAgent.click();
				deleteAgent.click();
				confirmDelete.click();	
				isDelete= true;
				break;
			}
			else
			{
				isDelete = false;
			}
		}
		
//		if(isDelete == true)
//		{
//		System.out.println("Agent Found And Deleted")	;
//		}
//		else {
//			System.out.println("The "+ agentToDelete +" Agent Not Found To Delete");
//		}
		System.out.println("is Agent deleted : " + isDelete);
		return isDelete;
	}
	
	public boolean editAgent(String agentOldName, String newName) throws InterruptedException//, String descToEdit, String logoToEDit, String urlToEdit
	{
		boolean isEdit = false;
		List <WebElement> agentList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[contains(text(),'"+agentOldName+"')]")));
		for(WebElement agent : agents)
		{
			if(agent.getText().equals(agentOldName))
			{
				WebElement menuBtnForEachAgent = agent.findElement(By.xpath("./ancestor::div[contains(@class,'border-gray-100')]//button[@aria-haspopup='menu']"));  ////button[@aria-haspopup='menu']/ancestor::div[contains(@class,'border-gray-100 bg-white')]
				menuBtnForEachAgent.click();
				
				editAgent.click();
				editName.clear();
				editName.sendKeys(newName);
				Thread.sleep(1000);
				updateButton.click();
				if(!agentList.isEmpty())//driver.findElements(By.xpath("//h3[contains(text(),'"+agentOldName+"')]")) != null
				{
					isEdit = false;
					System.out.println("Agent is Not Updated");
				}
				else
				{
					isEdit = true;
					System.out.println("Agent Name updated successfully..");
				}
			}
			Thread.sleep(1000);
		}
		System.out.println("is the agent Updated : " + isEdit);
		return isEdit;
	}
	
	
	 
}


