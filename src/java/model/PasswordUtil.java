/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author ADMIN
 */
public class PasswordUtil {
private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    private static final int PASSWORD_LENGTH = 9;  // Độ dài mật khẩu tối thiểu là 9 ký tự

    public static String hashPassword(String password) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Chuyển mật khẩu thành dạng byte và thực hiện hash
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Chuyển byte thành dạng String hex
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Chuyển đổi byte[] thành chuỗi hex
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public static boolean isValidPassword(String password) {
        // Kiểm tra độ dài mật khẩu có ít nhất 8 ký tự
        return password != null && password.length() >= 8;
    }
    
   public static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        // Tạo chuỗi ngẫu nhiên từ bộ ký tự đã định nghĩa
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        // Gọi hàm để tạo mật khẩu ngẫu nhiên và in ra màn hình
        String randomPassword = generateRandomPassword();
        System.out.println("Mật khẩu ngẫu nhiên: " + randomPassword);
    }
}