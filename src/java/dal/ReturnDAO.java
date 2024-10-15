/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.util.List;
import model.Return;

/**
 *
 * @author LocPham
 */
public class ReturnDAO extends DBContext{
    public boolean insertReturn(Return return1) {
    PreparedStatement stm = null;
    String sql = "INSERT INTO dbo.[Return] (TotalReturnAmount, Reason, RefundMethod, RefundStatus, ReturnStatus, OrderId, ReturnDate) \n" +
                 "VALUES (?,?, ?, ?, ?, ?, ?);";

    try {
        stm = connection.prepareStatement(sql);
        
        // Gán giá trị từ đối tượng return1 vào câu lệnh SQL
        stm.setInt(1, return1.getTotalReturnAmount());
        stm.setString(2, return1.getReason());
        stm.setString(3, return1.getRefundMethod());
        stm.setString(4, return1.getRefundStatus());
        stm.setString(5, return1.getReturnStatus());
        stm.setInt(6, return1.getOder().getId()); // Lấy ID của đơn hàng
        stm.setObject(7, return1.getReturnDate());  // Gán ngày trả về

        // Thực thi câu lệnh và kiểm tra kết quả
        int rowsInserted = stm.executeUpdate();
        return rowsInserted > 0; // Trả về true nếu thêm thành công

    } catch (java.sql.SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (stm != null) stm.close(); // Đóng PreparedStatement sau khi sử dụng
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }

    return false; // Trả về false nếu có lỗi xảy ra
}
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List <Return> list = dao.listReturn();
        System.out.println(list);
    }
}
