package testPackage.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.com.google.LandingPage;
import pages.com.google.SearchPage;
import utils.actions.ActionBot;
import utils.drivers.BaseDriver;

public class AbstractTest extends BaseDriver {
    protected WebDriver driver;
    protected ActionBot bot;

    @BeforeMethod
    public void setUp() {
        BaseDriver.initializeBrowser("edge");
        BaseDriver.navigate("https://www.google.com/ncr");
        this.driver = BaseDriver.getDriver();
        this.bot = new ActionBot(driver);
    }

    @AfterMethod
    public void tearDown() {
        BaseDriver.closeBrowser();
    }
}
