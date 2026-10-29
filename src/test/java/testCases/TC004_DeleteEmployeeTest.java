package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.AddEmployeePage;
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

        // 1. Create a dedicated employee with a unique ID
        pim.clickAddEmployee();
        AddEmployeePage addEmp = new AddEmployeePage(driver);
        String firstName = "TempDel" + (System.currentTimeMillis() % 1000);
        String lastName = "User";
        String uniqueEmpId = String.valueOf(System.currentTimeMillis() % 100000);
        String savedEmpId = addEmp.addEmployee(firstName, "", lastName, uniqueEmpId);

        // 2. Search for the employee by ID
        EmployeeListPage emp = new EmployeeListPage(driver);

        emp.clickEmployeeList();
        Thread.sleep(1500);

        emp.searchByEmployeeId(savedEmpId);
        Thread.sleep(1500);

        // 3. Delete the employee
        emp.deleteEmployee(firstName);

        Assert.assertTrue(emp.isDeleteSuccessful());

        captureScreenshot("DeleteEmployee");
    }
}
