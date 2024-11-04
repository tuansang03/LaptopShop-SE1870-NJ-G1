package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;

public class AddressDAO extends DBContext {

    // Lấy tất cả địa chỉ dựa trên UserId
    public ArrayList<Address> getAddressesByUserId(int userId) {
        ArrayList<Address> addressList = new ArrayList<>();
        String sql = "SELECT * FROM Address WHERE UserId = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, userId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("Id"));
                address.setNamereceive(rs.getString("NameReceive"));
                address.setPhonenumber(rs.getString("PhoneNumber"));
                address.setAddress(rs.getString("Address"));
                address.setIsdefault(rs.getBoolean("IsDefault"));
                addressList.add(address);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addressList;
    }

public boolean insertAddress(Address address, int userId) {
    String sql = "INSERT INTO Address (NameReceive, PhoneNumber, Address, IsDefault, UserId) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setString(1, address.getNamereceive());
        stm.setString(2, address.getPhonenumber());
        stm.setString(3, address.getAddress());
        stm.setBoolean(4, address.isIsdefault());
        stm.setInt(5, userId); // Set the UserId
        
        int rowsInserted = stm.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException ex) {
        Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}


    // Xoá địa chỉ dựa trên Id
    public boolean deleteAddress(int id) {
        String sql = "DELETE FROM Address WHERE Id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            int rowsDeleted = stm.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Cập nhật thông tin địa chỉ
    public boolean updateAddress(Address address) {
        String sql = "UPDATE Address SET NameReceive = ?, PhoneNumber = ?, Address = ?, IsDefault = ? WHERE Id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, address.getNamereceive());
            stm.setString(2, address.getPhonenumber());
            stm.setString(3, address.getAddress());
            stm.setBoolean(4, address.isIsdefault());            
            stm.setInt(5, address.getId());
            int rowsUpdated = stm.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Address> findAddressesByUserId(int userId) {
        ArrayList<Address> addressList = new ArrayList<>();
        String sql = "SELECT * FROM Address WHERE UserId = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("Id"));
                address.setNamereceive(rs.getString("NameReceive"));
                address.setPhonenumber(rs.getString("PhoneNumber"));
                address.setAddress(rs.getString("Address"));
                address.setIsdefault(rs.getBoolean("IsDefault"));
                addressList.add(address);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return addressList;
    }
    
    
    public static void main(String[] args) {
        AddressDAO a = new AddressDAO();
        List<Address> list = a.getAddressesByUserId(7);
        System.out.println(list);
    }
}
