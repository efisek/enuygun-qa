package model;

import org.openqa.selenium.WebDriver;
import pages.FlightTicketSearchPage;
import pages.HomePage;

public class Flight extends HomePage {

    private Ports ports;
    private FlightDates flightDates;
    private int numberOfPassengers;
    private String cabinClass;

    public Flight(WebDriver driver) {
        super(driver);
    }

    public Ports getPorts() {
        return ports;
    }

    public void setPorts(Ports ports) {
        this.ports = ports;
    }

    public FlightDates getFlightDates() {
        return flightDates;
    }

    public void setFlightDates(FlightDates flightDates) {
        this.flightDates = flightDates;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }
}
