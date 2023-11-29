package com.example.airlineapp;

public class Payment {
    private int paymentID;
    private String cardholdername;
    private int cardnumber;
    private int cardcode;

    public int getPaymentID() {
	return paymentID;
    }

    public void setPaymentID(int paymentID) {
	this.paymentID = paymentID;
    }

    public String getCardholdername() {
	return cardholdername;
    }

    public void setCardholdername(String cardholdername) {
	this.cardholdername = cardholdername;
    }

    public int getCardnumber() {
	return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
	this.cardnumber = cardnumber;
    }

    public int getCardcode() {
	return cardcode;
    }

    public void setCardcode(int cardcode) {
	this.cardcode = cardcode;
    }
}
