/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Category;
import model.Color;
import model.Configuration;
import model.Product;
import model.ProductDetail;

/**
 *
 * @author ADMIN
 */
public class ProductDAOS extends DBContext {

    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                BrandDAOS brand = new BrandDAOS();
                CategoryDAOS cate = new CategoryDAOS();

                Product p = new Product(
                        rs.getInt("Id"),
                        brand.getBrandById(rs.getInt("BrandId")),
                        cate.getCategoryById(rs.getInt("CategoryId")),
                        rs.getString("Name"),
                        rs.getString("Status"));

                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Configuration getConfigurationById(int id) {
        String sql = "select * from Configuration where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Configuration c = new Configuration(
                        rs.getInt("Id"),
                        rs.getString("Name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Color getColorById(int id) {
        String sql = "SELECT * FROM Color where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Color c = new Color(
                        rs.getInt("Id"),
                        rs.getString("Name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<ProductDetail> getAllProductDetail() {
        String sql = "SELECT * FROM ProductDetail";
        List<ProductDetail> listPD = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ProductDetail pd = new ProductDetail(
                        rs.getInt("Id"),
                        getProductById(rs.getInt("ProductId")),
                        getColorById(rs.getInt("ColorId")),
                        getConfigurationById(rs.getInt("ConfigurationId")),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("ShortDescription"),
                        rs.getString("Description"),
                        rs.getString("Status"));

                listPD.add(pd);
            }
        } catch (Exception e) {
        }
        return listPD;
    }
    
    public ProductDetail getProductDetailByID(int id) {
        String sql = "SELECT * FROM ProductDetail WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ProductDetail pd = new ProductDetail(
                        rs.getInt("Id"),
                        getProductById(rs.getInt("ProductId")),
                        getColorById(rs.getInt("ColorId")),
                        getConfigurationById(rs.getInt("ConfigurationId")),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("ShortDescription"),
                        rs.getString("Description"),
                        rs.getString("Status"));
                return pd;

            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public ProductDetail getProductDetailByProductID(int pid, int colorID, int confiID) {
        String sql = "SELECT * FROM ProductDetail WHERE ProductId = ? AND ColorID = ? AND [Configurationid] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            st.setInt(2, colorID);
            st.setInt(3, confiID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ProductDetail pd = new ProductDetail(
                        rs.getInt("Id"),
                        getProductById(rs.getInt("ProductId")),
                        getColorById(rs.getInt("ColorId")),
                        getConfigurationById(rs.getInt("ConfigurationId")),
                        rs.getInt("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("ShortDescription"),
                        rs.getString("Description"),
                        rs.getString("Status"));
                return pd;

            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        ProductDAOS p = new ProductDAOS();
        ProductDetail pp = p.getProductDetailByProductID(1, 4, 1);
        
        
        System.out.println(pp);
    }
}
