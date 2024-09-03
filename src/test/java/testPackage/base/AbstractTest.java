package testPackage.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.actions.ActionBot;
import utils.drivers.BaseDriver;

public class AbstractTest extends BaseDriver {
    protected WebDriver driver;
    protected ActionBot bot;

    @BeforeMethod
    public void setUp() {
        BaseDriver.initializeBrowser("edge");
        BaseDriver.navigate("https://duckduckgo.com/");
        this.driver = BaseDriver.getDriver();
        this.bot = new ActionBot(driver);
    }

    @AfterMethod
    public void tearDown() {
        BaseDriver.closeBrowser();
    }
}
