package testPackage.modularApproach.w3Testd;

import org.testng.annotations.Test;
import pages.pom.com.w3.HtmlTablesPage;
import testPackage.base.AbstractTest;

public class Poc4 extends AbstractTest {
    HtmlTablesPage tablesPage;
    @Test
    public void task07(){
        tablesPage=new HtmlTablesPage(driver);
        String countryName=tablesPage.getCountryNameByCompany("Ernst Handel");
        bot.assertEquals(countryName,"Austria");
    }
}
