package utils.actions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class ActionBot {
    WebDriver driver;
    Wait<WebDriver> wait;

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
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public void navigate(String url) {
        this.driver.navigate().to(url);
    }

    public void type(By locator, String text) {
        wait.until(driver1 -> {
            driver.findElement(locator).sendKeys(text);
            return true;
        });
    }

    public void submit(By locator) {
        wait.until(driver1 -> {
            driver.findElement(locator).submit();
            return true;
        });
    }

    public void click(By locator) {
        wait.until(driver1 -> {
            driver.findElement(locator).click();
            return true;
        });
    }

    public String getText(By locator) {
        final String[] elementText = new String[1];
        wait.until(driver -> {

            elementText[0] = driver.findElement(locator).getText();
            return true;
        });
        return elementText[0];
    }

    public boolean elementDisplayed(By locator) {
        wait.until(driver1 -> {
            driver.findElement(locator);
            return true;
        });
        return driver.findElement(locator).isDisplayed();
    }

    public void closeDriver() {
        if (this.driver != null) {
            this.driver.quit();
            this.driver = null;
        }
    }

    private Wait<WebDriver> createFluentWait(WebDriver driver, int timeoutSeconds, int pollingMillis) {
        return new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds)).pollingEvery(Duration.ofMillis(pollingMillis)).ignoring(NotFoundException.class).ignoring(ElementNotInteractableException.class).ignoring(StaleElementReferenceException.class).ignoring(AssertionError.class);
    }

}
