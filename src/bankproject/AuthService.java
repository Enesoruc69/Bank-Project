/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankproject;

/**
 *
 * @author macbookpro
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, String> userDatabase; // Kullanıcı adı ve hashlenmiş şifre eşleşmeleri

    public AuthService() {
        this.userDatabase = new HashMap<>();
    }

    // Kullanıcı kaydı
    public void registerUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            System.out.println("Kullanıcı zaten kayıtlı.");
        } else {
            String hashedPassword = hashPassword(password);
            userDatabase.put(username, hashedPassword);
            System.out.println("Kullanıcı kaydedildi.");
        }
    }

    // Kullanıcı giriş işlemi
    public boolean login(String username, String password) {
        if (!userDatabase.containsKey(username)) {
            System.out.println("Kullanıcı bulunamadı.");
            return false;
        }
        String hashedPassword = hashPassword(password);
        if (userDatabase.get(username).equals(hashedPassword)) {
            System.out.println("Giriş başarılı.");
            return true;
        } else {
            System.out.println("Şifre yanlış.");
            return false;
        }
    }

    // Şifre hashleme
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hashBytes) {
                hashedPassword.append(String.format("%02x", b));
            }
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algoritması bulunamadı.");
        }
    }
}
