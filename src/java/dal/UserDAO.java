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
import model.Role;
import model.User;


/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {
public List<User> getAll() {
    PreparedStatement stm = null;
    ResultSet rs = null;
    List<User> users = new ArrayList<>();
    String sql = "SELECT u.Id, u.Username, u.Password, u.Fullname, u.Email, u.RoleId, u.Status " +
                 "FROM [User] u";

    try {
        stm = connection.prepareStatement(sql);
        rs = stm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("Id");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            String fullname = rs.getString("Fullname");
            String email = rs.getString("Email");
//            int roleId = rs.getInt("RoleId");
            String status = rs.getString("Status");

            // Tạo đối tượng User và thêm vào danh sách
            User user = new User(id, username, password, fullname, email, status);
            users.add(user);
        }
        return users;

    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Đóng các tài nguyên
        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (stm != null) try { stm.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
    return users;
}

 public void banAnUser(int userId) {
    PreparedStatement stm = null;

    String sql = "UPDATE [dbo].[user] SET [status] = 'ban' WHERE id = ?";
    try {
        stm = connection.prepareStatement(sql);
        stm.setInt(1, userId);
        stm.executeUpdate();

    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (stm != null) {
                stm.close(); // Đóng PreparedStatement
            }
            // Không đóng connection ở đây
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
// private Connection connection; // Biến kết nối tới database

    // Hàm tìm kiếm user theo từ khóa
public List<User> getUsersByKeyword(String keyword) {
    List<User> users = new ArrayList<>();
    PreparedStatement stm = null;
    ResultSet rs = null;

    // Sử dụng tên bảng đầy đủ với schema nếu cần (ví dụ: dbo.User)
    String sql = "SELECT * FROM dbo.[User]  WHERE userName LIKE ? OR email LIKE ?";

    try {
        stm = connection.prepareStatement(sql);
        String searchPattern = "%" + keyword + "%";  // Sử dụng wildcard cho tìm kiếm
        stm.setString(1, searchPattern);
        stm.setString(2, searchPattern);
        rs = stm.executeQuery();

        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("userName"));
            user.setFullName(rs.getString("fullName"));
            user.setEmail(rs.getString("email"));
            user.setStatus(rs.getString("status"));
            // Thêm user vào danh sách
            users.add(user);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            // Không đóng connection ở đây
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    return users;
}




    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(dao.getUsersByKeyword("Loc"));
    }
}
