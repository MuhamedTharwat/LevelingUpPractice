package utils.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseDriver {
    private final static Logger log = LogManager.getLogger(BaseDriver.class);
    private static WebDriver driver;

    public static void initializeBrowser(String browser) {
        try {
            if (driver == null) {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        driver = new FirefoxDriver();
                        break;
                    default:
                        driver = new EdgeDriver();
                }
                driver.manage().window().setPosition(new Point(0, 0));
                driver.manage().window().setSize(new Dimension(1280, 720));
                log.info("Initialized {} browser.", browser);
            }
        } catch (WebDriverException e) {
            log.error("Failed to initialize {} browser. with Exception: {}", browser, e.getMessage());
        }
    }

    public static void navigate(String url) {
        try {
            driver.navigate().to(url);
            log.info("Navigated to URL: {}", url);
        } catch (WebDriverException e) {
            log.error("Failed to navigate to URL: {}. with Exception: {}", url, e.getMessage());
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            log.error("WebDriver has not been initialized. Ensure to call initializeBrowser() before accessing the driver.");
        }
        return driver;
    }

    public static void closeBrowser() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
                log.info("Browser closed successfully.");
            }
        } catch (WebDriverException e) {
            log.error("Failed to close the browser. with Exception: {}", e.getMessage());
        }
    }
}