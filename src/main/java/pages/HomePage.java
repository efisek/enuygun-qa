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
    @FindBy(xpath = "//input[@data-testid='endesign-flight-origin-autosuggestion-input']")
    public WebElement portOrigin;

    @FindBy(xpath = "//li[@data-testid='endesign-flight-origin-autosuggestion-option-item-0']")
    public WebElement suggestedOriginPort;

    @FindBy(xpath = "//input[@data-testid='endesign-flight-destination-autosuggestion-input']")
    public WebElement portDestination;

    @FindBy(xpath = "//li[@data-testid='endesign-flight-destination-autosuggestion-option-item-0']")
    public WebElement suggestedDestinationPort;

    @FindBy(xpath = "//div[@data-testid='enuygun-homepage-flight-departureDate-datepicker']")
    public WebElement departureDatePicker;

    @FindBy(xpath = "//div[@data-testid='enuygun-homepage-flight-returnDate-datepicker']")
    public WebElement returnDatePicker;

    @FindBy(xpath = "//button[@data-testid='enuygun-homepage-flight-submitButton']")
    public WebElement buttonSubmit;

    @FindBy(xpath = "//input[@data-testid='undefined-popover-button']")
    public WebElement passengerAndCabin;

    @FindBy(xpath = "//button[@data-testid='flight-adult-counter-plus-button']")
    public WebElement adultCounterPlusButton;

    @FindBy(xpath = "//button[@data-testid='flight-child-counter-plus-button']")
    public WebElement childCounterPlusButton;

    @FindBy(xpath = "//button[@data-testid='flight-infant-counter-plus-button']")
    public WebElement infantCounterPlusButton;

    //getters
    public WebElement getPortOrigin() {
        return portOrigin;
    }

    public WebElement getPortDestination() {
        return portDestination;
    }

    public WebElement getDepartureDatePicker() {
        return departureDatePicker;
    }

    public WebElement getReturnDatePicker() {
        return returnDatePicker;
    }

    public WebElement getButtonSubmit() {
        return buttonSubmit;
    }

    public WebElement getPassengerAndCabin() {
        return passengerAndCabin;
    }

    public WebElement getAdultCounterPlusButton() {
        return adultCounterPlusButton;
    }

    public WebElement getChildCounterPlusButton() {
        return childCounterPlusButton;
    }

    public WebElement getInfantCounterPlusButton() {
        return infantCounterPlusButton;
    }

    public WebElement getSuggestedOriginPort() {
        return suggestedOriginPort;
    }

    public WebElement getSuggestedDestinationPort() {
        return suggestedDestinationPort;
    }


    //methods
    public void searchFlightsRoundTrip(String tripType,String from, String to, String departureDate, String returnDate, int passengerAdult, int passengerChild, int passengerInfant, String cabinClass){
        selectTripType(tripType);
        sendInput(getPortOrigin(),from);
        clickToElement(getSuggestedOriginPort());
        sendInput(getPortDestination(),to);
        clickToElement(getSuggestedDestinationPort());
        clickToElement(getDepartureDatePicker());
        selectDate(departureDate);
        clickToElement(getReturnDatePicker());
        selectDate(returnDate);
        selectPassengers(passengerAdult,passengerChild,passengerInfant);
        selectCabinClass(cabinClass);
        clickToElement(getButtonSubmit());
    }

    public void selectTripType(String type){
        WebElement tripType = findElementByXpath("//div[@data-testid='search-"+type+"-text']");
        clickToElement(tripType);
    }

    public void selectDate(String date) {
        WebElement flightDate=findElementByXpath("//button[@title='"+date+"']");
        clickToElement(flightDate);
    }


    //select cabin class
    public void selectCabinClass(String cabinClass) {
        WebElement cabinType = findElementByXpath("//button[@data-testid='enuygun-homepage-flight-"+cabinClass+"Cabin']");
        clickToElement(cabinType);
    }


    //select the number of passengers
    public void selectPassengers(int numAdults, int numChildren, int numInfants) {
        clickToElement(getPassengerAndCabin());

        passengerCount(getAdultCounterPlusButton(), numAdults, 1);  // Default number of adult passengers is 1
        passengerCount(getChildCounterPlusButton(), numChildren, 0);  // Default 0
        passengerCount(getInfantCounterPlusButton(), numInfants, 0);  // Default 0
    }


    //passenger count based on passenger type
    public void passengerCount(WebElement incrementButton, int desiredCount, int defaultCount) {
        int difference = desiredCount - defaultCount;

        for (int i = 0; i < difference; i++) {
            clickToElement(incrementButton);
        }
    }


}




