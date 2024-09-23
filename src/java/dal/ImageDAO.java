/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Image;
/**
 *
 * @author ADMIN
 */
public class ImageDAO extends DBContext {
    public void insertImage(Image img) {
        // Câu lệnh SQL để chèn một bản ghi vào bảng Image
        String sql = "INSERT INTO Image (ProductDetailId, Image) VALUES (?, ?)";

        try {
            // Tạo PreparedStatement và gán giá trị cho các tham số
            PreparedStatement pre = new DBContext().connection.prepareStatement(sql);
            pre.setInt(1, img.getProductDetail().getId());  // Đặt giá trị ProductDetailId
            pre.setString(2, img.getImage());  // Đặt giá trị URL của ảnh

            // Thực thi lệnh SQL
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng PreparedStatement và Connection nếu cần
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

