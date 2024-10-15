/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.sun.security.auth.login.ConfigFile;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Color;
import model.Configuration;
import model.Product;
import model.ProductAttribute;
import model.ProductDetail;

/**
 *
 * @author ADMIN
 */
public class ProductDetailDAO extends DBContext {

    public void insertDetail(ProductDetail productDetail) {
        String sql = "UPDATE ProductDetail\n"
                + "SET \n"
                + "    price = ?, \n"
                + "    quantity = ?, \n"
                + "    shortDescription = ?\n"
                + "WHERE \n"
                + "    id = ?;";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            // Thiết lập các giá trị cho PreparedStatement
            pre.setInt(1, productDetail.getPrice());
            pre.setInt(2, productDetail.getQuantity());
            pre.setString(3, productDetail.getShortDescription());
            pre.setInt(4, productDetail.getId());
            // Thực thi câu lệnh SQL
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertMultipleProductDetails(int productId, int[] colorIds, int[] configIds, String description) {
        String sql = "INSERT INTO [ProductDetail] "
                + "([ProductId], [ColorId], [ConfigurationId], [Description]) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            // Loop through all combinations of color and config
            for (int colorId : colorIds) {
                for (int configId : configIds) {
                    pre.setInt(1, productId);
                    pre.setInt(2, colorId);
                    pre.setInt(3, configId);
                    pre.setString(4, description); // Thiết lập tham số cho Description

                    pre.addBatch();  // Add to batch
                }
            }

            pre.executeBatch();  // Execute all inserts at once
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ProductDetail> getAllProductDetailById(int productId) {
        ArrayList<ProductDetail> productDetails = new ArrayList<>();
        String sql = "SELECT pd.[Id], pd.[ProductId], p.[Name] AS ProductName, \n"
                + "       pd.[ColorId], c.[Name] AS ColorName, \n"
                + "       pd.[ConfigurationId], cfg.[Name] AS ConfigurationName, \n"
                + "       pd.[Price], pd.[Quantity], pd.[ShortDescription], \n"
                + "       pd.[Description], pd.[Status]\n"
                + "FROM [ProductDetail] pd\n"
                + "JOIN [Color] c ON pd.[ColorId] = c.[Id]\n"
                + "JOIN [Configuration] cfg ON pd.[ConfigurationId] = cfg.[Id]\n"
                + "JOIN [Product] p ON pd.[ProductId] = p.[Id]\n"
                + "WHERE pd.[ProductId] = ?";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDetail d = new ProductDetail();
                d.setId(rs.getInt(1));
                Product p = new Product();
                p.setId(rs.getInt(2));
                p.setName(rs.getString(3));
                d.setProduct(p);
                Color c = new Color();
                c.setId(rs.getInt(4));
                c.setName(rs.getString(5));
                d.setColor(c);
                Configuration config = new Configuration();
                config.setId(rs.getInt(6));
                config.setName(rs.getString(7));
                d.setConfiguration(config);
                d.setPrice(rs.getInt(8));
                d.setQuantity(rs.getInt(9));
                d.setShortDescription(rs.getString(10));
                d.setDescription(rs.getString(11));
                productDetails.add(d);
            }
            return productDetails;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set productId into the query
        return null;  // Return the list of product details
    }

    public void addProductDetailAttribute(ProductAttribute productAttribute) {
        String sql = "INSERT INTO [Product_Attribute] (ProductDetailId, AttributeId, Value) VALUES (?, ?, ?)";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            // Gán giá trị cho các tham số
            pre.setInt(1, productAttribute.getProductdetail().getId()); // Gán ProductDetailId
            pre.setInt(2, productAttribute.getAttribute().getId()); // Gán AttributeId
            pre.setString(3, productAttribute.getValue()); // Gán Value

            // Thực thi câu lệnh chèn
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void deletePDBeforeUpdate(int id){
    String sql="DELETE FROM ProductDetail WHERE Id = ?";
        try {
            PreparedStatement pre =connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

public void deletePDById(int id){
   String sql ="DELETE FROM [Image]\n" +
"WHERE ProductDetailId = ?;\n" +
"\n" +
"DELETE FROM Product_Attribute\n" +
"WHERE ProductDetailId = ?\n" +
"\n" +
"DELETE FROM CartItem\n" +
"WHERE ProductDetailId = ?\n" +
"\n" +
"DELETE FROM Favorite\n" +
"WHERE ProductDetailId = ?\n" +
"\n" +
"DELETE FROM OrderDetail\n" +
"WHERE ProductDetailId = ?\n" +
"\n" +
"DELETE FROM ProductDetail\n" +
"WHERE Id= ?";
        try {
            PreparedStatement pre =connection.prepareStatement(sql);
           pre.setInt(1, id);
            pre.setInt(2, id);
            pre.setInt(3, id);
            pre.setInt(4, id);
            pre.setInt(5, id);
            pre.setInt(6, id);
            pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            
}

public void updateProductDetail(ProductDetail product){
    String sql ="UPDATE [dbo].[ProductDetail]\n" +
"   SET \n" +
"      [Price] = ?\n" +
"      ,[Quantity] = ?\n" +
"      ,[ShortDescription] = ?\n" +
"      ,[Description] = ?\n" +
"      \n" +
" WHERE Id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, product.getPrice()); // Thiết lập giá trị cho Price
        pre.setInt(2, product.getQuantity()); // Thiết lập giá trị cho Quantity
        pre.setString(3, product.getShortDescription()); // Thiết lập giá trị cho ShortDescription
        pre.setString(4, product.getDescription()); // Thiết lập giá trị cho Description
        pre.setInt(5, product.getId());
        pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void updateProductDetailAttribute(ProductAttribute productAttribute) {
    String sql = "UPDATE [dbo].Product_Attribute SET [Value] = ? WHERE [ProductDetailId] = ? AND [AttributeId] = ?";

    try (PreparedStatement pre = connection.prepareStatement(sql)) {
        pre.setString(1, productAttribute.getValue());
        pre.setInt(2, productAttribute.getProductdetail().getId());
        pre.setInt(3, productAttribute.getAttribute().getId());

        int rowsAffected = pre.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("ProductAttribute updated successfully: " + productAttribute.getValue());
        } else {
            System.out.println("No ProductAttribute found for update.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public void deletePDAfterUpdate(int id){
    
}

public int getProductIdByProductDetailId(int productdetailId){
    String sql = "SELECT ProductId\n" +
"FROM ProductDetail\n" +
"WHERE Id = ?; ";
    int productId = 0;
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, productdetailId);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
               productId = rs.getInt("ProductId");
               return productId;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    
}
    public static void main(String[] args) {
        ProductDetailDAO dao = new ProductDetailDAO();
        ArrayList<ProductDetail> pDList = dao.getAllProductDetailById(138);
        System.out.println(pDList);
    }
}
