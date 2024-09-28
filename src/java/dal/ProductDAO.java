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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Category;

import java.util.List;

import java.util.List;

import java.util.List;

import java.util.ArrayList;
import model.Attribute;
import model.Color;
import model.Configuration;
import model.Image;
import model.ProductAttribute;
import model.ProductDetail;
import model.ProductList;
import model.ProductList;
import model.*;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> readProduct(int pageNumber, int rowsPerPage) {
        ArrayList<Product> pList = new ArrayList<>();
        String sql = "WITH ProductCTE AS ( "
                + "    SELECT p.Id, p.Name, p.BrandId, p.CategoryId, b.Name AS BrandName, c.Name AS CategoryName, "
                + "           ROW_NUMBER() OVER (ORDER BY p.Id) AS RowNum "
                + "    FROM product p "
                + "    JOIN brand b ON p.BrandId = b.Id "
                + "    JOIN category c ON p.CategoryId = c.Id "
                + ") "
                + "SELECT * "
                + "FROM ProductCTE "
                + "WHERE RowNum BETWEEN ? AND ?;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            // Tính toán giá trị cho tham số phân trang
            int startRow = (pageNumber - 1) * rowsPerPage + 1;
            int endRow = pageNumber * rowsPerPage;

            // Set giá trị cho các tham số trong câu truy vấn
            pre.setInt(1, startRow);  // Giá trị bắt đầu
            pre.setInt(2, endRow);    // Giá trị kết thúc

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("Id"));  // Lấy ID của sản phẩm
                p.setName(rs.getString("Name"));  // Lấy tên sản phẩm

                // Tạo đối tượng Brand
                Brand b = new Brand();
                b.setId(rs.getInt("BrandId"));  // Lấy ID của brand
                b.setName(rs.getString("BrandName"));  // Lấy tên của brand
                p.setBrand(b);

                // Tạo đối tượng Category
                Category c = new Category();
                c.setId(rs.getInt("CategoryId"));  // Lấy ID của category
                c.setName(rs.getString("CategoryName"));  // Lấy tên của category
                p.setCategory(c);

                // Thêm sản phẩm vào danh sách
                pList.add(p);
            }

            // Đóng ResultSet và PreparedStatement
            rs.close();
            pre.close();

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

    public void deleteById(int id) {
        String sql = "DELETE FROM Product \n"
                + "WHERE Id= ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countProduct() {
        String sql = "SELECT COUNT(*) FROM product"; // Câu lệnh SQL để đếm sản phẩm
        try {
            // Chuẩn bị câu lệnh
            PreparedStatement pre = connection.prepareStatement(sql);
            // Thực thi câu lệnh và nhận kết quả
            ResultSet rs = pre.executeQuery();

            // Kiểm tra nếu có kết quả, trả về tổng số sản phẩm
            if (rs.next()) {
                return rs.getInt(1); // Lấy giá trị đếm được từ cột đầu tiên
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // Trả về 0 nếu có lỗi
    }

    public static void main(String[] args) {
        ProductDAO p = new ProductDAO();

    }
}
