package webpages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InteractPage {

	
	private  WebDriver driver;
	
	public InteractPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//textarea[@placeholder='Ask anything ...']")
	private WebElement inputQuestion;
	
	@FindBy(xpath="//button[@title='Send message']")
	private WebElement sendMessage;
	
	public void askQuestion(String question)
	{
		inputQuestion.clear();
		inputQuestion.sendKeys(question);
		sendMessage.click();
		
	}
}



























//@FindBy(xpath = "(//div[contains(@class, 'break-words')]//p[contains(@class, 'text-neutral-800')])[last()]")
//private WebElement siaLastResponse;