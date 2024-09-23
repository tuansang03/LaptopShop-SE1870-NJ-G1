/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.beans.Statement;
import java.util.ArrayList;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Category;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> readProduct() {
        ArrayList<Product> pList = new ArrayList<>();
        String sql = "SELECT p.*, b.Name, c.Name\n"
                + "FROM product p\n"
                + "JOIN brand b ON p.BrandId = b.Id\n"
                + "JOIN category c ON p.CategoryId = c.Id;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                Brand b = new Brand();
                b.setId(rs.getInt(2));
                b.setName(rs.getString(6));
                p.setBrand(b);
                Category c = new Category();
                c.setId(rs.getInt(3));
                c.setName(rs.getString(7));
                p.setCategory(c);
                p.setName(rs.getString(4));
                pList.add(p);
            }
            return pList;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int insertProduct(int brandId, int categoryId, String name) {
    String sql = "INSERT INTO [dbo].[Product] (BrandId, CategoryId, Name) VALUES (?, ?, ?)";
    int generatedId = -1;  // Khởi tạo giá trị mặc định cho ID sinh ra
    
    try {
        // Sử dụng PreparedStatement với tùy chọn RETURN_GENERATED_KEYS để lấy ID tự động sinh
        PreparedStatement pre = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pre.setInt(1, brandId);
        pre.setInt(2, categoryId);
        pre.setString(3, name);
        
        // Thực thi câu lệnh INSERT
        int affectedRows = pre.executeUpdate();
        
        // Kiểm tra nếu có dòng nào bị ảnh hưởng (thêm thành công)
        if (affectedRows > 0) {
            // Lấy kết quả của khóa tự tăng
            ResultSet generatedKeys = pre.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);  // Lấy ID của sản phẩm vừa thêm
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return generatedId;  // Trả về ID của sản phẩm vừa được thêm
}
    


    public static void main(String[] args) {
        ProductDAO p =new ProductDAO();
        ArrayList<Product> pList=p.readProduct();
        System.out.println(pList);
    }
}
