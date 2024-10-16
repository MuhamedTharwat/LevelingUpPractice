package pages.pom.com.w3;

import Base.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HtmlTablesPage extends AbstractPage {
    By countryText;
    public HtmlTablesPage(WebDriver driver) {
        super(driver);
    }
    public String getCountryNameByCompany(String companyName){
        countryText= By.xpath("//tr//td[text()='"+companyName+"']//following::td[2]");
        return bot.getText(countryText);
    }
}
