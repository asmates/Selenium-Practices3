package tests.sorular_01;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

public class Q02_DependsOn {

    /*
    -> "test01" method'u olusturunuz.
    -> "https://www.amazon.com/" adresine gidiniz.
    -> "Amazon" anasayfaya gidildigini test ediniz.
    -> "test02" method'u olusturunuz.
    -> "test01" basarili ise "search Box’i" kullanarak “Nutella” icin arama yapiniz.
    -> Aramanin gerceklestigini test ediniz.
    -> "test03" method'u olusturunuz.
    -> “Nutella” icin arama yapildiysa ilk urunu tiklayiniz.
    -> Urunun fiyatinin "$26.5" oldugunu test ediniz.
    -> Sayfayi kapatiniz.
    */

    @Test
    public void test01() {

        //"https://www.amazon.com/" adresine gitme :
        Driver.getDriver().get("https://www.amazon.com");

        //"Amazon" anasayfaya gidildigini test etme :
        String actualUrl = Driver.getDriver().getCurrentUrl();
        String expectedUrl = "https://www.amazon.com/";

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test (dependsOnMethods = "test01")
    public void test02() {

        //"test01" basarili ise "search Box’i" kullanarak “Nutella” icin arama yapma :
        WebElement searchBox = Driver.getDriver().findElement(By.id("twotabsearchtextbox"));

        searchBox.sendKeys("Nutella" + Keys.ENTER);

        //Aramanin gerceklestigini test etme :
        WebElement aramaSonucu = Driver.getDriver().
        findElement(By.xpath("//*[@class='a-section a-spacing-small a-spacing-top-small']"));

        Assert.assertTrue(aramaSonucu.isDisplayed());
    }

    @Test (dependsOnMethods = "test02")
    public void test03() {

        //“Nutella” icin arama yapildiysa ilk urunu tiklama :
        Driver.getDriver().findElement(By.xpath("(//*[@class='s-image'])[1]")).click();

        //Urunun fiyatinin "$26.5" oldugunu test etme :
        WebElement fiyat = Driver.getDriver().
        findElement(By.xpath("//*[@id='productTitle']"));

        String actualFiyat = fiyat.getText();
        String expectedFiyat = "26.5";

        System.out.println(actualFiyat);

        Assert.assertTrue(actualFiyat.contains(expectedFiyat));

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}
