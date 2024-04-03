package testCases;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import utilities.ExcelUtilis;

public class BaseClassReusableComponents {
	public static WebDriver driver;
	ExcelUtilis ff;
	public static XSSFWorkbook wbook;
	public static FileOutputStream fos;
	int staticRow=0;
	public static Logger logger;
	public String browserType;
	

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String br) throws IOException {

		browserType = br;

		// loading log4j file
		logger = LogManager.getLogger(this.getClass());

		// Launching browser based on condition
		switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("No matching browser");
			return;
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ff=new ExcelUtilis();
		wbook=new XSSFWorkbook();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String filetimestamp=sdf.format(timestamp)+String.valueOf(br.charAt(0));
		fos=new FileOutputStream(System.getProperty("user.dir")+"\\EXCELDATA\\"+filetimestamp+".xlsx");
	}

	@AfterTest
	public void quit() throws IOException {
		driver.quit();
		wbook.write(fos);
		wbook.close();
		fos.close();
	}


	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	public String captureScreen(String name, WebDriver d) 
	
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot)d;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	


}
