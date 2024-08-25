package pages.com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.actions.ActionBot;

public class SearchPage {
    private WebDriver driver;
    private final ActionBot bot;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        bot=new ActionBot(this.driver);
    }
    private By searchResultXpath(int index){
        return By.xpath("(//div[@id='rso']/div/div)[" + index + "]//h3");
    }
    public String getSearchResultHeadline(int index){
        By searchResultElement=searchResultXpath(index);
        return bot.getText(searchResultElement);
    }


}
