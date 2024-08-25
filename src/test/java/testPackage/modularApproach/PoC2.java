package testPackage.modularApproach;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.google.LandingPage;
import pages.com.google.SearchPage;
import utils.actions.ActionBot;

public class PoC2 {
    WebDriver driver;
    ActionBot bot;
    LandingPage landingPage;
    SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        bot = new ActionBot(driver);
        bot.initializeDriver("firefox");
        bot.navigate("https://www.google.com/ncr");
    }

    @Test(description = "verify landing page title")
    public void tc01() {
        landingPage = new LandingPage(bot.getDriver());
        String pageTitle = landingPage.getLandingPageTitle();
        bot.assertEquals(pageTitle, "Googles");

    }

    @Test(description = "verify landing page logo exist")
    public void tc02() {
        landingPage = new LandingPage(bot.getDriver());
        Boolean result = landingPage.checkLogo();
        bot.assertTrue(result);

    }

    @Test(description = "verify fourth search result")
    public void tc03() {
        landingPage = new LandingPage(bot.getDriver());
        searchPage = landingPage.submitSearch("testng");
        String fourthResult = searchPage.getSearchResultHeadline(5);
        bot.assertEquals(fourthResult, "org.testng");
    }

    @AfterMethod
    public void tearDown() {
        bot.closeDriver();
    }
}
