package testutilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SiaBaseTest {
	
	static WebDriver driver;
	
	public static WebDriver openBrowser() throws IOException
	{
		//WebDriver driver = null;
		
		FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\SiaAIPlatform\\src\\main\\java\\resources\\configuration.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		
		//Select Browser.
		String browser = properties.getProperty("browser");
		if(browser.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		
		//Get URL
		String url = properties.getProperty("baseUrl");
		driver.get(url);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	
		return driver;
		
	}
	
	public void closeBrowser() throws InterruptedException
	{
		driver.close();
	}

}
