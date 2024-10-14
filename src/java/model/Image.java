/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Image {
   private int id;
   public ProductDetail productDetail;
   private Feedback feedBack;
   public String image;

    public Image() {
    }

    public Image(int id, ProductDetail productDetail, Feedback feedBack, String image) {
        this.id = id;
        this.productDetail = productDetail;
        this.feedBack = feedBack;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public Feedback getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(Feedback feedBack) {
        this.feedBack = feedBack;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", productDetail=" + productDetail + ", feedBack=" + feedBack + ", image=" + image + '}';
    }
   
}
