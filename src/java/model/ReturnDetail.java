/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LocPham
 */
public class ReturnDetail {
    private int id;
    private Return returnn;
    private OrderDetail oderDetail;
    private int quantity;
    private int returnAmount;

    public ReturnDetail() {
    }

    public ReturnDetail(int id, Return returnn, OrderDetail oderDetail, int quantity, int returnAmount) {
        this.id = id;
        this.returnn = returnn;
        this.oderDetail = oderDetail;
        this.quantity = quantity;
        this.returnAmount = returnAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Return getReturnn() {
        return returnn;
    }

    public void setReturnn(Return returnn) {
        this.returnn = returnn;
    }

    public OrderDetail getOderDetail() {
        return oderDetail;
    }

    public void setOderDetail(OrderDetail oderDetail) {
        this.oderDetail = oderDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(int returnAmount) {
        this.returnAmount = returnAmount;
    }

    @Override
    public String toString() {
        return "ReturnDetail{" + "id=" + id + ", returnn=" + returnn + ", oderDetail=" + oderDetail + ", quantity=" + quantity + ", returnAmount=" + returnAmount + '}';
    }
    
    






    
}
