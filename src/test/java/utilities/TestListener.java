package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("reports/ExtentReport.html");

            spark.config().setReportName("OrangeHRM Automation Report");
            spark.config().setDocumentTitle("Automation Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Chandan Kumar");
            extent.setSystemInfo("Project", "OrangeHRM Automation");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}