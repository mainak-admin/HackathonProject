package emicalculator.net;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {

	WebDriver driver;

	public WebDriver getDriver() throws Exception  {
		
		//To load the properties file
		File file = new File("src/main/resources/emicalculator.properties");
		FileInputStream fis = new FileInputStream(file);
		
		
		Properties prop = new Properties();
		prop.load(fis);

		String webUrl = prop.getProperty("url");
		String driverType = prop.getProperty("driver");

		//Check the driver type
		if(driverType.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\SeleniumDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(webUrl);
		}
		else if(driverType.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\room\\firefox\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get(webUrl);
		}
		
		return driver;
	
	}
	
}
