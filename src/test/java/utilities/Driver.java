package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    /*
    POM'de Driver icin TestBase class'ina extends etmek yerine Driver class'indan static method'lar
    kullanarak driver olusturup ilgili ayrlarin yapilmasi ve en sond driver'in kapatilmasi tecih edilmistir.
    */

    /*
    POM'de Driver class'indaki "getDriver()'in" obje olusturularak kullanilmasini engellemek icin Singleton pattern
    kullanimi tercih edilmistir.
    Singleton pattern : tekli kullanim. Bir class'in farki class'lardan obje olusturularak kullanimini engellemek
    icin kullanilir. Bunu yapmak icin kullanilan constructor'i private yapilir ve baska class'larda Driver
    class'indan obje olusturulmasi mumkun olmaz.
    */

    /*
    -> Kod yazarken "clean code" kapsaminda daha sade kod yazabilmek amaci ile her test method'u icerisinde
       ayri bir object create etmektense bu objecti class seviyesinde instance olarak create edip
       test method'larında buna deger atamak ve kullanmak daha uygun bir yontemdir.
    */

    private Driver() {


    }

    static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {

            switch (ConfigReader.getProperty("browser")) {

                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "edge" :
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "safari" :
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;

                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "headless-chrome" :
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;

                default :
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        return driver;
    }

    public static void closeDriver() {

        if (driver != null) {

            driver.quit();
            driver = null;
        }
    }
}
