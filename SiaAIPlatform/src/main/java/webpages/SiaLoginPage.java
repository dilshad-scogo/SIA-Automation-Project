package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.util.Assert;

import webPageUtilities.BasePage;

public class SiaLoginPage {

	private WebDriver driver;
	
	public SiaLoginPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement email;
	
	@FindBy(xpath="//button[text()='Login with Password']")
	private WebElement loginWithPassBtn;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement password;
	
	@FindBy(xpath="//button[text()='Login']")
	private WebElement loginBtn;
	
	public void siaLoginAction(String uEmail, String uPassword) throws InterruptedException
	{
		email.sendKeys(uEmail);
		loginWithPassBtn.click();
		password.sendKeys(uPassword);
		loginBtn.click();
		
		
		SiaHomePage homePage = new SiaHomePage(driver);
		//return homePage;	
		
	}
	public boolean isLoginSuccessful()
	{
		String currentUrl = driver.getCurrentUrl();
		boolean login = false;
		if (currentUrl.contains("development.scogo.ai")) 
		{
			login= true;
			System.out.println("Login Successfull."+ login);
		} else 
		{
			login=false;
		    System.out.println("Login failed : "+ login );
		}
		return login;
		
	}
}
