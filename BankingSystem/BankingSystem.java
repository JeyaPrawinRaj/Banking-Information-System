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
        sc.nextLine();

        if (deposit < 0) {
            System.out.println("Invalid initial deposit!");
            return;
        }

        User user = new User(name, address, phone, password);
        Account account = new Account(accountNumberGenerator++, user, deposit);

        if (deposit > 0) {
            account.addTransaction("Initial Deposit", deposit);
        }

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

                bankMenu(sc, acc);
                return;
            }
        }

        System.out.println("Invalid Account Number or Password!");
    }

    static void bankMenu(Scanner sc, Account acc) {

        while (true) {

            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transfer Funds");
            System.out.println("5. View Account Statement");
            System.out.println("6. View Account Details");
            System.out.println("7. Update Account Details");
            System.out.println("8. Logout");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

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
                    transferFunds(sc, acc);
                    break;

                case 5:
                    acc.showTransactions();
                    break;

                case 6:
                    acc.showAccountDetails();
                    break;

                case 7:
                    updateAccountDetails(sc, acc);
                    break;

                case 8:
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
        acc.addTransaction("Deposit", amount);

        System.out.println("Deposit Successful!");
        System.out.println("Current Balance: ₹" + acc.balance);
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
        acc.addTransaction("Withdrawal", amount);

        System.out.println("Withdrawal Successful!");
        System.out.println("Remaining Balance: ₹" + acc.balance);
    }

    static void checkBalance(Account acc) {
        System.out.println("Current Balance: ₹" + acc.balance);
    }

    static void transferFunds(Scanner sc, Account sender) {

        System.out.print("Enter Receiver Account Number: ");
        int receiverAccNo = sc.nextInt();

        if (receiverAccNo == sender.accountNumber) {
            System.out.println("Cannot transfer to same account!");
            return;
        }

        Account receiver = null;

        for (Account acc : accounts) {
            if (acc.accountNumber == receiverAccNo) {
                receiver = acc;
                break;
            }
        }

        if (receiver == null) {
            System.out.println("Receiver account not found!");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > sender.balance) {
            System.out.println("Insufficient Balance!");
            return;
        }

        sender.balance -= amount;
        receiver.balance += amount;

        sender.addTransaction("Transferred to Acc No " + receiver.accountNumber, amount);
        receiver.addTransaction("Received from Acc No " + sender.accountNumber, amount);

        System.out.println("Transfer Successful!");
        System.out.println("Your New Balance: ₹" + sender.balance);
    }

    // NEW FINAL FEATURE
    static void updateAccountDetails(Scanner sc, Account acc) {

        System.out.println("\n===== UPDATE ACCOUNT DETAILS =====");

        System.out.print("Enter New Name: ");
        String newName = sc.nextLine();

        System.out.print("Enter New Address: ");
        String newAddress = sc.nextLine();

        System.out.print("Enter New Phone: ");
        String newPhone = sc.nextLine();

        acc.user.updateDetails(newName, newAddress, newPhone);

        System.out.println("Account details updated successfully!");
    }
}