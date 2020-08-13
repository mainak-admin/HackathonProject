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
import emicalculatorExcel.ReadExcelNegative;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class NegativeTestClass 
{
	WebDriver driver;
	Logger log = Logger.getLogger(NegativeTestClass.class);
	static AutomateEmiCalculator emi;
	static ReadExcelNegative negativeRead;
	ExtentReports extent;
	ExtentTest Test;

	@BeforeTest(alwaysRun = true)
	public void setup_NegativeTest() throws Exception
	{
		DriverSetup drv = new DriverSetup();
		driver = drv.getDriver();
		log.info("**********************Logging into browser**********************");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		emi = new AutomateEmiCalculator(driver);
		negativeRead = new ReadExcelNegative();
	}

	@Test(priority = 0)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify webpage page title")
	public void getTitle_NegativeTest() 
	{
		extent = new ExtentReports("C:\\Users\\user\\eclipse-Mainak\\emicalculator.net\\target\\surefire-reports\\Extent_NegativeReport.html", true);
		Test = extent.startTest("Verify webpage title for negative testing");
		Test.log(LogStatus.INFO, "Negative test for title verification initiated");
		log.info("**********************Starting test cases**********************");
		log.info("****************Fetching the page title for negative testing****************");
		String title = driver.getTitle();
		Assert.assertEquals(title, "EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
		Test.log(LogStatus.PASS, "The title of webpage is verified");
	}

	@Test(priority = 1)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify car loan button for negative testing")
	public void verifybutton_NegativeTest() throws Exception
	{
		Test = extent.startTest("Verify car loan button for negative testing");
		Test.log(LogStatus.INFO, "Negative test for button verification initiated");
		log.info("**********************Selecting car loan tab**********************");
		emi.checkLoanTab();
		log.info("******************Car loan button is verified**********************");
		Test.log(LogStatus.PASS, "The car loan button is verified");
	}

	@Test(priority = 2)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Select the type of loan --> Home/Personal/Car")
	public void selectLoanType_NegativeTest() throws Exception
	{
		Test = extent.startTest("Select type of loan as car loan for negative testing");
		Test.log(LogStatus.INFO, "Negative test for car loan selection is initiated");
		log.info("**********************Car loan tab is selected**********************");
		emi.selectCarLoan();
		Test.log(LogStatus.PASS, "The car loan button is selected");
	}

	@Test(priority = 3)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: To enter the car loan amount from excel sheet for negative testing")
	public void selectamount_NegativeTest() throws Exception
	{
		Test = extent.startTest("Enter car loan amount for negative testing");
		Test.log(LogStatus.INFO, "Negative test for car loan amount is initiated");
		log.info("**********************Entering loan amount**********************");
		emi.carloanAmount(negativeRead.readExcelNegative()[0]);
		Test.log(LogStatus.PASS, "The car loan amount is entered");
		log.info("**********************Car loan amount is set**********************");
	}

	@Test(priority = 4)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: To enter the rate of interest from the excel sheet for negative testing")
	public void selectInterestRate_NegativeTest() throws Exception
	{
		Test = extent.startTest("Enter rate of interest for negative testing");
		Test.log(LogStatus.INFO, "Negative test for interest rate is initiated");
		log.info("**********************Entering rate of interest**********************");
		log.info("**********************Rate of interest is set**********************");
		emi.interestRate_negativeTest(negativeRead.readExcelNegative()[1]);
		log.info("**********************Checking the value of interest field**********************");
		String currValue = emi.CarloanInterest.getAttribute("currValue");
		Assert.assertEquals(currValue, "0");
		Test.log(LogStatus.PASS, "The rate of interest has been entered");
		log.info("**********************An error has occured**********************");
	}


	@Test(priority = 6)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: To take a screenshot for negative testing")
	public void screenshot_NegativeTest() throws Exception
	{
		Test = extent.startTest("Preparing to take screenshot");
		Test.log(LogStatus.INFO, "Negative test for screenshot is generated");
		log.info("**********************Taking screenshot**********************");
		String imagePath = emi.negativeScreenshot(driver);
		log.info("Screenshot for negative test is complete");
		Test.log(LogStatus.PASS, Test.addScreenCapture(imagePath));
	}

	@Test(priority = 5)
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: To enter the time period for negative testing")
	public void selectTenure_NegativeTest() throws Exception
	{
		Test = extent.startTest("Enter tenure for negative testing");
		Test.log(LogStatus.INFO, "Negative test for tenure is initiated");
		log.info("**********************Entering the time period**********************");
		emi.tenure(negativeRead.readExcelNegative()[2]);
		Test.log(LogStatus.PASS, "The tenure has been entered");
	}

	@AfterMethod(alwaysRun=true)
	public void flush(ITestResult result) throws InterruptedException {

		if(result.getStatus()==ITestResult.FAILURE) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", emi.CarloanInterest);
			js.executeScript("alert('Loan Interest field is not set to 0')");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
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
	public void closeDriver_NegativeTest() 
	{
		log.info("**********************Closing browser**********************");
		driver.quit();
	}
}

