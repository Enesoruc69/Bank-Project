package bankproject;

import java.util.Map;

public class BankB implements Bank {
    private Map<String, Account> accounts;

    public BankB(String bankName) {
        this.accounts = BankDatabase.loadAccounts();  // Hesapları dosyadan yükle
    }

    @Override
    public double getBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        return account != null ? account.getBalance() : 0;
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            BankDatabase.saveAccounts(accounts);  // Hesap güncellenmişse kaydet
            BankDatabase.logTransaction(accountNumber, "DEPOSIT", amount);  // İşlem kaydını yaz
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.withdraw(amount);
            BankDatabase.saveAccounts(accounts);  // Hesap güncellenmişse kaydet
            BankDatabase.logTransaction(accountNumber, "WITHDRAWAL", amount);  // İşlem kaydını yaz
        }
    }

    @Override
    public Map<String, Account> getAccounts(String accountNumber) {
        return accounts;
    }

    // Transfer işlemi
    @Override
    public void transfer(String fromAccount, String toAccount, double amount) {
        Account from = accounts.get(fromAccount);
        Account to = accounts.get(toAccount);

        // Hesaplar ve yeterli bakiye kontrolü
        if (from != null && to != null && from.getBalance() >= amount) {
            // Gönderen hesaptan para çekme
            from.withdraw(amount);
            // Alıcı hesaba para yatırma
            to.deposit(amount);

            // Hesapları güncelle ve kaydet
            BankDatabase.saveAccounts(accounts);

            // İşlem kaydını yaz
            BankDatabase.logTransaction(fromAccount, "TRANSFER", amount);
            BankDatabase.logTransaction(toAccount, "TRANSFER_RECEIVED", amount);
        } else {
            System.out.println("Transfer işlemi başarısız. Yeterli bakiye veya hesap bulunamadı.");
        }
    }
}
