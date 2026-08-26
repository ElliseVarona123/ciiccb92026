package com.jcash;

import com.jcash.model.Transaction;
import com.jcash.model.User;
import com.jcash.service.BankService;
import com.jcash.storage.BankStorage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankStorage storage = new BankStorage();
        BankService bankService = new BankService(storage);

        // Seed a couple of sample accounts so there's something to log into
        storage.addUser(new User("1001", "bhea", "1234", 500.0));
        storage.addUser(new User("1002", "ellise", "5678", 1000.0));

        System.out.println("=== Welcome to JCash ===");

        // --- Login loop ---
        User currentUser = null;
        while (currentUser == null) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            currentUser = bankService.login(username, password);

            if (currentUser == null) {
                System.out.println("Invalid username or password. Try again.\n");
            }
        }

        System.out.println("\nLogin successful. Welcome, " + currentUser.getUsername() + "!");

        // --- Main menu loop ---
        boolean running = true;
        while (running) {
            System.out.println("\n--- JCash Menu ---");
            System.out.println("1. Check balance");
            System.out.println("2. Cash-in");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction logs");
            System.out.println("5. Logout / Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Current balance: " + bankService.checkBalance(currentUser));
                    break;

                case "2":
                    System.out.print("Amount to cash-in: ");
                    double cashInAmount = Double.parseDouble(scanner.nextLine());
                    boolean cashInSuccess = bankService.cashIn(currentUser, cashInAmount);
                    if (cashInSuccess) {
                        System.out.println("Cash-in successful. New balance: " + currentUser.getBalance());
                    } else {
                        System.out.println("Cash-in failed. Amount must be positive.");
                    }
                    break;

                case "3":
                    System.out.print("Receiver account number: ");
                    String receiverAccount = scanner.nextLine();
                    System.out.print("Amount to transfer: ");
                    double transferAmount = Double.parseDouble(scanner.nextLine());
                    boolean transferSuccess = bankService.transfer(currentUser, receiverAccount, transferAmount);
                    if (transferSuccess) {
                        System.out.println("Transfer successful. New balance: " + currentUser.getBalance());
                    } else {
                        System.out.println("Transfer failed. Check receiver account number and your balance.");
                    }
                    break;

                case "4":
                    ArrayList<Transaction> logs = bankService.getTransactionLogs(currentUser);
                    if (logs.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        System.out.println("--- Transaction Logs ---");
                        for (Transaction t : logs) {
                            System.out.println(t.describe());
                        }
                    }
                    break;

                case "5":
                    running = false;
                    System.out.println("Logged out. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        }

        scanner.close();
    }
}