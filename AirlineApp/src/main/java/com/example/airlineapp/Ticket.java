package com.example.airlineapp;

public class Ticket {
    private String flightID=null;
    private String cust_lastname=null;
    private String cust_firstname=null;
    private String customerID=null;

    private String seatID=null;
    private double price=0;
    private String depart_time;
    private String arrival_time;
    private String originOutput;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    private String destinationOutput;
    private String flightdate;
    private int ticketID;
    private int paymentID;

    @Override
    public String toString() {
        return "Ticket Information: \n" +
                "\nFlightID:  " + flightID  +
                ", \nSeatID:  " + seatID  +
                ", \nPrice:  $" + price +
                ", \nDepart time:  " + depart_time  +
                ", \nArrival time:  " + arrival_time  +
                ", \nOrigin:  " + originOutput  +
                ", \nDestination:  " + destinationOutput  +
                ", \nFlightdate:  " + flightdate  +
                ", \nAircraft:  " + aircraft ;
    }

    public String toStringForCancel() {
        return "Ticket Information: \n" +
                "\nPassenger First Name:  " + cust_firstname  +
                "\nLast Name:  " + cust_lastname  +
                "\nFlightID:  " + flightID  +
                ", \nSeatID:  " + seatID  +
                ", \nPrice:  $" + price +
                ", \nDepart time:  " + depart_time  +
                ", \nArrival time:  " + arrival_time  +
                ", \nOrigin:  " + originOutput  +
                ", \nDestination:  " + destinationOutput  +
                ", \nFlight Date:  " + flightdate  +
                ", \nEmail:  " + email ;
    }

    public String toStringForDisplay() {
        return  "\nPassenger First Name:  " + cust_firstname  +
                "\nLast Name:  " + cust_lastname  +
                "\nTicketID:  " + ticketID +
                "\nPaymentID:  " + paymentID  +
                "\nFlightID:  " + flightID  +
                ", \nSeatID:  " + seatID  +
                ", \nPrice:  $" + price +
                ", \nAircraft:  " + aircraft +
                ", \nEmail:  " + email ;
    }

    private String aircraft;

    public String getCust_lastname() {
        return cust_lastname;
    }

    public void setCust_lastname(String cust_lastname) {
        this.cust_lastname = cust_lastname;
    }

    public String getCust_firstname() {
        return cust_firstname;
    }

    public void setCust_firstname(String cust_firstname) {
        this.cust_firstname = cust_firstname;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(String depart_time) {
        this.depart_time = depart_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getOriginOutput() {
        return originOutput;
    }

    public void setOriginOutput(String originOutput) {
        this.originOutput = originOutput;
    }

    public String getDestinationOutput() {
        return destinationOutput;
    }

    public void setDestinationOutput(String destinationOutput) {
        this.destinationOutput = destinationOutput;
    }

    public String getFlightdate() {
        return flightdate;
    }

    public void setFlightdate(String flightdate) {
        this.flightdate = flightdate;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }


    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
//        System.out.println("Ticket flightID is set to: "+flightID);
    }
}
