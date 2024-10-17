/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.ProductDetail;

/**
 *
 * @author LocPham
 */
public class OrdeiDetailDAO extends DBContext {

    public List<Integer> getOrderDetailIdByOrderId(int orderId) {
        List<Integer> orderDetailIds = new ArrayList<>();
        String sql = "SELECT Id FROM OrderDetail WHERE OrderId = ?";
        try {
              PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
             int orderDetailId = rs.getInt("Id");
                orderDetailIds.add(orderDetailId);
            
            }
        } catch (Exception e) {
        }
        return orderDetailIds;

    }
    
    public Integer getUnitPriceByOrderDetailId(int orderDetailId) {
    String sql = "SELECT UnitPrice FROM OrderDetail WHERE Id = ?";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, orderDetailId);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("UnitPrice");
            }
        }
    } catch (Exception e) {
        // In lỗi ra log để kiểm tra nếu có vấn đề
    }
    return null; // Nếu không tìm thấy, trả về null
}
    
    
     public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        List<OrderDetail> orderDetail = new ArrayList<>();
        String sql = "SELECT Id,Quantity,UnitPrice  FROM OrderDetail WHERE OrderId = ?";
        try {
              PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
             int id = rs.getInt("Id");
             Order op = new Order();
             ProductDetail p = new ProductDetail();
             int quantity =rs.getInt("Quantity");
             int unitPrice = rs.getInt("UnitPrice");
             OrderDetail o = new OrderDetail(id,op,p, quantity, unitPrice);
                orderDetail.add(o);
            
            }
        } catch (Exception e) {
        }
        return orderDetail;

    }
     
     public static void main(String[] args) {
        OrdeiDetailDAO dao = new OrdeiDetailDAO();
         System.out.println(dao.getOrderDetailByOrderId(1));
    }
}
