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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//a[text()='Employee List']")
    WebElement employeeListMenu;

    @FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
    WebElement txtEmployeeName;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSearch;

    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement btnConfirmDelete;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast')]")
    WebElement successToast;

    // Open Employee List
    public void clickEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeListMenu)).click();
    }

    // Search Employee
    public void searchEmployee(String empName) throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(txtEmployeeName));

        txtEmployeeName.clear();

        txtEmployeeName.sendKeys(empName);

        Thread.sleep(2000);

        txtEmployeeName.sendKeys(Keys.ARROW_DOWN);

        txtEmployeeName.sendKeys(Keys.ENTER);

        btnSearch.click();

        Thread.sleep(3000);

    }

    // Delete Employee
    public void deleteEmployee(String empName) {

        WebElement deleteButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//i[contains(@class,'bi-trash')])[1]")));

        deleteButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(btnConfirmDelete));

        btnConfirmDelete.click();

    }

    // Verify Success
    public boolean isEmployeeDisplayed() {

        WebElement table = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='oxd-table-body']")));

        return table.isDisplayed();
    }
    
    
    public boolean isDeleteSuccessful() {

        WebElement successToast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'oxd-toast')]")));

        return successToast.isDisplayed();
    }

}