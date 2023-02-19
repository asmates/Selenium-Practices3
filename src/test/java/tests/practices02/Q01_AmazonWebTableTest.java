package tests.practices02;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class Q01_AmazonWebTableTest extends TestBaseRapor {

    /*
    -> "Amazon" sayfasina gidiniz.
    -> En alt sayfada bulunan webTable kismina kadar ininiz.
    -> "webtable’da" satir sayisinin 10 oldugunu test ediniz.
    -> "webtable’da" sutun sayisinin 13 oldugunu test ediniz.
    -> Testin her bir adiminin raporunu olusturunuz.
    -> Sayfayi kapatiniz.
    */

    @Test
    public void test01() throws Exception {

        AmazonPage amazonPage = new AmazonPage();

        extentTest = extentReports.
        createTest("webTable Testi", "Kullanici sutun ve satir sayilarini test eder.");

        //"Amazon" sayfasina gitme :
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        extentTest.info("Kullanici amazon sayfasina gider.");

        //En alt sayfada bulunan webTable kismina kadar inme :
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true);", amazonPage.webTable);

        extentTest.info("Kullanici webTable kismina kadar iner.");

        //"webtable’da" satir sayisinin 10 oldugunu test etme :
        int actualSatirSayisi = amazonPage.satirSayisi.size();
        int expectedSatirSayisi = 10;

        Assert.assertEquals(expectedSatirSayisi, actualSatirSayisi);
        extentTest.pass("Kullanici satir sayisinin 10 oldugunu test eder.");

        //"webtable’da" sutun sayisinin 13 oldugunu test etme :
        int actualSutunSayisi = amazonPage.sutunSayisi.size();
        int expectedSutunSayisi = 14;

        Assert.assertEquals(expectedSutunSayisi, actualSutunSayisi);
        extentTest.pass("Kullanici sutun sayisinin 14 oldugunu test eder.");

        //Sayfayi kapatma :
        Driver.closeDriver();
        extentTest.info("Kullanici sayfayi kapatir.");
    }
}
