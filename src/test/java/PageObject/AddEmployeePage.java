package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/input")
    WebElement txtEmployeeId;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSave;

    public void enterFirstName(String fname) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(txtFirstName));
        txtFirstName.sendKeys(Keys.CONTROL + "a");
        txtFirstName.sendKeys(Keys.BACK_SPACE);
        txtFirstName.sendKeys(fname);
    }

    public void enterMiddleName(String mname) {
        txtMiddleName.sendKeys(Keys.CONTROL + "a");
        txtMiddleName.sendKeys(Keys.BACK_SPACE);
        txtMiddleName.sendKeys(mname);
    }

    public void enterLastName(String lname) {
        txtLastName.sendKeys(Keys.CONTROL + "a");
        txtLastName.sendKeys(Keys.BACK_SPACE);
        txtLastName.sendKeys(lname);
    }

    public void enterEmployeeId(String empId) {
        txtEmployeeId.sendKeys(Keys.CONTROL + "a");
        txtEmployeeId.sendKeys(Keys.BACK_SPACE);
        txtEmployeeId.sendKeys(empId);
    }

    public String getEmployeeId() {
        return txtEmployeeId.getAttribute("value");
    }

    public String addEmployee(String fname, String mname, String lname, String empId) {
        enterFirstName(fname);
        enterMiddleName(mname);
        enterLastName(lname);
        if (empId != null && !empId.isEmpty()) {
            enterEmployeeId(empId);
        }
        String savedId = getEmployeeId();
        clickSave();
        return savedId;
    }

    public void clickSave() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for OrangeHRM loading overlay to disappear
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-form-loader")));
        } catch (Exception ignored) {}

        // Wait until Save button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(btnSave));

        try {
            btnSave.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btnSave);
        }

        // Wait for save to complete
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast')]")),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Personal Details']")),
                    ExpectedConditions.urlContains("viewPersonalDetails")));
        } catch (Exception ignored) {}
    }

    public boolean isEmployeeAdded() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Personal Details']")),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast')]")),
                    ExpectedConditions.urlContains("viewPersonalDetails")
            ));
        } catch (Exception e) {
            return false;
        }
    }
}
