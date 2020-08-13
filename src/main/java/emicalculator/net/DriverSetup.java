package emicalculator.net;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSetup {

	public WebDriver driver;
	
	//Setting the hub url
	String hubUrl = "http://172.26.20.70:4444/wd/hub";

	public WebDriver getDriver() throws Exception  {
		
		//To load the properties file
		File file = new File("src\\main\\resources\\emicalculator.properties");
		FileInputStream fis = new FileInputStream(file);
		
		
		Properties prop = new Properties();
		prop.load(fis);

		//Set the url and browser type
		String webUrl = prop.getProperty("url");
		String driverType = prop.getProperty("driver");
		String modeType = prop.getProperty("mode");

		//For running on chrome browser
		if(driverType.equalsIgnoreCase("chrome"))
		{
			if(modeType.equalsIgnoreCase("local"))
			{
				System.setProperty("webdriver.chrome.driver","C:\\room\\ChromeDriver\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.get(webUrl);
			}
			else
			{
				//Define deired capabilities
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName(driverType);
				cap.setPlatform(Platform.WINDOWS);
				
				//Set driver options
				ChromeOptions options = new ChromeOptions();
				options.merge(cap);
				
				driver = new RemoteWebDriver(new URL(hubUrl), options);
				
				driver.get(webUrl);
				
			}
			
		}
		
		//For running on firefox brwoser
		else if(driverType.equalsIgnoreCase("firefox"))
		{
			if(modeType.equalsIgnoreCase("local"))
			{
				System.setProperty("webdriver.gecko.driver","C:\\room\\firefox\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.get(webUrl);
			}
			else
			{
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName(driverType);
				cap.setPlatform(Platform.WINDOWS);
				
				FirefoxOptions options = new FirefoxOptions();
				options.merge(cap);
				driver = new RemoteWebDriver(new URL(hubUrl), options);
				
				driver.get(webUrl);
			}
			
		}
		
		//For running on internet explorer
		else if(driverType.equalsIgnoreCase("InternetExplorer"))
		{
			if(modeType.equalsIgnoreCase("local"))
			{
				System.setProperty("webdriver.ie.driver","C:\\room\\InternetExplorer\\IEDriverServer.exe");
				
				driver = new InternetExplorerDriver();
				driver.get(webUrl);
			}
			
			else
			{
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("browser", "IE");
				cap.setCapability("browser_version", "11.0");
				cap.setPlatform(Platform.WINDOWS);
				
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.merge(cap);
				driver = new RemoteWebDriver(new URL(hubUrl), options);
				
				driver.get(webUrl);
			}
			
		}
		else
		{
			System.out.println("Please select a valid browser");
		}
		
		return driver;
		
	}
	
}