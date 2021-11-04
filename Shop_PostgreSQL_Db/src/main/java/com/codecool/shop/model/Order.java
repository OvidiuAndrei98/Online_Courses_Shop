package com.codecool.shop.model;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private String address2;
    private String country;
    private String state;
    private String zipCode;
    private String sameAddress;
    private String save;
    private String cardName;
    private String cardNumber;
    private String expiration;
    private String cvv;
    private LocalDate date;
    private int customerId;

    public Order(int orderId, String firstName, String lastName, String username, String email, String address, String address2, String country, String state, String zipCode, String sameAddress, String save, String cardName, String cardNumber, String expiration, String cvv, int customerId) {
        this.orderId = orderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.address = address;
        this.address2 = address2;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
        this.sameAddress = sameAddress;
        this.save = save;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = cvv;
        this.date = LocalDate.now();
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getSameAddress() {
        return sameAddress;
    }

    public void setSameAddress(String sameAddress) {
        this.sameAddress = sameAddress;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
