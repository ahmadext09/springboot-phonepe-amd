package com.amdck.phonepe.pg.model;

public class PhonepeOrder {
    private double amount;
    private String email;


    public PhonepeOrder(double amount, String email) {
        this.amount = amount;
        this.email = email;
    }


    public PhonepeOrder() {

    }


    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PhonepeOrder{" +
                "amount=" + amount +
                ", email='" + email + '\'' +
                '}';
    }


}

