package tests.practices02;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.WebUniversityPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q03_WebUniversityTest {

    /*
    -> "http://webdriveruniversity.com/To-Do-List/index.html" adresine gidiniz.
    -> "Todos" listesi ekleyiniz.
       1.Kahvaltiyi hazirla!
       2.Bulasiklari yika!
       3.Bebekle ilgilen!
       4.Cocugun odevine yardim et!
       5.Selenium calis!
       6.Uyu!
    -> Tum yapilacaklarin uzerini ciziniz.
    -> Tum yapilacaklari siliniz.
    -> Tum yapilacaklarin silindigini dogrulayiniz.
    -> Sayfayi kapatiniz.
    */

    WebUniversityPage webUniversityPage;

    Actions actions;

    @Test
    public void test01() throws InterruptedException {

        webUniversityPage = new WebUniversityPage();

        actions = new Actions(Driver.getDriver());

        //"http://webdriveruniversity.com/To-Do-List/index.html" adresine gitme :
        Driver.getDriver().get(ConfigReader.getProperty("webdriverUniversityUrl"));

        //"Todos" listesi ekleme :

        //1.YOL :
        List<String> toDosListesi = new ArrayList<String>(Arrays.asList("Kahvaltiyi hazirla!", "Bulasiklari yika!",
                                                                        "Bebekle ilgilen!", "Cocugun odevine yardim et!",
                                                                        "Selenium calis!", "Uyu!"));

        for (String each : toDosListesi
             ) {

            actions.click(webUniversityPage.addNewToDo).sendKeys(each).sendKeys(Keys.ENTER).perform();
        }

        Thread.sleep(1000);

        /*
        2.YOL :
        List<String> toDosListesi = new ArrayList<String>(Arrays.asList("Kahvaltiyi hazirla!", "Bulasiklari yika!",
                                                                        "Bebekle ilgilen!", "Cocugun odevine yardim et!",
                                                                        "Selenium calis!", "Uyu!"));

        for (String each : toDosListesi
             ) {

            webUniversityPage.addNewToDo.sendKeys(each, Keys.ENTER);
        }
        */

        /*
        3.YOL :
        List<String> toDosListesi = new ArrayList<String>(Arrays.asList("Kahvaltiyi hazirla!", "Bulasiklari yika!",
                                                                        "Bebekle ilgilen!", "Cocugun odevine yardim et!",
                                                                        "Selenium calis!", "Uyu!"));

        toDosListesi.forEach(t -> webUniversityPage.addNewToDo.sendKeys(t, Keys.ENTER));
        */

        //Tum yapilacaklarin uzerini cizme :

        //1.YOL :
        List<WebElement> drawButtons = webUniversityPage.drawButtons;

        for (WebElement each : drawButtons
             ) {

            each.click();
        }

        Thread.sleep(1000);

        //2.YOL :
        //webUniversityPage.drawButtons.forEach(WebElement::click);

        //Tum yapilacaklari silme :

        //1.YOL :
        List<WebElement> deleteButtons = webUniversityPage.deleteButtons;

        for (WebElement each : deleteButtons
             ) {

            each.click();
        }

        Thread.sleep(1000);

        //2.YOL :
        //webUniversityPage.deleteButtons.forEach(WebElement::click);

        //Tum yapilacaklarin silindigini dogrulama :

        //1.YOL :
        List<WebElement> deleteVerification = webUniversityPage.drawButtons;

        Assert.assertEquals(0, deleteVerification.size());

        //2.YOL :
        //Assert.assertTrue(webUniversityPage.addNewToDo.getText().isEmpty());

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}
