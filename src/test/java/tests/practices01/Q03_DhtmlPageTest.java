package tests.practices01;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import page.DhtmlPage;
import utilities.ConfigReader;
import utilities.Driver;

public class Q03_DhtmlPageTest {

    /*
    -> "http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html" sayfasina gidiniz.
    -> Ulkelere gore baskentleri doldurunuz.
    -> Sayfayi kapatiniz.
    */

    Actions actions;

    @Test
    public void test01() throws InterruptedException {

        actions = new Actions(Driver.getDriver());

        //"http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html" sayfasina gitme :
        Driver.getDriver().get(ConfigReader.getProperty("dhtmlUrl"));
        Thread.sleep(1000);

        //Ulkelere gore baskentleri doldurma :
        DhtmlPage dhtmlPage = new DhtmlPage();

        actions.dragAndDrop(dhtmlPage.oslo, dhtmlPage.norway)
               .dragAndDrop(dhtmlPage.stockholm, dhtmlPage.sweden)
               .dragAndDrop(dhtmlPage.washington, dhtmlPage.unitedStates)
               .dragAndDrop(dhtmlPage.copenhagen, dhtmlPage.denmark)
               .dragAndDrop(dhtmlPage.seoul, dhtmlPage.southKorea)
               .dragAndDrop(dhtmlPage.rome, dhtmlPage.italy)
               .dragAndDrop(dhtmlPage.madrid, dhtmlPage.spain).perform();

        Thread.sleep(1000);

        //Sayfayi kapatma :
        Driver.closeDriver();
    }
}
