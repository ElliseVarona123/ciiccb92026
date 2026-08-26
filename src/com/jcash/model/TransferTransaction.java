package com.jcash.model;

public class TransferTransaction extends Transaction {
    private String receiverAccount;

    public TransferTransaction(String senderAccount, String receiverAccount, double amount) {
        super(senderAccount, amount);
        this.receiverAccount = receiverAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    @Override
    public String describe() {
        return "[TRANSFER] Account " + accountNumber + " sent " + amount
                + " to account " + receiverAccount + " at " + dateTime;
    }
}