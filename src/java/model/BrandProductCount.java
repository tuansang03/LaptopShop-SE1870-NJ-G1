/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LocPham
 */
public class BrandProductCount {
    private String brandName;
    private int productCount;

    // Constructor
    public BrandProductCount(String brandName, int productCount) {
        this.brandName = brandName;
        this.productCount = productCount;
    }

    // Getter cho brandName
    public String getBrandName() {
        return brandName;
    }

    // Getter cho productCount
    public int getProductCount() {
        return productCount;
    }
}
