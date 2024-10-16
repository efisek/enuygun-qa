package model;

import java.util.Date;

public class FlightDates {
    private Date departureDate;
    private Date returnDate;

    public FlightDates(Date departureDate, Date returnDate) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
