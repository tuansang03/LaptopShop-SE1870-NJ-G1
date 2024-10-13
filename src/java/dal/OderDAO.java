/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
            LocalDateTime odate, int totalAmountBeFore, int totalAmountAfter, String paymentMethod, String note) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([UserId]\n"
                + "           ,[Name]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[OrderDate]\n"
                + "           ,[TotalAmountBefore]\n"
                + "           ,[TotalAmountAfter]\n"
                + "           ,[PaymentMethod]\n"
                + "           ,[OrderStatus]\n"
                + "           ,[Note])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, 'wait', ?)";
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
            st.setString(9, note);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfCOD(int uid, String name, String address, String phone,
            LocalDateTime odate, int voucherID, int totalAmountBeFore, int discountAmount,
            int totalAmountAfter, String paymentMethod, String note) {
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
                + "           ,[OrderStatus]\n"
                + "           ,[Note])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'wait', ?)";
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
            st.setString(11, note);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfPaymentNoVoucher(int uid, String name, String address, String phone,
            LocalDateTime odate, int totalAmountBeFore, int totalAmountAfter,
            String paymentMethod, String VnPayId, String note) {
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
                + "           ,[OrderStatus]\n"
                + "           ,[Note])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, 'pending', ?,'wait', ?)";
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
            st.setString(10, note);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfPayment(int uid, String name, String address, String phone,
            LocalDateTime odate, int voucherID, int totalAmountBeFore,
            int discountAmount, int totalAmountAfter,
            String paymentMethod, String VnPayId, String note) {
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
                + "           ,[OrderStatus]\n"
                + "           ,[Note])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'pending', ?,'wait', ?)";
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
            st.setString(12, note);
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
        OrderDetail orderDetail = new OrderDetail();
        UserDAO userDa = new UserDAO();

        String orderDetailQuery = "SELECT * FROM OrderDetail WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(orderDetailQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

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

        String orderDetailQuery = "SELECT OrderId, SUM(Quantity) AS TotalQuantity, SUM(UnitPrice * Quantity) AS TotalPrice FROM OrderDetail GROUP BY OrderId";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(orderDetailQuery); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();

                // Gán giá trị với kiểu dữ liệu tương ứng
                orderDetail.setQuantity(resultSet.getInt("TotalQuantity")); // Ghi lại tổng số lượng
                orderDetail.setUnitPrice((int) resultSet.getDouble("TotalPrice")); // Chuyển đổi tổng giá trị từ double sang int

                int orderId = resultSet.getInt("OrderId");
                Order order = getOrderById(orderId);
                orderDetail.setOrder(order);

                orderDetailsList.add(orderDetail);
            }
        }

        return orderDetailsList;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException {
        List<OrderDetail> orderDetailsList = new ArrayList<>();

        // Câu truy vấn để lấy chi tiết đơn hàng theo orderId
        String orderDetailQuery = "SELECT Quantity, UnitPrice, ProductDetailId FROM OrderDetail WHERE OrderId = ?";
        UserDAO user = new UserDAO();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(orderDetailQuery)) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OrderDetail orderDetail = new OrderDetail();

                    // Gán giá trị với kiểu dữ liệu tương ứng
                    orderDetail.setQuantity(resultSet.getInt("Quantity"));
                    orderDetail.setUnitPrice((int) resultSet.getDouble("UnitPrice"));
                    int id = resultSet.getInt("ProductDetailId");
                    orderDetail.setProductDetail(user.getProductDetailById(id));
                    orderDetailsList.add(orderDetail);
                }
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
                + "[StartDate], [EndDate], [MinValue], [Status] "
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

    public Order getOneOrderNewest(int uid) {
        String sql = "SELECT TOP(1) * FROM [Order] WHERE UserId = ? ORDER BY Id DESC";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, uid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                UserDAO uDAO = new UserDAO();
                VoucherDAO vDAO = new VoucherDAO();
                LocalDateTime endDate = rs.getTimestamp("EndDate") != null ? rs.getTimestamp("EndDate").toLocalDateTime() : null;
                Order o = new Order(rs.getInt("Id"),
                        uDAO.getUserByIdD(rs.getInt("UserID")),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        vDAO.getVoucherByID(rs.getInt("VoucherID")),
                        rs.getInt("TotalAmountBefore"),
                        rs.getInt("DiscountAmount"),
                        rs.getInt("TotalAmountAfter"),
                        rs.getString("PaymentMethod"),
                        rs.getString("PaymentStatus"),
                        rs.getString("VnPayTransactionId"),
                        endDate,
                        rs.getString("OrderStatus"));
                return o;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   public boolean updateNewestOrderContactInfo(int userId, String newPhone, String newAddress, String newUsername) {
    String sql = "UPDATE [Order] " +
                 "SET Phone = ?, Address = ?, Name = ? " +
                 "WHERE Id = (SELECT TOP(1) Id FROM [Order] WHERE UserId = ? ORDER BY Id DESC)";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, newPhone);    // Đặt giá trị cho Phone
        st.setString(2, newAddress);  // Đặt giá trị cho Address
        st.setString(3, newUsername); // Đặt giá trị cho Name (username)
        st.setInt(4, userId);         // Đặt giá trị cho UserId

        int rowsUpdated = st.executeUpdate();

        if (rowsUpdated > 0) {
            return true; // Cập nhật thành công
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false; // Cập nhật thất bại
}



    public static void main(String[] args) {
        OderDAO o = new OderDAO();
       
        try {
            OrderDetail or = o.getOrderDetailById(3);
              System.out.println(or);
        } catch (SQLException ex) {
            Logger.getLogger(OderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
}
