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
	  String title = driver.getTitle();
	  System.out.println(title);
	  Assert.assertEquals(title, "EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
  }
  
  @Test(priority = 1)
  public void selectLoanType() throws Exception
  {
	  emi.selectCarLoan();
  }
  
  @Test(priority = 2)
  public void selectamount() throws Exception
  {
	  emi.carloanAmount(read.readExcel()[0]);
  }
  
  @Test(priority = 3)
  public void selectInterestRate() throws Exception
  {
	  emi.interestRate(read.readExcel()[1]);
  }
  
  @Test(priority = 4)
  public void selectTenure() throws Exception
  {
	  emi.tenure(read.readExcel()[2]);
  }
  
  @Test(priority = 5)
  public void expand() throws Exception
  {
	  emi.expand2020();
  }
  
  @Test(priority = 6)
  public void screenshot() throws Exception
  {
	  emi.screenshot(driver);
  }
  
  @Test(priority = 7)
  public void writeDataInExcel() throws Exception
  {
	  emi.getData();
  }
  
  @AfterSuite
  public void closeDriver() 
  {
	  driver.quit();
  }

}
