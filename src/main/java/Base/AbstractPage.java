package Base;

import org.openqa.selenium.WebDriver;
import utils.actions.ActionBot;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected ActionBot bot;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.bot = new ActionBot(driver);
    }
}
