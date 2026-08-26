package com.jcash.model;

import java.time.LocalDateTime;

public abstract class Transaction {
    protected String accountNumber;
    protected double amount;
    protected LocalDateTime dateTime;

    public Transaction(String accountNumber, double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public abstract String describe();
}