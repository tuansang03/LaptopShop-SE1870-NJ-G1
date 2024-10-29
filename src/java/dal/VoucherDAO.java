/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
public class VoucherDAO extends DBContext {

    public void addVoucher(String code, String name, int discount, int quantity,
            Date startDate, Date endDate, int minvalue) {
        String sql = "INSERT INTO [dbo].[Voucher]\n"
                + "           ([Code]\n"
                + "           ,[Name]\n"
                + "           ,[DiscountPercent]\n"
                + "           ,[Quantity]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate]\n"
                + "           ,[MinValue]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, 1)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setString(2, name);
            st.setInt(3, discount);
            st.setInt(4, quantity);
            st.setDate(5, startDate);
            st.setDate(6, endDate);
            st.setInt(7, minvalue);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Voucher> getAllVoucher() {
        String sql = "SELECT * FROM Voucher";
        List<Voucher> listVoucher = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Voucher v = new Voucher(
                        rs.getInt("Id"),
                        rs.getString("Code"),
                        rs.getString("Name"),
                        rs.getInt("DiscountPercent"),
                        rs.getInt("Quantity"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getInt("MinValue"),
                        rs.getString("Status"),
                        rs.getInt("UsedQuantity"));
                listVoucher.add(v);
            }

        } catch (Exception e) {
        }
        return listVoucher;
    }

    public Voucher getVoucherByID(int id) {
        String sql = "SELECT * FROM Voucher WHERE ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Voucher v = new Voucher(
                        rs.getInt("Id"),
                        rs.getString("Code"),
                        rs.getString("Name"),
                        rs.getInt("DiscountPercent"),
                        rs.getInt("Quantity"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getInt("MinValue"),
                        rs.getString("Status"),
                        rs.getInt("UsedQuantity"));
                return v;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void editVoucher(int id, String code, String name, int discount,
            int quantity, Date startDate, Date endDate, int minvalue,
            String status) {
        String sql = "UPDATE [dbo].[Voucher]\n"
                + "   SET [Code] = ? \n"
                + "      ,[Name] = ? \n"
                + "      ,[DiscountPercent] = ? \n"
                + "      ,[Quantity] = ? \n"
                + "      ,[StartDate] = ? \n"
                + "      ,[EndDate] = ? \n"
                + "      ,[MinValue] = ? \n"
                + "      ,[Status] = ? \n"
                + " WHERE id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(9, id);
            st.setString(1, code);
            st.setString(2, name);
            st.setInt(3, discount);
            st.setInt(4, quantity);
            st.setDate(5, startDate);
            st.setDate(6, endDate);
            st.setInt(7, minvalue);
            st.setString(8, status);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteVoucher(int id) {
        String sql = "DELETE FROM [dbo].[Voucher] WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Voucher checkVoucher(String voucher) {
        String sql = "SELECT * FROM Voucher WHERE Code = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, voucher);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Voucher v = new Voucher(rs.getInt("Id"),
                        rs.getString("Code"),
                        rs.getString("Name"),
                        rs.getInt("DiscountPercent"),
                        rs.getInt("Quantity"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getInt("MinValue"),
                        rs.getString("Status"));
                return v;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateQuantityVoucher(int quantity, int idVoucher) {
        String sql = "UPDATE [dbo].[Voucher] SET [Quantity] =  ? WHERE Id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, idVoucher);
            st.executeUpdate();

        } catch (Exception e) {
        }

    }

    public Voucher checkCodeVoucherDuplicate(String code) {
        String sql = "SELECT * FROM Voucher WHERE [Code] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Voucher v = new Voucher(rs.getInt("Id"),
                        rs.getString("Code"),
                        rs.getString("Name"),
                        rs.getInt("DiscountPercent"),
                        rs.getInt("Quantity"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getInt("MinValue"),
                        rs.getString("Status"));
                return v;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Voucher> searchVoucherByName(String name, String status, String op) {
        String sql = "SELECT * FROM Voucher WHERE [Name] LIKE ?";
        List<Voucher> listVoucher = new ArrayList<>();
        try {

            if ((op.equals("active") || op.equals("expired")) && !(name.equals("noName"))) {
                sql += " AND Status = ?";
            } else if (name.equals("noName")) {
                sql = "SELECT * FROM Voucher WHERE [Status] = ?";
            }

            PreparedStatement st = connection.prepareStatement(sql);

            if ((!(name.equals("noName")) && (op.equals("active") || op.equals("expired")))) {
                //search name + status
                st.setString(1, "%" + name + "%");
                st.setString(2, status);
            } else if (name.equals("noName")) {
                //search status
                st.setString(1, status);
            } else {
                //search name
                st.setString(1, "%" + name + "%");
            }

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Voucher v = new Voucher(rs.getInt("Id"),
                        rs.getString("Code"),
                        rs.getString("Name"),
                        rs.getInt("DiscountPercent"),
                        rs.getInt("Quantity"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getInt("MinValue"),
                        rs.getString("Status"));
                listVoucher.add(v);
            }
        } catch (Exception e) {
        }
        return listVoucher;
    }

    public void updateUsedQuantityVoucher(int quantity, int id) {
        String sql = "UPDATE [dbo].[Voucher]\n"
                + "   SET [UsedQuantity] = ?\n"
                + " WHERE Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
        
    
    }

    public static void main(String[] args) {
        VoucherDAO v = new VoucherDAO();
        Voucher a = v.getVoucherByID(1);
        //v.addVoucher("FE3424d", "VoucherTest 1", 70, 20, startDate, endDate, 2, 2);
        //Date startDate = Date.valueOf("2024-10-07");
        //Date endDate = Date.valueOf("2024-10-10");
        //v.editVoucher(5, "123hihi", "test22", 12, 12, startDate, endDate,  12, "0");
        // List<Voucher> list = v.searchVoucherByName("u", "1", "active");
        // List<Voucher> list = v.searchVoucherByName("noName", "1", "active");
        System.out.println(a);
    }
}
