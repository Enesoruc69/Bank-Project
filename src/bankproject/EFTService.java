/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankproject;

/**
 *
 * @author macbookpro
 */
public class EFTService {
    private final Bank bank;
    private double eftFeePercentage;

    public EFTService(Bank bank) {
        this.bank = bank;
        this.eftFeePercentage = 0.01;  // Standart EFT ücreti %1 olarak başlar.
    }

    public void setEftFeePercentage(double feePercentage) {
        if (feePercentage < 0) {
            System.out.println("EFT ücreti negatif olamaz.");
            return;
        }
        this.eftFeePercentage = feePercentage;
        System.out.println("EFT ücreti oranı: " + (feePercentage * 100) + "% olarak ayarlandı.");
    }

    public void transfer(String fromAccountNum, String toAccountNum, double amount) {
        if (amount <= 0) {
            System.out.println("EFT tutarı sıfırdan büyük olmalıdır.");
            return;
        }

        double fee = amount * eftFeePercentage;
        double totalAmount = amount + fee;

        if (bank.getBalance(fromAccountNum) < totalAmount) {
            System.out.println("EFT yapılamıyor. Yetersiz bakiye.");
            return;
        }

        bank.withdraw(fromAccountNum, totalAmount);
        bank.deposit(toAccountNum, amount);
        
        EFTService eftService = new EFTService(bank);

        // EFT oranını değiştirme (Örneğin: %2)
        eftService.setEftFeePercentage(0.02);

        // EFT yapma işlemi
        eftService.transfer("12345", "67890", 1000);

        System.out.println(fromAccountNum + " hesabından " + toAccountNum + " hesabına " + amount + " TL gönderildi.");
        System.out.println("EFT ücreti: " + fee + " TL.");
    }
}
    