package tests.practices01;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import page.DhtmlPage;
import utilities.Driver;

public class Q03_DhtmlPageTest {

    /*
    -> "http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html" sayfasina gidiniz.
    -> Ulkelere gore baskentleri doldurunuz.
    -> Sayfayi kapatiniz.
    */

    @Test
    public void test01() throws InterruptedException {

        //"http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html" sayfasina gitme :
        Driver.getDriver().get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
        Thread.sleep(2000);

        //Ulkelere gore baskentleri doldurma :
        DhtmlPage dhtmlPage = new DhtmlPage();

        Actions actions = new Actions(Driver.getDriver());

        actions.dragAndDrop(dhtmlPage.oslo, dhtmlPage.norway)
               .dragAndDrop(dhtmlPage.stockholm, dhtmlPage.sweden)
               .dragAndDrop(dhtmlPage.washington, dhtmlPage.unitedStates)
               .dragAndDrop(dhtmlPage.copenhagen, dhtmlPage.denmark)
               .dragAndDrop(dhtmlPage.seoul, dhtmlPage.southKorea)
               .dragAndDrop(dhtmlPage.rome, dhtmlPage.italy)
               .dragAndDrop(dhtmlPage.madrid, dhtmlPage.spain).perform();

        Thread.sleep(2000);

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}