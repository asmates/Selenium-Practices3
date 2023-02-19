package tests.practices02;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class Q02_AmazonWebTableTest extends TestBaseRapor {

    /*
    -> "Amazon" sayfasina gidiniz.
    -> En alt sayfada bulunan "webTable" kismina kadar ininiz.
    -> "webtable’da" 3.satir 5.sutun'daki yazinin “Home Services” yazisi icerdigini test ediniz.
    -> "webTable'da" 9.hucrede "AbeBooks” yazisi bulundugunu test ediniz.
    -> Testin her bir adiminin raporunu olusturunuz.
    -> Sayfayi kapatiniz.
    */

    @Test
    public void test01() throws Exception {

        AmazonPage amazonPage = new AmazonPage();

        extentTest = extentReports.
        createTest("webTable Testi", "Kullanici sutun ve satir'daki icerikleri test eder.");

        //"Amazon" sayfasina gitme :
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        extentTest.info("Kullanici amazon sayfasina gider.");

        //En alt sayfada bulunan webTable kismina kadar inme :
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true);", amazonPage.webTable);

        extentTest.info("Kullanici webTable kismina kadar iner.");

        //"webtable’da" 3.satir 5.sutun'daki yazinin “Home Services” yazisi icerdigini test etme :
        String actualYazi1 = amazonPage.homeServices.getText();
        String expectedYazi1 = "Home Services";

        Assert.assertTrue(actualYazi1.contains(expectedYazi1));
        extentTest.pass("Kullanici 3.satir 5.sutun'daki yazinin “Home Services” yazisi icerdigini test eder.");

        //"webTable'da" 9.hucrede "AbeBooks” yazisi bulundugunu test etme :
        String actualYazi2 = amazonPage.abeBooks.getText();
        String expectedYazi2 = "AbeBooks";

        Assert.assertTrue(actualYazi2.contains(expectedYazi2));
        extentTest.pass("Kullanici 9.hucrede ”AbeBooks” yazisi bulundugunu test eder.");

        //Sayfayi kapatma :
        Driver.closeDriver();
        extentTest.info("Kullanici sayfayi kapatir.");
    }
}

