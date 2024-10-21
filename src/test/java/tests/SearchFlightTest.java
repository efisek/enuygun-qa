package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlightTicketSearchPage;
import pages.HomePage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchFlightTest extends BaseTest {


    @Test
    public void validateFlightsDepartureTime(){
        HomePage homePage = new HomePage(driver);
        FlightTicketSearchPage flightTicketSearchPage = new FlightTicketSearchPage(driver);

        homePage.searchFlightsRoundTrip("round-trip","IST","ESB","2024-12-25","2024-12-28",1,0,0,"business");
        flightTicketSearchPage.filterDepartureFlightTime("departure","noon",2,10, 0);

        assertFlightTimes("departure",10,00,18, 00);
    }


    @Test
    public void validateAirlinesAndFlightsPricesInAscendingOrder(){
        HomePage homePage = new HomePage(driver);
        FlightTicketSearchPage flightTicketSearchPage = new FlightTicketSearchPage(driver);

        homePage.searchFlightsRoundTrip("round-trip","IST","ESB","2025-01-23","2025-01-26",1,1,1,"business");
        flightTicketSearchPage.filterDepartureFlightTime("departure","noon",2,10, 0);
        flightTicketSearchPage.filterAirlines("Türk Hava Yolları");

        assertFlightPricesAreSortedInAscendingOrder();
    }


    public void assertFlightTimes(String departureOrArrivalTime,int startHour, int startMinute, int endHour, int endMinute) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime startTime = LocalTime.of(startHour, startMinute);
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        List<WebElement> flightTimes = driver.findElements(By.xpath("//div[@data-testid='"+departureOrArrivalTime+"Time']"));

        for (WebElement flightTimeElement : flightTimes) {
            String flightTimeText = flightTimeElement.getText();  // to get the displayed time as text
            System.out.println(flightTimeText);

            LocalTime flightTime = LocalTime.parse(flightTimeText, timeFormatter);

            // to assert that the flights are between 10:00 - 18:00
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


}
