package pages.com.google;

import Base.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.actions.ActionBot;

public class LandingPage extends AbstractPage {
    private final By imgElement = By.xpath("//img[@alt='Google']");
    private final By searchTxtBox = By.name("q");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public String getLandingPageTitle() {
        return bot.getPageTitle();
    }

    public boolean checkLogo() {
        return bot.elementDisplayed(imgElement);
    }

    public void submitSearch(String value) {
        bot.type(searchTxtBox, value);
        bot.submit(searchTxtBox);
    }


}
