package TMS.model;

public class Transaction {
    private String fromAccount;
    private String toAccount;
    private int amount;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Transaction(String fromAccount, String toAccount, int amount) {
        if (!fromAccount.matches("\\d{5}-\\d{5}") || !toAccount.matches("\\d{5}-\\d{5}")) {
            throw new IllegalArgumentException("Не верный формат номера счета!");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма перевода должна быть положительной!");
        }
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }
}