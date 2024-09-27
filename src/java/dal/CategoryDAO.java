/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Category;

/**
 *
 * @author LOC
 */
public class CategoryDAO extends DBContext {

    public List<Category> getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Category> brands = new ArrayList<>();
        String sql = "select * from [Category]";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                brands.add(new Category(id, name));
            }
            return brands;

        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return brands;
    }

    public Category getCategoryById(int brandId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Category category = null;
        String sql = "select * from [category] where id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, brandId);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                category = new Category(id, name);
            }
            return category;

        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateCategory(Category category) {
        PreparedStatement stm = null;
        String sql = "UPDATE Category SET name = ? WHERE id = ?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, category.getName());
            stm.setInt(2, category.getId());
            int rowsUpdated = stm.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                // Không đóng connection tại đây
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean deleteCategoryById(int id) {
        PreparedStatement stm = null;
        String sql = "DELETE FROM Category WHERE id = ?";
        boolean isDeleted = false;

        try {
            // Chuẩn bị câu lệnh SQL
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id); // Thiết lập tham số id cho câu lệnh SQL

            // Thực thi câu lệnh DELETE và kiểm tra số hàng bị ảnh hưởng
            int rowsDeleted = stm.executeUpdate();
            isDeleted = rowsDeleted > 0; // Kiểm tra xem có hàng nào bị xóa không

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Không đóng PreparedStatement hoặc Connection tại đây
        return isDeleted;
    }

    public boolean insertCategory(String name) {
        PreparedStatement stm = null;
        String sql = "INSERT INTO Category (name) VALUES (?)";  // Giả sử bảng tên là Category và chỉ có cột name

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, name); // Gán giá trị tên category vào câu lệnh SQL

            // Thực thi câu lệnh và kiểm tra kết quả
            int rowsInserted = stm.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close(); // Đóng PreparedStatement sau khi sử dụng
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return false; // Trả về false nếu có lỗi xảy ra
    }

    public List<Category> getCategoriesByKeyword(String keyword) {
        List<Category> categories = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Category WHERE name LIKE ?";

        try {
            stm = connection.prepareStatement(sql);
            // Thêm phần trăm (%) trước và sau từ khóa để tìm kiếm gần đúng (LIKE)
            stm.setString(1, "%" + keyword + "%");
            rs = stm.executeQuery();

            // Duyệt qua kết quả và thêm các danh mục vào danh sách
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return categories;
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        System.out.println(dao.deleteCategoryById(1));
    }

}
