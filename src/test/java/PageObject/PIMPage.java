package PageObject;

import java.time.Duration;

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

    @FindBy(xpath = "//span[normalize-space()='PIM']")
    private WebElement menuPIM;

    @FindBy(xpath = "//a[normalize-space()='Add Employee']")
    private WebElement addEmployee;

    public void clickPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(menuPIM)).click();
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployee)).click();
    }
}