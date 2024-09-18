/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ProductAttribute {
    private int id;
    private ProductDetail productdetail;
    private Attribute attribute;
    private String value;

    public ProductAttribute() {
    }

    public ProductAttribute(int id, ProductDetail productdetail, Attribute attribute, String value) {
        this.id = id;
        this.productdetail = productdetail;
        this.attribute = attribute;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDetail getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(ProductDetail productdetail) {
        this.productdetail = productdetail;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
