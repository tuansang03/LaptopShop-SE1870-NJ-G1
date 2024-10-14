/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Voucher {
    private int id;
    private String Code;
    private String Name;
    private int DiscountPercent;
    private int Quantity;
    private String Image;
    private Date StartDate;
    private Date EndDate;
    private int MinValue;
    private String Status;

    public Voucher() {
    }

    public Voucher(int id, String Code, String Name, int DiscountPercent, int Quantity, Date StartDate, Date EndDate, int MinValue, String Status) {
        this.id = id;
        this.Code = Code;
        this.Name = Name;
        this.DiscountPercent = DiscountPercent;
        this.Quantity = Quantity;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.MinValue = MinValue;
        this.Status = Status;
    }

    public Voucher(int id, String Code, String Name, int DiscountPercent, int Quantity, String Image, Date StartDate, Date EndDate, int MinValue, String Status) {
        this.id = id;
        this.Code = Code;
        this.Name = Name;
        this.DiscountPercent = DiscountPercent;
        this.Quantity = Quantity;
        this.Image = Image;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.MinValue = MinValue;
        this.Status = Status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(int DiscountPercent) {
        this.DiscountPercent = DiscountPercent;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    public int getMinValue() {
        return MinValue;
    }

    public void setMinValue(int MinValue) {
        this.MinValue = MinValue;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }    

    @Override
    public String toString() {
        return "Voucher{" + "id=" + id + ", Code=" + Code + ", Name=" + Name + ", DiscountPercent=" + DiscountPercent + ", Quantity=" + Quantity + ", Image=" + Image + ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", MinValue=" + MinValue + ", Status=" + Status + '}';
    }
}
