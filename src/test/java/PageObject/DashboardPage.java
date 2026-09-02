package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    // Constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h6[text()='Dashboard']")
    WebElement dashboardTitle;

    public boolean isDashboardDisplayed() {
        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.xpath("//h6[text()='Dashboard'] | //h6[contains(.,'Dashboard')]"))).isDisplayed();
    }

}