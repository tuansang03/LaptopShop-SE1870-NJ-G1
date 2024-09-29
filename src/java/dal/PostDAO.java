/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.lang.System.Logger;
import java.sql.Date;
import java.lang.System.Logger.Level;
import java.security.Timestamp;
import model.Post;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kieud
 */
public class PostDAO extends DBContext {

    public boolean insertPost(Post post) {
        PreparedStatement stm = null;
        String sql = "INSERT INTO [dbo].[Post] " 
             + "([UserId], [BrandId], [CategoryId], [Title], [ShortContent], [FullContent], [Thumbnail]) " 
             + "VALUES (?, ?, ?, ?, ?, ?, ?)";


        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, post.getUser().getId());
            stm.setInt(2, post.getBrand().getId());
            stm.setInt(3, post.getCategory().getId());
            stm.setString(4, post.getTittle());
            stm.setString(5, post.getShortContent());
            stm.setString(6, post.getFullContent());
            stm.setString(7, post.getThumbnail());
            
            int rowsInserted = stm.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                // Không đóng connection tại đây
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public static void main(String[] args) {
        PostDAO post = new PostDAO();
        
    }

}
