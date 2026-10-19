package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(name = "username")
    private WebElement txtUsername;

    @FindBy(name = "password")
    private WebElement txtPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnLogin;

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(txtUsername)).clear();
        txtUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(txtPassword)).clear();
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[normalize-space()='Dashboard']")
        ));
    }
}
