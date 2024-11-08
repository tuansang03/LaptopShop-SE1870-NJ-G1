/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ADMIN
 */
public class Order {

    private int Id;
    private User User;
    private String Name;
    private String Address;
    private String Phone;
    private LocalDateTime OrderDate;
    private Voucher Voucher;
    private int TotalAmountBefore;
    private int DiscountAmount;
    private int TotalAmountAfter;
    private String PaymentMethod;
    private String PaymentStatus;
    private String VnPayTransactionId;
    private LocalDateTime EndDate;
    private String OrderStatus;
    private String Note;
    private int saleID;
    private LocalDateTime RejectDate;
    private LocalDateTime AcceptedDate;
    private LocalDateTime IntransitDate;
    private LocalDateTime ShipmentFailedDate;
    private String Trackingcode;

    public Order() {
    }

    public Order(int Id, User User, String Name, String Address, String Phone, LocalDateTime OrderDate, Voucher Voucher, int TotalAmountBefore, int DiscountAmount, int TotalAmountAfter, String PaymentMethod, String PaymentStatus, String VnPayTransactionId, LocalDateTime EndDate, String OrderStatus) {
        this.Id = Id;
        this.User = User;
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.OrderDate = OrderDate;
        this.Voucher = Voucher;
        this.TotalAmountBefore = TotalAmountBefore;
        this.DiscountAmount = DiscountAmount;
        this.TotalAmountAfter = TotalAmountAfter;
        this.PaymentMethod = PaymentMethod;
        this.PaymentStatus = PaymentStatus;
        this.VnPayTransactionId = VnPayTransactionId;
        this.EndDate = EndDate;
        this.OrderStatus = OrderStatus;
    }

    public Order(int Id, User User, String Name, String Address, String Phone, LocalDateTime OrderDate, Voucher Voucher, int TotalAmountBefore, int DiscountAmount, int TotalAmountAfter, String PaymentMethod, String PaymentStatus, String VnPayTransactionId, LocalDateTime EndDate, String OrderStatus, String Note) {
        this.Id = Id;
        this.User = User;
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.OrderDate = OrderDate;
        this.Voucher = Voucher;
        this.TotalAmountBefore = TotalAmountBefore;
        this.DiscountAmount = DiscountAmount;
        this.TotalAmountAfter = TotalAmountAfter;
        this.PaymentMethod = PaymentMethod;
        this.PaymentStatus = PaymentStatus;
        this.VnPayTransactionId = VnPayTransactionId;
        this.EndDate = EndDate;
        this.OrderStatus = OrderStatus;
        this.Note = Note;
    }

    public Order(int Id, User User, String Name, String Address, String Phone, LocalDateTime OrderDate, Voucher Voucher, int TotalAmountBefore, int DiscountAmount, int TotalAmountAfter, String PaymentMethod, String PaymentStatus, String VnPayTransactionId, LocalDateTime EndDate, String OrderStatus, String Note, int saleID) {
        this.Id = Id;
        this.User = User;
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.OrderDate = OrderDate;
        this.Voucher = Voucher;
        this.TotalAmountBefore = TotalAmountBefore;
        this.DiscountAmount = DiscountAmount;
        this.TotalAmountAfter = TotalAmountAfter;
        this.PaymentMethod = PaymentMethod;
        this.PaymentStatus = PaymentStatus;
        this.VnPayTransactionId = VnPayTransactionId;
        this.EndDate = EndDate;
        this.OrderStatus = OrderStatus;
        this.Note = Note;
        this.saleID = saleID;
    }

