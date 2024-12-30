package bankproject;

import java.util.Map;

public interface Bank {
    double getBalance(String accountNumber);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
    Map<String, Account> getAccounts(String accountNumber);

    // Transfer işlemi
    void transfer(String fromAccount, String toAccount, double amount);
}
