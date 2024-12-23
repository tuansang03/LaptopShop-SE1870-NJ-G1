/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Color;
import model.Configuration;
import model.Order;
import model.ProductDetail;
import model.User;
import model.Voucher;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class OderDAO extends DBContext {

    public void insertOrderOfCODNoVoucher(int uid, String name, String address, String phone,
            LocalDateTime odate, int totalAmountBeFore, int totalAmountAfter, String paymentMethod, String note, int saleID) {
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
                + "           ,[Note]\n"
                + "           ,[SaleId])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, 'wait', ?, ?)";
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
            st.setInt(10, saleID);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfCOD(int uid, String name, String address, String phone,
            LocalDateTime odate, int voucherID, int totalAmountBeFore, int discountAmount,
            int totalAmountAfter, String paymentMethod, String note, int saleID) {
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
                + "           ,[Note]\n"
                + "           ,[SaleId])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'wait', ?, ?)";
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
            st.setInt(12, saleID);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfPaymentNoVoucher(int uid, String name, String address, String phone,
            LocalDateTime odate, int totalAmountBeFore, int totalAmountAfter,
            String paymentMethod, String paymentStatus, String VnPayId, String note, int saleID) {
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
                + "           ,[Note]\n"
                + "           ,[SaleId])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'wait', ?, ?)";
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
            st.setString(9, paymentStatus);
            st.setString(10, VnPayId);
            st.setString(11, note);
            st.setInt(12, saleID);
            st.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void insertOrderOfPayment(int uid, String name, String address, String phone,
            LocalDateTime odate, int voucherID, int totalAmountBeFore,
            int discountAmount, int totalAmountAfter,
            String paymentMethod, String paymentStatus, String VnPayId, String note, int saleID) {
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
                + "           ,[Note]\n"
                + "           ,[SaleId])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'wait', ?, ?)";
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
            st.setString(11, paymentStatus);
            st.setString(12, VnPayId);
            st.setString(13, note);
            st.setInt(14, saleID);
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
        String orderDetailQuery = "SELECT Quantity, UnitPrice, ProductDetailId, Id FROM OrderDetail WHERE OrderId = ?";
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
                    orderDetail.setId(resultSet.getInt("Id"));
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
            order.setNote(resultSet.getString("Note"));
            order.setSaleID(resultSet.getInt("SaleId"));
            order.setRejectDate(resultSet.getTimestamp("RejectDate") != null ? resultSet.getTimestamp("RejectDate").toLocalDateTime() : null);
            order.setAcceptedDate(resultSet.getTimestamp("AcceptedDate") != null ? resultSet.getTimestamp("AcceptedDate").toLocalDateTime() : null);
            order.setIntransitDate(resultSet.getTimestamp("IntransitDate") != null ? resultSet.getTimestamp("IntransitDate").toLocalDateTime() : null);
            order.setShipmentFailedDate(resultSet.getTimestamp("ShipmentFailedDate") != null ? resultSet.getTimestamp("ShipmentFailedDate").toLocalDateTime() : null);
            order.setTrackingcode(resultSet.getString("Trackingcode"));

            int userId = resultSet.getInt("UserId");
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByIdD(userId);
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

    public boolean updateNewestOrderContactInfo(int userId, String newUsername) {
        String sql = "UPDATE [Order] "
                + "SET Phone = ?, Address = ?, Name = ? "
                + "WHERE Id = (SELECT TOP(1) Id FROM [Order] WHERE UserId = ? ORDER BY Id DESC)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(3, newUsername); // Đặt giá trị cho Name (username)
            st.setInt(4, userId);         // Đặt giá trị cho UserId

            int rowsUpdated = st.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated); // Log số dòng đã cập nhật

            return rowsUpdated > 0; // Cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace(); // In ra thông báo lỗi
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông báo lỗi
        }
        return false; // Cập nhật thất bại
    }

    public List<Order> getAllOrder(String op, int saleID) {
        String sql = "SELECT * FROM [Order] WHERE OrderStatus ";
        List<Order> listOrder = new ArrayList<>();

        try {
            if (op.equals("wait")) {
                sql += "LIKE 'wait' AND SaleId = ?";
            } else if (op.equals("rejected")) {
                sql += "LIKE 'rejected' AND SaleId = ?";
            } else if (op.equals("accepted")) {
                sql += "LIKE 'accepted' AND SaleId = ?";
            } else if (op.equals("intransit")) {
                sql += "LIKE 'intransit' AND SaleId = ?";
            } else if (op.equals("failed")) {
                sql += "LIKE 'failed' AND SaleId = ?";
            } else if (op.equals("done")) {
                sql += "LIKE 'done' AND SaleId = ?";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, saleID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                UserDAO uDAO = new UserDAO();
                VoucherDAO vDAO = new VoucherDAO();
                LocalDateTime endDate = rs.getTimestamp("EndDate") != null ? rs.getTimestamp("EndDate").toLocalDateTime() : null;
                LocalDateTime RejectDate = rs.getTimestamp("RejectDate") != null ? rs.getTimestamp("RejectDate").toLocalDateTime() : null;
                LocalDateTime AcceptedDate = rs.getTimestamp("AcceptedDate") != null ? rs.getTimestamp("AcceptedDate").toLocalDateTime() : null;
                LocalDateTime IntransitDate = rs.getTimestamp("IntransitDate") != null ? rs.getTimestamp("IntransitDate").toLocalDateTime() : null;
                LocalDateTime ShipmentFailedDate = rs.getTimestamp("ShipmentFailedDate") != null ? rs.getTimestamp("ShipmentFailedDate").toLocalDateTime() : null;

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
                        rs.getString("OrderStatus"),
                        rs.getString("Note"),
                        rs.getInt("SaleId"),
                        RejectDate,
                        AcceptedDate,
                        IntransitDate,
                        ShipmentFailedDate,
                        rs.getString("Trackingcode"));

                listOrder.add(o);
            }

        } catch (Exception e) {
        }
        return listOrder;
    }

    public void changePaymentStatus(String op, int id) {
        String sql = "UPDATE [dbo].[Order] SET[PaymentStatus] = ? WHERE Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, op);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void changeOrderStatus(String op, int id) {
        String sql = "UPDATE [dbo].[Order] SET[OrderStatus] = ? WHERE Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, op);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Order getOrderByID(int id) {
        String sql = "SELECT * FROM [Order] WHERE Id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
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
                        rs.getString("OrderStatus"),
                        rs.getString("Note"));
                return o;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<OrderDetail> getAllOrdetailByID(int id) {
        String sql = "SELECT * FROM OrderDetail WHERE OrderId = ?";
        List<OrderDetail> listODetail = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OderDAO oDAO = new OderDAO();
                ProductDAOS pDAO = new ProductDAOS();
                OrderDetail odt = new OrderDetail(
                        rs.getInt("Id"),
                        oDAO.getOrderByID(rs.getInt("OrderID")),
                        pDAO.getProductDetailByID(rs.getInt("ProductDetailID")),
                        rs.getInt("Quantity"),
                        rs.getInt("UnitPrice"));
                listODetail.add(odt);
            }
        } catch (Exception e) {
        }
        return listODetail;
    }


    public List<OrderDetail> getOrderDetailsByUserAndOrder(int userId, int orderId) throws SQLException {
        List<OrderDetail> orderDetailsList = new ArrayList<>();

        // Câu truy vấn lấy OrderDetail theo UserId và OrderId
        String sql = "SELECT od.Id, od.OrderId, od.ProductDetailId, od.Quantity, od.UnitPrice "
                + "FROM OrderDetail od "
                + "JOIN [Order] o ON od.OrderId = o.Id "
                + "WHERE o.UserId = ? AND o.Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Gán giá trị cho các tham số trong câu truy vấn
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, orderId);

            // Thực thi câu truy vấn và lấy kết quả
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Tạo đối tượng OrderDetail và gán các giá trị từ ResultSet
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(resultSet.getInt("Id"));

                    // Gán đối tượng Order
                    Order order = getOrderById(resultSet.getInt("OrderId"));
                    orderDetail.setOrder(order);

                    // Gán đối tượng ProductDetail
                    ProductDetail productDetail = getProductDetailById(resultSet.getInt("ProductDetailId"));
                    orderDetail.setProductDetail(productDetail);

                    // Gán các trường còn lại
                    orderDetail.setQuantity(resultSet.getInt("Quantity"));
                    orderDetail.setUnitPrice(resultSet.getInt("UnitPrice"));
                    // Gán các trường còn lại
                    orderDetail.setQuantity(resultSet.getInt("Quantity"));
                    orderDetail.setUnitPrice(resultSet.getInt("UnitPrice"));

                    // Thêm OrderDetail vào danh sách
                    orderDetailsList.add(orderDetail);
                }
            }
        }

        return orderDetailsList;
    }

    public ProductDetail getProductDetailById(int productDetailId) {
        ProductDetail productDetail = null;

        String sql = "SELECT pd.[Id], pd.[ProductId], p.[Name] AS ProductName, \n"
                + "       pd.[ColorId], c.[Name] AS ColorName, \n"
                + "       pd.[ConfigurationId], cfg.[Name] AS ConfigurationName, \n"
                + "       pd.[Price], pd.[Quantity], pd.[ShortDescription], \n"
                + "       pd.[Description], pd.[Status] \n"
                + "FROM [ProductDetail] pd \n"
                + "JOIN [Color] c ON pd.[ColorId] = c.[Id] \n"
                + "JOIN [Configuration] cfg ON pd.[ConfigurationId] = cfg.[Id] \n"
                + "JOIN [Product] p ON pd.[ProductId] = p.[Id] \n"
                + "WHERE pd.[Id] = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Gán tham số productDetailId vào câu truy vấn
            ps.setInt(1, productDetailId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    productDetail = new ProductDetail();
                    productDetail.setId(rs.getInt("Id"));

                    // Thiết lập đối tượng Product
                    Product product = new Product();
                    product.setId(rs.getInt("ProductId"));
                    product.setName(rs.getString("ProductName"));
                    productDetail.setProduct(product);
                    // Thiết lập đối tượng Color
                    Color color = new Color();
                    color.setId(rs.getInt("ColorId"));
                    color.setName(rs.getString("ColorName"));
                    productDetail.setColor(color);
                    // Thiết lập đối tượng Configuration
                    Configuration config = new Configuration();
                    config.setId(rs.getInt("ConfigurationId"));
                    config.setName(rs.getString("ConfigurationName"));
                    productDetail.setConfiguration(config);
                    // Thiết lập các thuộc tính khác
                    productDetail.setPrice(rs.getInt("Price"));
                    productDetail.setQuantity(rs.getInt("Quantity"));
                    productDetail.setShortDescription(rs.getString("ShortDescription"));
                    productDetail.setDescription(rs.getString("Description"));
                    productDetail.setStatus(rs.getString("Status"));
                }
            }
        } catch (SQLException ex) {
        }
        return productDetail;
    }

    public List<Order> getOrderByOrderStatus(String status) {
        String sql = "SELECT * FROM [Order] WHERE [OrderStatus] = ?";
        List<Order> listOrder = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                        rs.getString("OrderStatus"),
                        rs.getString("Note"));
                listOrder.add(o);
            }
        } catch (Exception e) {
        }
        return listOrder;
    }

    public void updateEnddate(LocalDateTime endDate, int id) {
        String sql = "UPDATE [dbo].[Order] SET [EndDate] = ?   WHERE Id = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTimestamp(1, Timestamp.valueOf(endDate));
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteOrderDetail(int oid) {
        String sql = "DELETE FROM [dbo].[OrderDetail] WHERE OrderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteOrder(int id) {
        String sql = "DELETE FROM [dbo].[Order] WHERE Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int totalOrderByOrderStatus(String status, int saleID) {
        String sql = "SELECT COUNT(ID) FROM [Order] WHERE OrderStatus LIKE ? AND SaleId = ?";
        int totalOrders = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, saleID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                totalOrders = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return totalOrders;
    }

    public int totalAmountByOrderStatus(String status, int saleID) {
        String sql = "SELECT SUM(TotalAmountAfter) FROM [Order] WHERE OrderStatus LIKE ? AND SaleId = ?";
        int totalAmount = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, saleID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                totalAmount = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return totalAmount;
    }

    public int getSallerMinOrder() {
        String sql = "SELECT TOP 1 u.Id, COUNT(o.Id) AS OrderCount\n"
                + "FROM [User] u\n"
                + "LEFT JOIN [Order] o ON u.Id = o.SaleId\n"
                + "WHERE u.RoleId = 2\n"
                + "GROUP BY u.Id\n"
                + "ORDER BY OrderCount ASC";
        int saleid = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                saleid = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return saleid;
    }

    public List<Order> searchOrderByUserNameAndDate(String userName,
            String startDate, String endDate, int saleID) {
        
        String sql = "SELECT * FROM [Order] o ";
        List<Order> listOrder = new ArrayList<>();
        try {

            if (startDate.equals("Null") && endDate.equals("Null") && userName.equals("Null")) {
                sql += " WHERE SaleID = ?";
            } else if (startDate.equals("Null") && endDate.equals("Null")) {
                sql += " WHERE (o.[name] LIKE ?) AND SaleID = ?";
            } else if (userName.equals("Null") && endDate.equals("Null")) {
                sql += " WHERE (o.OrderDate >= ?) AND SaleID = ?";
            } else if (userName.equals("Null") && startDate.equals("Null")) {
                sql += " WHERE (o.OrderDate <= ?) AND SaleID = ?";
            } else if (endDate.equals("Null")) {
                sql += " WHERE (o.[name] LIKE ? AND o.OrderDate >= ?) AND SaleID = ?";
            } else if (startDate.equals("Null")) {
                sql += " WHERE (o.[name] LIKE ? AND o.OrderDate <= ?) AND SaleID = ?";
            } else if (userName.equals("Null")) {
                sql += " WHERE (o.OrderDate BETWEEN ? AND ?) AND SaleID = ?";
            } else {
                sql += " WHERE (o.[name] LIKE ? AND o.OrderDate BETWEEN ? AND ?) AND SaleID = ?";
            }

            PreparedStatement st = connection.prepareStatement(sql);

            if (startDate.equals("Null") && endDate.equals("Null") && userName.equals("Null")) {
                st.setInt(1, saleID);
            } else if (startDate.equals("Null") && endDate.equals("Null")) {
                st.setString(1, "%" + userName + "%");
                st.setInt(2, saleID);
            } else if (userName.equals("Null") && endDate.equals("Null")) {
                st.setString(1, startDate);
                st.setInt(2, saleID);
            } else if (userName.equals("Null") && startDate.equals("Null")) {
                st.setString(1, endDate);
                st.setInt(2, saleID);
            } else if (endDate.equals("Null")) {
                st.setString(1, "%" + userName + "%");
                st.setString(2, startDate);
                st.setInt(3, saleID);
            } else if (startDate.equals("Null")) {
                st.setString(1, "%" + userName + "%");
                st.setString(2, endDate);
                st.setInt(3, saleID);
            } else if (userName.equals("Null")) {
                st.setString(1, startDate);
                st.setString(2, endDate);
                st.setInt(3, saleID);
            } else {
                st.setString(1, "%" + userName + "%");
                st.setString(2, startDate);
                st.setString(3, endDate);
                st.setInt(4, saleID);
            }
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                UserDAO uDAO = new UserDAO();
                VoucherDAO vDAO = new VoucherDAO();
                LocalDateTime aendDate = rs.getTimestamp("EndDate") != null ? rs.getTimestamp("EndDate").toLocalDateTime() : null;
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
                        aendDate,
                        rs.getString("OrderStatus"),
                        rs.getString("Note"));
                listOrder.add(o);
            }
            
        } catch (Exception e) {
        }
        return listOrder;
    }
    
    public void updateAccepteDate(String date, int id) {
        String sql = "UPDATE [dbo].[Order] SET [AcceptedDate] = ? WHERE Id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addTrackingCode(String code, int id) {
        String sql = "UPDATE [dbo].[Order] SET [Trackingcode] = ? WHERE Id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateIntransitDate(String date, int id) {
        String sql = "UPDATE [dbo].[Order] SET [IntransitDate] = ? WHERE Id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateRejected(String date, int id) {
        String sql = "UPDATE [dbo].[Order] SET [RejectDate] = ? WHERE Id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateShipmentFailedDate(String date, int id) {
        String sql = "UPDATE [dbo].[Order] SET [ShipmentFailedDate] = ? WHERE Id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateDoneDate(String date, int id) {
        String sql = "UPDATE [dbo].[Order] SET [EndDate] = ? WHERE Id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }


    public static void main(String[] args) {
        try {
            OderDAO o = new OderDAO();
            OrderDetail x = o.getOrderDetailById(21);
            System.out.println(x.getOrder());
        } catch (SQLException ex) {
            Logger.getLogger(OderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
