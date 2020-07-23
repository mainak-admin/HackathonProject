package emicalculator.Test;

import org.testng.annotations.Test;

import emicalculator.net.AutomateEmiCalculator;
import emicalculator.net.DriverSetup;
import emicalculatorExcel.ReadExcel;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class TestClass 
{
	WebDriver driver;
	Logger log = Logger.getLogger(TestClass.class);
	AutomateEmiCalculator emi;
	ReadExcel read;
	
  @BeforeSuite
  public void getDriver() throws Exception 
  {
	  DriverSetup drv = new DriverSetup();
	  driver = drv.getDriver();
	   
  }
  
  @BeforeTest
  public void setup()
  {
	  log.info("**********************Logging into browser**********************");
	  driver.manage().window().maximize();
  }
  
  @BeforeClass
  public void objectRef()
  {
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  emi = new AutomateEmiCalculator(driver);
	  read = new ReadExcel();
  }
  
  @Test(priority = 0, description = "verifying title of the webpage page")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test Case Description: Verify webpage page title")
  public void getTitle()
  {
	  log.info("**********************Starting test cases**********************");
	  log.info("**********************Fetching the page title**********************");
	  String title = driver.getTitle();
	  System.out.println(title);
	  Assert.assertEquals(title, "EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
  }
  
  @Test(priority = 1, description = "Select the type of loan")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test Case Description: Select the type of loan --> Home/Personal/Car")
  public void selectLoanType() throws Exception
  {
	  log.info("**********************Clicking on car loan tab**********************");
	  emi.selectCarLoan();
  }
  
  @Test(priority = 2, description = "Enter the car loan amount")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test Case Description: To enter the car loan amount from excel sheet")
  public void selectamount() throws Exception
  {
	  log.info("**********************Entering loan amount**********************");
	  emi.carloanAmount(read.readExcel()[0]);
  }
  
  @Test(priority = 3, description = "Enter the rate of interest")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test Case Description: To enter the rate of interest from the excel sheet")
  public void selectInterestRate() throws Exception
  {
	  log.info("**********************Entering rate of interest**********************");
	  emi.interestRate(read.readExcel()[1]);
  }
  
  @Test(priority = 4, description = "Enter the time period")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test Case Description: To enter the time period for payment of interest")
  public void selectTenure() throws Exception
  {
	  log.info("**********************Entering the time period**********************");
	  emi.tenure(read.readExcel()[2]);
  }
  
  @Test(priority = 5, description = "Click on the expand button")
  @Severity(SeverityLevel.NORMAL)
  public void expand() throws Exception
  {
	  log.info("**********************Clicking on expand button**********************");
	  emi.expand2020();
  }
  
  @Test(priority = 6, description = "To take the screenshot")
  public void screenshot() throws Exception
  {
	  log.info("**********************Taking screenshot**********************");
	  emi.screenshot(driver);
  }
  
  @Test(priority = 7, description = "To write the final values in excel sheet")
  public void writeDataInExcel() throws Exception
  {
	  log.info("**********************Writing data to excel sheet**********************");
	  emi.getData();
  }
  
  @AfterSuite
  public void closeDriver() 
  {
	  log.info("**********************Closing browser**********************");
	  driver.quit();
  }

}
