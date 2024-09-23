/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Product;

/**
 *
 * @author LOC
 */
public class BrandDAO extends DBContext {

    public List<Brand> getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Brand> brands = new ArrayList<>();
        String sql = "select * from [brand]";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                brands.add(new Brand(id, name));
            }
            return brands;

        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return brands;
    }

    public Brand getBrandById(int brandId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Brand brand = null;
        String sql = "select * from [brand] where id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, brandId);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                brand = new Brand(id, name);
            }
            return brand;

        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

 public boolean updateBrand(Brand brand) {
    PreparedStatement stm = null;
    String sql = "UPDATE brand SET name = ? WHERE id = ?";

    try {
        stm = connection.prepareStatement(sql);
        stm.setString(1, brand.getName());
        stm.setInt(2, brand.getId());
        int rowsUpdated = stm.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    } finally {
        try {
            if (stm != null) stm.close();
            // Không đóng connection tại đây
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
 
public boolean deleteBrandById(int id) {
    PreparedStatement stm = null;
    String sql = "DELETE FROM brand WHERE id = ?";
    boolean isDeleted = false;

    try {
        // Chuẩn bị câu lệnh SQL
        stm = connection.prepareStatement(sql);
        stm.setInt(1, id); // Thiết lập tham số id cho câu lệnh SQL

        // Thực thi câu lệnh DELETE và kiểm tra số hàng bị ảnh hưởng
        int rowsDeleted = stm.executeUpdate();
        isDeleted = rowsDeleted > 0; // Kiểm tra xem có hàng nào bị xóa không

    } catch (SQLException ex) {
        Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Không đóng PreparedStatement hoặc Connection tại đây
    return isDeleted;
}


}
