package BankingSystem;

public class User {

    String name;
    String address;
    String phone;
    String password;

    public User(String name, String address, String phone, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}