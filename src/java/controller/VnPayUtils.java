/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.security.MessageDigest;
import java.util.Map;

public class VnPayUtils {
    public static String createSecureHash(Map<String, String> params, String secretKey) {
        StringBuilder hashData = new StringBuilder();
        params.keySet().stream().sorted().forEach(key -> {
            String value = params.get(key);
            if (value != null && !value.isEmpty()) {
                hashData.append(key).append("=").append(value).append("&");
            }
        });
        hashData.append("vnp_HashSecret=").append(secretKey);
        return hashData(hashData.toString());
    }

    private static String hashData(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}