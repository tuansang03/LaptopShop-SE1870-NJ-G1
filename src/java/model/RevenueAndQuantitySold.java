/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LocPham
 */
public class RevenueAndQuantitySold {
    int revenue;
    int quantity;

    public RevenueAndQuantitySold() {
    }

    public RevenueAndQuantitySold(int revenue, int quantity) {
        this.revenue = revenue;
        this.quantity = quantity;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RevenueAndQuantitySold{" + "revenue=" + revenue + ", quantity=" + quantity + '}';
    }
    
}
