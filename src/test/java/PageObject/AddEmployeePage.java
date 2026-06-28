package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        txtFirstName.sendKeys(fname);
    }

    public void enterMiddleName(String mname) {
        txtMiddleName.sendKeys(mname);
    }

    public void enterLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void clickSave() {
        btnSave.click();
    }
}