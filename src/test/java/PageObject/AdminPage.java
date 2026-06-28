package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage extends BasePage {

    WebDriverWait wait;

    public AdminPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================= MENU =================

    @FindBy(xpath = "//span[text()='Admin']")
    WebElement adminMenu;

    // ================= SEARCH USER =================

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement txtSearchUsername;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSearch;

    @FindBy(xpath = "//div[@class='oxd-table-body']")
    WebElement searchResult;

    // ================= ADD USER =================

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[1]")
    WebElement userRoleDropdown;

    @FindBy(xpath = "//span[text()='Admin']")
    WebElement optionAdmin;

    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[2]")
    WebElement statusDropdown;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement txtEmployeeName;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement txtUsername;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement txtPassword;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSave;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast')]")
    WebElement successToast;

    // ================= EDIT USER =================

    @FindBy(xpath = "(//button[contains(@class,'oxd-icon-button')])[2]")
    WebElement btnEdit;

    @FindBy(xpath = "//span[text()='Disabled']")
    WebElement optionDisabled;

    // ================= COMMON METHODS =================

    public void clickAdmin() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    // ================= SEARCH USER =================

    public void enterSearchUsername(String username) {

        wait.until(ExpectedConditions.visibilityOf(txtSearchUsername));

        txtSearchUsername.clear();

        txtSearchUsername.sendKeys(username);
    }

    public void clickSearch() {

        wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
    }

    public boolean isUserDisplayed() {

        wait.until(ExpectedConditions.visibilityOf(searchResult));

        return searchResult.isDisplayed();
    }

    // ================= ADD USER =================

    public void clickAdd() {

        wait.until(ExpectedConditions.elementToBeClickable(btnAdd)).click();
    }

    public void selectUserRole() {

        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

        wait.until(ExpectedConditions.elementToBeClickable(optionAdmin)).click();
    }

    public void selectStatus() {

        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();

        WebElement enabled = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='listbox']//div[@role='option'][1]")));

        enabled.click();
    }

    public void enterEmployeeName(String employeeName) {

        wait.until(ExpectedConditions.visibilityOf(txtEmployeeName));

        txtEmployeeName.clear();

        txtEmployeeName.sendKeys(employeeName);

        WebElement suggestion = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='listbox']//span")));

        suggestion.click();
    }

    public void enterUsername(String username) {

        txtUsername.clear();

        txtUsername.sendKeys(username);
    }

    public void enterPassword(String password) {

        txtPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {

        txtConfirmPassword.sendKeys(password);
    }

    public void clickSave() {

        wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
    }

    public boolean verifySuccessMessage() {

        wait.until(ExpectedConditions.visibilityOf(successToast));

        return successToast.isDisplayed();
    }

    // ================= EDIT USER =================

    public void clickEdit() {

        wait.until(ExpectedConditions.elementToBeClickable(btnEdit)).click();
    }

    public void selectDisabledStatus() {

        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();

        wait.until(ExpectedConditions.elementToBeClickable(optionDisabled)).click();
    }

    public void clickSaveButton() {

        wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
    }

    public boolean verifyUpdateSuccess() {

        wait.until(ExpectedConditions.visibilityOf(successToast));

        return successToast.isDisplayed();
    }

}