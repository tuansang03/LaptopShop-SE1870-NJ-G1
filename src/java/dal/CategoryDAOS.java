/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Category;

/**
 *
 * @author ADMIN
 */
public class CategoryDAOS extends DBContext{
    public Category getCategoryById(int id) {
        String sql = "select * from Category where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category newCategory = new Category(rs.getInt("Id"),
                        rs.getString("Name"));
                return newCategory;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
