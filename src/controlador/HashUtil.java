package controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashUtil {

    public static String getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((salt + password).getBytes());
            byte[] bytes = md.digest();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateSaltedHash(String password) {
        String salt = getSalt();
        return salt + "$" + hashPassword(password, salt);
    }

    public static boolean verifyPassword(String password, String storedPassword) {
        String[] parts = storedPassword.split("\\$");
        if (parts.length != 2) {
            return false;
        }
        String salt = parts[0];
        String hash = parts[1];
        return hash.equals(hashPassword(password, salt));
    }
    
    public static String hashPassword(String newPassword) {
        String salt = getSalt();
        return hashPassword(newPassword, salt);
    }
}
