package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightTicketSearchPage extends BasePage {

    public FlightTicketSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Elements
    @FindBy(xpath = "//div[@class='ctx-filter-departure-return-time card-header']")
    public WebElement filterDepartureReturnTime;

    @FindBy(xpath = "//p[@class='search__filter_departure-noon active']")
    public WebElement filterDepartureTimeNoon;

    @FindBy(xpath = "//div[@class='ctx-filter-airline card-header']")
    public WebElement filterAirlines;

    @FindBy(xpath = "//div[@data-testid='departureDepartureTimeSlider']")
    public WebElement departureTimeSlider;




}
