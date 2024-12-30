/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankproject;

/**
 *
 * @author macbookpro
 */
public class LoanService {
    private final Bank bank;

    public LoanService(Bank bank) {
        this.bank = bank;
    }

    public void provideLoan(String accountNum, double loanAmount, double interestRate, int termMonths) {
        double totalRepayment = calculateTotalRepayment(loanAmount, interestRate, termMonths);

        System.out.println("Kredi detayları:");
        System.out.println("- Hesap Numarası: " + accountNum);
        System.out.println("- Kredi Miktarı: " + loanAmount + " TL");
        System.out.println("- Faiz Oranı: %" + (interestRate * 100));
        System.out.println("- Vade: " + termMonths + " ay");
        System.out.println("- Geri Ödeme Tutarı: " + totalRepayment + " TL");

       
        bank.deposit(accountNum, loanAmount);
    }

    private double calculateTotalRepayment(double loanAmount, double interestRate, int termMonths) {
        // Basit faiz formülü: Ana Para + (Ana Para * Faiz Oranı * Ay Sayısı / 12)
        return loanAmount + (loanAmount * interestRate * termMonths / 12);
    }
}
