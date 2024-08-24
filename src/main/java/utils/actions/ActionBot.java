package utils.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class ActionBot {
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private final static Logger log= LogManager.getLogger(ActionBot.class);

    public ActionBot(WebDriver driver) {
        this.driver = driver;
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
        driver.manage().window().setSize(new Dimension(1920, 1080));
        this.wait = createFluentWait(this.driver, 10, 800);
        log.info("Initialized {} browser.", browser);
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
            waitForElement(locator);
            driver.findElement(locator).sendKeys(text);
            log.info("Text Typed '{}' into element: {}", text, locator);
        } catch (Exception e) {
            log.error("Unable to type form element: {}. with Exception: {}", locator, e.getMessage());
        }
    }

    public void submit(By locator) {
        try {
            waitForElement(locator);
            driver.findElement(locator).submit();
            log.info("Submitted form element: {}", locator);
        } catch (Exception e) {
            log.error("Unable to submit form element: {}. with Exception: {}", locator, e.getMessage());
        }
    }

    public void click(By locator) {
        try {
            waitForElement(locator);
            driver.findElement(locator).click();
            log.info("Clicked element: {}", locator);
        } catch (Exception e) {
            log.error("Unable to click element: {}. with Exception: {}", locator, e.getMessage());
        }
    }

    public String getText(By locator) {
        try {
            waitForElement(locator);
            String text= this.driver.findElement(locator).getText();
            log.info("Retrieved text '{}' from element: {}", text, locator);
            return text;
        } catch (Exception e) {
            log.error("Unable to retrieve text from element: {}. with Exception: {}", locator, e.getMessage());
            return "";
        }
    }

    public boolean elementDisplayed(By locator) {
        try {
            waitForElement(locator);
            Boolean displayed= driver.findElement(locator).isDisplayed();
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

    private Wait<WebDriver> createFluentWait(WebDriver driver, int timeoutSeconds, int pollingMillis) {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds)).pollingEvery(Duration.ofMillis(pollingMillis)).ignoring(NotFoundException.class).ignoring(ElementNotInteractableException.class).ignoring(StaleElementReferenceException.class).ignoring(AssertionError.class);
    }
    private void waitForElement(By locator){
        this.wait.until(d -> {
            this.driver.findElement(locator);
            return true;
        });
    }

}
