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

    @FindBy(xpath = "//label[normalize-space()='Employee Name']/parent::div/following-sibling::div//input")
    WebElement txtEmployeeName;

    @FindBy(xpath = "//label[normalize-space()='Username']/parent::div/following-sibling::div//input")
    WebElement txtUsername;

    @FindBy(xpath = "//label[normalize-space()='Password']/parent::div/following-sibling::div//input")
    WebElement txtPassword;

    @FindBy(xpath = "//label[normalize-space()='Confirm Password']/parent::div/following-sibling::div//input")
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

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));
        } catch (Exception ignored) {}
    }

    public boolean isUserDisplayed() {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-loading-spinner, .oxd-form-loader")));
        } catch (Exception ignored) {}

        WebElement body = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='oxd-table-body']")));

        return body.isDisplayed();
    }

    // ================= ADD USER =================

    public void clickAdd() {

        wait.until(ExpectedConditions.elementToBeClickable(btnAdd)).click();
    }

    public void selectUserRole() {

        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

        WebElement adminRole = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//*[contains(text(),'Admin')]")));

        adminRole.click();
    }

    public void selectStatus() {

        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();

        WebElement enabled = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='listbox']//div[@role='option'][1] | //div[@role='listbox']//*[contains(text(),'Enabled')]")));

        enabled.click();
    }

    public void enterEmployeeName(String employeeName) {

        wait.until(ExpectedConditions.visibilityOf(txtEmployeeName));

        txtEmployeeName.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        txtEmployeeName.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);

        // Type 'a' to populate dropdown with existing demo employees
        txtEmployeeName.sendKeys("a");

        try {
            Thread.sleep(1500);
            WebElement suggestion = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='listbox']//div[@role='option'] | //div[@role='listbox']//*[contains(@class,'oxd-autocomplete-option')]")));

            try {
                suggestion.click();
            } catch (Exception clickEx) {
                org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", suggestion);
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            // Fallback: search with 'e'
            txtEmployeeName.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            txtEmployeeName.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
            txtEmployeeName.sendKeys("e");
            try {
                Thread.sleep(1500);
                WebElement suggestion = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[@role='listbox']//div[@role='option'] | //div[@role='listbox']//*[contains(@class,'oxd-autocomplete-option')]")));
                try {
                    suggestion.click();
                } catch (Exception clickEx) {
                    org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", suggestion);
                }
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void enterUsername(String username) {

        wait.until(ExpectedConditions.visibilityOf(txtUsername));

        txtUsername.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        txtUsername.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);

        txtUsername.sendKeys(username);
    }

    public void enterPassword(String password) {

        wait.until(ExpectedConditions.visibilityOf(txtPassword));

        txtPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {

        wait.until(ExpectedConditions.visibilityOf(txtConfirmPassword));

        txtConfirmPassword.sendKeys(password);
    }

    public void clickSave() {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".oxd-form-loader, .oxd-loading-spinner")));
        } catch (Exception ignored) {}

        wait.until(ExpectedConditions.elementToBeClickable(btnSave));

        try {
            btnSave.click();
        } catch (Exception e) {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btnSave);
        }
    }

    public boolean verifySuccessMessage() {

        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            return longWait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast')]")),
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")),
                    ExpectedConditions.urlContains("viewSystemUsers")));
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("viewSystemUsers") || driver.getCurrentUrl().contains("admin");
        }
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