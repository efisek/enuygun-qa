package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class FlightTicketSearchPage extends BasePage {

    public FlightTicketSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Elements
    @FindBy(xpath = "//div[@class='ctx-filter-departure-return-time card-header']")
    public WebElement filterDepartureArrivalTime;

    @FindBy(xpath = "//div[@class='ctx-filter-airline card-header']")
    public WebElement filterAirlines;

    //methods
    public void filterDepartureFlightTime(String flightDirection, String time, int handleNo, int offsetX, int offsetY){
        filterFlightTime(flightDirection,time);
        moveSliderHandle(handleNo,offsetX,offsetY);
    }

    public void filterFlightTime(String flightDirection, String time){
        clickToElement(filterDepartureArrivalTime);
        WebElement timeFilter = findElementByXpath("//p[@class='search__filter_"+flightDirection+"-"+time+" ']");
        clickToElement(timeFilter);
    }

    public void moveSliderHandle(int sliderHandleNo, int offsetX, int offsetY){
        List<WebElement> sliderHandles = findElementsByXpath("//div[@class='rc-slider-handle rc-slider-handle-"+sliderHandleNo+"']");

        Actions actions = new Actions(driver);
        actions.
                clickAndHold(sliderHandles.getFirst()).
                moveByOffset(offsetX,offsetY).
                release().
                perform();
    }

    public void filterAirlines(String airlines){
        clickToElement(filterAirlines);
        WebElement airlineNameFilter = findElementByXpath("//span[contains(text(), '"+airlines+"')]");
        clickToElement(airlineNameFilter);
    }

    public List<Double> getFlightPrices() {
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@data-testid='flightInfoPrice']"));

        //to store the prices
        List<Double> prices = new ArrayList<>();

        //to extract the prices as doubles through the list
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();

            //to convert the string to double
            priceText =
                    priceText.replace("TL", "")
                    .replace(".", "")
                    .replace(",", ".")
                    .trim();

            double price = Double.parseDouble(priceText);
            System.out.println(price);
            prices.add(price);  // Add to the list
        }
        return prices;
    }



}
