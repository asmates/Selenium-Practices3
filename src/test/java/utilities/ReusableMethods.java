package utilities;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static utilities.Driver.driver;

public class ReusableMethods {

    //=====ScreenShot(Sayfanin resmini alma)=====//
    public static String getScreenshot(String name) throws IOException {

        //Naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());

        //TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        //Full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);

        //Save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);

        return target;
    }

    //=====ScreenShot WebElement(Bir webelement'in resmini alma)=====//
    public static String getScreenshotWebElement(String name, WebElement element) throws IOException {

        //Naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        //TakesScreenshot is an interface of selenium that takes the screenshot
        File source = element.getScreenshotAs(OutputType.FILE);

        //Full path to the screenshot location
        String wElementSS = System.getProperty("user.dir") + "/target/WElementScreenshots/" + name + date + ".png";
        File finalDestination = new File(wElementSS);

        //Save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);

        return wElementSS;
    }

    //=====Switching Window(Pencereler arasi gecis)=====//
    public static void switchToWindow(String targetTitle) {

        String origin = Driver.getDriver().getWindowHandle();

        for (String handle : Driver.getDriver().getWindowHandles()) {

            Driver.getDriver().switchTo().window(handle);

            if (Driver.getDriver().getTitle().equals(targetTitle)) {

                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    //=====Hover Over(Element'in Uzerinde bekleme)=====//
    public static void hover(WebElement element) {

        Actions actions = new Actions(Driver.getDriver());

        actions.moveToElement(element).perform();
    }

    //=====Return a list of String Given a list of WebElement=====//
    public static List<String> getElementsText(List<WebElement> list) {

        List<String> elementTexts = new ArrayList<>();

        for (WebElement each : list) {

            if (!each.getText().isEmpty()) {

                elementTexts.add(each.getText());
            }
        }
        return elementTexts;
    }

    //=====Returns the text of the Element Given an Element Locator=====//
    public static List<String> getElementsText(By locator) {

        List<WebElement> elements = Driver.getDriver().findElements(locator);

        List<String> elementTexts = new ArrayList<>();

        for (WebElement each : elements) {

            if (!each.getText().isEmpty()) {

                elementTexts.add(each.getText());
            }
        }
        return elementTexts;
    }

    //=====Hard Wait with Thread.Sleep=====//
    public static void waitFor(int sec) {

        try {

            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    //=====Explicit Wait=====//
    public static WebElement waitForVisibility(WebElement element, int timeout) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));

        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));

        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void clickWithTimeOut(WebElement element, int timeout) {

        for (int i = 0; i < timeout; i++) {

            try {

                element.click();

                return;
            } catch (WebDriverException e) {

                waitFor(1);
            }
        }
    }

    public static void waitForPageToLoad(long timeout) {

        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {

                return ((JavascriptExecutor) driver)
                       .executeScript("return document.readyState").equals("complete");
            }
        };

        try {

            System.out.println("Waiting for page to load...");

            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));

