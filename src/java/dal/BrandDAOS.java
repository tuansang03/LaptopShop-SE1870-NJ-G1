/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Brand;

/**
 *
 * @author ADMIN
 */
public class BrandDAOS extends DBContext{
        public Brand getBrandById(int id) {
        String sql = "select * from Brand where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Brand newBrand = new Brand(rs.getInt("Id"),
                        rs.getString("Name"));
                return newBrand;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
