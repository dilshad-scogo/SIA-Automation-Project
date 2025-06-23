package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webPageUtilities.BasePage;

import java.time.Duration;
import java.util.List;

public class KnowledgeBasePage extends BasePage {
	
	private WebDriver driver;

	public KnowledgeBasePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@type='text' and @placeholder='Search folders...']")
	private WebElement searchFolderName;
	
	@FindBy(xpath="//span[text()='Teams']")
	private WebElement teamTab;	
	
	@FindBy(xpath="//a[contains(@href, '/knowledge/drive')]/div/div/h3") ////a[@class='block']
	private String folderList;

//	@FindBy(xpath="//a[contains(@href, '/knowledge/drive')]/div/div/h3") ////a[@class='block']
//	private List<WebElement> folderListName;
	
	@FindBy(xpath="//h3[text()='Apple']")
	private WebElement appleElement;
	
	public List<WebElement> getfreshFolderList()
	{
		return driver.findElements(By.xpath("//a[contains(@href, '/knowledge/drive')]/div/div/h3"));
	}
//	
	public boolean searchIffolderExist(String folderNameExist) throws InterruptedException
	{
		boolean folderExist = false;
		searchFolderName.clear();
		searchFolderName.click();
		searchFolderName.sendKeys(folderNameExist);
		
		
		WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> folderListName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3[contains(text(), '"+ folderNameExist+"')]")));
		try
		{
			//List<WebElement> folders = getfreshFolderList();
			for(WebElement folder: folderListName)
			{
				if(folder.getText().equals(folderNameExist)) {
					folderExist= true; 
					System.out.println("The Folder With Name : " +folderNameExist+ " Exist.");
				}
				else
				{
					System.out.println("The Folder With Name : " +folderNameExist+ " Does Not Exist.");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return folderExist;
	}
	
}

//public void validateFolderExist()
//{
//	boolean isFolderFound = false;
//	for(WebElement folder: folderList)
//	{
//		if(folder.get)
//	}
//}



//System.out.println("Before Wait");
////BasePage.waitUntilVislibleListElements(driver, folderListName);
////BasePage.scrollToElement(appleElement);
//System.out.println("After Wait");