package com.amdck.phonepe.pg.model;

public class PhonepeOrder {
    private double amount;
    private String email;
    private long userId;


    public PhonepeOrder(double amount, String email, long userId) {
        this.amount = amount;
        this.email = email;
        this.userId = userId;

    }


    public PhonepeOrder() {

    }


    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "PhonepeOrder{" +
                "amount=" + amount +
                ", email='" + email + '\'' +
                "userId=" + userId +
                '}';
    }


}

