package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightReservationPage extends BasePage {

    public FlightReservationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='contact_email']")
    public WebElement emailInfo;

    @FindBy(xpath = "//input[@id='contact_cellphone']")
    public WebElement contactMobile;

    @FindBy(xpath = "//button[@id='continue-button']")
    public WebElement proceedToCheckoutButton;

    public WebElement getEmailInfo() {
        return emailInfo;
    }

    public WebElement getContactMobile() {
        return contactMobile;
    }

    public WebElement getProceedToCheckoutButton() {
        return proceedToCheckoutButton;
    }


    //methods
    public void fillContactInfo(String email, String mobileNo){
        sendInput(getEmailInfo(),email);
        sendInput(getContactMobile(),mobileNo);

    }

    public void fillPassengersInfoCard(String[] firstName, String[] lastName, String[] birthDateDay, String[] birthDateMonth, String[] birthDateYear, String[] gender){
        for (int passengerIndex=0; passengerIndex<firstName.length; passengerIndex++){
            WebElement firstNameInputField = findElementByXpath("//input[@id='firstName_"+passengerIndex+"']");
            sendInput(firstNameInputField,firstName[passengerIndex]);

            WebElement lastNameInputField = findElementByXpath("//input[@id='firstName_"+passengerIndex+"']");
            sendInput(lastNameInputField,lastName[passengerIndex]);

            WebElement birthDateDayField = findElementByXpath("//select[@id='birthDateDay_"+passengerIndex+"']");
            sendInput(birthDateDayField,birthDateDay[passengerIndex]);

            WebElement birthDateMonthField = findElementByXpath("//select[@id='birthDateMonth_"+passengerIndex+"']");
            sendInput(birthDateMonthField,birthDateMonth[passengerIndex]);

            WebElement birthDateYearField = findElementByXpath("//select[@id='birthDateYear_"+passengerIndex+"']");
            sendInput(birthDateYearField,birthDateYear[passengerIndex]);

            WebElement trCitizenshipField = findElementByXpath("//input[@id='foreignCheckbox_"+passengerIndex+"']");
            clickToElement(trCitizenshipField);

            WebElement genderInfo = findElementByXpath("//input[@id='gender_"+gender+"_"+passengerIndex+"']");
            clickToElement(genderInfo);

        }
    }

    public void proceedToCheckout(){
        clickToElement(getProceedToCheckoutButton());
    }

}
