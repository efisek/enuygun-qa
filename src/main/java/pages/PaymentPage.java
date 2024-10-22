package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BasePage {
    public PaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@data-testid='cardNumber']")
    public WebElement cardNumberInputField;

    @FindBy(xpath = "//input[@data-testid='CVV']")
    public WebElement cvvInputField;

    @FindBy(xpath = "//input[@data-testid='cardMonth-input']")
    public WebElement cardMonthInputField;

    @FindBy(xpath = "//input[@data-testid='cardYear-input']")
    public WebElement cardYearInputField;

    @FindBy(xpath = "//button[@data-testid='payment-form-submit-button']")
    public WebElement paymentSubmitButton;

    @FindBy(xpath = "${confirmation-message-xpath}")
    public WebElement confirmationMessage;

    public WebElement getCardNumberInputField() {
        return cardNumberInputField;
    }

    public WebElement getCvvInputField() {
        return cvvInputField;
    }

    public WebElement getCardMonthInputField() {
        return cardMonthInputField;
    }

    public WebElement getCardYearInputField() {
        return cardYearInputField;
    }

    public WebElement getPaymentSubmitButton() {
        return paymentSubmitButton;
    }

    public WebElement getConfirmationMessage() {
        return confirmationMessage;
    }

    public void fillCardInfo(String cardNo, String cvv, String month, String year){
        sendInput(getCardNumberInputField(),cardNo);
        sendInput(getCvvInputField(),cvv);
        sendInput(getCardMonthInputField(),month);
        sendInput(getCardYearInputField(),year);
    }

    public void submitPayment(){
        clickToElement(getPaymentSubmitButton());
    }


}
