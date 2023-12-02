package com.example.airlineapp;

public class User {
    private String lastname;
    private String firstname;
    private String email;
    private boolean registered=false;
    private boolean loggedin=false;
    private String username;
    private String password;
    private String usertype;
    private String discountcode;


    public String getUsertype() {
	return usertype;
    }

    public void setUsertype(String usertype) {
	this.usertype = usertype;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getDiscountcode() {
	return discountcode;
    }

    public void setDiscountcode(String discountcode) {
	this.discountcode = discountcode;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public boolean isRegistered() {
	return registered;
    }

    public void setRegistered(boolean registered) {
	this.registered = registered;
    }

    public boolean isLoggedin() {
	return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
	this.loggedin = loggedin;
    }

    @Override
    public String toString() {
	return "User{" +
		"lastname='" + lastname + '\'' +
		", firstname='" + firstname + '\'' +
		", email='" + email + '\'' +
		", registered=" + registered +
		", loggedin=" + loggedin +
		", username='" + username + '\'' +
		", password='" + password + '\'' +
		", usertype='" + usertype + '\'' +
		", discountcode='" + discountcode + '\'' +
		'}';
    }
}
