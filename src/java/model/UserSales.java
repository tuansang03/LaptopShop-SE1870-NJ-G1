/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LocPham
 */
public class UserSales {
    private int userId;
    private String userName;
    private String fullName;
    private int totalOrders;

    public UserSales(int userId, String userName, String fullName, int totalOrders) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.totalOrders = totalOrders;
    }

    // Getter và setter (nếu cần)
    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getFullName() { return fullName; }
    public int getTotalOrders() { return totalOrders; }
}

