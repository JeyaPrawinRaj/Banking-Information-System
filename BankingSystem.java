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
                return;
            }
        }

        System.out.println("Invalid Account Number or Password!");
    }
}