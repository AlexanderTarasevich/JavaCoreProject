package TMS.service;

import TMS.exception.InvalidAccountException;
import TMS.model.Account;
import TMS.model.Transaction;
import TMS.util.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferService {
    private Map<String, Account> accounts;

    public TransferService() throws FileNotFoundException {
        accounts = new HashMap<>();
        loadAccounts();
    }

    private void loadAccounts() throws FileNotFoundException {
        List<String> accountLines = FileHandler.readFile("accounts.txt");
        for (String line : accountLines) {
            String[] parts = line.split("\\|");
            Account account = new Account(parts[0], Integer.parseInt(parts[1]));
            accounts.put(account.getAccountNumber(), account);
        }
    }

    public void transfer(Transaction transaction) throws InvalidAccountException {
        Account fromAccount = accounts.get(transaction.getFromAccount());
        Account toAccount = accounts.get(transaction.getToAccount());

        if (fromAccount == null || toAccount == null) {
            throw new InvalidAccountException("Один из счетов не найден!");
        }

        if (fromAccount.getBalance() < transaction.getAmount()) {
            throw new InvalidAccountException("Недостаточно средств на счету отправителя");
        }

        fromAccount.setBalance(fromAccount.getBalance() - transaction.getAmount());
        toAccount.setBalance(toAccount.getBalance() + transaction.getAmount());

        updateAccountsFile();

    }

    private void updateAccountsFile() {
        List<String> accountLines = new ArrayList<>();
        for (Account account : accounts.values()) {

            String accountLine = account.getAccountNumber() + "|" + account.getBalance();
            accountLines.add(accountLine);
        }

        try {
            FileHandler.writeFile("accounts.txt", accountLines);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл 'accounts.txt': " + e.getMessage());
            e.printStackTrace();
        }
    }
}
