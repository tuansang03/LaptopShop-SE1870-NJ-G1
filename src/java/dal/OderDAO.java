/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.OrderDetail;
import model.ProductDetail;
import model.User;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
public class OderDAO extends DBContext {

    public void insertOrderOfCODNoVoucher(int uid, String name, String address, String phone,
            LocalDateTime odate, int totalAmountBeFore, int totalAmountAfter, String paymentMethod) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserId]\n"
                + "           ,[Name]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[OrderDate]\n"
                + "           ,[TotalAmountBefore]\n"
                + "           ,[TotalAmountAfter]\n"
                + "           ,[PaymentMethod]\n"
                + "           ,[OrderStatus])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, 'wait')";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.setString(2, name);
            st.setString(3, address);
            st.setString(4, phone);
            st.setObject(5, odate);
            st.setInt(6, totalAmountBeFore);
            st.setInt(7, totalAmountAfter);
            st.setString(8, paymentMethod);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfCOD(int uid, String name, String address, String phone,
            LocalDateTime odate, int voucherID, int totalAmountBeFore, int discountAmount,
            int totalAmountAfter, String paymentMethod) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserId]\n"
                + "           ,[Name]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[OrderDate]\n"
                + "           ,[VoucherId]\n"
                + "           ,[TotalAmountBefore]\n"
                + "           ,[DiscountAmount]\n"
                + "           ,[TotalAmountAfter]\n"
                + "           ,[PaymentMethod]\n"
                + "           ,[OrderStatus])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'wait')";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.setString(2, name);
            st.setString(3, address);
            st.setString(4, phone);
            st.setObject(5, odate);
            st.setObject(6, voucherID);
            st.setInt(7, totalAmountBeFore);
            st.setInt(8, discountAmount);
            st.setInt(9, totalAmountAfter);
            st.setString(10, paymentMethod);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfPaymentNoVoucher(int uid, String name, String address, String phone,
            LocalDateTime odate, int totalAmountBeFore, int totalAmountAfter,
            String paymentMethod, String VnPayId) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserId]\n"
                + "           ,[Name]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[OrderDate]\n"
                + "           ,[TotalAmountBefore]\n"
                + "           ,[TotalAmountAfter]\n"
                + "           ,[PaymentMethod]\n"
                + "           ,[PaymentStatus]\n"
                + "           ,[VnPayTransactionId]\n"
                + "           ,[OrderStatus])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, 'pending', ?,'wait')";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.setString(2, name);
            st.setString(3, address);
            st.setString(4, phone);
            st.setObject(5, odate);
            st.setInt(6, totalAmountBeFore);
            st.setInt(7, totalAmountAfter);
            st.setString(8, paymentMethod);
            st.setString(9, VnPayId);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfPayment(int uid, String name, String address, String phone,
            LocalDateTime odate, int voucherID, int totalAmountBeFore,
            int discountAmount, int totalAmountAfter,
            String paymentMethod, String VnPayId) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserId]\n"
                + "           ,[Name]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[OrderDate]\n"
                + "           ,[VoucherId]\n"
                + "           ,[TotalAmountBefore]\n"
                + "           ,[DiscountAmount]\n"
                + "           ,[TotalAmountAfter]\n"
                + "           ,[PaymentMethod]\n"
                + "           ,[PaymentStatus]\n"
                + "           ,[VnPayTransactionId]\n"
                + "           ,[OrderStatus])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, 'pending', ?,'wait')";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            st.setString(2, name);
            st.setString(3, address);
            st.setString(4, phone);
            st.setObject(5, odate);
            st.setInt(6, voucherID);
            st.setInt(7, totalAmountBeFore);
            st.setInt(8, discountAmount);
            st.setInt(9, totalAmountAfter);
            st.setString(10, paymentMethod);
            st.setString(11, VnPayId);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public int getIdOfOrderNewest() {
        String sql = "SELECT TOP(1) Id FROM [Order] ORDER BY Id DESC";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                return oid;
            }

        } catch (Exception e) {
        }
        return -1;
    }

    public void insertOrderDetail(int oid, int pid, int qlt, int unitPrice) {
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([OrderId]\n"
                + "           ,[ProductDetailId]\n"
                + "           ,[Quantity]\n"
                + "           ,[UnitPrice])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.setInt(2, pid);
            st.setInt(3, qlt);
            st.setInt(4, unitPrice);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    
public OrderDetail getOrderDetailById(int id) throws SQLException {
    OrderDetail orderDetail = null;
    UserDAO userDa = new UserDAO();

    String orderDetailQuery = "SELECT * FROM OrderDetail WHERE id = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(orderDetailQuery)) {
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            orderDetail = new OrderDetail();
            orderDetail.setId(resultSet.getInt("id"));
            orderDetail.setQuantity(resultSet.getInt("quantity"));
            orderDetail.setUnitPrice(resultSet.getInt("UnitPrice"));

            int orderId = resultSet.getInt("orderid");
            Order order = getOrderById(orderId);
            orderDetail.setOrder(order);

            int productDetailId = resultSet.getInt("ProductDetailId");
            ProductDetail productDetail = userDa.getProductDetailById(productDetailId);
            orderDetail.setProductDetail(productDetail);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Ghi lại lỗi
    }
    
    return orderDetail; // Trả về null nếu không tìm thấy
}
    public List<OrderDetail> getAllOrderDetails() throws SQLException {
    List<OrderDetail> orderDetailsList = new ArrayList<>();
    UserDAO userDao = new UserDAO();

    String orderDetailQuery = "SELECT * FROM OrderDetail";

    try (
         PreparedStatement preparedStatement = connection.prepareStatement(orderDetailQuery);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(resultSet.getInt("id"));
            orderDetail.setQuantity(resultSet.getInt("quantity"));
            orderDetail.setUnitPrice(resultSet.getInt("UnitPrice"));

            int orderId = resultSet.getInt("orderid"); 
            Order order = getOrderById(orderId);
            orderDetail.setOrder(order);

            int productDetailId = resultSet.getInt("ProductDetailId"); 
            ProductDetail productDetail = userDao.getProductDetailById(productDetailId);
            orderDetail.setProductDetail(productDetail);
            orderDetailsList.add(orderDetail);
        }
    }
    
    return orderDetailsList;
}


    public Order getOrderById(int orderId) {
    Order order = null;

    String orderQuery = "SELECT * FROM [dbo].[Order] WHERE Id = ?";


    try (
         PreparedStatement preparedStatement = connection.prepareStatement(orderQuery)) {

        preparedStatement.setInt(1, orderId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setName(resultSet.getString("name"));
            order.setAddress(resultSet.getString("address"));
            order.setPhone(resultSet.getString("phone"));
            order.setOrderDate(resultSet.getTimestamp("OrderDate").toLocalDateTime());
            order.setTotalAmountBefore(resultSet.getInt("TotalAmountBefore"));
            order.setDiscountAmount(resultSet.getInt("DiscountAmount"));
            order.setTotalAmountAfter(resultSet.getInt("TotalAmountAfter"));
            order.setPaymentMethod(resultSet.getString("PaymentMethod"));
            order.setPaymentStatus(resultSet.getString("PaymentStatus"));
            order.setVnPayTransactionId(resultSet.getString("VnPayTransactionId"));
            order.setEndDate(resultSet.getTimestamp("EndDate") != null ? resultSet.getTimestamp("end_date").toLocalDateTime() : null);
            order.setOrderStatus(resultSet.getString("OrderStatus"));

         
            int userId = resultSet.getInt("UserId");
            UserDAO userd = new UserDAO();
            User user = userd.getUserByIdD(userId);
            order.setUser(user);

            int voucherId = resultSet.getInt("VoucherId"); 
            if (voucherId > 0) {
                Voucher voucher = getVoucherById(voucherId);
                order.setVoucher(voucher);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return order;
}
public Voucher getVoucherById(int voucherId) {
    Voucher voucher = null;

String voucherQuery = "SELECT [Id], [Code], [Name], [DiscountPercent], [Quantity], [Image], "
                    + "[StartDate], [EndDate], [MinValue], [DiscountCap], [Status] "
                    + "FROM [dbo].[Voucher] WHERE [Id] = ?";


    try (
         PreparedStatement preparedStatement = connection.prepareStatement(voucherQuery)) {

        preparedStatement.setInt(1, voucherId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            voucher = new Voucher();
            voucher.setId(resultSet.getInt("Id"));
            voucher.setCode(resultSet.getString("Code"));
            voucher.setName(resultSet.getString("Name"));
            voucher.setDiscountPercent(resultSet.getInt("DiscountPercent"));
            voucher.setQuantity(resultSet.getInt("Quantity"));
            voucher.setImage(resultSet.getString("Image"));
            voucher.setStartDate(resultSet.getDate("StartDate"));
            voucher.setEndDate(resultSet.getDate("EndDate"));
            voucher.setMinValue(resultSet.getInt("MinValue"));
            voucher.setDiscountCap(resultSet.getInt("DiscountCap"));
            voucher.setStatus(resultSet.getString("Status"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return voucher;
}


    public Order getNewestOrder(int userId) {
    Order order = null;

    String orderQuery = "SELECT TOP (1) * FROM [dbo].[Order] WHERE UserId = ? ORDER BY OrderDate DESC";

    try (
         PreparedStatement preparedStatement = connection.prepareStatement(orderQuery)) {

        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            order = new Order();
            order.setId(resultSet.getInt("Id"));
            order.setName(resultSet.getString("Name"));
            order.setAddress(resultSet.getString("Address"));
            order.setPhone(resultSet.getString("Phone"));
            order.setOrderDate(resultSet.getTimestamp("OrderDate").toLocalDateTime());
            order.setTotalAmountBefore(resultSet.getInt("TotalAmountBefore"));
            order.setDiscountAmount(resultSet.getInt("DiscountAmount"));
            order.setTotalAmountAfter(resultSet.getInt("TotalAmountAfter"));
            order.setPaymentMethod(resultSet.getString("PaymentMethod"));
            order.setPaymentStatus(resultSet.getString("PaymentStatus"));
            order.setVnPayTransactionId(resultSet.getString("VnPayTransactionId"));
            order.setEndDate(resultSet.getTimestamp("EndDate") != null ? resultSet.getTimestamp("EndDate").toLocalDateTime() : null);
            order.setOrderStatus(resultSet.getString("OrderStatus"));

            UserDAO userd = new UserDAO();
            User user = userd.getUserByIdD(userId);
            order.setUser(user);

            int voucherId = resultSet.getInt("VoucherId");
            if (voucherId > 0) {
                Voucher voucher = getVoucherById(voucherId);
                order.setVoucher(voucher);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return order;
}
    public List<Order> getOrderList(int userId) {
    List<Order> orders = new ArrayList<>(); 


    String orderQuery = "SELECT TOP (1) * FROM [dbo].[Order] WHERE UserId = ? ORDER BY OrderDate DESC";

    try (PreparedStatement preparedStatement = connection.prepareStatement(orderQuery)) {
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("Id"));
            order.setName(resultSet.getString("Name"));
            order.setAddress(resultSet.getString("Address"));
            order.setPhone(resultSet.getString("Phone"));
            order.setOrderDate(resultSet.getTimestamp("OrderDate").toLocalDateTime());
            order.setTotalAmountBefore(resultSet.getInt("TotalAmountBefore"));
            order.setDiscountAmount(resultSet.getInt("DiscountAmount"));
            order.setTotalAmountAfter(resultSet.getInt("TotalAmountAfter"));
            order.setPaymentMethod(resultSet.getString("PaymentMethod"));
            order.setPaymentStatus(resultSet.getString("PaymentStatus"));
            order.setVnPayTransactionId(resultSet.getString("VnPayTransactionId"));
            order.setEndDate(resultSet.getTimestamp("EndDate") != null ? resultSet.getTimestamp("EndDate").toLocalDateTime() : null);
            order.setOrderStatus(resultSet.getString("OrderStatus"));

            UserDAO userd = new UserDAO();
            User user = userd.getUserByIdD(userId);
            order.setUser(user);

            int voucherId = resultSet.getInt("VoucherId");
            if (voucherId > 0) {
                Voucher voucher = getVoucherById(voucherId);
                order.setVoucher(voucher);
            }

            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return orders; 
}


    public static void main(String[] args) {
        OderDAO o = new OderDAO();
        OrderDetail order = null;
       
        try {
            order = o.getOrderDetailById(1);
        } catch (SQLException ex) {
            Logger.getLogger(OderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println(order);
        //o.insertOrder(5, "ss", "ss", "123", LocalDateTime.MAX, 0, 0, "hehe");
    }
}
