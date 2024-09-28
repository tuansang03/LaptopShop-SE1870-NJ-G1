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
public class ConfigurationDAO extends DBContext {

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
                if (stm != null) {
                    stm.close();
                }
                // Không đóng connection tại đây
            } catch (SQLException ex) {
                Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean deleteConfiguration(int id) {
        PreparedStatement stm = null;
        String sql = "DELETE FROM Configuration WHERE id = ?";

        try {
            // Chuẩn bị câu lệnh SQL
            stm = connection.prepareStatement(sql);
            // Thiết lập tham số cho câu lệnh SQL (id)
            stm.setInt(1, id);
            // Thực thi câu lệnh DELETE và trả về số hàng bị ảnh hưởng
            int rowsDeleted = stm.executeUpdate();
            // Trả về true nếu ít nhất một hàng bị xóa
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ConfigurationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            // Đóng PreparedStatement nhưng không đóng connection
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConfigurationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
public boolean insertConfiguration(String name) {
    PreparedStatement stm = null;
    String sql = "INSERT INTO Configuration (name) VALUES (?)"; // Giả sử bảng tên là Configuration và chỉ có cột name

    try {
        stm = connection.prepareStatement(sql);
        stm.setString(1, name); // Gán giá trị tên cấu hình vào câu lệnh SQL

        // Thực thi câu lệnh và kiểm tra kết quả
        int rowsInserted = stm.executeUpdate();
        return rowsInserted > 0; // Trả về true nếu thêm thành công

    } catch (SQLException ex) {
        ex.printStackTrace(); // In lỗi nếu có
    } finally {
        try {
            if (stm != null) stm.close(); // Đóng PreparedStatement sau khi sử dụng
        } catch (SQLException ex) {
            ex.printStackTrace(); // In lỗi nếu có khi đóng
        }
    }

    return false; // Trả về false nếu có lỗi xảy ra
}

public List<Configuration> getConfigurationsByKeyword(String keyword) {
    List<Configuration> configurations = new ArrayList<>();
    PreparedStatement stm = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM Configuration WHERE name LIKE ?";

    try {
        stm = connection.prepareStatement(sql);
        // Thêm phần trăm (%) trước và sau từ khóa để tìm kiếm gần đúng (LIKE)
        stm.setString(1, "%" + keyword + "%");
        rs = stm.executeQuery();

        // Duyệt qua kết quả và thêm các cấu hình vào danh sách
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            // Bạn có thể thêm các trường khác nếu cần, ví dụ description hoặc value, v.v.
            Configuration config = new Configuration(id, name);
            configurations.add(config);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ConfigurationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigurationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    return configurations;
}

    public ArrayList<Configuration> getAllConfig(){
        ArrayList<Configuration> cList=new ArrayList<>();
        String sql="select * from Configuration";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Configuration c= new Configuration();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                cList.add(c);
            }
            return cList;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public static void main(String[] args) {
//        ConfigurationDAO dao = new ConfigurationDAO();
//        boolean check = dao.insertConfiguration("loc");
//        System.out.println(check);
//    }

}
