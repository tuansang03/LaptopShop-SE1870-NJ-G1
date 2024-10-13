/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LocPham
 */
public class StatisticDAO extends DBContext {

    public int countProductsByBrandNameAndOrderStatusDone(String brandName) {
        String sql = "SELECT SUM(od.[Quantity]) AS ProductCount "
                + "FROM [Order] o "
                + "JOIN [OrderDetail] od ON o.[Id] = od.[OrderId] "
                + "JOIN [ProductDetail] pd ON od.[ProductDetailId] = pd.[Id] "
                + "JOIN [Product] p ON pd.[ProductId] = p.[Id] "
                + "JOIN [Brand] b ON p.[BrandId] = b.[Id] "
                + "WHERE b.[Name] = ? AND o.[OrderStatus] = 'done';";  // Tìm kiếm theo tên thương hiệu và trạng thái đơn hàng

        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, brandName);  // Thiết lập tên thương hiệu vào câu lệnh SQL
            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt("ProductCount");  // Lấy tổng số lượng sản phẩm từ cột đầu tiên
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return count;  // Trả về tổng số lượng sản phẩm bán ra cho thương hiệu với trạng thái đơn hàng là "done"

    }

    public int calculateRevenueByBrand(String brandName) {
        String sql = "SELECT SUM(od.[Quantity] * pd.[Price]) AS TotalRevenue "
                + "FROM [Order] o "
                + "JOIN [OrderDetail] od ON o.[Id] = od.[OrderId] "
                + "JOIN [ProductDetail] pd ON od.[ProductDetailId] = pd.[Id] "
                + "JOIN [Product] p ON pd.[ProductId] = p.[Id] "
                + "JOIN [Brand] b ON p.[BrandId] = b.[Id] "
                + "WHERE b.[Name] = ? AND o.[OrderStatus] = 'done';";  // Lọc theo tên thương hiệu và trạng thái "done"

        PreparedStatement ps = null;
        ResultSet rs = null;
        int totalRevenue = 0;  // Khởi tạo biến để lưu doanh thu

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, brandName);  // Thiết lập tên thương hiệu vào câu lệnh SQL
            rs = ps.executeQuery();

            if (rs.next()) {
                totalRevenue = rs.getInt("TotalRevenue");  // Lấy tổng doanh thu từ cột "TotalRevenue"
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return totalRevenue;  // Trả về tổng doanh thu cho thương hiệu cụ thể
    }

    public int[] calculateMonthlyRevenueForYear(int year) {
        String sql = "SELECT MONTH(o.[OrderDate]) AS Month, "
                + "SUM(od.[Quantity] * pd.[Price]) AS TotalRevenue "
                + "FROM [Order] o "
                + "JOIN [OrderDetail] od ON o.[Id] = od.[OrderId] "
                + "JOIN [ProductDetail] pd ON od.[ProductDetailId] = pd.[Id] "
                + "WHERE o.[OrderStatus] = 'done' "
                + "AND YEAR(o.[OrderDate]) = ? " // Lấy doanh thu cho năm được chỉ định
                + "GROUP BY MONTH(o.[OrderDate]) "
                + "ORDER BY Month;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        int[] monthlyRevenue = new int[12];  // Mảng để lưu doanh thu cho 12 tháng (kiểu int)

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);  // Thiết lập năm vào câu lệnh SQL
            rs = ps.executeQuery();

            while (rs.next()) {
                int month = rs.getInt("Month");
                int totalRevenue = rs.getInt("TotalRevenue");  // Lấy doanh thu trực tiếp dưới dạng int

                // Thêm doanh thu vào mảng theo tháng (giảm 1 vì tháng bắt đầu từ 1)
                monthlyRevenue[month - 1] += totalRevenue;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return monthlyRevenue;  // Trả về mảng doanh thu kiểu int theo tháng
    }

    public int[] calculateMonthlyProductSalesForYear(int year) {
        String sql = "WITH Months AS (\n"
                + "    SELECT 1 AS Month\n"
                + "    UNION ALL\n"
                + "    SELECT 2\n"
                + "    UNION ALL\n"
                + "    SELECT 3\n"
                + "    UNION ALL\n"
                + "    SELECT 4\n"
                + "    UNION ALL\n"
                + "    SELECT 5\n"
                + "    UNION ALL\n"
                + "    SELECT 6\n"
                + "    UNION ALL\n"
                + "    SELECT 7\n"
                + "    UNION ALL\n"
                + "    SELECT 8\n"
                + "    UNION ALL\n"
                + "    SELECT 9\n"
                + "    UNION ALL\n"
                + "    SELECT 10\n"
                + "    UNION ALL\n"
                + "    SELECT 11\n"
                + "    UNION ALL\n"
                + "    SELECT 12\n"
                + ")\n"
                + "\n"
                + "SELECT m.Month, \n"
                + "       COALESCE(SUM(od.Quantity), 0) AS TotalProductsSold\n"
                + "FROM Months m\n"
                + "LEFT JOIN [Order] o ON MONTH(o.OrderDate) = m.Month \n"
                + "    AND YEAR(o.OrderDate) = ?\n"
                + "    AND o.OrderStatus = 'done'\n"
                + "LEFT JOIN [OrderDetail] od ON o.Id = od.OrderId\n"
                + "GROUP BY m.Month\n"
                + "ORDER BY m.Month;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        int[] monthlyProductSales = new int[12];  // Mảng để lưu số lượng sản phẩm bán được cho 12 tháng

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, year);  // Thiết lập năm vào câu lệnh SQL
            rs = ps.executeQuery();

            while (rs.next()) {
                int month = rs.getInt("Month");
                int totalProductsSold = rs.getInt("TotalProductsSold");  // Lấy số lượng sản phẩm bán được

                // Thêm số lượng sản phẩm bán được vào mảng theo tháng (giảm 1 vì tháng bắt đầu từ 1)
                monthlyProductSales[month - 1] += totalProductsSold;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return monthlyProductSales;  // Trả về mảng số lượng sản phẩm bán được theo tháng
    }

    public List<ProductSales> getTopSellingProducts() {
        List<ProductSales> topSellingProducts = new ArrayList<>();
        String sql = "SELECT TOP 5 "
                + "pd.Id AS ProductId, p.Name AS ProductName, SUM(od.Quantity) AS TotalSold "
                + "FROM OrderDetail od "
                + "JOIN ProductDetail pd ON od.ProductDetailId = pd.Id "
                + "JOIN Product p ON pd.ProductId = p.Id "
                + "JOIN [Order] o ON od.OrderId = o.Id "
                + "WHERE o.OrderStatus = 'done' "
                + "GROUP BY pd.Id, p.Name "
                + "ORDER BY TotalSold DESC;";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("ProductId");
                String productName = rs.getString("ProductName");
                int totalSold = rs.getInt("TotalSold");

                topSellingProducts.add(new ProductSales(productId, productName, totalSold));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topSellingProducts;
    }

    // Lớp ProductSales để lưu trữ thông tin về sản phẩm bán chạy
    public static class ProductSales {

        private int productId;
        private String productName;
        private int totalSold;

        public ProductSales(int productId, String productName, int totalSold) {
            this.productId = productId;
            this.productName = productName;
            this.totalSold = totalSold;
        }

        // Getters và Setters

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getTotalSold() {
            return totalSold;
        }

        public void setTotalSold(int totalSold) {
            this.totalSold = totalSold;
        }
        
        
    }

    public static void main(String[] args) {
        StatisticDAO dao = new StatisticDAO();
        System.out.println(dao.getTopSellingProducts().toString());
    }
}
