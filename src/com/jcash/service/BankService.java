package com.jcash.service;

import com.jcash.model.CashInTransaction;
import com.jcash.model.Transaction;
import com.jcash.model.TransferTransaction;
import com.jcash.model.User;
import com.jcash.storage.BankStorage;

import java.util.ArrayList;

/**
 * Holds the actual bank operations (Issues 5-9).
 * This is the layer Main.java's menu will call into —
 * it doesn't touch ArrayLists directly, it goes through BankStorage.
 */
public class BankService {
    private BankStorage storage;

    public BankService(BankStorage storage) {
        this.storage = storage;
    }

    // --- Issue 5: Login ---
    public User login(String username, String password) {
        User user = storage.findUserByUsername(username);
        if (user == null) {
            return null; // no such username
        }
        if (!user.checkPassword(password)) {
            return null; // wrong password
        }
        return user;
    }

    // --- Issue 6: Balance inquiry ---
    public double checkBalance(User user) {
        return user.getBalance();
    }

    // --- Issue 7: Cash-in ---
    public boolean cashIn(User user, double amount) {
        boolean success = user.deposit(amount);
        if (success) {
            storage.addTransaction(new CashInTransaction(user.getAccountNumber(), amount));
        }
        return success;
    }

    // --- Issue 8: Transfer ---
    public boolean transfer(User sender, String receiverAccountNumber, double amount) {
        User receiver = storage.findUserByAccountNumber(receiverAccountNumber);
        if (receiver == null) {
            return false; // receiver doesn't exist
        }
        if (!sender.withdraw(amount)) {
            return false; // insufficient funds or invalid amount
        }
        receiver.deposit(amount);
        storage.addTransaction(
                new TransferTransaction(sender.getAccountNumber(), receiverAccountNumber, amount));
        return true;
    }

    // --- Issue 9: Transaction logs ---
    public ArrayList<Transaction> getTransactionLogs(User user) {
        return storage.getTransactionsForAccount(user.getAccountNumber());
    }
}