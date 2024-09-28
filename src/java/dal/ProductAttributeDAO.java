/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attribute;
import model.ProductAttribute;
import model.ProductDetail;

/**
 *
 * @author ADMIN
 */
public class ProductAttributeDAO extends DBContext {

    public ArrayList<ProductAttribute> getAllProductAttribute(int productDetailId) {
        ArrayList<ProductAttribute> pAList = new ArrayList<>();
        String sql = "SELECT pa.Id AS ProductAttributeId, \n"
                + "       pa.ProductDetailId, \n"
                + "       pa.AttributeId, \n"
                + "       a.Name AS AttributeName, \n"
                + "       pa.Value \n"
                + "FROM Product_Attribute pa\n"
                + "JOIN Attribute a ON pa.AttributeId = a.Id\n"
                + "WHERE pa.ProductDetailId = ?";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, productDetailId);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                // Create ProductAttribute object from ResultSet
                ProductAttribute productAttribute = new ProductAttribute();
                productAttribute.setId(rs.getInt("ProductAttributeId")); // Use ProductAttributeId instead of Id
                // Optional if you need this directly

                // Set ProductDetail object if needed (depends on your structure)
                ProductDetail productDetail = new ProductDetail();
                productDetail.setId(rs.getInt("ProductDetailId"));
                productAttribute.setProductdetail(productDetail);
                productAttribute.setProductdetail(productDetail);
                
            // Set Attribute object
            Attribute attribute = new Attribute();
                attribute.setId(rs.getInt("AttributeId"));
                attribute.setName(rs.getString("AttributeName")); // Assuming Attribute class has a setName method
                productAttribute.setAttribute(attribute);

                // Set value and attribute name
                productAttribute.setValue(rs.getString("Value"));

                // Add object to the list
                pAList.add(productAttribute);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductAttributeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pAList; // Return the list of ProductAttribute
    }

    public static void main(String[] args) {
        ProductAttributeDAO dao = new ProductAttributeDAO();
        ArrayList<ProductAttribute> pA = dao.getAllProductAttribute(274);
        System.out.println(pA);
    }
}
