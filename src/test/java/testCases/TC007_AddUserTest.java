package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.AdminPage;
import PageObject.DashboardPage;
import PageObject.LoginPage;
import testBase.BaseClass;

public class TC007_AddUserTest extends BaseClass {

    @Test
    public void verifyAddUser() {

        // Login
        LoginPage lp = new LoginPage(driver);
        lp.login(p.getProperty("username"), p.getProperty("password"));

        // Verify Dashboard
        DashboardPage dp = new DashboardPage(driver);
        Assert.assertTrue(dp.isDashboardDisplayed());

        // Admin Page
        AdminPage admin = new AdminPage(driver);

        admin.clickAdmin();

        admin.clickAdd();

        admin.selectUserRole();

        admin.selectStatus();

        admin.enterEmployeeName("Linda Anderson");

        String user = "Admin" + System.currentTimeMillis();

        admin.enterUsername(user);

        admin.enterPassword("Admin@123");

        admin.enterConfirmPassword("Admin@123");

        admin.clickSave();

        Assert.assertTrue(admin.verifySuccessMessage());

        captureScreenshot("AddUser");
    }
}