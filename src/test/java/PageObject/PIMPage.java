package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PIMPage extends BasePage {

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='PIM']")
    WebElement menuPIM;

    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement addEmployee;

    public void clickPIM() {
        menuPIM.click();
    }

    public void clickAddEmployee() {
        addEmployee.click();
    }
}