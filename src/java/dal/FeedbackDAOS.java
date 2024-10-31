/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAOS extends ProductDAO {

    public Feedback getFeedbackById(int id) {
        String sql = "SELECT * FROM Feedback where OrderDetailId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("Id"),
                        getUser(rs.getInt("UserId")),
                        getOrderDetail(rs.getInt("OrderDetailId")),
                        rs.getInt("Rating"),
                        rs.getString("FeedbackContent"),
                        rs.getDate("FeedbackDate"),
                        rs.getInt("ReplyFeedbackId"));
                return f;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public OrderDetail getOrderDetail(int id) {
        String sql = "SELECT * FROM [OrderDetail] WHERE Id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    OrderDetail i = new OrderDetail(
                            re.getInt("Id"),
                            getOrder(re.getInt("OrderId")),
                            getProductDetail(re.getInt("ProductDetailId")),
                            re.getInt("Quantity"),
                            re.getInt("UnitPrice"));
                    return i;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void addFeedBack(int uid, int odid, int r, String text) {
        String sql = "INSERT INTO Feedback (UserId, OrderDetailId, Rating, FeedbackContent) values (?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, uid);
            pre.setInt(2, odid);
            pre.setInt(3, r);
            pre.setString(4, text);
            pre.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void updateFeedBack(int id, int r, String text) {
        String sql = "UPDATE Feedback\n"
                + "SET Rating = ?, FeedbackContent = ?\n"
                + "WHERE Id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, r);
            pre.setString(2, text);
            pre.setInt(3, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public Image getImageByProductDetailId(int id) {
        String sql = "SELECT * FROM Image WHERE ProductDetailId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Image i = new Image(
                            re.getInt("id"),
                            getProductDetail(re.getInt("productdetailid")),
                            getFeedbackById(re.getInt("feedbackid")),
                            re.getString("image"));
                    return i;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Feedback> getFeedbackByProduct(int id) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.[Id]\n"
                + "      ,f.[UserId]\n"
                + "      ,f.[OrderDetailId]\n"
                + "      ,f.[Rating]\n"
                + "      ,f.[FeedbackContent]\n"
                + "      ,f.[FeedbackDate]\n"
                + "      ,f.[ReplyFeedbackId]\n"
                + "FROM Feedback f\n"
                + "JOIN OrderDetail od on od.Id=f.OrderDetailId\n"
                + "JOIN ProductDetail pd on pd.Id=od.ProductDetailId\n"
                + "WHERE pd.id in (select Id from ProductDetail where ProductId = (select ProductId from ProductDetail where Id=?))";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("Id"),
                            getUser(rs.getInt("UserId")),
                            getOrderDetail(rs.getInt("OrderDetailId")),
                            rs.getInt("Rating"),
                            rs.getString("FeedbackContent"),
                            rs.getDate("FeedbackDate"),
                            rs.getInt("ReplyFeedbackId"));
                    list.add(f);
                }
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public double getRatingPoint(int id) {
        String sql = "SELECT AVG(CAST(Rating AS FLOAT)) AS trungbinh\n"
                + "FROM Feedback f\n"
                + "JOIN OrderDetail od ON od.Id = f.OrderDetailId\n"
                + "JOIN ProductDetail pd ON pd.Id = od.ProductDetailId\n"
                + "WHERE pd.Id IN (SELECT Id FROM ProductDetail WHERE ProductId = (SELECT ProductId FROM ProductDetail WHERE Id = ?))";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    double r = re.getDouble("trungbinh");
                    return r;
                }
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public int getSaleNumber(int id) {
        String sql = "SELECT SUM(od.Quantity) AS num\n"
                + "FROM OrderDetail od \n"
                + "JOIN ProductDetail pd ON pd.Id = od.ProductDetailId\n"
                + "WHERE pd.Id IN (SELECT Id FROM ProductDetail WHERE ProductId = (SELECT ProductId FROM ProductDetail WHERE Id = ?))";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    int r = re.getInt("num");
                    return r;
                }
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        FeedbackDAOS dao = new FeedbackDAOS();
        int i = dao.getSaleNumber(16);
        System.out.println(i);
    }
}
