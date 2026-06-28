package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
		
	}
	
	 @FindBy(name = "username")
	    WebElement txtUsername;

	    @FindBy(name = "password")
	    WebElement txtPassword;

	    @FindBy(xpath = "//button[@type='submit']")
	    WebElement btnLogin;

	    public void enterUsername(String username) {
	        txtUsername.clear();
	        txtUsername.sendKeys(username);
	    }

	    public void enterPassword(String password) {
	        txtPassword.clear();
	        txtPassword.sendKeys(password);
	    }

	    public void clickLogin() {
	        btnLogin.click();
	    }

	    public void login(String username,String password) {
	        enterUsername(username);
	        enterPassword(password);
	        clickLogin();
	    }

}
