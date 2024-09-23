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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author LOC
 */
public class ProductDAO extends DBContext{
public List<Product> getProductsByBrand(int bid) {
    PreparedStatement stm = null;
    ResultSet rs = null;
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM [product] WHERE brand_id = ?";
    try {
        stm = connection.prepareStatement(sql);
        stm.setInt(1, bid);
        rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            // Tạo sản phẩm với các trường cần thiết
            products.add(new Product(id, name));
        }
        return products;
    } catch (SQLException ex) {
        Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}




}
