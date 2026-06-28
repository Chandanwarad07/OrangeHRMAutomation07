package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.DashboardPage;
import PageObject.EmployeeListPage;
import PageObject.LoginPage;
import PageObject.PIMPage;
import testBase.BaseClass;

public class TC004_DeleteEmployeeTest extends BaseClass {

    @Test
    public void verifyDeleteEmployee() throws Exception {

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

        emp.searchEmployee("Chandan Kumar");

        emp.deleteEmployee("Chandan Kumar");

        Assert.assertTrue(emp.isDeleteSuccessful());

        captureScreenshot("DeleteEmployee");

    }

}