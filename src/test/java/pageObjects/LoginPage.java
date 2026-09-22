package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-name")
	WebElement txtUsername;
	
	@FindBy(id="password")
	WebElement txtPassword;
	
	@FindBy(id="login-button")
	WebElement btnLogin;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement btnMenu;
	
	@FindBy(id="logout_sidebar_link")
	WebElement btnLogout;
	
	public void setUsername(String username) {
		txtUsername.sendKeys(username);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void clickLogout() throws InterruptedException {
		btnMenu.click();
		Thread.sleep(1000);
		btnLogout.click();
	}
	

}
