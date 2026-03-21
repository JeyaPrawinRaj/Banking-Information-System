package BankingSystem;

import java.util.*;

public class BankingSystem {

    static ArrayList<Account> accounts = new ArrayList<>();
    static int accountNumberGenerator = 1001;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    registerUser(sc);
                    break;

                case 2:
                    loginUser(sc);
                    break;

                case 3:
                    System.out.println("Thank you for using the system!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void registerUser(Scanner sc) {

        System.out.println("\n--- User Registration ---");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Create Password: ");
        String password = sc.nextLine();

        System.out.print("Initial Deposit: ");
        double deposit = sc.nextDouble();

        User user = new User(name, address, phone, password);

        Account account = new Account(accountNumberGenerator++, user, deposit);

        accounts.add(account);

        System.out.println("Registration Successful!");
        System.out.println("Your Account Number: " + account.accountNumber);
    }

    static void loginUser(Scanner sc) {

        System.out.println("\n--- Login ---");

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        for (Account acc : accounts) {

            if (acc.accountNumber == accNo && acc.user.getPassword().equals(password)) {

                System.out.println("Login Successful!");
                System.out.println("Welcome " + acc.user.name);

                bankMenu(sc,acc);
                return;
            }
        }

        System.out.println("Invalid Account Number or Password!");
    }
    // ================= WEEK 3 CODE =================

    static void bankMenu(Scanner sc, Account acc) {

        while (true) {

            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    deposit(sc, acc);
                    break;

                case 2:
                    withdraw(sc, acc);
                    break;

                case 3:
                    checkBalance(acc);
                    break;

                case 4:
                    System.out.println("Logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void deposit(Scanner sc, Account acc) {

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        acc.balance += amount;

        System.out.println("Deposit Successful!");
        System.out.println("Current Balance: " + acc.balance);
    }

    static void withdraw(Scanner sc, Account acc) {

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > acc.balance) {
            System.out.println("Insufficient Balance!");
            return;
        }

        acc.balance -= amount;

        System.out.println("Withdrawal Successful!");
        System.out.println("Remaining Balance: " + acc.balance);
    }

    static void checkBalance(Account acc) {
        System.out.println("Current Balance: " + acc.balance);
    }
}