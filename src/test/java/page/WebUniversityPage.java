package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class WebUniversityPage {

    public WebUniversityPage() {

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath ="//*[@type='text']")
    public WebElement addNewToDo;

    @FindBy (xpath = "//li" )
    public List<WebElement> drawButtons;

    @FindBy (xpath = "//i")
    public List<WebElement> deleteButtons;
}
