package pageObjects;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OneCognizant extends BasePage {

	public OneCognizant(WebDriver driver) throws IOException {
		super(driver);
	}

	@FindBy(xpath = "//input[contains(@placeholder,'Search this site...')]")
	WebElement inputSearchBoxInChrome;
	@FindBy(xpath = "//div[@class='appsResultText']")
	WebElement gsdResult;

	@FindBy(xpath = "//*[@id=\"DesktopPlatformBar\"]/nav[1]/div/ul[2]/li")
	WebElement inputSearchIconInEdge;
	@FindBy(xpath = "//*[@id=\"oneCSearchTop\"]")
	WebElement inputSearchBoxInEdge;

	public void goToGSD(String browserType) throws InterruptedException, IOException {

		WebDriverWait my = new WebDriverWait(driver, Duration.ofSeconds(60));

		try {
			Set<String> ids = driver.getWindowHandles();
			List<String> lis = new ArrayList<String>(ids);
			String parent = lis.get(0);
			String child = lis.get(1);
			driver.switchTo().window(child);
			try {
				if (my.until(ExpectedConditions.titleContains("OneCognizant")) == false) {
					Exception redirect = new Exception();
					throw redirect;
				}

				System.out.println("redirecting properly");
			} catch (Exception redirect) {
			System.out.println("redirect Exception");
			driver.navigate().refresh();
			}
		    if (driver.getTitle().contains("Redirecting") || driver.getTitle().contains("Working")
					|| driver.getTitle().contains("Connect")) {
		    	System.out.println("directly enterd into if condition instead of try catch");
		    	my.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.oneC_brandName")));
		    }
		} catch (Exception inOneCognizant) {
		}
		if (browserType.equalsIgnoreCase("chrome")) {

			inputSearchBoxInChrome.sendKeys(p.getProperty("inputdata"));
		} else if (browserType.equalsIgnoreCase("edge")) {
			my.until(ExpectedConditions.visibilityOf(inputSearchIconInEdge)).click();

			inputSearchBoxInEdge.sendKeys(p.getProperty("inputdata"));
		}
		my.until(ExpectedConditions.visibilityOf(gsdResult)).click();
	}

}
