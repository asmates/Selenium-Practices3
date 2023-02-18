package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HotelMyCampPage {

    public HotelMyCampPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//*[text()='Log in']")
    public WebElement anaLogIn;

    @FindBy (xpath = "//*[@id='UserName']")
    public WebElement userName;

    @FindBy (xpath = "//*[@id='Password']")
    public WebElement password;

    @FindBy (id = "btnSubmit")
    public WebElement altLogIn;

    @FindBy (xpath = "//*[@class='col-md-12']")
    public WebElement basariliGiris;

    @FindBy (id= "divMessageResult")
    public WebElement girisYapilamadi;
}
