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
public class Comment {
    private int id;
    private User user;
    private Product product;
    private String commentContent;
    private Date commentDate;
     private Integer repplyCommentId;

    public Comment() {
    }

    public Comment(int id, User user, Product product, String commentContent, Date commentDate, Integer repplyCommentId) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.repplyCommentId = repplyCommentId;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getRepplyCommentId() {
        return repplyCommentId;
    }

    public void setRepplyCommentId(Integer repplyCommentId) {
        this.repplyCommentId = repplyCommentId;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", user=" + user + ", product=" + product + ", commentContent=" + commentContent + ", commentDate=" + commentDate + ", repplyCommentId=" + repplyCommentId + '}';
    }

   
    
    
}
