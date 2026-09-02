package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.AdminPage;
import PageObject.DashboardPage;
import PageObject.EmployeeListPage;
import PageObject.LoginPage;
import PageObject.PIMPage;
import testBase.BaseClass;
import utilities.PerformanceUtility;

public class TC009_PagePerformanceTest extends BaseClass {

    private static final long MAX_ALLOWED_LOAD_TIME_MS = 6000;

    @Test
    public void verifyApplicationPerformanceMetrics() {

        PerformanceUtility perf = new PerformanceUtility(driver);

        // 1. Measure Login Page Performance
        long loginLoadTime = perf.getPageLoadTimeMs();
        perf.printPerformanceMetrics("OrangeHRM Login Page");
        Assert.assertTrue(loginLoadTime < MAX_ALLOWED_LOAD_TIME_MS,
                "Login Page load time (" + loginLoadTime + " ms) exceeded SLA threshold!");

        // 2. Login & Measure Dashboard Performance
        LoginPage lp = new LoginPage(driver);
        lp.login(p.getProperty("username"), p.getProperty("password"));

        DashboardPage dp = new DashboardPage(driver);
        Assert.assertTrue(dp.isDashboardDisplayed());

        long dashboardLoadTime = perf.getPageLoadTimeMs();
        perf.printPerformanceMetrics("OrangeHRM Dashboard");

        // 3. Measure PIM Module Performance
        PIMPage pim = new PIMPage(driver);
        pim.clickPIM();

        EmployeeListPage empList = new EmployeeListPage(driver);
        empList.clickEmployeeList();

        long pimLoadTime = perf.getPageLoadTimeMs();
        perf.printPerformanceMetrics("OrangeHRM PIM Employee List");

        // 4. Measure Admin Module Performance
        AdminPage admin = new AdminPage(driver);
        admin.clickAdmin();

        long adminLoadTime = perf.getPageLoadTimeMs();
        perf.printPerformanceMetrics("OrangeHRM Admin System Users");

        captureScreenshot("PerformanceMetrics_Validated");
    }
}
