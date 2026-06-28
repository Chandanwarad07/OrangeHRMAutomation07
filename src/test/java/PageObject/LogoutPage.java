package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage {

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    WebElement profileIcon;

    @FindBy(linkText = "Logout")
    WebElement lnkLogout;

    @FindBy(name = "username")
    WebElement txtUsername;

    public void clickProfile() {
        profileIcon.click();
    }

    public void clickLogout() {
        lnkLogout.click();
    }

    public boolean isLoginPageDisplayed() {
        return txtUsername.isDisplayed();
    }
}