/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LocPham
 */
public class BrandProductPrice {
    private String brandName;
    private int price;

    // Constructor
    public BrandProductPrice(String brandName, int price) {
        this.brandName = brandName;
        this.price = price;
    }

    // Getter cho brandName
    public String getBrandName() {
        return brandName;
    }

    // Getter cho productCount
    public int getPrice() {
        return price;
    }
}