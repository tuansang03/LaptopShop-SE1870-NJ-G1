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

    public Feedback getFeedback(int id) {
        String sql = "SELECT * FROM Feedback where Id = ?";
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
                + "WHERE pd.Id IN (SELECT Id FROM ProductDetail WHERE ProductId = (SELECT ProductId FROM ProductDetail WHERE Id = ?))\n"
                + "AND f.ReplyFeedbackId IS NULL"; // Only include rows where ReplyFeedbackId is NULL
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Set the parameter in the SQL statement
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    double r = re.getDouble("trungbinh");
                    return Math.round(r * 10.0) / 10.0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Integer> getRatingCount(int id) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT COUNT(*) AS Count\n"
                + "FROM Feedback f\n"
                + "JOIN OrderDetail od ON od.Id = f.OrderDetailId\n"
                + "JOIN ProductDetail pd ON pd.Id = od.ProductDetailId\n"
                + "WHERE pd.Id IN (\n"
                + "    SELECT Id \n"
                + "    FROM ProductDetail \n"
                + "    WHERE ProductId = (\n"
                + "        SELECT ProductId \n"
                + "        FROM ProductDetail \n"
                + "        WHERE Id = ?\n"
                + "    )\n"
                + ") AND f.Rating = 5 AND f.ReplyFeedbackId IS NULL\n"
                + "\n"
                + "UNION ALL\n"
                + "\n"
                + "SELECT COUNT(*) AS Count\n"
                + "FROM Feedback f\n"
                + "JOIN OrderDetail od ON od.Id = f.OrderDetailId\n"
                + "JOIN ProductDetail pd ON pd.Id = od.ProductDetailId\n"
                + "WHERE pd.Id IN (\n"
                + "    SELECT Id \n"
                + "    FROM ProductDetail \n"
                + "    WHERE ProductId = (\n"
                + "        SELECT ProductId \n"
                + "        FROM ProductDetail \n"
                + "        WHERE Id = ?\n"
                + "    )\n"
                + ") AND f.Rating = 4 AND f.ReplyFeedbackId IS NULL\n"
                + "\n"
                + "UNION ALL\n"
                + "\n"
                + "SELECT COUNT(*) AS Count\n"
                + "FROM Feedback f\n"
                + "JOIN OrderDetail od ON od.Id = f.OrderDetailId\n"
                + "JOIN ProductDetail pd ON pd.Id = od.ProductDetailId\n"
                + "WHERE pd.Id IN (\n"
                + "    SELECT Id \n"
                + "    FROM ProductDetail \n"
                + "    WHERE ProductId = (\n"
                + "        SELECT ProductId \n"
                + "        FROM ProductDetail \n"
                + "        WHERE Id = ?\n"
                + "    )\n"
                + ") AND f.Rating = 3 AND f.ReplyFeedbackId IS NULL\n"
                + "\n"
                + "UNION ALL\n"
                + "\n"
                + "SELECT COUNT(*) AS Count\n"
                + "FROM Feedback f\n"
                + "JOIN OrderDetail od ON od.Id = f.OrderDetailId\n"
                + "JOIN ProductDetail pd ON pd.Id = od.ProductDetailId\n"
                + "WHERE pd.Id IN (\n"
                + "    SELECT Id \n"
                + "    FROM ProductDetail \n"
                + "    WHERE ProductId = (\n"
                + "        SELECT ProductId \n"
                + "        FROM ProductDetail \n"
                + "        WHERE Id = ?\n"
                + "    )\n"
                + ") AND f.Rating = 2 AND f.ReplyFeedbackId IS NULL\n"
                + "\n"
                + "UNION ALL\n"
                + "\n"
                + "SELECT COUNT(*) AS Count\n"
                + "FROM Feedback f\n"
                + "JOIN OrderDetail od ON od.Id = f.OrderDetailId\n"
                + "JOIN ProductDetail pd ON pd.Id = od.ProductDetailId\n"
                + "WHERE pd.Id IN (\n"
                + "    SELECT Id \n"
                + "    FROM ProductDetail \n"
                + "    WHERE ProductId = (\n"
                + "        SELECT ProductId \n"
                + "        FROM ProductDetail \n"
                + "        WHERE Id = ?\n"
                + "    )\n"
                + ") AND f.Rating = 1 AND f.ReplyFeedbackId IS NULL;";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, id);
            st.setInt(3, id);
            st.setInt(4, id);
            st.setInt(5, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int i = rs.getInt("Count");
                    list.add(i);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
        }
        return list;
    }

    public List<Feedback> getAllFeedback(String r) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";

        // Nếu r khác null và không phải là "all", thêm điều kiện WHERE Rating = ?
        if (r != null && !r.equals("all")) {
            sql += " WHERE Rating = ?";
        }

        sql += " ORDER BY Id DESC";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // Nếu r khác null và không phải là "all", thiết lập tham số cho câu lệnh SQL
            if (r != null && !r.equals("all")) {
                st.setString(1, r);
            }

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("Id"),
                            getUser(rs.getInt("UserId")),
                            getOrderDetail(rs.getInt("OrderDetailId")),
                            rs.getInt("Rating"),
                            rs.getString("FeedbackContent"),
                            rs.getDate("FeedbackDate"),
                            rs.getInt("ReplyFeedbackId")
                    );
                    if (f.getReplyFeedbackId() == 0) {
                        list.add(f);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Feedback> getAllFeedbackReply() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("Id"),
                            getUser(rs.getInt("UserId")),
                            getOrderDetail(rs.getInt("OrderDetailId")),
                            rs.getInt("Rating"),
                            rs.getString("FeedbackContent"),
                            rs.getDate("FeedbackDate"),
                            rs.getInt("ReplyFeedbackId")
                    );
                    if (f.getReplyFeedbackId() != 0) {
                        list.add(f);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Feedback> getListReplyFeedback(int id) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE ReplyFeedbackId=?";
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
                            rs.getInt("ReplyFeedbackId")
                    );
                    if (f.getReplyFeedbackId() != 0) {
                        list.add(f);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addReply(int uid, int odid, int rating, int rid, String text) {
        String sql = "INSERT INTO Feedback (UserId, OrderDetailId, Rating, FeedbackContent, ReplyFeedbackId) values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, uid);
            pre.setInt(2, odid);
            pre.setInt(3, rating);
            pre.setString(4, text);
            pre.setInt(5, rid);

            pre.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void deleteFeedback(int id) {
        String sql = "DELETE FROM Feedback WHERE Id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public List<Integer> getFeedbackStatus() {
        List<Integer> list = new ArrayList();
        String sql = "select ReplyFeedbackId from Feedback";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int i = rs.getInt("ReplyFeedbackId");
                    if (i != 0) {
                        list.add(i);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
        }
        return list;
    }

    public static void main(String[] args) {
        FeedbackDAOS dao = new FeedbackDAOS();
        List<Integer> list = dao.getFeedbackStatus();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
