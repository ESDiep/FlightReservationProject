package com.example.airlineapp;

public class Booking {
    private static boolean userLoggedIn=false;

    public static boolean isDiscountApplied() {
        return discountApplied;
    }

    public static void setDiscountApplied(boolean discountApplied) {
        Booking.discountApplied = discountApplied;
    }

    private static boolean discountApplied=false;

    public static User getGlobalUserObject() {
        return globalUserObject;
    }

    public static void setGlobalUserObject(User globalUserObject) {
        Booking.globalUserObject = globalUserObject;
    }

    private static User globalUserObject;

    public static boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public static void setUserLoggedIn(boolean userLoggedIn) {
        Booking.userLoggedIn = userLoggedIn;
    }
}
