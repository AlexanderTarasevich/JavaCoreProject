package TMS.model;

public class Account {
    private String accountNumber;
    private int balance;

    public Account(String accountNumber, int balance) {
        if (!accountNumber.matches("\\d{5}-\\d{5}")) {
            throw new IllegalArgumentException("Не верный формат номера счета!");
        }
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}