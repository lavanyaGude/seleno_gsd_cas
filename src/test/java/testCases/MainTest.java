package testCases;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.BeCognizant;
import pageObjects.GSDHomePage;
import pageObjects.OneCognizant;


@Listeners(utilities.ExtentReportCreator.class)
public class MainTest extends BaseClassReusableComponents {

	
	@Test(priority = 1)
	public void beCognizantTest() throws InterruptedException, IOException {
		logger.info("***** starting MainTest *****");
		BeCognizant bc = new BeCognizant(driver);
		
		bc.openBeCognizant();
		logger.info("opening BeCognizant");
		
		bc.getUserData();
	
		captureScreen("img_userprofile",driver);
		
		logger.info("getting user information");
		
	
		
		
		
		bc.navigateToOneCognizant();
		logger.info("opening OneCognizant");
		captureScreen("img_onecognizant",driver);
	

	}

	@Test(priority = 2)
	public void oneCognizantTest() throws InterruptedException, IOException {

		logger.info("Navigated to OneCognizant");
		OneCognizant oc = new OneCognizant(driver);
				
		oc.goToGSD(super.browserType);
		logger.info("clicked on gsd from results");

	}

	@Test(priority = 3)
	public void GSDHomePageTest() throws InterruptedException, IOException {
		logger.info("Navigated to gsd");
		
		GSDHomePage hp = new GSDHomePage(driver);
		
		hp.switchToFrame();
		logger.info("switched to appframe");

		String def_lang=hp.validatingDefaultLanguage();
		Assert.assertEquals(def_lang, "English");
		System.out.println("Default language is Validated");
		logger.info("validating default language");
		
		String def_country=hp.validatingDefaultCountry();
		Assert.assertEquals(def_country, "India");
		System.out.println("Default Country is Validated");
		logger.info("Validating default country");
		
		captureScreen("img_defaultValues",driver);
		
		hp.getLanguages();
		captureScreen("img_allLanguages",driver);
		logger.info("getting all language options from language drop down box");
		
		String[] countriesArray = hp.getCountries();
		captureScreen("img_gsdPage",driver);
		logger.info("getting tooltips data from three random countries");
		
		for(int ite=0;ite<3;ite++) {
			System.out.println("*******************************************************************************");
			int ind=getRandomNumber(1, countriesArray.length-1);
			System.out.println("----------------------------"+countriesArray[ind]);
			hp.selectRandomCountry(countriesArray[ind]);
			List<String> hh=hp.getData();
			System.out.println("*******************************************************************************");
			ff.setData( "Sheet1", staticRow, 0,"Text",1);
			ff.setData( "Sheet1", staticRow, 1,"TOOL TIP",1);
			staticRow+=1;
			ff.setData( "Sheet1", staticRow, 0,countriesArray[ind],1);
			staticRow+=1;
			for(int temp=0;temp<hh.size()-1;temp+=2) {
				int manageTemp=temp+1;
				ff.setData( "Sheet1", staticRow, 0, hh.get(temp),0);
				ff.setData("Sheet1", staticRow, 1, hh.get(manageTemp),0);
				staticRow+=1;
				
			}
			captureScreen("img_gsdDetailsOfACountry",driver);
		}
		logger.info("*************closing the browser**********");
	}

}
