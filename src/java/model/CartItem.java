/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class CartItem {
    private int id;
    private Cart cart;
    private ProductDetail productdetail;
    private int quantity;
    private String status;
    
    public CartItem() {
    }

    public CartItem(int id, Cart cart, ProductDetail productdetail, int quantity) {
        this.id = id;
        this.cart = cart;
        this.productdetail = productdetail;
        this.quantity = quantity;
    }

    public CartItem(int id, Cart cart, ProductDetail productdetail, int quantity, String status) {
        this.id = id;
        this.cart = cart;
        this.productdetail = productdetail;
        this.quantity = quantity;
        this.status = status;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ProductDetail getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(ProductDetail productdetail) {
        this.productdetail = productdetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    @Override
    public String toString() {
        return "CartItem{" + "id=" + id + ", cart=" + cart + ", productdetail=" + productdetail + ", quantity=" + quantity + ", status=" + status + '}';
    }
            
    
}
