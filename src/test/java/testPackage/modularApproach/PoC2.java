package testPackage.modularApproach;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.com.google.LandingPage;
import pages.com.google.SearchPage;
import utils.actions.ActionBot;
import utils.drivers.DriverManager;

public class PoC2 {
    WebDriver driver;
    ActionBot bot;
    LandingPage landingPage;
    SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        DriverManager.initializeBrowser("edge");
        DriverManager.navigate("https://www.google.com/ncr");
        this.driver=DriverManager.getDriver();
        this.bot = new ActionBot(driver);
    }

    @Test(description = "verify landing page title")
    public void tc01() {
        landingPage = new LandingPage(driver);
        String pageTitle = landingPage.getLandingPageTitle();
        bot.assertEquals(pageTitle, "Google");

    }

    @Test(description = "verify landing page logo exist")
    public void tc02() {
        landingPage = new LandingPage(driver);
        Boolean result = landingPage.checkLogo();
        bot.assertTrue(result);

    }

    @Test(description = "verify fourth search result")
    public void tc03() {
        landingPage = new LandingPage(driver);
        searchPage = landingPage.submitSearch("testng");
        String fourthResult = searchPage.getSearchResultHeadline(5);
        bot.assertEquals(fourthResult, "org.testng");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.closeBrowser();
    }
}
