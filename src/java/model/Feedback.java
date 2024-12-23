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
public class Feedback {
    private int id;
    private User user;
    private OrderDetail orderdetailid;
    private int rating;
    private String FeedbackContent;
    private Date FeedbackDate;
    private int ReplyFeedbackId;

    public Feedback() {
    }

    public Feedback(int id, User user, OrderDetail orderdetailid, int rating, String FeedbackContent, Date FeedbackDate, int ReplyFeedbackId) {
        this.id = id;
        this.user = user;
        this.orderdetailid = orderdetailid;
        this.rating = rating;
        this.FeedbackContent = FeedbackContent;
        this.FeedbackDate = FeedbackDate;
        this.ReplyFeedbackId = ReplyFeedbackId;
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

    public OrderDetail getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(OrderDetail orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedbackContent() {
        return FeedbackContent;
    }

    public void setFeedbackContent(String FeedbackContent) {
        this.FeedbackContent = FeedbackContent;
    }

    public Date getFeedbackDate() {
        return FeedbackDate;
    }

    public void setFeedbackDate(Date FeedbackDate) {
        this.FeedbackDate = FeedbackDate;
    }

    public int getReplyFeedbackId() {
        return ReplyFeedbackId;
    }

    public void setReplyFeedbackId(int ReplyFeedbackId) {
        this.ReplyFeedbackId = ReplyFeedbackId;
    }
    
    
}
