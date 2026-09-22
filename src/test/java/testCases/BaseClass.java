package testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ReadConfig;

public class BaseClass {
	
	public static WebDriver driver;
	
	ReadConfig readConfig = new ReadConfig();
	public String url = readConfig.getURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	
	public static Logger logger = LogManager.getLogger(BaseClass.class);
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		
		if(br.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			
			Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);			driver = new ChromeDriver(options);
		}
		else if(br.equals("edge")) {
			driver = new EdgeDriver();
		}
		else if(br.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Incorrect browser name");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+ "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
				
	}

}
