package utils.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class ActionBot {
    private final static Logger log = LogManager.getLogger(ActionBot.class);

    private WebDriver driver;
    private Wait<WebDriver> wait;

    public ActionBot(WebDriver driver) {
        try {
            this.driver = driver;
            this.wait = createFluentWait(this.driver, 5, 500);
        } catch (Exception e) {
            log.error("Failed to initialize ActionBot. Exception: {}", e.getMessage());
        }

    }

    public void initializeDriver(String browser) {
        if (this.driver != null) {
            this.driver.quit();
        }
        switch (browser.toLowerCase()) {
            case "chrome":
                this.driver = new ChromeDriver();
                break;
            case "firefox":
                this.driver = new FirefoxDriver();
                break;
            default:
                driver = new EdgeDriver();
        }
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1280, 720));
        log.info("Initialized {} browser.", browser);
    }

    public WebDriver getDriver() {
        return driver;
    }


    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public void navigate(String url) {
        this.driver.navigate().to(url);
        log.info("Navigated to URL: {}", url);
    }

    public void type(By locator, String text) {
        try {
            getElementAfterWait(locator).sendKeys(text);
            log.info("Text Typed '{}' into element: {}", text, locator);
        } catch (Exception e) {
            log.error("Unable to type form element: {}. with Exception: {}", locator, e.getMessage());
        }
    }

    public void submit(By locator) {
        try {
            getElementAfterWait(locator).submit();
            log.info("Submitted form element: {}", locator);
        } catch (Exception e) {
            log.error("Unable to submit form element: {}. with Exception: {}", locator, e.getMessage());
        }
    }

    public void click(By locator) {
        try {
            getElementAfterWait(locator).click();
            log.info("Clicked element: {}", locator);
        } catch (Exception e) {
            log.error("Unable to click element: {}. with Exception: {}", locator, e.getMessage());
        }
    }

    public String getText(By locator) {
        try {
            String text = getElementAfterWait(locator).getText();
            log.info("Retrieved text '{}' from element: {}", text, locator);
            return text;
        } catch (Exception e) {
            log.error("Unable to retrieve text from element: {}. with Exception: {}", locator, e.getMessage());
            return "";
        }
    }

    public boolean elementDisplayed(By locator) {
        try {
            Boolean displayed = getElementAfterWait(locator).isDisplayed();
            log.info("Element displayed status for {}: {}", locator, displayed);
            return displayed;
        } catch (Exception e) {
            log.error("Element not displayed: {}. Exception: {}", locator, e.getMessage());
            return false;
        }
    }

    public void closeDriver() {
        if (this.driver != null) {
            this.driver.quit();
            this.driver = null;
            log.info("WebDriver closed successfully.");
        }
    }

    public <T> void assertEquals(T actual, T expected) {
        boolean success = false;
        AtomicBoolean warningLogged = new AtomicBoolean(false);

        try {
            wait.until(driver -> {
                try {
                    Assert.assertEquals(actual, expected);
                    log.info("Assertion passed. Actual: {}, Expected: {}", actual, expected);
                    return true;
                } catch (AssertionError e) {
                    if (!warningLogged.get()) {
                        log.warn("Assertion attempt failed not matched. Retrying again ... ");
                        warningLogged.set(true);
                    }
                    return false;
                }
            });
            success = true;
        } catch (TimeoutException e) {
            log.error("Assertion failed after retries. Actual: {}, Expected: {}", actual, expected);
        }

        if (!success) {
            Assert.fail("Assertion failed after waiting for the condition to meet. Actual: " + actual + ", Expected: " + expected);
        }
    }

    public void assertTrue(Boolean condition) {
        boolean success = false;
        AtomicBoolean warningLogged = new AtomicBoolean(false);

        try {
            wait.until(driver -> {
                try {
                    Assert.assertTrue(condition, "Condition was not true.");
                    log.info("Assertion passed. Condition is true.");
                    return true;
                } catch (AssertionError e) {
                    if (!warningLogged.get()) {
                        log.warn("Assertion attempt failed. Retrying again...");
                        warningLogged.set(true);
                    }
                    return false;
                }
            });
            success = true;
        } catch (TimeoutException e) {
            log.error("Timeout reached. Final assertion failed after retries. Condition is still false.");
        }

        if (!success) {
            Assert.fail("Assertion failed after retrying for the condition to be true.");
        }
    }


    public void assertText(String actual, String expected) {
        wait.until(d -> {
            Assert.assertEquals(actual, expected, "text not matched");
            return true;
        });

    }

    private Wait<WebDriver> createFluentWait(WebDriver driver, int timeoutSeconds, int pollingMillis) {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds)).pollingEvery(Duration.ofMillis(pollingMillis)).ignoring(NotFoundException.class).ignoring(ElementNotInteractableException.class).ignoring(StaleElementReferenceException.class).ignoring(AssertionError.class);
    }

    private WebElement getElementAfterWait(By locator) {
        return this.wait.until(driver -> this.driver.findElement(locator));
    }
}