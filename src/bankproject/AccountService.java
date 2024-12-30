/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankproject;

/**
 *
 * @author macbookpro
 */
public class AccountService {
    private final Bank bank;

    public AccountService(Bank bank) {  
        this.bank = bank;
    }

    public void increaseBalance(String accountNum, double amount) {
        if (amount <= 0) {
            System.out.println("Eklenen miktar sıfırdan büyük olmalıdır.");
            return;
        }
        bank.deposit(accountNum, amount);
        System.out.println(accountNum + " hesabına " + amount + " TL eklendi.");
    }

    public void decreaseBalance(String accountNum, double amount) {
        double currentBalance = bank.getBalance(accountNum);

        if (amount <= 0) {
            System.out.println("Çekilen miktar sıfırdan büyük olmalıdır.");
            return;
        }

        if (currentBalance < amount) {
            System.out.println("Yetersiz bakiye. İşlem yapılamadı.");
            return;
        }

        bank.withdraw(accountNum, amount);
        System.out.println(accountNum + " hesabından " + amount + " TL çekildi.");
    }

    public void displayBalance(String accountNum) {
        double balance = bank.getBalance(accountNum);
        System.out.println(accountNum + " hesabının mevcut bakiyesi: " + balance + " TL");
    }
}
