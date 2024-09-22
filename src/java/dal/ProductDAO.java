/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author PHONG
 */
public class ProductDAO extends DBContext {

    public List<ProductList> getProductList() {
        List<ProductList> list = new ArrayList<>();
        String sql = "select p.[Name] as name, b.[Name] as brand, c.[Name] as category, co.[Name] as color, im.[Image] as img, pd.Price as price\n"
                + "from Product p\n"
                + "join ProductDetail pd on p.Id=pd.ProductId\n"
                + "join [Image] im on im.ProductDetailId=pd.id\n"
                + "join Brand b on b.Id=p.BrandId\n"
                + "join Category c on c.Id=p.CategoryId\n"
                + "join Color co on co.Id=pd.ColorId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                ProductList p = new ProductList(
                        re.getString("name"),
                        re.getString("brand"),
                        re.getString("category"),
                        re.getString("color"),
                        re.getString("img"),
                        re.getInt("price")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;

    }

    public List<ProductList> listProduct(String category, String brand, String color) {
        List<ProductList> list = new ArrayList<>();
        String sql = "select p.[Name] as name, b.[Name] as brand, c.[Name] as category, co.[Name] as color, im.[Image] as img, pd.Price as price\n"
                + "from Product p\n"
                + "join ProductDetail pd on p.Id=pd.ProductId\n"
                + "join [Image] im on im.ProductDetailId=pd.id\n"
                + "join Brand b on b.Id=p.BrandId\n"
                + "join Category c on c.Id=p.CategoryId\n"
                + "join Color co on co.Id=pd.ColorId ";

        if (category != null) {
            sql += " and c.[Name] ='" + category + "'";
        }
        if (brand != null) {
            sql += " and b.[Name] ='" + brand + "'";
        }
        if (color != null) {
            sql += " and co.[Name] ='" + color + "'";
        }
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            //st.setInt(1, cid);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                ProductList p = new ProductList(
                        re.getString("name"),
                        re.getString("brand"),
                        re.getString("category"),
                        re.getString("color"),
                        re.getString("img"),
                        re.getInt("price")
                );
                list.add(p);

            }
        } catch (SQLException e) {
        }

        return list;

    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<ProductList> list = new ArrayList<>();
        list = dao.getProductList();
        System.out.printf("%-30s %-7s %-5s%n", "name", "img", "price");
        for (ProductList p : list) {
            System.out.printf("%-30s %-7s %-5s%n", p.getName(), p.getImg(), p.getPrice());
        }
    }
}
