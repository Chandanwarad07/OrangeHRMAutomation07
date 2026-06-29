package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeePage extends BasePage {

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "firstName")
    WebElement txtFirstName;

    @FindBy(name = "middleName")
    WebElement txtMiddleName;

    @FindBy(name = "lastName")
    WebElement txtLastName;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSave;

    public void enterFirstName(String fname) {
        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void enterMiddleName(String mname) {
        txtMiddleName.clear();
        txtMiddleName.sendKeys(mname);
    }

    public void enterLastName(String lname) {
        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void clickSave() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for OrangeHRM loading overlay to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".oxd-form-loader")));

        // Wait until Save button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(btnSave));

        try {
            btnSave.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btnSave);
        }
    }
}