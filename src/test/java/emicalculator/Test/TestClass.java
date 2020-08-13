package emicalculator.Test;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import emicalculator.net.AutomateEmiCalculator;
import emicalculator.net.DriverSetup;
import emicalculatorExcel.ReadExcel;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestClass 
{
	WebDriver driver;
	Logger log = Logger.getLogger(TestClass.class);
	static AutomateEmiCalculator emi;
	static ReadExcel read;
	ExtentReports extent;
	ExtentTest Test;

	@BeforeTest(alwaysRun = true)
	public void setup() throws Exception
	{
		DriverSetup drv = new DriverSetup();
		driver = drv.getDriver();
		log.info("*************** *******Logging into browser**********************");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		emi = new AutomateEmiCalculator(driver);
		read = new ReadExcel();
	}

	@Test(priority = 0, description = "Verifying title of the webpage page", groups = "regression")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify webpage page title")
	public void getTitle()
	{
		extent = new ExtentReports("C:\\Users\\user\\eclipse-Mainak\\emicalculator.net\\target\\surefire-reports\\Extent_RegressionReport.html", true);
		Test = extent.startTest("Verify webpage title for regression testing");
		Test.log(LogStatus.INFO, "Regression test for title verification initiated");
		log.info("**********************Starting test cases**********************");
		log.info("**********************Fetching the page title**********************");
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
		Test.log(LogStatus.PASS, "The title of webpage is verified");
	}

	@Test(priority = 1, description = "Select the type of loan", groups = "regression")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Select the type of loan --> Home/Personal/Car")
	public void selectLoanType() throws Exception
	{
		Test = extent.startTest("Select car loan tab");
		Test.log(LogStatus.INFO, "Regression test for car loan selection initiated");
		log.info("**********************Car loan tab is selected**********************");
		emi.selectCarLoan();
		Test.log(LogStatus.PASS, "Car loan tab is selected");
	}

	@Test(priority = 2, description = "Enter the car loan amount", groups = "regression")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: To enter the car loan amount from excel sheet")
	public void selectamount() throws Exception
	{
		Test = extent.startTest("Enter loan amount");
		Test.log(LogStatus.INFO, "Regression test for entering loan amount initiated");
		log.info("**********************Entering loan amount**********************");
		emi.carloanAmount(read.readExcel()[0]);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid green'", emi.CarloanAmount);
		Test.log(LogStatus.PASS, "Car loan amount is entered");
	}


	@Test(priority = 3, description = "Enter the rate of interest", groups = "regression")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: To enter the rate of interest from the excel sheet")
	public void selectInterestRate() throws Exception
	{
		Test = extent.startTest("Enter rate of interest");
		Test.log(LogStatus.INFO, "Regression test for entering rate of interest initiated");
		log.info("**********************Entering rate of interest**********************");
		emi.interestRate_positiveTest(read.readExcel()[1]);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid green'", emi.CarloanInterest);
		Test.log(LogStatus.PASS, "Rate of interest is entered");
	}


	@Test(priority = 4, description = "Enter the time period", groups = "regression")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: To enter the time period for payment of interest")
	public void selectTenure() throws Exception
	{
		Test = extent.startTest("Enter the tenure for payment");
		Test.log(LogStatus.INFO, "Regression test for entering time period initiated");
		log.info("**********************Entering the time period**********************");
		emi.tenure(read.readExcel()[2]);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid green'", emi.CarloanTenure);
		Test.log(LogStatus.PASS, "Tenure is entered");
	}

	@Test(priority = 5, description = "Click on the expand button", groups = "regression")
	@Severity(SeverityLevel.NORMAL)
	public void expand() throws Exception
	{
		Test = extent.startTest("Click on the expand button for loan details");
		Test.log(LogStatus.INFO, "Regression test for clicking on expand button initiated");
		log.info("**********************Clicking on expand button**********************");
		emi.expand2020();
		Test.log(LogStatus.PASS, "Expand button is clicked");
	}

	@Test(priority = 6, description = "To take the screenshot", groups = "regression")
	@Severity(SeverityLevel.CRITICAL)
	public void screenshot() throws Exception
	{
		Test = extent.startTest("Preparing to take screenshot");
		Test.log(LogStatus.INFO, "Regression test for screenshot is generated");
		log.info("**********************Taking screenshot**********************");
		String imagePath = emi.screenshot(driver);
		Test.log(LogStatus.PASS, "Screenshot has been taken");
		Test.log(LogStatus.PASS, Test.addScreenCapture(imagePath));
	}

	@Test(priority = 7, description = "To write the final values in excel sheet", groups = "regression")
	@Severity(SeverityLevel.NORMAL)
	public void writeDataInExcel() throws Exception
	{
		Test = extent.startTest("Storing details in excel file");
		Test.log(LogStatus.INFO, "Regression test for writing details is generated");
		log.info("**********************Writing data to excel sheet**********************");
		emi.getData();
		Test.log(LogStatus.PASS, "Data has been stored in excel file");
	}

	@AfterMethod(alwaysRun=true, groups ="regression")
	public void flush(ITestResult result) {

		if(result.getStatus()==ITestResult.FAILURE) {
			Test.log(LogStatus.FAIL,"The test case "+result.getName()+" is failed");
			Test.log(LogStatus.FAIL, result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			Test.log(LogStatus.SKIP,"The test case "+result.getName()+" is skipped");
		}

		extent.endTest(Test);
		extent.flush();
	}

	@AfterTest(alwaysRun = true) 
	public void closeDriver() 
	{
		log.info("**********************Closing browser**********************");
		driver.quit();
	}

}