package tests.sorular_01;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.SwagLabsPage;
import utilities.ConfigReader;
import utilities.Driver;

public class Q04_SwagLabsPageTest {

    /*
    -> "https://www.saucedemo.com/" adresine gidiniz.
    -> Kullanici adini "standard_user" olarak giriniz.
    -> Sifreyi "secret_sauce" olarak giriniz.
    -> Giris dugmesine tiklayiniz.
    -> Dusukten yuksege fiyatlari seciniz ve fiyatlarin dusukten yuksege gorunur oldugunu dogrulayiniz.
    -> Sayfayi kapatiniz.
    */

    @Test
    public void test01() throws Exception {

        SwagLabsPage swagLabsPage = new SwagLabsPage();

        //"https://www.saucedemo.com/" adresine gitme :
        Driver.getDriver().get(ConfigReader.getProperty("swagLabsUrl"));
        Thread.sleep(1000);

        //Kullanici adini "standard_user" olarak girme :
        swagLabsPage.userName.sendKeys(ConfigReader.getProperty("swglabActualUserName"));
        Thread.sleep(1000);

        //Sifreyi "secret_sauce" olarak girme :
        swagLabsPage.password.sendKeys(ConfigReader.getProperty("swglabActualPassword"));
        Thread.sleep(1000);

        //Giris dugmesine tiklama :
        swagLabsPage.loginButton.click();
        Thread.sleep(1000);

        //Dusukten yuksege fiyatlari secme ve fiyatlarin dusukten yuksege gorunur oldugunu dogrulama :
        Select select = new Select(SwagLabsPage.dropDownMenu);
        select.selectByVisibleText("Price (low to high)");

        String actualMenu = select.getFirstSelectedOption().getText();
        String expectedMenu = "Price (low to high)";

        Assert.assertEquals(expectedMenu, actualMenu);
        Thread.sleep(1000);

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}
