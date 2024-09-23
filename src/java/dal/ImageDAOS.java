/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Image;

/**
 *
 * @author ADMIN
 */
public class ImageDAOS extends DBContext{
    public Image getOneImageByProductDetailID(int id) {
        String sql = "SELECT TOP(1)* FROM Image WHERE ProductDetailID = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                ProductDAO pDAO = new ProductDAO();
                FeedbackDAO fDAO = new FeedbackDAO();
                Image image = new Image(
                        rs.getInt("Id"), 
                        pDAO.getProductDetailByID(rs.getInt("ProductDetailId")), 
                        fDAO.getFeedbackById(rs.getInt("FeedbackId")), 
                rs.getString("Image"));
                return image;
            }
            
            
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        ImageDAOS i = new ImageDAOS();
        System.out.println(i.getOneImageByProductDetailID(1));
    }
}
