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

        // 1. Create a dedicated employee with auto-generated ID
        pim.clickAddEmployee();
        AddEmployeePage addEmp = new AddEmployeePage(driver);
        String firstName = "TempDel" + (System.currentTimeMillis() % 1000);
        String lastName = "User";
        String savedEmpId = addEmp.addEmployee(firstName, "", lastName, "");

        // 2. Search for the employee by ID
        EmployeeListPage emp = new EmployeeListPage(driver);

        emp.clickEmployeeList();

        emp.searchByEmployeeId(savedEmpId);

        // 3. Delete the employee
        emp.deleteEmployee(firstName);

        Assert.assertTrue(emp.isDeleteSuccessful());

        captureScreenshot("DeleteEmployee");

    }

}