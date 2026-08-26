package com.jcash.model;

public class CashInTransaction extends Transaction {

    public CashInTransaction(String accountNumber, double amount) {
        super(accountNumber, amount);
    }

    @Override
    public String describe() {
        return "[CASH-IN] Account " + accountNumber + " received " + amount
                + " at " + dateTime;
    }
}