            wait.until(expectation);
        } catch (Throwable error) {

            System.out.println("Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }

    //=====Fluent Wait=====//
    public static WebElement fluentWait(final WebElement webElement, int timeout) {

        /*
        -> FluentWait<WebDriver> wait = new FluentWait<WebDriver>
           (Driver.java.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
        */

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
        .withTimeout(Duration.ofSeconds(3)) //Wait 3 second each time
        .pollingEvery(Duration.ofSeconds(1)); //Check for the element every 1 second

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {

                return webElement;
            }
        });

        return element;
    }

    /**
     * Performs double click action on an element
     *
     * @param element
    */
    public static void doubleClick(WebElement element) {

        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * @param element
     * @param check
    */
    public static void selectCheckBox(WebElement element, boolean check) {

        if (check) {

            if (!element.isSelected()) {

                element.click();
            }
        }
        else {

            if (element.isSelected()) {

                element.click();
            }
        }
    }

    /**
     * Selects a random value from a dropdown list and returns the selected Web Element
     *
     * @param select
     * @return
    */
    public static WebElement selectRandomTextFromDropdown(Select select) {

        Random random = new Random();

        List<WebElement> weblist = select.getOptions();

        int optionIndex = 1 + random.nextInt(weblist.size() - 1);

        select.selectByIndex(optionIndex);

        return select.getFirstSelectedOption();
    }

    static Faker faker;

    static Actions actions;

    static Select select;

    static WebElement ddm;

    //=====Faker=====//
    public static Faker getFaker() { //getFaker method

        return faker = new Faker();
    }

    //=====Actions=====//
    public static Actions getActions() { //getActions method

        return actions = new Actions(Driver.getDriver());
    }

    //=====Select=====//
    public static Select select(WebElement ddm) {

        return select = new Select(ddm);
    }

    //=====JavascriptExecutor=====//
    public static void jsclick(WebElement webElement) {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        js.executeScript("arguments[0].click();", webElement);

        try {

            webElement.click();
        } catch (Exception e) {

            JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();

            executor.executeScript("arguments[0].click();", webElement);
        }
    }

    public static String getValueWithJs(String elementId) {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        String value = js.executeScript("return document.getElementById('" + elementId + "').value").toString();

        return value;
    }

    public static boolean elementIsDisplayed(String fieldName) {

        By locator = By.xpath("//*[text()='" + fieldName + "']");

        waitForVisibility(locator, 4);

        return driver.findElement(locator).isDisplayed();
    }

    public static void jsScrollClick(WebElement webElement) { //kaydir ve tikla

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        try {

            webElement.click();
        } catch (Exception e) {

            js.executeScript("arguments[0].scrollIntoView(true);", webElement);

            js.executeScript("arguments[0].click()", webElement);
        }
    }

    public static Random random() {

        Random random;

        return random = new Random();
    }

    public static void jsScroll(WebElement webElement) {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static void toBeClickableWait(WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void visibilityOfWait(WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void visibilityOfElementLocatedWait(By located) {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(located));
    }

    public static void enterField(String fieldName, String fieldValue) {

        By locator = By.xpath("//label[text()='" + fieldName + "']//following-sibling::input");

        driver.findElement(locator).sendKeys(fieldValue);

        sendKeys(Keys.TAB);
    }

    public static void homepageClick(String fieldName) {

        By locator = By.xpath("//*[text()='" + fieldName + "']");

        driver.findElement(locator).click();
    }

    public static void click(By locator) {

        driver.findElement(locator).click();
    }

    public static void clickButton(String buttonName) {

        By locator = By.xpath("//*[text()='" + buttonName + "']/parent::button");

        driver.findElement(locator).click();
    }

    public static void scrollDownJs() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0, 250)", "");
    }

    public static boolean confirmValid(String fieldName) {

        boolean flag = false;

        try {

            flag = driver.findElement(By.xpath
            ("//label[text()='" + fieldName + "']//following-sibling::div")).isDisplayed();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return flag;
    }

    public static void sendKeys(Keys Keys) {

        Actions actions = new Actions(driver);

        actions.sendKeys(Keys);
    }

    public static void voidClickByAction(String fieldName) {

        By locator = By.xpath("//label[text()='" + fieldName + "']//following-sibling::input");

        Actions actions = new Actions(driver);

        actions.click(driver.findElement(locator)).perform();
    }

    public static boolean containTextDisplayed(String fieldName) {

        boolean flag = false;

        try {

            driver.findElement(By.xpath
            ("//div[@class='Toastify']//child::div[@role='alert']")).isDisplayed();

            flag = true;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return flag;
    }

    public static List<String> getHeadText() {

        By locator = By.xpath("//*[@class='hand']");

        List<WebElement> headList = driver.findElements(locator);

        List<String> headListString = new ArrayList<>();

        for (WebElement x : headList) {

            headListString.add(x.getText());
        }
        return headListString;
    }

    public static void sayfaKucult() throws AWTException {

        Robot robot = new Robot();

        for (int i = 0; i < 2; i++) {

            robot.keyPress(KeyEvent.VK_CONTROL); //CTRL'ye tiklandi.
            robot.keyPress(KeyEvent.VK_SUBTRACT); //"-'ye" tiklandi.
            robot.keyRelease(KeyEvent.VK_SUBTRACT); //CTRL'yi birakti.
            robot.keyRelease(KeyEvent.VK_CONTROL); //"-'yi" birakti.
            //"CTRL -'ye" basilarak ekran belirlenen miktarda kucultulmus oldu.
        }
    }
}



