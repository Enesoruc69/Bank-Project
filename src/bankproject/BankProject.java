package bankproject;

public class BankProject {
    public static void main(String[] args) {
        // Banka nesnelerini oluştur
        Bank bankA = new BankA("Bank A");
        Bank bankB = new BankB("Bank B");

        // Bankalarda hesap oluşturma ve bakiye ekleme
        bankA.deposit("12345", 1000);
        bankB.deposit("67890", 500);

        // Transfer servisini kullanarak para transferi
        TransferService transferService = new TransferService(bankA, bankB);
        transferService.transfer("12345", "67890", 300);

        // Son bakiye kontrolü
        System.out.println("Bank A Hesap Bakiyesi: " + bankA.getBalance("12345"));
        System.out.println("Bank B Hesap Bakiyesi: " + bankB.getBalance("67890"));
    }
}
