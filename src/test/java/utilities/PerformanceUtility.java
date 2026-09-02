package utilities;

import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PerformanceUtility {

    private WebDriver driver;

    public PerformanceUtility(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retrieves W3C Navigation Timing metrics from the browser
     */
    public long getPageLoadTimeMs() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object loadTime = js.executeScript(
                "var perf = window.performance.timing;" +
                "return (perf.loadEventEnd > 0) ? (perf.loadEventEnd - perf.navigationStart) : (Date.now() - perf.navigationStart);");
        return loadTime != null ? ((Number) loadTime).longValue() : 0;
    }

    public long getTTFBMs() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object ttfb = js.executeScript(
                "var perf = window.performance.timing;" +
                "return (perf.responseStart - perf.requestStart);");
        return ttfb != null ? ((Number) ttfb).longValue() : 0;
    }

    public long getDomInteractiveTimeMs() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object domReady = js.executeScript(
                "var perf = window.performance.timing;" +
                "return (perf.domInteractive - perf.navigationStart);");
        return domReady != null ? ((Number) domReady).longValue() : 0;
    }

    public void printPerformanceMetrics(String pageName) {
        long loadTime = getPageLoadTimeMs();
        long ttfb = getTTFBMs();
        long domReady = getDomInteractiveTimeMs();

        System.out.println("====================================================");
        System.out.println(" PERFORMANCE METRICS: " + pageName);
        System.out.println(" Time to First Byte (TTFB) : " + ttfb + " ms");
        System.out.println(" DOM Interactive Time      : " + domReady + " ms");
        System.out.println(" Total Page Load Time      : " + loadTime + " ms");
        System.out.println("====================================================");
    }
}
