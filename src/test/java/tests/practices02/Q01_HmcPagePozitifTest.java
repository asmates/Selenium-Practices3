package tests.practices02;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HotelMyCampPage;
import utilities.ConfigReader;
import utilities.Driver;

public class Q01_HmcPagePozitifTest {

    /*
    -> "https://www.hotelmycamp.com/" adresine gidiniz.
    -> "Log in" butonuna basiniz.
    -> "username : manager", "password : Manager1!" girip "Log in" tusuna basiniz.
    -> Sayfaya girilebildigini test ediniz.
    -> Sayfayi kapatiniz.
    */

    HotelMyCampPage hotelMyCampPage;

    @Test
    public void test01() throws InterruptedException {

        hotelMyCampPage = new HotelMyCampPage();

        //"https://www.hotelmycamp.com/" adresine gitme :
        Driver.getDriver().get(ConfigReader.getProperty("hotelMyCampUrl"));
        Thread.sleep(1000);

        //"Log in" butonuna basma :
        hotelMyCampPage.anaLogIn.click();
        Thread.sleep(1000);

        //"username : manager", "password : Manager1!" girip "Log in" tusuna basma :
        hotelMyCampPage.userName.sendKeys(ConfigReader.getProperty("hmcActualUserName"));
        hotelMyCampPage.password.sendKeys(ConfigReader.getProperty("hmcActualPassword"));

        Thread.sleep(1000);
        hotelMyCampPage.altLogIn.click();

        //Sayfaya girilebildigini test etme :
        Assert.assertTrue(hotelMyCampPage.basariliGiris.isDisplayed());

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}
