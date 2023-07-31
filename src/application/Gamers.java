package application;

import java.util.Date;

public class Gamers {
    private int gamerID;
    private String gamerPassword;
    private String lastName;
    private String firstName;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Date accountCreatedDate;

    // Constructor
    public Gamers(int gamerID, String gamerPassword, String lastName, String firstName, String phone, String address1,
                  String address2, String city, String state, String postalCode, String country, Date accountCreatedDate) {
        this.gamerID = gamerID;
        this.gamerPassword = gamerPassword;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.accountCreatedDate = accountCreatedDate;
    }
    
    

    // Getter and Setter methods for each attribute
    public int getGamerID() {
        return gamerID;
    }

    public void setGamerID(int gamerID) {
        this.gamerID = gamerID;
    }

    public String getGamerPassword() {
        return gamerPassword;
    }

    public void setGamerPassword(String gamerPassword) {
        this.gamerPassword = gamerPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(Date accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }
}


