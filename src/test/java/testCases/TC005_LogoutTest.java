package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.DashboardPage;
import PageObject.LoginPage;
import PageObject.LogoutPage;
import testBase.BaseClass;

public class TC005_LogoutTest extends BaseClass {

    @Test
    public void verifyLogout() {

        LoginPage lp = new LoginPage(driver);

        lp.login(
                p.getProperty("username"),
                p.getProperty("password"));

        DashboardPage dp = new DashboardPage(driver);

        Assert.assertTrue(dp.isDashboardDisplayed());

        LogoutPage logout = new LogoutPage(driver);

        logout.clickProfile();

        logout.clickLogout();

        Assert.assertTrue(logout.isLoginPageDisplayed());

        captureScreenshot("Logout");

    }
}