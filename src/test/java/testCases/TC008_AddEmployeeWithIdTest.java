package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.AddEmployeePage;
import PageObject.DashboardPage;
import PageObject.EmployeeListPage;
import PageObject.LoginPage;
import PageObject.PIMPage;
import testBase.BaseClass;

public class TC008_AddEmployeeWithIdTest extends BaseClass {

    @Test
    public void verifyAddEmployeeWithNameAndId() throws Exception {

        LoginPage lp = new LoginPage(driver);
        lp.login(p.getProperty("username"), p.getProperty("password"));

        DashboardPage dp = new DashboardPage(driver);
        Assert.assertTrue(dp.isDashboardDisplayed());

        PIMPage pim = new PIMPage(driver);
        pim.clickPIM();
        pim.clickAddEmployee();

        AddEmployeePage addEmp = new AddEmployeePage(driver);

        String firstName = "Alex";
        String middleName = "Morgan";
        String lastName = "Smith";
        String employeeId = String.valueOf(System.currentTimeMillis() % 100000);

        String savedId = addEmp.addEmployee(firstName, middleName, lastName, employeeId);

        captureScreenshot("AddEmployeeWithNameAndId");

        // Verify newly created employee in the Employee List
        EmployeeListPage empList = new EmployeeListPage(driver);
        empList.clickEmployeeList();
        empList.searchByEmployeeId(savedId);

        Assert.assertTrue(empList.isEmployeeDisplayed(), "Employee should be found in Employee List");

        captureScreenshot("SearchEmployeeById");
    }
}
