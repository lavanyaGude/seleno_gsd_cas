package pageObjects;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
/*
 * 	This class have PageFactory class object and Every class in PageObects package should 
 * 	Inherit this "BASEPAGE" So that those classes can use @FindBy annotation to find webElements
 * 	and
 * 	Every class should pass driver object in constructor using super(driver)
 */
public class BasePage {
	WebDriver driver;
	WebDriverWait my;
	public Properties p;
	

	public BasePage(WebDriver driver) throws IOException {
		this.driver=driver;
		my=new WebDriverWait(driver,Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
		//Loading property file
		FileReader file=new FileReader(".//src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
	}
}
