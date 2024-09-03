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
import java.util.function.Function;

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
        return executeWithWait(driver1 -> {
            String title=this.driver.getTitle();
            log.info("page title to URL: {}", title);
            return title;
        },"Unable to retrieve page title.");
    }

    public void navigate(String url) {
        this.driver.navigate().to(url);
        log.info("Navigated to URL: {}", url);
    }

    public void type(By locator, String text) {
        executeWithWait(driver1 -> {
            driver.findElement(locator).sendKeys(text);
            log.info("Text Typed '{}' into element: {}", text, locator);
            return true;
        },"Unable to type form element: "+locator);
    }

    public void submit(By locator) {
        executeWithWait(driver1 -> {
            driver.findElement(locator).submit();
            log.info("Submitted form element: {}", locator);
            return true;
        },"Unable to submit form element: "+locator);
    }

    public void click(By locator) {
        executeWithWait(driver1 -> {
            this.driver.findElement(locator).click();
            log.info("Clicked element: {}", locator);
            return true;
        },"Unable to click element: "+locator);
    }

    public String getText(By locator) {
        return executeWithWait(driver1 -> {
            String text = this.driver.findElement(locator).getText();
            log.info("Retrieved text '{}' from element: {}", text, locator);
            return text;
        },"Unable to retrieve text from element: " + locator);
    }
    public String getElementAttribute(By locator,String attribute) {
        return executeWithWait(driver1 -> {
            String value = this.driver.findElement(locator).getAttribute(attribute.toLowerCase());
            log.info("Retrieved attribute value '{}' from element: {}", value, locator);
            return value;
        },"Unable to retrieve attribute value from element: " + locator);
    }


    public boolean elementDisplayed(By locator) {
        return Boolean.TRUE.equals(executeWithWait(driver -> {
            boolean displayed = this.driver.findElement(locator).isDisplayed();
            log.info("Element displayed status for {}: {}", locator, displayed);
            return displayed;
        }, "Element not displayed: " + locator));
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

    /**
     * Creates a FluentWait instance for the specified WebDriver with the given timeout and polling interval.
     *
     * @param driver the WebDriver instance
     * @param timeoutSeconds the maximum time to wait
     * @param pollingMillis the interval between polling
     * @return a FluentWait instance
     */
    private Wait<WebDriver> createFluentWait(WebDriver driver, int timeoutSeconds, int pollingMillis) {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds)).pollingEvery(Duration.ofMillis(pollingMillis)).ignoring(NotFoundException.class).ignoring(ElementNotInteractableException.class).ignoring(StaleElementReferenceException.class).ignoring(AssertionError.class);
    }

    private WebElement getElementAfterWait(By locator) {
        return this.wait.until(driver -> this.driver.findElement(locator));
    }
    /**
     * Executes an driverTFunction on WebDriver with a FluentWait, retrying if necessary.
     *
     * @param driverTFunction the driverTFunction to perform by the WebDriver
     * @param errorMessage the error message to log if the driverTFunction fails
     * @param <T> the type of the result
     * @return the driverTFunction
     */
    private <T> T executeWithWait(Function<WebDriver, T> driverTFunction, String errorMessage) {
        AtomicBoolean exceptionLogged = new AtomicBoolean(false);
        try {
            return wait.until(driver -> {
                try {
                    return driverTFunction.apply(driver);
                } catch (Exception e) {
                    if (!exceptionLogged.get()) {
                        log.error("{} Exception: {}", errorMessage, e.getMessage());
                        exceptionLogged.set(true);
                    }
                    throw e;
                }
            });
        } catch (TimeoutException e) {
            log.error("Timed out Reached ! .... Still {}", errorMessage);
            return null;
        }
    }
}