/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class OrderDetail {
    private int Id;
    private Order Order;
    private ProductDetail ProductDetail;
    private int Quantity;
    private int UnitPrice;

    public OrderDetail() {
    }

    public OrderDetail(int Id, Order Order, ProductDetail ProductDetail, int Quantity, int UnitPrice) {
        this.Id = Id;
        this.Order = Order;
        this.ProductDetail = ProductDetail;
        this.Quantity = Quantity;
        this.UnitPrice = UnitPrice;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Order getOrder() {
        return Order;
    }

    public void setOrder(Order Order) {
        this.Order = Order;
    }

    public ProductDetail getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(ProductDetail ProductDetail) {
        this.ProductDetail = ProductDetail;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(int UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "Id=" + Id + ", Order=" + Order + ", ProductDetail=" + ProductDetail + ", Quantity=" + Quantity + ", UnitPrice=" + UnitPrice + '}';
    }
    
}
