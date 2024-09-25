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
import model.Configuration;

/**
 *
 * @author LOC
 */
public class ConfigurationDAO extends DBContext{
        public List<Configuration> getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Configuration> configuration = new ArrayList<>();
        String sql = "select * from [Configuration]";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                configuration.add(new Configuration(id, name));
            }
            return configuration;

        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return configuration;
    }
//        public static void main(String[] args) {
//        ConfigurationDAO dao = new ConfigurationDAO();
//            System.out.println(dao.getAll()); 
//            
//    }
        
            public Configuration getConfigurationById(int brandId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Configuration configuration = null;
        String sql = "select * from [Configuration] where id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, brandId);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                configuration = new Configuration(id, name);
            }
            return configuration;

        } catch (SQLException ex) {
            Logger.getLogger(ConfigurationDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
            
             public boolean updateConfiguration(Configuration c) {
    PreparedStatement stm = null;
    String sql = "UPDATE Configuration SET name = ? WHERE id = ?";

    try {
        stm = connection.prepareStatement(sql);
        stm.setString(1, c.getName());
        stm.setInt(2, c.getId());
        int rowsUpdated = stm.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        Logger.getLogger(ConfigurationDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    } finally {
        try {
            if (stm != null) stm.close();
            // Không đóng connection tại đây
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}
