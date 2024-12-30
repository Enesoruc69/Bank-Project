package bankproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankUI extends JFrame {
    private static BankA bankA = new BankA("Bank A");
    private static BankB bankB = new BankB("Bank B");
    
    private JLabel resultLabel;  // Sonuç etiketini tanımlıyoruz
    private JLabel accountInfoLabel;  // Hesap bilgilerini gösterecek etiket
    
    public BankUI(String bankName) {
        // Ana pencereyi oluştur
        setTitle(bankName + " Menü");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));  // Ortalanacak şekilde layout

        // Banka menüsüne göre butonlar oluşturulacak
        JButton depositButton = new JButton("Para Yatır");
        JButton withdrawButton = new JButton("Para Çek");
        JButton transferButton = new JButton("Para Gönder");
        JButton currencyButton = new JButton("Döviz Kur Çevir");
        JButton loanButton = new JButton("Kredi Başvuru");
        JButton accountInfoButton = new JButton("Hesap Bilgileri");

        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(currencyButton);
        add(loanButton);
        add(accountInfoButton);

        // Sonuçları göstermek için bir label (sonuçlar burada gösterilecek)
        resultLabel = new JLabel("");
        add(resultLabel);

        // Hesap bilgilerini gösterecek bir etiket
        accountInfoLabel = new JLabel("");
        add(accountInfoLabel);

        // Para yatırma işlemi
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = JOptionPane.showInputDialog("Hesap Numarası:");
                String amount = JOptionPane.showInputDialog("Yatırılacak Tutar (TL):");

                try {
                    double amountValue = Double.parseDouble(amount);
                    if (bankName.equals("Bank A")) {
                        bankA.deposit(accountNumber, amountValue);
                        resultLabel.setText("Yatırma başarılı. Yeni bakiye: " + bankA.getBalance(accountNumber) + " TL");
                        accountInfoLabel.setText("Hesap Numarası: " + accountNumber + " | Bakiye: " + bankA.getBalance(accountNumber) + " TL");
                    } else {
                        bankB.deposit(accountNumber, amountValue);
                        resultLabel.setText("Yatırma başarılı. Yeni bakiye: " + bankB.getBalance(accountNumber) + " TL");
                        accountInfoLabel.setText("Hesap Numarası: " + accountNumber + " | Bakiye: " + bankB.getBalance(accountNumber) + " TL");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Geçersiz miktar.");
                }
            }
        });

        // Para çekme işlemi
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = JOptionPane.showInputDialog("Hesap Numarası:");
                String amount = JOptionPane.showInputDialog("Çekilecek Tutar (TL):");

                try {
                    double amountValue = Double.parseDouble(amount);
                    if (bankName.equals("Bank A")) {
                        if (bankA.getBalance(accountNumber) >= amountValue) {
                            bankA.withdraw(accountNumber, amountValue);
                            resultLabel.setText("Çekme başarılı. Yeni bakiye: " + bankA.getBalance(accountNumber) + " TL");
                            accountInfoLabel.setText("Hesap Numarası: " + accountNumber + " | Bakiye: " + bankA.getBalance(accountNumber) + " TL");
                        } else {
                            resultLabel.setText("Yeterli bakiye yok.");
                        }
                    } else {
                        if (bankB.getBalance(accountNumber) >= amountValue) {
                            bankB.withdraw(accountNumber, amountValue);
                            resultLabel.setText("Çekme başarılı. Yeni bakiye: " + bankB.getBalance(accountNumber) + " TL");
                            accountInfoLabel.setText("Hesap Numarası: " + accountNumber + " | Bakiye: " + bankB.getBalance(accountNumber) + " TL");
                        } else {
                            resultLabel.setText("Yeterli bakiye yok.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Geçersiz miktar.");
                }
            }
        });

        // Para gönderme işlemi (Bank A'dan Bank B'ye veya Bank A içi)
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromAccount = JOptionPane.showInputDialog("Gönderen Hesap Numarası:");
                String toAccount = JOptionPane.showInputDialog("Alıcı Hesap Numarası:");
                String amount = JOptionPane.showInputDialog("Gönderilecek Tutar (TL):");

                try {
                    double amountValue = Double.parseDouble(amount);
                    if (bankName.equals("Bank A")) {
                        if (bankA.getBalance(fromAccount) >= amountValue) {
                            bankA.transfer(fromAccount, toAccount, amountValue);
                            resultLabel.setText("Transfer başarılı.");
                            accountInfoLabel.setText("Hesap Numarası: " + fromAccount + " | Bakiye: " + bankA.getBalance(fromAccount) + " TL");
                        } else {
                            resultLabel.setText("Yeterli bakiye yok.");
                        }
                    } else {
                        if (bankB.getBalance(fromAccount) >= amountValue) {
                            bankB.transfer(fromAccount, toAccount, amountValue);
                            resultLabel.setText("Transfer başarılı.");
                            accountInfoLabel.setText("Hesap Numarası: " + fromAccount + " | Bakiye: " + bankB.getBalance(fromAccount) + " TL");
                        } else {
                            resultLabel.setText("Yeterli bakiye yok.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Geçersiz miktar.");
                }
            }
        });

        // Döviz Kur Çevirme işlemi
        currencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog("Çevrilecek Tutar (TL):");
                try {
                    double amountValue = Double.parseDouble(amount);
                    double convertedAmount = convertCurrency(amountValue);  // Fiktif bir döviz dönüşümü
                    resultLabel.setText("Çevrilen Tutar: " + convertedAmount + " USD");
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Geçersiz miktar.");
                }
            }
        });

        // Kredi başvuru işlemi
        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = JOptionPane.showInputDialog("Hesap Numarası:");
                String amount = JOptionPane.showInputDialog("Kredi Tutarı (TL):");

                try {
                    double amountValue = Double.parseDouble(amount);
                    double balance = 0;

                    // Hesap bakiyesi kontrolü
                    if (bankName.equals("Bank A")) {
                        balance = bankA.getBalance(accountNumber);
                    } else {
                        balance = bankB.getBalance(accountNumber);
                    }

                    // Kredi miktarının, mevcut bakiyenin 2 katından fazla olup olmadığını kontrol et
                    if (amountValue > balance * 2) {
                        resultLabel.setText("Kredi talebiniz başarısız. Kredi limitiniz bakiyenizin 2 katını aşamaz.");
                    } else {
                        resultLabel.setText("Kredi başvurusu başarılı.");

                        // Kredi talebi başarılıysa bakiyeye ekle
                        if (bankName.equals("Bank A")) {
                            bankA.deposit(accountNumber, amountValue);
                            resultLabel.setText("Kredi başarılı. Yeni bakiye: " + bankA.getBalance(accountNumber) + " TL");
                            accountInfoLabel.setText("Hesap Numarası: " + accountNumber + " | Bakiye: " + bankA.getBalance(accountNumber) + " TL");
                        } else {
                            bankB.deposit(accountNumber, amountValue);
                            resultLabel.setText("Kredi başarılı. Yeni bakiye: " + bankB.getBalance(accountNumber) + " TL");
                            accountInfoLabel.setText("Hesap Numarası: " + accountNumber + " | Bakiye: " + bankB.getBalance(accountNumber) + " TL");
                        }
                    }

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Geçersiz miktar.");
                }
            }
        });

        // Hesap bilgileri görüntüleme
        accountInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = JOptionPane.showInputDialog("Hesap Numarası:");
                String accountInfo = "";

                if (bankName.equals("Bank A")) {
                    Account account = bankA.getAccounts(accountNumber).get(accountNumber);
                    if (account != null) {
                        accountInfo = account.getAccount();  // Hesap bilgilerini al
                    }
                } else {
                    Account account = bankB.getAccounts(accountNumber).get(accountNumber);
                    if (account != null) {
                        accountInfo = account.getAccount();  // Hesap bilgilerini al
                    }
                }

                if (accountInfo.isEmpty()) {
                    accountInfoLabel.setText("Hesap bulunamadı.");
                } else {
                    accountInfoLabel.setText("<html>" + accountInfo.replace("\n", "<br>") + "</html>");  // HTML formatında görüntüle
                }
            }
        });

        // Pencereyi görünür yap
        setVisible(true);
    }

    // Döviz dönüşüm fonksiyonu (örnek olarak 1 TL = 0.05 USD)
    private double convertCurrency(double amount) {
        return amount * 0.05;
    }

    public static void main(String[] args) {
        // BankUI'yi çalıştır
        new BankUI("Bank A");
    }
}