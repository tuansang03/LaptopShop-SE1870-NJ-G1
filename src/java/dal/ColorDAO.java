package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Color;

public class ColorDAO extends DBContext {

    public ArrayList<Color> getAllColor() {
        ArrayList<Color> cList = new ArrayList<>();
        String sql = "SELECT * FROM Color";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Color c = new Color();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                cList.add(c);
            }
            return cList;
        } catch (SQLException ex) {
            Logger.getLogger(ColorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertColor(String name) {
        String sql = "INSERT INTO Color (Name) VALUES (?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, name); // Thiết lập giá trị cho cột "Name"
            int rowsInserted = stm.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công
        } catch (SQLException ex) {
            Logger.getLogger(ColorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public boolean deleteColor(int id) {
        String sql = "DELETE FROM Color WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id); // Thiết lập tham số cho câu lệnh SQL (id)
            int rowsDeleted = stm.executeUpdate();
            return rowsDeleted > 0; // Trả về true nếu ít nhất một hàng bị xóa
        } catch (SQLException ex) {
            Logger.getLogger(ColorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }
}
