package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Elements
    @FindBy(xpath = "//div[@data-testid='search-round-trip-text']")
    public WebElement roundTripOption;

    @FindBy(xpath = "//div[@data-testid='endesign-flight-origin-autosuggestion']")
    public WebElement portOrigin;

    @FindBy(xpath = "//div[@data-testid='endesign-flight-destination-autosuggestion']")
    public WebElement portDestination;

    @FindBy(xpath = "//div[@data-testid='enuygun-homepage-flight-departureDate-datepicker']")
    public WebElement departureDate;

    @FindBy(xpath = "//div[@data-testid='enuygun-homepage-flight-returnDate-datepicker']")
    public WebElement returnDate;

    @FindBy(xpath = "//button[@data-testid='enuygun-homepage-flight-submitButton']")
    public WebElement buttonSubmit;


}




