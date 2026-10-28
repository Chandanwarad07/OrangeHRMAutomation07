package PageObject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage extends BasePage {

    private WebDriverWait wait;

    public PIMPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//a[contains(@href,'viewPimModule')] | //span[normalize-space()='PIM']")
    private WebElement menuPIM;

    @FindBy(xpath = "//a[normalize-space()='Add Employee'] | //button[normalize-space()='Add']")
    private WebElement addEmployee;

    public void clickPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(menuPIM));
        try {
            menuPIM.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", menuPIM);
        }
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployee));
        try {
            addEmployee.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", addEmployee);
        }
    }
}
