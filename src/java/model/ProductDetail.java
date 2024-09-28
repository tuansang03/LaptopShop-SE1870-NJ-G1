/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class ProductDetail {

    private int id;
    private Product product;
    private Color color;
    private Configuration configuration;
    private int price;
    private int quantity;
    private String shortDescription;
    private String description;
    private String status;
    private ArrayList<ProductAttribute> productAttributes; // Đã thêm thuộc tính này
    private ArrayList<ProductAttribute> attributes;

    public ArrayList<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(ArrayList<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public ArrayList<ProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<ProductAttribute> attributes) {
        this.attributes = attributes;
    }

   

    public ProductDetail() {
    }

    public ProductDetail(int id, Product product, Color color, Configuration configuration, int price, int quantity, String shortDescription, String description, String status) {
        this.id = id;
        this.product = product;
        this.color = color;
        this.configuration = configuration;
        this.price = price;
        this.quantity = quantity;
        this.shortDescription = shortDescription;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "id=" + id + ", product=" + product + ", color=" + color + ", configuration=" + configuration + ", price=" + price + ", quantity=" + quantity + ", shortDescription=" + shortDescription + ", description=" + description + ", status=" + status + '}';
    }

}
