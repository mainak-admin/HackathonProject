package emicalculator.net;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import emicalculatorExcel.WriteExcel;
import io.qameta.allure.Step;

public class AutomateEmiCalculator					 
{
	WebDriver driver;
	
	public AutomateEmiCalculator(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locate the car loan tab
	@FindBy(xpath = "//*[text()='Car Loan']")
	public WebElement CarLoan; 
	
	//Locate loan amount field
	@FindBy(id = "loanamount")
	public WebElement CarloanAmount;
	
	//Locate rate of interest field
	@FindBy(id = "loaninterest")
	public WebElement CarloanInterest;
	
	//Locate the field for loan tenure 
	@FindBy(id = "loanterm")
	public WebElement CarloanTenure;
	
	//Locate the expand button
	@FindBy(id = "year2020")
	public WebElement expand;
	
	//Locate the month July
	@FindBy(xpath = "//*[@id='monthyear2020']//table//tbody//tr[1]//td[1]")
	public WebElement month;
	
	//Locate the principal amount for the month july
	@FindBy(xpath = "//*[@id='monthyear2020']//table//tbody//tr[1]//td[2]")
	public WebElement principalAmount;
	
	//Locate the interest amount for the month july
	@FindBy(xpath = "//*[@id='monthyear2020']//table//tbody//tr[1]//td[3]")
	public WebElement interestAmount;
	
	
	@Step("Click on the car loan tab")
	public void selectCarLoan() throws Exception
	{
		CarLoan.click();
		Thread.sleep(3000);

	}

	@Step("To enter data in the LoanAmount field")
	public void carloanAmount(String loanAmount) throws Exception
	{
		CarloanAmount.sendKeys(Keys.CONTROL,"a");
		CarloanAmount.sendKeys(Keys.DELETE);
		CarloanAmount.sendKeys(loanAmount);
		Thread.sleep(3000);
	}

	@Step("To enter data in the interest field")
	public void interestRate(String interest) throws Exception
	{
		CarloanInterest.sendKeys(Keys.CONTROL,"a");
		CarloanInterest.sendKeys(Keys.DELETE);
		CarloanInterest.sendKeys(interest);
		Thread.sleep(3000);

	}

	@Step("To enter data in the tenure field")
	public void tenure(String timeperiod) throws Exception
	{
		CarloanTenure.sendKeys(Keys.CONTROL,"a");
		CarloanTenure.sendKeys(Keys.DELETE);
		CarloanTenure.sendKeys(timeperiod);
		Thread.sleep(3000);
	}

	@Step("To click on the expand button")
	public void expand2020() throws Exception
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", expand);
		js.executeScript("arguments[0].click();", expand);
		Thread.sleep(3000);

	}

	@Step("To take a screenshot")
	public void screenshot(WebDriver driver) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ScrObj = (TakesScreenshot) driver;
		File CaptureImg = ScrObj.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(CaptureImg, new File("./ErrorImages/"+timeStamp+"_error.jpg"));

	}
	
	@Step("Write data in an excel sheet")
	public void getData() throws IOException
	{

		String monthof2020 = month.getText();
		String principalAmountofjuly = principalAmount.getText();
		String interestAmountofJuly = interestAmount.getText();

		WriteExcel write = new WriteExcel();
		write.getValues(monthof2020, principalAmountofjuly, interestAmountofJuly);

	}
}
