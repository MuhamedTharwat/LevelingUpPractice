package pages.pom.com.google;

import Base.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends AbstractPage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private By searchResultXpath(int index) {
        return By.xpath("(//div[@id='rso']/div/div)[" + index + "]//h3");
    }

    public String getSearchResultHeadline(int index) {
        By searchResultElement = searchResultXpath(index);
        return bot.getText(searchResultElement);
    }


}
