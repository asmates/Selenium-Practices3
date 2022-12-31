package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class AmazonPage {

    public AmazonPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(className = "navFooterMoreOnAmazon")
    public WebElement webTable;

    @FindBy (xpath = "//table//tbody//tr")
    public List<WebElement> satirSayisi;

    @FindBy (xpath = "//tbody//tr[1]//td")
    public List<WebElement> sutunSayisi;

    @FindBy (xpath = "//tbody//tr[3]//td[5]")
    public WebElement homeServices;

    @FindBy (xpath = "//tbody//tr[1]//td[9]")
    public WebElement abeBooks;

}
