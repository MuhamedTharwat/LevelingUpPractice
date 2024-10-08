package testPackage.modularApproach.googleTests;

import org.testng.annotations.Test;
import pages.pom.com.google.LandingPage;
import pages.pom.com.google.SearchPage;
import testPackage.base.AbstractTest;

public class PoC2 extends AbstractTest {
    LandingPage landingPage;
    SearchPage searchPage;

    @Test(description = "verify landing page title")
    public void task01() {
        landingPage = new LandingPage(driver);
        String pageTitle = landingPage.getLandingPageTitle();
        bot.assertEquals(pageTitle, "Google");

    }

    @Test(description = "verify landing page logo exist")
    public void task02() {
        landingPage = new LandingPage(driver);
        Boolean result = landingPage.checkLogo();
        bot.assertTrue(result);

    }

    @Test(description = "verify fourth search result")
    public void task03() {
        landingPage = new LandingPage(driver);
        landingPage.submitSearch("testng");
        searchPage = new SearchPage(driver);
        String fourthResult = searchPage.getSearchResultHeadline(5);
        bot.assertEquals(fourthResult, "org.testng");
    }

}
