package pages.com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.actions.ActionBot;

public class LandingPage {
    private final WebDriver driver;
    private final ActionBot bot;
    private final By imgElement = By.xpath("//img[@alt='Google']");
    private final By searchTxtBox = By.name("q");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        bot = new ActionBot(this.driver);
    }

    public String getLandingPageTitle() {
        return bot.getPageTitle();
    }

    public boolean checkLogo() {
        return bot.elementDisplayed(imgElement);
    }

    public SearchPage submitSearch(String value) {
        bot.type(searchTxtBox, value);
        bot.submit(searchTxtBox);
        return new SearchPage(this.driver);
    }


}
