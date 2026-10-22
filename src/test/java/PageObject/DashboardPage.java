package PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage {

    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(xpath = "//h6[normalize-space()='Dashboard']")
    private WebElement dashboardTitle;

    public boolean isDashboardDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(dashboardTitle)
        ).isDisplayed();
    }
}