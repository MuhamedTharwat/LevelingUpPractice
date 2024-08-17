package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Poc {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new EdgeDriver();
        driver.get("https://www.google.com/ncr");
    }

    @Test
    public void verifyPageTitle() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Google","title did not match");
    }

    @Test
    public void verifyLogo() {
        By imgElement = By.xpath("//img[@alt='Google']");
        Assert.assertTrue(driver.findElement(imgElement).isDisplayed(), "logo not displayed");

    }
    @Test
    public void verifySearch(){
        By searchTxtBox=By.name("q");
        By firstResult=By.cssSelector("h3");
        driver.findElement(searchTxtBox).sendKeys("Selenium WebDriver");
        driver.findElement(searchTxtBox).submit();
        String firstResultTxt=driver.findElement(firstResult).getText();
        Assert.assertEquals(firstResultTxt,"WebDriver - Selenium","first result not matched");
    }

    @AfterTest
    public void tearDown() {
        //driver.quit();
    }


}
