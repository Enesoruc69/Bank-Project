package bankproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    public Dashboard() {
        // Ana pencereyi ayarla
        setTitle("Ana Menü");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Başlık
        JLabel headerLabel = new JLabel("Bankanızı Seçin", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Kartlar için panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(1, 2, 20, 0));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // A Bankası Kartı
        JPanel bankACard = createBankCard("Bank A", "bankA_logo.png");
        JButton bankASelect = new JButton("Bank A'ya Git");
        bankASelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BankUI("Bank A").setVisible(true);
                setVisible(false);
            }
        });
        bankACard.add(bankASelect, BorderLayout.SOUTH);

        // B Bankası Kartı
        JPanel bankBCard = createBankCard("Bank B", "bankB_logo.png");
        JButton bankBSelect = new JButton("Bank B'ye Git");
        bankBSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BankUI("Bank B").setVisible(true);
                setVisible(false);
            }
        });
        bankBCard.add(bankBSelect, BorderLayout.SOUTH);

        // Kartları panele ekle
        cardPanel.add(bankACard);
        cardPanel.add(bankBCard);

        // Ana pencereye ekle
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createBankCard(String bankName, String logoPath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        card.setBackground(Color.WHITE);

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = new ImageIcon(logoPath);
        Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledLogo));

        // Banka adı
        JLabel nameLabel = new JLabel(bankName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Kart bileşenlerini ekle
        card.add(logoLabel, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.NORTH);

        return card;
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
