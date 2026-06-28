package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.DashboardPage;
import PageObject.EmployeeListPage;
import PageObject.LoginPage;
import PageObject.PIMPage;
import testBase.BaseClass;

public class TC003_SearchEmployeeTest extends BaseClass {

    @Test
    public void verifySearchEmployee() throws Exception {

        LoginPage lp = new LoginPage(driver);

        lp.login(
                p.getProperty("username"),
                p.getProperty("password"));

        DashboardPage dp = new DashboardPage(driver);

        Assert.assertTrue(dp.isDashboardDisplayed());

        PIMPage pim = new PIMPage(driver);

        pim.clickPIM();

        EmployeeListPage emp = new EmployeeListPage(driver);

        emp.clickEmployeeList();

        // Search Employee
        emp.searchEmployee("Chandan Kumar");

        // Verify Search Result
        Assert.assertTrue(emp.isEmployeeDisplayed());

        captureScreenshot("SearchEmployee");
    }
}