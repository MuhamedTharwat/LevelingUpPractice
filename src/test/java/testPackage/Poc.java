package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.actions.ActionBot;

public class Poc {
    WebDriver driver;
    ActionBot bot;
    By imgElement = By.xpath("//img[@alt='Google']");
    By searchTxtBox = By.name("q");
    By fourthResult = By.xpath("(//div[@id='rso']/div/div)[5]//h3");
    @BeforeMethod
    public void setUp() {
        bot = new ActionBot(driver);
        bot.initializeDriver("firefox");
        bot.navigate("https://www.google.com/ncr");
    }

    @Test
    public void verifyPageTitle() {
        String pageTitle = bot.getPageTitle();
        Assert.assertEquals(pageTitle, "Google", "title did not match");
    }

    @Test
    public void verifyLogo() {
        Assert.assertTrue(bot.elementDisplayed(imgElement), "logo not displayed");

    }

    @Test
    public void verifySearch() {
        bot.type(searchTxtBox, "testng");
        bot.submit(searchTxtBox);
        Assert.assertEquals(bot.getText(fourthResult), "org.testng", "fourth result not matched");

    }

    @AfterMethod
    public void tearDown() {
        bot.closeDriver();
    }


}
