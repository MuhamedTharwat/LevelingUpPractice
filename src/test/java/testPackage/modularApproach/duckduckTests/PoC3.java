package testPackage.modularApproach.duckduckTests;

import org.testng.annotations.Test;
import pages.pom.com.duckduckgo.HomePage;
import pages.pom.com.duckduckgo.SearchPage;
import testPackage.base.AbstractTest;

public class PoC3 extends AbstractTest {
    HomePage homePage;
    SearchPage searchPage;
    @Test
    public void task05(){
        homePage=new HomePage(driver);
        homePage.search("Cucumber IO");
        searchPage=new SearchPage(driver);
        searchPage.viewPage(2);
        searchPage.getSearchResultLink("2","3");
    }
}
