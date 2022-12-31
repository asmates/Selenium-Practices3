package utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class TestBase02 {

    /*
    -> @BeforeClass ve @AfterClass notasyonlari her test asamasinda tum testlerden once ve sonra bir kere calisir
       ve Junit'deki gibi static olmak zorunda degildirler.
    */

    protected WebDriver driver;

    protected Actions actions;

    protected Faker faker;

    protected String tarih;

    @BeforeClass
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        actions = new Actions(driver);
        faker = new Faker();

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        tarih = date.format(formatter);
    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }
}
