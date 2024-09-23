/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Feedback;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAOS extends DBContext{
    public Feedback getFeedbackById(int id) {
        String sql = "SELECT * FROM Feedback where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                UserDAO uDAO = new UserDAO();
                ProductDAOS pDAO = new ProductDAOS();
                Feedback f = new Feedback(
                        rs.getInt("Id"), 
                        uDAO.getUserByIdD(rs.getInt("UserId")), 
                        pDAO.getProductById(rs.getInt("ProductId")), 
                        rs.getInt("Rating"), 
                        rs.getString("FeedbackContent"), 
                        rs.getDate("FeedbackDate"), 
                        rs.getInt("ReplyFeedbackId"));
                return f;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        FeedbackDAOS f = new FeedbackDAOS();
        
        Feedback ff = f.getFeedbackById(1);
        
        System.out.println(ff);
    }
}
