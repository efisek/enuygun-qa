package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlightTicketSearchPage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchFlightTest extends BaseTest {
    //the parameters used in testcase#1 and testcase#2
    public final String airlineName="Türk Hava Yolları";
    public final String tripType="round-trip";
    public final String from = "IST";
    public final String to = "ESB";
    public final String departureDate="2024-12-12";
    public final String returnDate="2024-12-15";
    public final int adultPassengerCount=1;
    public final int childPassengerCount=1;
    public final int infantPassengerCount=1;
    public final String cabinClass="business";
    public final String flightDirectionDeparture ="departure";
    public final String flightDirectionReturn ="return";
    public final  String time="noon";
    public final int handleNo=2;
    public final int offsetX=10;
    public final int offsetY=0;
    public final int startHour=10;
    public final int startMinute=00;
    public final int endHour=18;
    public final int endMinute=00;

    //the parameters used in testcase#3 addition to testcase#1 & testcase#2
    //inputs should be given and can be changed due to the desired test case
    public final String[] firstNames={};
    public final String[] lastNames={};
    public final String[] birthDateDay={};
    public final String[] birthMonthDay={};
    public final String[] birthDateYear={};
    public final String[] genders={};
    public final String email="";
    public final String mobileNo="";
    String cardNo = "";
    String cvv = "";
    String cardMonth="";
    String cardYear="";


    //not to write the same code block again
    public void commonStepsForAllCases(){
        homePage.searchFlightsRoundTrip(tripType,from,to,departureDate,returnDate,adultPassengerCount,childPassengerCount,infantPassengerCount,cabinClass);
        flightTicketSearchPage.filterDepartureFlightTime(flightDirectionDeparture,time,handleNo,offsetX, offsetY);
    }

    @Test
    public void validateFlightsDepartureTime(){
        commonStepsForAllCases();
        assertFlightTimes(flightDirectionDeparture,startHour,startMinute,endHour, endMinute);
    }

    @Test
    public void validateAirlinesAndFlightsPricesInAscendingOrder(){
        commonStepsForAllCases();
        flightTicketSearchPage.filterAirlines(airlineName);
        assertFlightPricesAreSortedInAscendingOrder();
    }

    @Test
    public void purchaseFlightTicket(){
        commonStepsForAllCases();
        flightTicketSearchPage.selectFirstFlight();
        flightReservationPage.fillContactInfo(email,mobileNo);
        flightReservationPage.fillPassengersInfoCard(firstNames,lastNames,birthDateDay,birthMonthDay,birthDateYear,genders);
        flightReservationPage.proceedToCheckout();
        paymentPage.fillCardInfo(cardNo,cvv,cardMonth,cardYear);
        paymentPage.submitPayment();
        assertPaymentConfirmationMessage();
    }


    //helper assert methods
    public void assertFlightTimes(String departureOrArrivalTime,int startHour, int startMinute, int endHour, int endMinute) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime startTime = LocalTime.of(startHour, startMinute);
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        List<WebElement> flightTimes = driver.findElements(By.xpath("//div[@data-testid='"+departureOrArrivalTime+"Time']"));

        for (WebElement flightTimeElement : flightTimes) {
            String flightTimeText = flightTimeElement.getText();  // to get the displayed time as text
            System.out.println(flightTimeText);

            LocalTime flightTime = LocalTime.parse(flightTimeText, timeFormatter);

            // to assert that the flights are between the times given
            Assert.assertTrue(flightTime.isAfter(startTime) && flightTime.isBefore(endTime),
                    "Flight time " + flightTimeText + " is not in the desired time range ("+startHour+":"+startMinute+" - "+endHour+":"+endMinute+").");
        }
    }

        public void assertFlightPricesAreSortedInAscendingOrder() {
            FlightTicketSearchPage flightTicketSearchPage = new FlightTicketSearchPage(driver);

            // to get the list of flight prices
            List<Double> flightPrices = flightTicketSearchPage.getFlightPrices();

            // to assert that the prices are sorted in ascending order
            for (int i = 0; i < flightPrices.size() - 1; i++) {
                Assert.assertTrue(flightPrices.get(i) <= flightPrices.get(i + 1),
                        "Flight prices are not sorted in ascending order: " + flightPrices);
            }

        }

        public void assertPaymentConfirmationMessage(){
            Assert.assertTrue(paymentPage.getConfirmationMessage().isDisplayed());
        }


}
