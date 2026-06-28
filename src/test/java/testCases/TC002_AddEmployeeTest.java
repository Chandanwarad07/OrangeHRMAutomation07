package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.AddEmployeePage;
import PageObject.DashboardPage;
import PageObject.LoginPage;
import PageObject.PIMPage;
import testBase.BaseClass;

public class TC002_AddEmployeeTest extends BaseClass {

    @Test
    public void verifyAddEmployee() {

        LoginPage lp = new LoginPage(driver);

        lp.login(
                p.getProperty("username"),
                p.getProperty("password"));

        DashboardPage dp = new DashboardPage(driver);

        Assert.assertTrue(dp.isDashboardDisplayed());

        PIMPage pim = new PIMPage(driver);

        pim.clickPIM();

        pim.clickAddEmployee();

        AddEmployeePage emp = new AddEmployeePage(driver);

        emp.enterFirstName("Chandan");

        emp.enterMiddleName("Kumar");

        emp.enterLastName("Warad");

        emp.clickSave();

        captureScreenshot("EmployeeAdded");

    }
}