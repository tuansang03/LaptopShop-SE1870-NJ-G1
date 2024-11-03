/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PHONG
 */
public class Address {
    private int id;
    private String namereceive;
    private String phonenumber;
    private String address;
    private boolean isdefault;
    private User userid;

    public Address() {
    }

    public Address(int id, String namereceive, String phonenumber, String address, boolean isdefault, User userid) {
        this.id = id;
        this.namereceive = namereceive;
        this.phonenumber = phonenumber;
        this.address = address;
        this.isdefault = isdefault;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamereceive() {
        return namereceive;
    }

    public void setNamereceive(String namereceive) {
        this.namereceive = namereceive;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }
    
}
