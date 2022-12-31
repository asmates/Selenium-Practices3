package tests.sorular_01;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.BlueRentalCarsPage;
import utilities.Driver;
import utilities.TestBaseRapor;

public class Q05_BlueRentalCarsTest extends TestBaseRapor {

    /*
    -> "https://www.bluerentalcars.com/" adresine gidiniz.
    -> "Login" butonuna basiniz.
    -> "Email adresss ve password" kisimlarina verilen bilgileri giriniz.
       -email : customer@bluerentalcars.com
       -password : 12345
    -> "Login" butonuna tiklayiniz.
    -> Degerler girildiginde sayfaya basarili sekilde girilebildigini test ediniz.
    -> Sayfayi kapatiniz.
    */

    @Test
    public void test01() throws InterruptedException {

        BlueRentalCarsPage blueRentalCarsPage = new BlueRentalCarsPage();

        extentTest = extentReports.
        createTest("Pozitif Test", "Gecerli email ve password ile siteye giris yapilabilmelidir.");

        //"https://www.bluerentalcars.com/" adresine gitme :
        Driver.getDriver().get("https://www.bluerentalcars.com/");

        Thread.sleep(1000);
        extentTest.info("Bluerentalcars adresine gidildi.");

        //"Login" butonuna basma :
        blueRentalCarsPage.anaLogin.click();

        Thread.sleep(1000);
        extentTest.info("Login tusuna basildi.");

        //"Email adresss ve password" kisimlarina verilen bilgileri girme :
        blueRentalCarsPage.email.sendKeys("customer@bluerentalcars.com");
        blueRentalCarsPage.password.sendKeys("12345");

        Thread.sleep(1000);
        extentTest.info("email ve password bilgileri girildi.");

        //"Login" butonuna tiklama :
        blueRentalCarsPage.altLogin.click();

        Thread.sleep(1000);
        extentTest.info("Login butonuna tiklandi.");

        //Degerler girildiginde sayfaya basarili sekilde girilebildigini test etme :
        Assert.assertTrue(blueRentalCarsPage.basariliGiris.isDisplayed());

        Thread.sleep(1000);
        extentTest.info("Basarili bir sekilde girildigi test edildi.");

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}
