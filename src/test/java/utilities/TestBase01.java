package utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class TestBase01 {

    /*
    TestBase :
    -> Testlerden önce ve sonra tekrar tekrar kullanilan kodlari iceren yapidir.
    -> Her seferinde driver olusturmak yerine "utilities" package'i olusturup icerisine "TestBase" class'i
       olusturulur ve buraya kullanilacak methodlar yazilir. Bu method'larin kullanilabilmesi icin
       diger class'lardan "TestBase" class'ina "extends" yapilir.
    -> Class'in abstract yapilmasinin nedeni bu class'dan obje uretilmesinin onune gecmektir.
    -> Olusturulan driver’in farkli package’lardan kullanabilmesi icin ve guvenlik aciklarini engellemek
       amaciyla access modifier'in protected yapilmasi tercih edilir.
    -> @BeforeMethod ve @AfterMethod notasyonlari her test asamasindan once ve sonra calisir.
    */

    protected WebDriver driver;

    protected Actions actions;

    protected Faker faker;

    protected String tarih;

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        actions = new Actions(driver);
        faker = new Faker();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYMMddHHmmss");
        tarih = date.format(dtf);
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
