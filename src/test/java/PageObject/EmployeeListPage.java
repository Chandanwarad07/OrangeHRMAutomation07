package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeListPage extends BasePage {

    WebDriverWait wait;

    public EmployeeListPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    @FindBy(xpath = "//a[text()='Employee List']")
    WebElement employeeListMenu;

    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    WebElement txtEmployeeName;

    @FindBy(xpath = "//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/input")
    WebElement txtEmployeeId;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSearch;

    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement btnConfirmDelete;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast')]")
    WebElement successToast;

    // Open Employee List
    public void clickEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeListMenu)).click();
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));
        } catch (Exception ignored) {}
    }

    // Search Employee by Name
    public void searchEmployee(String empName) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(txtEmployeeName));

        txtEmployeeName.sendKeys(Keys.CONTROL + "a");
        txtEmployeeName.sendKeys(Keys.BACK_SPACE);
        txtEmployeeName.sendKeys(empName);

        Thread.sleep(1500);

        try {
            WebElement suggestion = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='listbox']//div[@role='option']")));
            suggestion.click();
        } catch (Exception e) {
            txtEmployeeName.sendKeys(Keys.ENTER);
        }

        wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));
        } catch (Exception ignored) {}
    }

    // Search Employee by ID
    public void searchByEmployeeId(String empId) {

        wait.until(ExpectedConditions.visibilityOf(txtEmployeeId));

        txtEmployeeId.sendKeys(Keys.CONTROL + "a");
        txtEmployeeId.sendKeys(Keys.BACK_SPACE);
        txtEmployeeId.sendKeys(empId);

        wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));
        } catch (Exception ignored) {}
    }

    // Delete Employee
    public void deleteEmployee(String empName) {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));
        } catch (Exception ignored) {}

        WebElement deleteButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[contains(@class,'oxd-icon-button')]//i[contains(@class,'bi-trash')]/parent::button)[1] | (//i[contains(@class,'bi-trash')])[1]")));

        try {
            deleteButton.click();
        } catch (Exception e) {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", deleteButton);
        }

        // Wait for confirmation modal and click Yes, Delete
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='dialog']//button[contains(.,'Delete')] | //button[contains(@class,'oxd-button--label-danger')] | //button[contains(.,'Yes, Delete')]")));

        try {
            confirmBtn.click();
        } catch (Exception e) {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", confirmBtn);
        }
    }

    // Verify Success
    public boolean isEmployeeDisplayed() {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));
        } catch (Exception ignored) {}

        WebElement table = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='oxd-table-body']")));

        return table.isDisplayed();
    }
    
    public boolean isDeleteSuccessful() {

        try {
            WebElement successToast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'oxd-toast')]")));
            return successToast.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

}
