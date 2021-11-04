package com.codecool.shop.model;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
