package pageObjects;

import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BeCognizant extends BasePage {

	public BeCognizant(WebDriver driver) throws IOException {
		super(driver);
	}

	// locators
	@FindBy(css= "#O365_MainLink_Help > div")
	WebElement help_link;	
	@FindBy(xpath = "//button[@id='O365_MainLink_Me']")
	WebElement acc_manager;
	
	
	@FindBy(xpath = "//*[@id='mectrl_currentAccount_primary']")
	WebElement user_name;
	@FindBy(xpath = "//*[@id='mectrl_currentAccount_secondary']")
	WebElement user_mail;
	@FindBy(xpath = "//a[contains(@href,'onecognizant.cognizant.com/Home?GlobalAppId=926')]")
	WebElement one_cog;
    @FindBy(xpath="//div[@title='OneCognizant']")
    WebElement one_cog1;
    @FindBy(xpath="//div[normalize-space()='OneCognizant']//div[@id='QuicklinksItemTitle'")
    WebElement one_cog2;
    By microsoftDataFrame=By.xpath("//*[@id='o365shellwcssframe']");
  
	public void openBeCognizant() throws InterruptedException, IOException {
		WebDriverWait my = new WebDriverWait(driver, Duration.ofSeconds(6));
		driver.get(p.getProperty("BeCognizantURL"));
		try {
			if (driver.getTitle().equals("Sign in to your account")) {
				System.out.println("Authentication is required...");
				super.my.until(ExpectedConditions.titleIs("Be.Cognizant - Home"));
			}
		} catch (Exception signin) {
			System.out.println("OOPS!... Authentication is Unsuccessful...");
		}
		driver.manage().window().maximize();
		try {
			my.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='o365shellwcssframe']")));
		}catch(Exception o365) {
			System.out.println("Data from the Microsoft office is loading very slowly..");
			try {
				my.until(ExpectedConditions.visibilityOfElementLocated(microsoftDataFrame));
			}catch(Exception e) {
				
			}
		}
		

	}

	public void getUserData() throws InterruptedException {

		WebDriverWait my = new WebDriverWait(driver, Duration.ofSeconds(15));
		my.until(ExpectedConditions.visibilityOf(help_link));
		my.until(ExpectedConditions.visibilityOf(acc_manager)).click();

		String userName = my.until(ExpectedConditions.visibilityOf(user_name)).getText();
		String userMailId = my.until(ExpectedConditions.visibilityOf(user_mail)).getText();

		System.out.println("userName - " + userName);
		System.out.println("userMail - " + userMailId);

	}

	public void navigateToOneCognizant() throws InterruptedException {
		WebDriverWait my = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		WebElement scrollElement = driver.findElement(By.xpath("//*[@data-automation-id='contentScrollRegion']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[1];",scrollElement, 100);
		
		try {
			my.until(ExpectedConditions.visibilityOf(one_cog1)).click();
		}catch(Exception e) {
			try {
				my.until(ExpectedConditions.visibilityOf(one_cog2)).click();
			}catch(Exception ee) {
			}
		}
		
	

	}


}


