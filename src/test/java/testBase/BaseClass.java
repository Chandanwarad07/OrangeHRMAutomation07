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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public WebDriver driver;
    public Properties p;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws IOException {

        p = new Properties();

        FileInputStream file =
                new FileInputStream("src/test/resources/config.properties");

        p.load(file);

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL"));
        
        

    }

    public String captureScreenshot(String testName) {

        TakesScreenshot ts=(TakesScreenshot)driver;

        File source=ts.getScreenshotAs(OutputType.FILE);

        String path=System.getProperty("user.dir")
                +"\\screenshots\\"+testName+".png";

        try{
            org.openqa.selenium.io.FileHandler.copy(source,new File(path));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return path;

    }

    @AfterClass
    public void tearDown() {

        if(driver!=null)
            driver.quit();

    }

}