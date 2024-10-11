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
    public boolean deletePostById(int id){
        PreparedStatement ps = null;
        String sql = "DELETE FROM [dbo].[Post]\n" +
"      WHERE Id = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
      public boolean updatePost(Post post) {
        PreparedStatement stm = null;
        String sql = "UPDATE Post SET BrandId = ?, CategoryId = ?, Title = ?, ShortContent = ?, FullContent = ?, Thumbnail = ? WHERE Id = ?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, post.getBrand().getId());
            stm.setInt(2, post.getCategory().getId());
            stm.setString(3, post.getTittle());
            stm.setString(4, post.getShortContent());
            stm.setString(5, post.getFullContent());
            stm.setString(6, post.getThumbnail());
            stm.setInt(7, post.getId());

            int rowsUpdated = stm.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException ex) {
           
            return false;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
               
            }
        }
    }

    
    public static void main(String[] args) {
        PostDAO post = new PostDAO();
        
    }

}
