package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.AdminPage;
import PageObject.DashboardPage;
import PageObject.LoginPage;
import testBase.BaseClass;

public class TC006_SearchUserTest extends BaseClass {

    @Test
    public void verifySearchUser() {

        LoginPage lp = new LoginPage(driver);

        lp.login(
                p.getProperty("username"),
                p.getProperty("password"));

        DashboardPage dp = new DashboardPage(driver);

        Assert.assertTrue(dp.isDashboardDisplayed());

        AdminPage admin = new AdminPage(driver);

        admin.clickAdmin();

        admin.enterSearchUsername("Admin");

        admin.clickSearch();

        Assert.assertTrue(admin.isUserDisplayed());

        captureScreenshot("SearchUser");
    }
}
