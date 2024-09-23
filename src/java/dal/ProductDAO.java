/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author LOC
 */
public class ProductDAO extends DBContext{
    public List<Product> getProductsByBrandId(int brandId) {
    List<Product> products = new ArrayList<>();
    PreparedStatement stm = null;
    ResultSet rs = null;

    String sql = "SELECT * FROM Product WHERE BrandId = ?";

    try {
        // Chuẩn bị câu lệnh SQL
        stm = connection.prepareStatement(sql);
        stm.setInt(1, brandId);  // Thiết lập giá trị cho BrandId

        // Thực thi câu lệnh và lấy kết quả
        rs = stm.executeQuery();
        while (rs.next()) {
            // Lấy dữ liệu từ kết quả trả về
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int categoryId = rs.getInt("CategoryId");
            String status = rs.getString("status");

            // Tạo đối tượng Product
            Product product = new Product(id, name, brandId, categoryId, status);
            products.add(product);  // Thêm sản phẩm vào danh sách
        }

    } catch (SQLException ex) {
        Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    return products;
}

}
