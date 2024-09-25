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

    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Product p = new Product(
                            re.getInt("id"),
                            getBrandById(re.getInt("brandid")),
                            getCategoryById(re.getInt("categoryid")),
                            re.getString("name"),
                            re.getString("status")
                    );
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Brand getBrandById(int id) {
        String sql = "SELECT * FROM Brand WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Brand b = new Brand(re.getInt("id"), re.getString("name"));
                    return b;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM Category WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Category c = new Category(re.getInt("id"), re.getString("name"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Color getColorById(int id) {
        String sql = "SELECT * FROM Color WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Color c = new Color(re.getInt("id"), re.getString("name"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Configuration getConfigurationById(int id) {
        String sql = "SELECT * FROM Configuration WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Configuration c = new Configuration(re.getInt("id"), re.getString("name"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<ProductList> listProduct(String category, String brand, String price, String name) {
        List<ProductList> list = new ArrayList<>();
        String sql = "WITH RankedProducts AS (\n"
                + "    SELECT p.Id,\n"
                + "	       pd.Id as detail,\n"
                + "           p.[Name] AS name, \n"
                + "           b.[Name] AS brand, \n"
                + "           c.[Name] AS category, \n"
                + "           im.[Image] AS img, \n"
                + "           pd.Price AS price,\n"
                + "           ROW_NUMBER() OVER (PARTITION BY p.Id ORDER BY pd.Id) AS rn -- Đánh số thứ tự theo ProductId và lấy ProductDetailId đầu tiên\n"
                + "    FROM Product p\n"
                + "    JOIN ProductDetail pd ON pd.ProductId = p.Id\n"
                + "    JOIN [Image] im ON im.ProductDetailId = pd.Id\n"
                + "    JOIN Brand b ON b.Id = p.BrandId\n"
                + "    JOIN Category c ON c.Id = p.CategoryId\n"
                + ")\n"
                + "SELECT Id, detail, name, brand, category, img, price\n"
                + "FROM RankedProducts\n"
                + "WHERE rn = 1";

        if (category != null) {
            sql += " and category ='" + category + "'";
        }
        if (brand != null) {
            sql += " and brand ='" + brand + "'";
        }
        if (price != null) {
            if (price.compareTo("default") == 0) {
                sql += " order by Id";
            } else {
                sql += " order by price " + price;
            }
        }

        if (name != null) {
            sql += " and name like N'%" + name + "%'";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            //st.setInt(1, cid);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                ProductList p = new ProductList(
                        re.getInt("id"),
                        re.getInt("detail"),
                        re.getString("name"),
                        re.getString("brand"),
                        re.getString("category"),
                        re.getString("img"),
                        re.getInt("price")
                );
                list.add(p);

            }
        } catch (SQLException e) {
        }

        return list;

    }

    public List<Image> getImageById(int id) {
        List<Image> list = new ArrayList<>();
        String sql = "select p.Id as product, pd.Id, i.FeedbackId, i.Image\n"
                + "from Image i\n"
                + "join ProductDetail pd on pd.Id=i.ProductDetailId\n"
                + "join Product p on p.Id=pd.ProductId\n"
                + "and pd.Id=" + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                Image i = new Image(
                        re.getInt("id"),
                        null,
                        null,
                        re.getString("image"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public ProductDetail getProductDetail(int id) {
        String sql = "SELECT * FROM ProductDetail WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    ProductDetail p = new ProductDetail(
                            re.getInt("id"),
                            getProductById(re.getInt("productid")),
                            getColorById(re.getInt("colorid")),
                            getConfigurationById(re.getInt("configurationid")),
                            re.getInt("price"),
                            re.getInt("quantity"),
                            re.getString("shortdescription"),
                            re.getString("description"),
                            re.getString("status")
                    );
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<ProductAttribute> getAttributeById(int id) {
        List<ProductAttribute> list = new ArrayList<>();
        String sql = "select * from Product_Attribute where ProductDetailId = "+id+" order by AttributeId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                ProductAttribute p = new ProductAttribute(
                        re.getInt("id"),
                        getProductDetail(re.getInt("productdetailid")),
                        getAttribute(re.getInt("attributeid")),
                        re.getString("value"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
    public Attribute getAttribute(int id) {
        String sql = "SELECT * FROM Attribute WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Attribute b = new Attribute(re.getInt("id"), re.getString("name"));
                    return b;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
}
