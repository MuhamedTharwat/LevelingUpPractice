package pages.pom.com.duckduckgo;

import Base.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    By searchTextBox =By.id("searchbox_input");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void search(String text){
        bot.type(searchTextBox,text);
        bot.submit(searchTextBox);
    }
}
