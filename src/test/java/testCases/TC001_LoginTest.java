package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.DashboardPage;
import PageObject.LoginPage;
import testBase.BaseClass;

public class TC001_LoginTest extends BaseClass {

    @Test
    public void verifyLogin() {

        LoginPage lp = new LoginPage(driver);

        lp.login(
                p.getProperty("username"),
                p.getProperty("password"));

        DashboardPage dp = new DashboardPage(driver);

        Assert.assertTrue(dp.isDashboardDisplayed());

        captureScreenshot("OrangeHRM_Login");

    }

}