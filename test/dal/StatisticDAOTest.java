package dal;
import dal.StatisticDAO;
import dal.StatisticDAO.ProductSales;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticDAOTest {

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private StatisticDAO statisticDAO;

    @Before
    public void setUp() throws SQLException {
        // Tạo các mock object cho Connection, PreparedStatement và ResultSet
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Thiết lập behavior cho mock connection
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Khởi tạo DAO với mock connection
        statisticDAO = new StatisticDAO(mockConnection);
    }

    @Test
    public void testCountProductsByBrandNameAndOrderStatusDone() throws SQLException {
        // Giả lập behavior của ResultSet trả về dữ liệu mong đợi
        when(mockResultSet.next()).thenReturn(true); // Có kết quả
        when(mockResultSet.getInt("ProductCount")).thenReturn(10); // Giá trị mong đợi

        // Giả lập PreparedStatement trả về ResultSet mock
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Gọi phương thức cần kiểm tra
        int result = statisticDAO.countProductsByBrandNameAndOrderStatusDone("MacBook");

        // Kiểm tra kết quả
        assertEquals(10, result);  // So sánh với giá trị mong đợi

        // Xác minh rằng các phương thức được gọi chính xác
        verify(mockPreparedStatement).setString(1, "MacBook");
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
    }
    
        @Test
    public void testCalculateRevenueByBrand() throws SQLException {
        // Giả lập ResultSet trả về dữ liệu
        when(mockResultSet.next()).thenReturn(true);  // Có kết quả
        when(mockResultSet.getInt("TotalRevenue")).thenReturn(50000);  // Doanh thu mong đợi

        // Giả lập behavior của PreparedStatement
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Gọi phương thức cần kiểm thử
        int result = statisticDAO.calculateRevenueByBrand("MacBook");

        // Kiểm tra kết quả
        assertEquals(50000, result);

        // Xác minh các phương thức được gọi đúng cách
        verify(mockPreparedStatement).setString(1, "MacBook");
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
    }
    

}
