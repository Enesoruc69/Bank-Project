package bankproject;

import java.io.*;
import java.util.*;

public class BankDatabase {

    private static final String ACCOUNTS_FILE = "/Users/macbookpro/Desktop/accounts.txt";  // Dosya yolu
    private static final String TRANSACTIONS_FILE = "/Users/macbookpro/Desktop/transactions.txt";  // Dosya yolu

    // Hesapları dosyadan yükler
    public static Map<String, Account> loadAccounts() {
        Map<String, Account> accounts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");  // Hesap numarası, isim ve bakiye virgülle ayrılmış
                if (parts.length == 3) {
                    String accountNumber = parts[0];
                    String accountHolderName = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    accounts.put(accountNumber, new Account(accountNumber, accountHolderName, balance));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Hesapları dosyaya kaydeder
    public static void saveAccounts(Map<String, Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (Account account : accounts.values()) {
                writer.write(account.getAccountNumber() + "," + account.getAccountHolderName() + "," + account.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // İşlem kaydını dosyaya yazar
    public static void logTransaction(String accountNumber, String transactionType, double amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            writer.write("Account: " + accountNumber + ", Type: " + transactionType + ", Amount: " + amount);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
