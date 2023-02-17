package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SwagLabsPage {

    public SwagLabsPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (id = "user-name")
    public WebElement userName;

    @FindBy (xpath = "//*[@id='password']")
    public WebElement password;

    @FindBy (id = "login-button")
    public WebElement loginButton;

    @FindBy (xpath = "//*[@class='product_sort_container']")
    public static WebElement dropDownMenu;
}
