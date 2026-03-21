package BankingSystem;

public class Account {

    int accountNumber;
    User user;
    double balance;

    public Account(int accountNumber, User user, double balance) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = balance;
    }

}