    public Order(int Id, User User, String Name, String Address, String Phone, LocalDateTime OrderDate, Voucher Voucher, int TotalAmountBefore, int DiscountAmount, int TotalAmountAfter, String PaymentMethod, String PaymentStatus, String VnPayTransactionId, LocalDateTime EndDate, String OrderStatus, String Note, int saleID, LocalDateTime RejectDate, LocalDateTime AcceptedDate, LocalDateTime IntransitDate, LocalDateTime ShipmentFailedDate, String Trackingcode) {
        this.Id = Id;
        this.User = User;
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.OrderDate = OrderDate;
        this.Voucher = Voucher;
        this.TotalAmountBefore = TotalAmountBefore;
        this.DiscountAmount = DiscountAmount;
        this.TotalAmountAfter = TotalAmountAfter;
        this.PaymentMethod = PaymentMethod;
        this.PaymentStatus = PaymentStatus;
        this.VnPayTransactionId = VnPayTransactionId;
        this.EndDate = EndDate;
        this.OrderStatus = OrderStatus;
        this.Note = Note;
        this.saleID = saleID;
        this.RejectDate = RejectDate;
        this.AcceptedDate = AcceptedDate;
        this.IntransitDate = IntransitDate;
        this.ShipmentFailedDate = ShipmentFailedDate;
        this.Trackingcode = Trackingcode;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Voucher getVoucher() {
        return Voucher;
    }

    public void setVoucher(Voucher Voucher) {
        this.Voucher = Voucher;
    }

    public int getTotalAmountBefore() {
        return TotalAmountBefore;
    }

    public void setTotalAmountBefore(int TotalAmountBefore) {
        this.TotalAmountBefore = TotalAmountBefore;
    }

    public int getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(int DiscountAmount) {
        this.DiscountAmount = DiscountAmount;
    }

    public int getTotalAmountAfter() {
        return TotalAmountAfter;
    }

    public void setTotalAmountAfter(int TotalAmountAfter) {
        this.TotalAmountAfter = TotalAmountAfter;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    public String getVnPayTransactionId() {
        return VnPayTransactionId;
    }

    public void setVnPayTransactionId(String VnPayTransactionId) {
        this.VnPayTransactionId = VnPayTransactionId;
    }

    public LocalDateTime getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDateTime OrderDate) {
        this.OrderDate = OrderDate;
    }

    public LocalDateTime getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDateTime EndDate) {
        this.EndDate = EndDate;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    public String getOrderDateAsDateString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Định dạng ngày yyyy-MM-dd
        return OrderDate.format(dateFormatter);
    }
    
    public String getOrderDateAsTimeString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Định dạng giờ
        return OrderDate.format(timeFormatter);
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public LocalDateTime getRejectDate() {
        return RejectDate;
    }

    public void setRejectDate(LocalDateTime RejectDate) {
        this.RejectDate = RejectDate;
    }

    public LocalDateTime getAcceptedDate() {
        return AcceptedDate;
    }

    public void setAcceptedDate(LocalDateTime AcceptedDate) {
        this.AcceptedDate = AcceptedDate;
    }

    public LocalDateTime getIntransitDate() {
        return IntransitDate;
    }

    public void setIntransitDate(LocalDateTime IntransitDate) {
        this.IntransitDate = IntransitDate;
    }

    public LocalDateTime getShipmentFailedDate() {
        return ShipmentFailedDate;
    }

    public void setShipmentFailedDate(LocalDateTime ShipmentFailedDate) {
        this.ShipmentFailedDate = ShipmentFailedDate;
    }

    public String getTrackingcode() {
        return Trackingcode;
    }

    public void setTrackingcode(String Trackingcode) {
        this.Trackingcode = Trackingcode;
    }

    public String getRejectDateAsDateTimeString() {
        return RejectDate != null ? RejectDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : null;
    }

    public String getAcceptedDateAsDateTimeString() {
        return AcceptedDate != null ? AcceptedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : null;
    }

    public String getIntransitDateAsDateTimeString() {
        return IntransitDate != null ? IntransitDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : null;
    }

    public String getShipmentFailedDateAsDateTimeString() {
        return ShipmentFailedDate != null ? ShipmentFailedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : null;
    }
    
    public String getDoneDateAsDateTimeString() {
        return EndDate != null ? EndDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) : null;
    }
    
    public String getOrderDateAsDateTimeString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); // Định dạng giờ
        return OrderDate.format(timeFormatter);
    }

    @Override
    public String toString() {
        return "Order{" + "Id=" + Id + ", User=" + User + ", Name=" + Name + ", Address=" + Address + ", Phone=" + Phone + ", OrderDate=" + OrderDate + ", Voucher=" + Voucher + ", TotalAmountBefore=" + TotalAmountBefore + ", DiscountAmount=" + DiscountAmount + ", TotalAmountAfter=" + TotalAmountAfter + ", PaymentMethod=" + PaymentMethod + ", PaymentStatus=" + PaymentStatus + ", VnPayTransactionId=" + VnPayTransactionId + ", EndDate=" + EndDate + ", OrderStatus=" + OrderStatus + ", Note=" + Note + ", saleID=" + saleID + ", RejectDate=" + RejectDate + ", AcceptedDate=" + AcceptedDate + ", IntransitDate=" + IntransitDate + ", ShipmentFailedDate=" + ShipmentFailedDate + ", Trackingcode=" + Trackingcode + '}';
    }
    

   

}
