package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utilities.XLUtils;

public class TC_LoginDDT_02 extends BaseClass {
	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String username, String password) throws InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		logger.info("username entered");
		lp.setPassword(password);
		logger.info("password entered");
		lp.clickLogin();
		logger.info("loggin button clicked");
		
		Thread.sleep(2000);
		
		String current_url = driver.getCurrentUrl();
		System.out.println("URL is: "+ current_url);
		
		if(current_url.equals("https://www.saucedemo.com/inventory.html")) {
			Assert.assertTrue(true, "Login passed");
			logger.info("login passed for username: "+username);
			lp.clickLogout();
			logger.info("logout button clicked");
			Thread.sleep(2000);
		}
		else {
			logger.info("login failed for username: "+username);
			driver.navigate().refresh();
			Assert.assertTrue(false, "Login failed");
			}
		
	}
	
	@DataProvider(name = "LoginData")
	public String [][] getData() throws IOException{
		
		String path = System.getProperty("user.dir")+"/src/test/java/testData/SwagLabs_LoginData.xlsx";
		int rowcount = XLUtils.getRowcount(path, "Sheet1");
		int colcount = XLUtils.getCellcount(path, "Sheet1", rowcount);
		
		String loginData[][] = new String[rowcount][colcount];
		 
		 for(int i=1; i<=rowcount; i++) {
			 
			 for(int j=0; j<colcount; j++) {
				 
				 loginData[i-1][j] = XLUtils.getCelldata(path, "Sheet1", i, j);
			 }
		 }
		 return loginData;
	}

}
