/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Image;
import model.ProductDetail;

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
        } 
    }

    public ArrayList<Image> getAllImageByPDId(int productDetailId) {
        ArrayList<Image> imgList = new ArrayList<>();
        String sql = "select * from Image\n"
                + "where ProductDetailId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, productDetailId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Image i = new Image();
                i.setId(rs.getInt(1));
                ProductDetail p = new ProductDetail();
                p.setId(rs.getInt(2));
                i.setProductDetail(p);
                i.setImage(rs.getString(4));

                imgList.add(i);
            }
            return imgList;
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateImage(Image image) {
        String sql = "UPDATE [dbo].[Image] SET [Image] = ? WHERE [Id] = ?";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            // Set giá trị cho prepared statement
            pre.setString(1, image.getImage()); // URL ảnh mới
            pre.setInt(2, image.getId()); // ID của ảnh cần cập nhật

            // Thực hiện cập nhật
            int rowsAffected = pre.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Image updated successfully: " + image.getImage());
            } else {
                System.out.println("No image found with ID: " + image.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean deleteImageById(int imageId) {
    String sql = "DELETE FROM [Image] WHERE [Id] = ?;";
    try {
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, imageId);
        int rowsAffected = pre.executeUpdate();

        // Nếu có ít nhất 1 dòng bị ảnh hưởng, tức là xóa thành công
        return rowsAffected > 0;
    } catch (SQLException ex) {
        Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;  // Trả về false nếu có lỗi xảy ra
    }
}
     public boolean deleteImageByProductDetailId(int productDetailId) {
    String sql = "DELETE FROM [Image] WHERE [ProductDetailId] = ?;";
    try {
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.setInt(1, productDetailId);
        int rowsAffected = pre.executeUpdate();

        // Nếu có ít nhất 1 dòng bị ảnh hưởng, tức là xóa thành công
        return rowsAffected > 0;
    } catch (SQLException ex) {
        Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;  // Trả về false nếu có lỗi xảy ra
    }
}
}
