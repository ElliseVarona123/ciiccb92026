package com.jcash.model;

/**
 * Represents a JCash bank customer.
 * Fields are private (encapsulation) — outside code cannot change
 * balance or password directly, only through these controlled methods.
 */
public class User {
    private String accountNumber;
    private String username;
    private String password;
    private double balance;

    public User(String accountNumber, String username, String password, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // --- Getters ---
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    // --- Login check ---
    public boolean checkPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    // --- Controlled balance changes ---
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false; // invalid deposit
        }
        this.balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > this.balance) {
            return false; // invalid or insufficient funds
        }
        this.balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "accountNumber='" + accountNumber + '\'' +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}
