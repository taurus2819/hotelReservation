package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom iroom;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom iroom, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.iroom = iroom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getIroom() {
        return iroom;
    }

    public void setIroom(IRoom iroom) {
        this.iroom = iroom;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", iroom=" + iroom +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
