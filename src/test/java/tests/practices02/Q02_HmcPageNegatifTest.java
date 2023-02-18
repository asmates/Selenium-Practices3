package tests.practices02;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HotelMyCampPage;
import utilities.ConfigReader;
import utilities.Driver;

public class Q02_HmcPageNegatifTest {

    /*
    -> "https://www.hotelmycamp.com/" adresine gidiniz.
    -> "Log in" butonuna basiniz.
    -> "username : manager1", "password : manager1!" girip "Log in" tusuna basiniz.
    -> Sayfaya girilemedigini test ediniz.
    -> Sayfayi kapatiniz.
    */

    HotelMyCampPage hotelMyCampPage;

    @Test
    public void test01() throws Exception {

        hotelMyCampPage = new HotelMyCampPage();

        //"https://www.hotelmycamp.com" adresine gitme :
        Driver.getDriver().get(ConfigReader.getProperty("hotelMyCampUrl"));
        Thread.sleep(1000);

        //"Log in" butonuna basma :
        hotelMyCampPage.anaLogIn.click();
        Thread.sleep(1000);

        //"username : manager1, password : manager1!" girip "Log in" tusuna basma :
        hotelMyCampPage.userName.sendKeys(ConfigReader.getProperty("hmcWrongUserName"));
        hotelMyCampPage.password.sendKeys(ConfigReader.getProperty("hmcWrongPassword"));

        Thread.sleep(1000);
        hotelMyCampPage.altLogIn.click();

        //Sayfaya girilemedigini test etme :
        Assert.assertTrue(hotelMyCampPage.girisYapilamadi.isDisplayed());

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}
