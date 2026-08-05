package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class TC_LoginTest_01 extends BaseClass {


	@Test
	public void loginTest() throws IOException {
		
		logger.info("url is opened");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		logger.info("username entered");
		
		lp.setPassword(password);
		logger.info("password entered");
		
		lp.clickLogin();
		
		if(driver.getTitle().equals("Swag Labs")) {
			Assert.assertTrue(true);
			logger.info("login test passed");
		}
		else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("login test failed");
		}
	}

}
