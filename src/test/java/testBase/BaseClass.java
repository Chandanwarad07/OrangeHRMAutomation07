package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public WebDriver driver;
    public Properties p;

    @BeforeClass
    @Parameters("browser")
    public void setup(@org.testng.annotations.Optional("chrome") String browser) throws IOException {

        p = new Properties();

        FileInputStream file =
                new FileInputStream("src/test/resources/config.properties");

        p.load(file);
        file.close();

        // Browser selection
        String headless = System.getProperty("headless", p.getProperty("headless", "false"));
        boolean isCI = System.getenv("JENKINS_HOME") != null || System.getenv("CI") != null;

        if (browser.equalsIgnoreCase("chrome")) {

            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");

            if ("true".equalsIgnoreCase(headless) || isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {

            io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
            org.openqa.selenium.edge.EdgeOptions options = new org.openqa.selenium.edge.EdgeOptions();

            if ("true".equalsIgnoreCase(headless) || isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            driver = new EdgeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
            org.openqa.selenium.firefox.FirefoxOptions options = new org.openqa.selenium.firefox.FirefoxOptions();

            if ("true".equalsIgnoreCase(headless) || isCI) {
                options.addArguments("--headless");
            }

            driver = new FirefoxDriver(options);

        } else {

            throw new IllegalArgumentException(
                    "Browser is not supported: " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL"));
    }

    public String captureScreenshot(String testName) {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir")
                + File.separator + "screenshots" + File.separator + testName + ".png";

        try {

            File destDir = new File(System.getProperty("user.dir") + File.separator + "screenshots");
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            org.openqa.selenium.io.FileHandler.copy(
                    source, new File(path));

        } catch (Exception e) {

            e.printStackTrace();
        }

        return path;
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}