package pages.pom.com.duckduckgo;

import Base.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends AbstractPage {
    By moreResultBtn=By.id("more-results");
    By searchResultLink;
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void viewPage (int pageNumber) {
        for (int i = 1; i < pageNumber; i++) {
            bot.click(moreResultBtn);
        }
    }
    public void getSearchResultLink(String pageNumber,String linkNumber){
        searchResultLink= By.xpath("(//div[@aria-label='Page "+pageNumber+"']/following::li["+linkNumber+"]//a)[2]");
        String link=bot.getElementAttribute(searchResultLink,"href");
        bot.assertEquals(link,"https://www.linkedin.com");
    }
}
