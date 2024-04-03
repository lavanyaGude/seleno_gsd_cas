package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class GSDHomePage extends BasePage {
	

	WebDriverWait my;
	
	public GSDHomePage(WebDriver driver) throws IOException {
		super(driver);
		my=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	

	@FindBy(css="div.application-tiles") List<WebElement> textDiv;
	@FindBy(css="input.hdnApplicationID")List<WebElement> textData;
	
	String frameId="appFrame";
	
	@FindBy(xpath="/html/body/nav[1]/div/form/div[1]/ul") List<WebElement> allLanguageOptions;
	@FindBy(xpath="//form[@class='d-flex ms-auto']//a[@id='menu1']") WebElement languageDropdown;
	@FindBy(xpath="/html/body/nav[1]/div/form/div[1]/a/span") WebElement defaultLanguage;
	@FindBy(xpath="/html/body/nav[1]/div/form/div[2]/a/span") WebElement defaultCountry;
	
	@FindBy(xpath="//form[@class='d-flex ms-auto']//a[@id='menu2']") WebElement countryDropdown;
	@FindBy(xpath="/html[1]/body[1]/nav[1]/div[1]/form[1]/div[2]/ul[1]/li") List<WebElement> allCountries;
	@FindBy(xpath="//*[@id=\"ui-view\"]/div/div[3]/p[1]") WebElement welcome_msg;
	


	
	public void switchToFrame() {
		my.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
	}
	
	public String validatingDefaultLanguage() {
		String act_dl=defaultLanguage.getText();
		return act_dl;
	}
	public String validatingDefaultCountry() {
		String act_dc=defaultCountry.getText();
		return act_dc;
	}
	
	public void getLanguages() {
		languageDropdown.click();
		List<WebElement> options=allLanguageOptions;
		for(int i=0;i<options.size();i++) {
			System.out.println(options.get(i).getText());
		}
		
		
	}
	
	public List<String> getData() throws IOException{

		
		my.until(ExpectedConditions.visibilityOf(welcome_msg));
		
		List<WebElement> toolTipsElements=textDiv;
		List<String> textFollowedByTooltip=new ArrayList<String>();
		try { if(welcome_msg.isDisplayed()) {
			System.out.println( welcome_msg.getText());
		}}
		catch(Exception ee) {	
		}
		
		for(int i=0;i< toolTipsElements.size();i++ ) {
			
			String toolTip=toolTipsElements.get(i).getAttribute("data-bs-original-title");
			String innerText= toolTipsElements.get(i).getText();
			textFollowedByTooltip.add(innerText);
			textFollowedByTooltip.add(toolTip);
			System.out.println("Inner Text - "+innerText);
			System.out.println("TOOLTIP - "+toolTip);
		}
		return textFollowedByTooltip;
	}
	
	public String[] getCountries() {
		countryDropdown.click();
		List<WebElement> countries = allCountries;
		String[] allCountries = new String[countries.size()];
		for(int i = 0; i < countries.size(); i++) {
			String ans = countries.get(i).getText();
			allCountries[i] = ans;
		}
		return allCountries;
	}


	public void selectRandomCountry(String name) throws InterruptedException, IOException {
		countryDropdown.click();
		List<WebElement> countries = allCountries;
		for(int i = 1; i < countries.size(); i++) {
			if(countries.get(i).getText().equalsIgnoreCase(name)) {
				my.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,\"Search Countries\")]"))).sendKeys(name);
				my.until(ExpectedConditions.visibilityOf(countries.get(i)));
				countries.get(i).click();
			}
		}
	}
}


