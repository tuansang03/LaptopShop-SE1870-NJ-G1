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
    String sql = "SELECT u.id, u.fullName, u.phone, u.email, r.id as roleId, r.name as roleName " +
                 "FROM [user] u JOIN [role] r ON u.roleId = r.id";

    try {
        stm = connection.prepareStatement(sql);
        rs = stm.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String fullName = rs.getString("fullName");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String address = rs.getString("address");
            // Lấy thông tin role từ bảng role
            String status = rs.getString("status");
            // Tạo đối tượng User và thêm vào danh sách
            User user = new User(id, fullName, phone, email,address, status);
            users.add(user);
        }
        return users;

    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return users;
}

}
