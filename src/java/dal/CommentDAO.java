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
import model.Comment;
import model.Product;
import model.Role;
import model.User;

/**
 *
 * @author ADMIN
 */
public class CommentDAO extends DBContext {

    public boolean insertComment(User user, String commentContent, int productId) {
        String sql = "INSERT INTO Comment (UserId, ProductId, CommentContent, CommentDate, RepplyCommentId)\n"
                + "VALUES (?, ?, ?, GETDATE(), NULL);";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, user.getId());
            pre.setInt(2, productId);
            pre.setString(3, commentContent);
            pre.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Comment> getCommentByProductId(int productId) {
        ArrayList<Comment> cList = new ArrayList<>();
        String sql = "select * from Comment c\n"
                + "               \n"
                + "				join [User] u on c.UserId = u.Id \n"
                + "				join [Product] p on p.Id = c.ProductId\n"
                + "				where ProductId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, productId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                User u = new User();
                u.setId(rs.getInt("UserId"));
                u.setFullName(rs.getString("Fullname"));
                Role role = new Role();
                role.setId(rs.getInt("RoleId"));
                u.setRole(role);
                c.setUser(u);
                Product p = new Product();
                p.setId(rs.getInt("ProductId"));
                p.setName(rs.getString("Name"));
                c.setProduct(p);
                c.setCommentContent(rs.getString("CommentContent"));
                c.setCommentDate(rs.getTimestamp("CommentDate"));
                c.setRepplyCommentId(rs.getObject("RepplyCommentId") != null ? rs.getInt("RepplyCommentId") : null);

                cList.add(c);
            }
            return cList;
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Comment> readAllComment() {
        ArrayList<Comment> cList = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM Comment c\n"
                + "JOIN [User] u ON c.UserId = u.Id \n"
                + "JOIN [Product] p ON c.ProductId = p.Id \n"
                + "ORDER BY p.Id;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                User u = new User();
                u.setId(rs.getInt("UserId"));
                u.setFullName(rs.getString("Fullname"));
                Role role = new Role();
                role.setId(rs.getInt("RoleId"));
                u.setRole(role);
                c.setUser(u);
                Product p = new Product();
                p.setId(rs.getInt("ProductId"));
                p.setName(rs.getString("Name"));
                c.setProduct(p);
                c.setCommentContent(rs.getString("CommentContent"));
                c.setCommentDate(rs.getTimestamp("CommentDate"));
                c.setRepplyCommentId(rs.getObject("RepplyCommentId") != null ? rs.getInt("RepplyCommentId") : null);

                cList.add(c);
            }
            return cList;
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteCommentById(int id) {
        String sql = "DELETE FROM Comment WHERE repplyCommentId = ?;\n"
                + "DELETE FROM Comment WHERE id = ?;";
        boolean isDeleted = false;

        try {
            // Chuẩn bị câu lệnh SQL
            PreparedStatement pre = connection.prepareStatement(sql);
            // Đặt giá trị cho tham số ?
            pre.setInt(1, id);
            pre.setInt(2, id);
            // Thực hiện câu lệnh, trả về số lượng dòng bị ảnh hưởng
            int rowsAffected = pre.executeUpdate();

            // Nếu có ít nhất một dòng bị xóa, tức là xóa thành công
            isDeleted = (rowsAffected > 0);

        } catch (SQLException ex) {
            // Log lỗi chi tiết
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Trả về kết quả của việc xóa (true nếu thành công, false nếu thất bại)
        return isDeleted;
    }

    public boolean insertRepplyComment(User user, String commentContent, int productId, int repplyId) {
        String sql = "INSERT INTO Comment (UserId, ProductId, CommentContent, CommentDate, RepplyCommentId) VALUES (?, ?, ?, GETDATE(), ?)";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            // Đặt giá trị cho PreparedStatement
            pre.setInt(1, user.getId()); // Giả sử bạn có phương thức getId() trong lớp User
            pre.setInt(2, productId);
            pre.setString(3, commentContent);
            // Thêm thời gian hiện tại
            pre.setInt(4, repplyId);

            // Thực hiện câu lệnh INSERT
            int affectedRows = pre.executeUpdate();

            // Kiểm tra xem có dòng nào bị ảnh hưởng không (nếu có, tức là thành công)
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Nếu có lỗi, trả về false
        }
    }

    public static void main(String[] args) {
        CommentDAO c = new CommentDAO();
        ArrayList<Comment> cList = c.readAllComment();
        System.out.println(cList);
    }
}
