/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author LocPham
 */
public class Return {

    private int id, totalReturnAmount;
    private String reason, refundMethod, refundStatus, returnStatus;
    private Order oder;
    private LocalDateTime returnDate;

    public Return() {
    }

    public Return(int id, int totalReturnAmount, String reason, String refundMethod, String refundStatus, String returnStatus, Order oder, LocalDateTime returnDate) {
        this.id = id;
        this.totalReturnAmount = totalReturnAmount;
        this.reason = reason;
        this.refundMethod = refundMethod;
        this.refundStatus = refundStatus;
        this.returnStatus = returnStatus;
        this.oder = oder;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalReturnAmount() {
        return totalReturnAmount;
    }

    public void setTotalReturnAmount(int totalReturnAmount) {
        this.totalReturnAmount = totalReturnAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(String refundMethod) {
        this.refundMethod = refundMethod;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Order getOder() {
        return oder;
    }

    public void setOder(Order oder) {
        this.oder = oder;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Return{" + "id=" + id + ", totalReturnAmount=" + totalReturnAmount + ", reason=" + reason + ", refundMethod=" + refundMethod + ", refundStatus=" + refundStatus + ", returnStatus=" + returnStatus + ", oder=" + oder + ", returnDate=" + returnDate + '}';
    }
    
    




    
    

}
