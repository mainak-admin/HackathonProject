package emicalculator.Test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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

public class SmokeTest 
{
	WebDriver driver;
	Logger log = Logger.getLogger(SmokeTest.class);
	static AutomateEmiCalculator emi;
	static ReadExcel read;
	ExtentReports extent;
	ExtentTest Test;

	@BeforeTest(alwaysRun = true)
	public void setup_smokeTesting() throws Exception
	{
		DriverSetup drv = new DriverSetup();
		driver = drv.getDriver();
		log.info("*************** *******Logging into browser**********************");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		emi = new AutomateEmiCalculator(driver);
		read = new ReadExcel();
	}

	@Test(priority = 0, description = "Verifying title of the webpage page", groups = "smoke")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify webpage page title for smoke testing")
	public void getTitle_smokeTesting()
	{
		extent = new ExtentReports("C:\\Users\\user\\eclipse-Mainak\\emicalculator.net\\target\\surefire-reports\\Extent_SmokeReport.html", true);
		Test = extent.startTest("Verify webpage title for smoke testing");
		Test.log(LogStatus.INFO, "Smoke test for title verification initiated");
		log.info("**********************Starting test cases**********************");
		log.info("**********************Fetching the page title for smoke testing**********************");
		String title = driver.getTitle();
		Assert.assertEquals(title, "EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
		System.out.println(title);
		Test.log(LogStatus.PASS, "The title of webpage is verified");
		log.info("**********************The webpage title is verified**********************");
	}

	@Test(priority = 1, description = "Verify car loan button", groups = "smoke")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: Verify car loan button")
	public void verifybutton_smokeTesting() throws Exception
	{
		Test = extent.startTest("Verify car loan tab for smoke testing");
		Test.log(LogStatus.INFO, "Smoke test for loan tab is initiated");
		log.info("**********************Verifying car loan tab**********************");
		emi.checkLoanTab();
		Test.log(LogStatus.PASS, "Car loan tab is verified");
	}

	@Test(priority = 2, description = "Verify car loan amount tab", groups = "smoke")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: To check whether car loan amount tab is enabled or not")
	public void verifySelectamount_smokeTesting() throws Exception
	{
		Test = extent.startTest("Verify car loan amount tab for smoke testing");
		Test.log(LogStatus.INFO, "Smoke test for loan amount tab is initiated");
		log.info("**********************Checking whether loan amount tab is enabled or not**********************");
		emi.checkLoanAmount();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid green'", emi.CarloanAmount);
		Test.log(LogStatus.PASS, "Loan amount tab is verified");
	}

	@Test(priority = 3, description = "Verify the rate of interest tab", groups = "smoke")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: To check whether rate of interest tab is enabled or not")
	public void verifyInterestRate_smokeTesting() throws Exception
	{
		Test = extent.startTest("Verify rate of interest tab for smoke testing");
		Test.log(LogStatus.INFO, "Smoke test for rate of interest tab is initiated");
		log.info("**********************Verifying rate of interest**********************");
		emi.checkInterest();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid green'", emi.CarloanInterest);
		Test.log(LogStatus.PASS, "Rate of interest tab is verified");
	}

	@Test(priority = 4, description = "Verify the time period tab", groups = "smoke")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: To check whether the time period field is enabled or not")
	public void verifyTenure_smokeTesting() throws Exception
	{
		Test = extent.startTest("Verify tenure tab for smoke testing");
		Test.log(LogStatus.INFO, "Smoke test for tenure tab is initiated");
		log.info("**********************Verifying the tenure tab**********************");
		emi.checkTenure();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid green'", emi.CarloanTenure);
		Test.log(LogStatus.PASS, "Tenure tab is verified");

	}

	@AfterMethod(alwaysRun=true, groups ="smoke")
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
	public void closeDriver_smokeTesting() 
	{
		log.info("**********************Closing browser**********************");
		driver.quit();
	}
}
