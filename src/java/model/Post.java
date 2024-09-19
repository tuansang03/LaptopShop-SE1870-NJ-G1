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
public class Post {
    private int id;
    private User user;
    private Brand brand;
    private Category category;
    private String tittle;
    private String shortContent;
    private String fullContent;
    private String thumbnail;
    private Date publishDate;

    public Post() {
    }

    public Post(int id, User user, Brand brand, Category category, String tittle, String shortContent, String fullContent, String thumbnail, Date publishDate) {
        this.id = id;
        this.user = user;
        this.brand = brand;
        this.category = category;
        this.tittle = tittle;
        this.shortContent = shortContent;
        this.fullContent = fullContent;
        this.thumbnail = thumbnail;
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", user=" + user + ", brand=" + brand + ", category=" + category + ", tittle=" + tittle + ", shortContent=" + shortContent + ", fullContent=" + fullContent + ", thumbnail=" + thumbnail + ", publishDate=" + publishDate + '}';
    }
    
    
    
}
