package tests.practices02;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HerokuappPage;
import utilities.Driver;

public class Q05_HerokuappTest {

    /*
    -> "https://testpages.herokuapp.com/styled/events/javascript-events.html" adresine gidiniz.
    -> Tum butonlara tiklayiniz.
    -> Tum butonlarin hepsinin tiklandigini test ediniz.
    -> Sayfayi kapatiniz.
    */

    HerokuappPage herokuappPage;

    Actions actions = new Actions(Driver.getDriver());

    @Test
    public void test01() throws Exception {

        herokuappPage = new HerokuappPage();

        //"https://testpages.herokuapp.com/styled/events/javascript-events.html" adresine gitme :
        Driver.getDriver().get("https://testpages.herokuapp.com/styled/events/javascript-events.html");
        Thread.sleep(1000);

        //Tum butonlara tiklama :

        //"onBlur" butonuna tiklama :
        herokuappPage.onBlur.click();
        herokuappPage.onClick.click();

        Thread.sleep(1000);

        //"onClick" butonuna tiklama :
        herokuappPage.onClick.click();
        Thread.sleep(1000);

        //"onContextMenu" butonuna tiklama :
        actions.contextClick(herokuappPage.onContextMenu).perform();
        Thread.sleep(1000);

        //"onDoubleClick" butonuna tiklama :
        actions.doubleClick(herokuappPage.onDoubleClick).perform();
        Thread.sleep(1000);

        //"onFocus" butonuna tiklama :
        herokuappPage.onFocus.click();
        Thread.sleep(1000);

        //"onKeyDown" butonuna tiklama :
        herokuappPage.onKeyDown.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //"onKeyUp" butonuna tiklama :
        herokuappPage.onKeyUp.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //"onKeyPress" butonuna tiklama :
        herokuappPage.onKeyPress.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //"onMouseOver" butonuna tiklama :
        actions.moveToElement(herokuappPage.onMouseOver).perform();
        Thread.sleep(1000);

        //"onMouseOver" butonuna tiklama :
        actions.clickAndHold(herokuappPage.onMouseLeave).perform();
        Thread.sleep(1000);

        //"onMouseDown" butonuna tiklama :
        actions.moveToElement(herokuappPage.onMouseDown).click().perform();
        Thread.sleep(1000);

        //Tum butonlarin hepsinin tiklandigini test etme :

        //1.YOL :
        for (int i = 0; i < herokuappPage.eventTriggered.size(); i++) {

            Assert.assertTrue(herokuappPage.eventTriggered.get(i).isDisplayed());
            Thread.sleep(1000);
        }

        /*
        2.YOL :
        int actualVerificaton = herokuappPage.eventTriggered.size();
        int expectedVerificaton = 11;

        Assert.assertEquals(expectedVerificaton, actualVerificaton);
        Thread.sleep(1000);
        */

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}
