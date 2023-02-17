package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class TestBaseRapor {

    protected static ExtentReports extentReports;
    //extent report'a ilk atamayi yapar.

    protected static ExtentTest extentTest;
    //test; pass veya failed gibi bilgileri kaydeder. Ayrica ekran resmi icin de kullanilir.

    protected static ExtentHtmlReporter extentHtmlReporter;
    //Html raporu duzenler.

    //Test islemine baslamadan hemen once(test method'undan once degil, tum test isleminden once) olusturulur.
    @BeforeTest(alwaysRun = true) //alwaysRun; her zaman calistirir.
    public void setUpTest() {

        extentReports = new ExtentReports();

        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        String filePath = System.getProperty("user.dir") + "/target/Rapor/rapor" + date + ".html";
        //Rapor olusturulduktan sonra raporun nereye eklenecegi yazilir.

        extentHtmlReporter = new ExtentHtmlReporter(filePath);
        extentReports.attachReporter(extentHtmlReporter);
        //Olusturulma istenilen rapor(html formatinda) baslatilir, "filePath" ile dosya yolu belirlenir.

        extentReports.setSystemInfo("Enviroment","Test");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("Automation Engineer", "asmates");
        extentHtmlReporter.config().setDocumentTitle("Rapor");
        extentHtmlReporter.config().setReportName("TestNG Reports");
        //Istenilen bilgiler buraya eklenir.
    }

    //Her test method'undan sonra eger testte hata varsa ekran goruntusu alinip rapora eklenir.
    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) { //Eger testin sonucu basarisizsa.

            String screenshotLocation = ReusableMethods.getScreenshot(result.getName());

            extentTest.fail(result.getName());
            extentTest.addScreenCaptureFromPath(screenshotLocation);
            extentTest.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) { //Eger test calistirilmadan gecilmezse.

            extentTest.skip("Test Case is skipped: " + result.getName()); //Ignore olanlar.
        }
        Driver.closeDriver();
    }

    //Raporlandirma sonlandirilir.
    @AfterTest(alwaysRun = true)
    public void tearDownTest() {

        extentReports.flush();
    }
}