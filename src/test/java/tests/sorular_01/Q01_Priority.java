package tests.sorular_01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

public class Q01_Priority {

    /*
    -> "https://www.youtube.com" adresine gidiniz.
    -> "titleTest, imageTest, searchBoxTest, wrongTitleTest" method'lari olusturunuz.
    -> Sayfa basliginin “YouTube” oldugunu test ediniz.
    -> "YouTube" resminin goruntulendigini test ediniz.
    -> "Search Box 'in" erisilebilir oldugunu test ediniz.
    -> Sayfa basliginin “youtube” olmadigini dogrulayiniz.
    -> Sayfayi kapatiniz.
    */

    @Test
    public void test01() {

        //"https://www.youtube.com" adresine gitme :
        Driver.getDriver().get("https://www.youtube.com");

        //"titleTest" method'u olusturma :
        titleTest();

        //"imageTest" method'u olusturma :
        imageTest();

        //"searchBoxTest" method'u olusturma :
        searchBoxTest();

        //"wrongTitleTest" method'u olusturma :
        wrongTitleTest();

        //Sayfayi kapatma :
        Driver.closeDriver();
    }

    public void  titleTest() {

        //Sayfa basliginin “YouTube” oldugunu test etme :
        String actualTitle = Driver.getDriver().getTitle();
        String expectedTitle = "YouTube";

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    public void imageTest() {

        //"YouTube" resminin goruntulendigini test etme :
        WebElement logo = Driver.getDriver().findElement(By.xpath("(//*[@id='logo-icon'])[1]"));

        Assert.assertTrue(logo.isDisplayed());
    }

    public void searchBoxTest() {

        //"Search Box 'in" erisilebilir oldugunu test etme :
        WebElement searchBox = Driver.getDriver().findElement(By.xpath("(//*[@id='search'])[2]"));

        Assert.assertTrue(searchBox.isEnabled());
    }

    public void wrongTitleTest() {

        //Sayfa basliginin “youtube” olmadigini dogrulama :
        String actualTitle = Driver.getDriver().getTitle();
        String expectedTitle = "youtube";

        Assert.assertNotEquals(expectedTitle, actualTitle);
    }
}
