package bankproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kullanıcı Girişi");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ana panel oluştur ve GridBagLayout kullan
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Komutlar arasındaki boşluk
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Bileşenlerin yatayda genişlemesi

        // Başlık
        JLabel titleLabel = new JLabel("Kullanıcı Girişi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Başlık için iki sütun
        panel.add(titleLabel, gbc);

        // Kullanıcı adı girişi
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.add(usernameField, gbc);

        // Şifre girişi
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Şifre:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.add(passwordField, gbc);

        // Giriş butonu
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;  // Butonun ortalanması için
        JButton loginButton = new JButton("Giriş Yap");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(72, 128, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.setPreferredSize(new Dimension(200, 40));  // Butonun boyutunu belirle
        panel.add(loginButton, gbc);

        // Sonuç göstermek için bir label
        gbc.gridy = 4;
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(resultLabel, gbc);

        // Giriş butonuna tıklandığında yapılacak işlemler
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Kullanıcı adı ve şifre doğrulaması
                if (validateLogin(username, password)) {
                    resultLabel.setText("Giriş Başarılı!");
                    resultLabel.setForeground(new Color(34, 139, 34));
                    new Dashboard().setVisible(true);  // Ana menü aç
                    frame.setVisible(false);
                } else {
                    resultLabel.setText("Geçersiz kullanıcı adı veya şifre.");
                    resultLabel.setForeground(Color.RED);
                }
            }
        });

        frame.setVisible(true);
    }

    // Kullanıcı adı ve şifreyi dosyada kontrol eden fonksiyon
    private static boolean validateLogin(String username, String password) {
        String filePath = "/Users/macbookpro/Desktop/users.txt";  // Buraya dosyanızın tam yolunu yazın

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(":");
                String storedUsername = credentials[0];
                String storedPassword = credentials[1];

                // Kullanıcı adı ve şifreyi kontrol et
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
