package BankingSystem;

import java.util.ArrayList;

public class Account {

    int accountNumber;
    User user;
    double balance;
    ArrayList<Transaction> transactions;

    public Account(int accountNumber, User user, double balance) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(String type, double amount) {
        transactions.add(new Transaction(type, amount, balance));
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n===== ACCOUNT STATEMENT =====");
        for (Transaction t : transactions) {
            t.displayTransaction();
        }
    }

    public void showAccountDetails() {
        System.out.println("\n===== ACCOUNT DETAILS =====");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Name           : " + user.name);
        System.out.println("Address        : " + user.address);
        System.out.println("Phone          : " + user.phone);
        System.out.println("Balance        : ₹" + balance);
    }
}