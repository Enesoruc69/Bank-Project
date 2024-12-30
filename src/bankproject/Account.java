package bankproject;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        if (accountHolderName.contains(" ")) {
            return accountHolderName.split(" ")[0];  // Ad kısmı
        }
        return accountHolderName;  // Eğer sadece tek bir isim varsa, onu döndür
    }

    public String getSurname() {
        if (accountHolderName.contains(" ")) {
            return accountHolderName.split(" ")[1];  // Soyad kısmı
        }
        return "";  // Soyadı yoksa boş döndür
    }

    // Setters
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    // Para Yatırma
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("Yatırılacak miktar pozitif olmalıdır.");
        }
    }

    // Para Çekme
    public void withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
        } else if (amount <= 0) {
            throw new IllegalArgumentException("Çekilecek miktar pozitif olmalıdır.");
        } else {
            throw new IllegalArgumentException("Yeterli bakiye yok.");
        }
    }

    // Bakiye Yeterliliği Kontrolü
    public boolean hasSufficientBalance(double amount) {
        return this.balance >= amount;
    }

    // Hesap Bilgilerini Döndüren Metot
    public String getAccount() {
        return "Hesap Numarası: " + accountNumber +
               "\nAd: " + getName() +
               "\nSoyad: " + getSurname() +
               "\nBakiye: " + balance + " TL";
    }

    // Hesap Bilgilerini String Olarak Döndürme
    @Override
    public String toString() {
        return "Hesap Numarası: " + accountNumber +
               "\nHesap Sahibi: " + accountHolderName +
               "\nBakiye: " + balance + " TL";
    }
}
