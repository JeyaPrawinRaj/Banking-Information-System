package BankingSystem;

import java.time.LocalDateTime;

public class Transaction {

    String type;
    double amount;
    LocalDateTime date;
    double balanceAfter;

    public Transaction(String type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.date = LocalDateTime.now();
    }

    public void displayTransaction() {
        System.out.println(date + " | " + type + " | Amount: ₹" + amount + " | Balance: ₹" + balanceAfter);
    }
}