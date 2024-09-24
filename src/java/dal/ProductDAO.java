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

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.ArrayList;
import model.ProductList;

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
    



    public List<Product> list() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                Product p = new Product(
                        re.getInt("id"),
                        getBrandById(re.getInt("brand")),
                        getCategoryById(re.getInt("category")),
                        re.getString("name"),
                        re.getString("status"));
                Category c = new Category(re.getInt("productCategoryID"), re.getString("category_name"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Brand getBrandById(int id) {
        String sql = "select * from Brand where Id=" + id;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            if (re.next()) {
                Brand b = new Brand(re.getInt("id"), re.getString("name"));
                return b;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;

    }

    public Category getCategoryById(int id) {
        String sql = "select * from Category where Id=" + id;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            if (re.next()) {
                Category c = new Category(re.getInt("id"), re.getString("name"));
                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;

    }

    public List<ProductList> listProduct(String category, String brand, String price, String name) {
        List<ProductList> list = new ArrayList<>();
        String sql = "WITH RankedProducts AS (\n"
                + "    SELECT p.Id, \n"
                + "           p.[Name] AS name, \n"
                + "           b.[Name] AS brand, \n"
                + "           c.[Name] AS category, \n"
                + "           im.[Image] AS img, \n"
                + "           pd.Price AS price,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY p.Id ORDER BY pd.Id) AS rn\n"
                + "    FROM Product p\n"
                + "    JOIN ProductDetail pd ON p.Id = pd.ProductId\n"
                + "    JOIN [Image] im ON im.ProductDetailId = pd.Id\n"
                + "    JOIN Brand b ON b.Id = p.BrandId\n"
                + "    JOIN Category c ON c.Id = p.CategoryId\n"
                + ")\n"
                + "SELECT Id, name, brand, category, img, price\n"
                + "FROM RankedProducts\n"
                + "WHERE rn = 1";

        if (category != null) {
            sql += " and category ='" + category + "'";
        }
        if (brand != null) {
            sql += " and brand ='" + brand + "'";
        }
        if (price != null){
         if (price.compareTo("default")==0) {
            sql += " order by Id"; 
        } else {
            sql += " order by price "+price; 
         }   
        }
         
         if (name != null) {
            sql += " and name like N'%" + name + "%'";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            //st.setInt(1, cid);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                ProductList p = new ProductList(
                        re.getInt("id"),
                        re.getString("name"),
                        re.getString("brand"),
                        re.getString("category"),
                        re.getString("img"),
                        re.getInt("price")
                );
                list.add(p);

            }
        } catch (SQLException e) {
        }

        return list;

    }


}
