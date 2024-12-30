/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankproject;

/**
 *
 * @author macbookpro
 */
public class TransferService {
    private final Bank sourceBank;
    private final Bank targetBank;

    public TransferService(Bank sourceBank, Bank targetBank) {
        this.sourceBank = sourceBank;
        this.targetBank = targetBank;
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        double sourceBalance = sourceBank.getBalance(fromAccount);
        if (sourceBalance < amount) {
            System.out.println("Kaynak hesapta yeterli bakiye yok.");
            return;
        }

        sourceBank.withdraw(fromAccount, amount); // Kaynak bankadan para çek
        targetBank.deposit(toAccount, amount);   // Hedef bankaya para yatır

        System.out.println("Transfer başarılı: " + amount + " TL " + fromAccount + " -> " + toAccount);
    }
}
