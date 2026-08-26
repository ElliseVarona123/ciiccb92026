package com.jcash.storage;

import com.jcash.model.Transaction;
import com.jcash.model.User;

import java.util.ArrayList;

/**
 * In-memory "database" for JCash.
 * ArrayLists replace the JDBC-backed tables — everything here lives only
 * while the program is running.
 *
 * Each method maps to a JDBC checklist item:
 *   addUser / addTransaction   -> "Insert"
 *   getAllUsers / findUserByAccountNumber / getAllTransactions -> "Read"
 *   updateUser (via returned reference + setters) -> "Update"
 *   removeUser -> "Delete"
 */
public class BankStorage {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // --- Insert ---
    public void addUser(User user) {
        users.add(user);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // --- Read ---
    public ArrayList<User> getAllUsers() {
        return users;
    }

    public ArrayList<Transaction> getAllTransactions() {
        return transactions;
    }

    public User findUserByAccountNumber(String accountNumber) {
        for (User u : users) {
            if (u.getAccountNumber().equals(accountNumber)) {
                return u;
            }
        }
        return null; // not found
    }

    public User findUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null; // not found
    }

    // --- Delete ---
    public boolean removeUser(String accountNumber) {
        User user = findUserByAccountNumber(accountNumber);
        if (user == null) {
            return false;
        }
        users.remove(user);
        return true;
    }

    // --- Transactions for one account (used by transaction logs feature) ---
    public ArrayList<Transaction> getTransactionsForAccount(String accountNumber) {
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAccountNumber().equals(accountNumber)) {
                result.add(t);
            }
        }
        return result;
    }
}   