/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.beans.Statement;
import java.util.ArrayList;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import java.util.ArrayList;
import model.Attribute;
import model.Color;
import model.Configuration;
import model.Image;
import model.ProductAttribute;
import model.ProductDetail;
import model.ProductList;
import model.ProductList;
import model.*;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> readProduct(int pageNumber, int rowsPerPage) {
        ArrayList<Product> pList = new ArrayList<>();
        String sql = "WITH ProductCTE AS ( "
                + "    SELECT p.Id, p.Name, p.BrandId, p.CategoryId, b.Name AS BrandName, c.Name AS CategoryName, "
                + "           ROW_NUMBER() OVER (ORDER BY p.Id) AS RowNum "
                + "    FROM product p "
                + "    JOIN brand b ON p.BrandId = b.Id "
                + "    JOIN category c ON p.CategoryId = c.Id "
                + ") "
                + "SELECT * "
                + "FROM ProductCTE "
                + "WHERE RowNum BETWEEN ? AND ?;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            // Tính toán giá trị cho tham số phân trang
            int startRow = (pageNumber - 1) * rowsPerPage + 1;
            int endRow = pageNumber * rowsPerPage;

            // Set giá trị cho các tham số trong câu truy vấn
            pre.setInt(1, startRow);  // Giá trị bắt đầu
            pre.setInt(2, endRow);    // Giá trị kết thúc

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("Id"));  // Lấy ID của sản phẩm
                p.setName(rs.getString("Name"));  // Lấy tên sản phẩm

                // Tạo đối tượng Brand
                Brand b = new Brand();
                b.setId(rs.getInt("BrandId"));  // Lấy ID của brand
                b.setName(rs.getString("BrandName"));  // Lấy tên của brand
                p.setBrand(b);

                // Tạo đối tượng Category
                Category c = new Category();
                c.setId(rs.getInt("CategoryId"));  // Lấy ID của category
                c.setName(rs.getString("CategoryName"));  // Lấy tên của category
                p.setCategory(c);

                // Thêm sản phẩm vào danh sách
                pList.add(p);
            }

            // Đóng ResultSet và PreparedStatement
            rs.close();
            pre.close();

            return pList;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int insertProduct(int brandId, int categoryId, String name) {
        String sql = "INSERT INTO [dbo].[Product] (BrandId, CategoryId, Name) VALUES (?, ?, ?)";
        int generatedId = -1;  // Khởi tạo giá trị mặc định cho ID sinh ra

        try {
            // Sử dụng PreparedStatement với tùy chọn RETURN_GENERATED_KEYS để lấy ID tự động sinh
            PreparedStatement pre = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pre.setInt(1, brandId);
            pre.setInt(2, categoryId);
            pre.setString(3, name);

            // Thực thi câu lệnh INSERT
            int affectedRows = pre.executeUpdate();

            // Kiểm tra nếu có dòng nào bị ảnh hưởng (thêm thành công)
            if (affectedRows > 0) {
                // Lấy kết quả của khóa tự tăng
                ResultSet generatedKeys = pre.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);  // Lấy ID của sản phẩm vừa thêm
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return generatedId;  // Trả về ID của sản phẩm vừa được thêm
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM Product \n"
                + "WHERE Id= ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countProduct() {
        String sql = "SELECT COUNT(*) FROM product"; // Câu lệnh SQL để đếm sản phẩm
        try {
            // Chuẩn bị câu lệnh
            PreparedStatement pre = connection.prepareStatement(sql);
            // Thực thi câu lệnh và nhận kết quả
            ResultSet rs = pre.executeQuery();

            // Kiểm tra nếu có kết quả, trả về tổng số sản phẩm
            if (rs.next()) {
                return rs.getInt(1); // Lấy giá trị đếm được từ cột đầu tiên
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // Trả về 0 nếu có lỗi
    }

    public List<Brand> listBrand() {
        List<Brand> list = new ArrayList<>();
        String sql = "select * from Brand";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                Brand b = new Brand(
                        re.getInt("id"),
                        re.getString("name"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Category> listCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                Category b = new Category(
                        re.getInt("id"),
                        re.getString("name"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
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
                + "           ROW_NUMBER() OVER (PARTITION BY p.Id ORDER BY pd.Id) AS rn\n"
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
            sql += " and category in (" + category + ")";
        }
        if (brand != null) {
            sql += " and brand in (" + brand + ")";
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
                        formatCurrency(re.getInt("price"))
                );
                list.add(p);

            }
        } catch (SQLException e) {
        }

        return list;

    }

    public static String formatCurrency(int amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount) + " VNĐ";
    }

    public List<Image> getImageById(int id) {
        List<Image> list = new ArrayList<>();
        String sql = "select p.Id as product, pd.Id, i.FeedbackId, i.Image\n"
                + "from Product p\n"
                + "join ProductDetail pd on pd.ProductId=p.Id\n"
                + "join Image i on i.ProductDetailId=pd.Id\n"
                + "and p.Id=(select ProductId from ProductDetail where Id=" + id + ")";
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

    public List<ProductAttribute> getAttributeById(int id) {
        List<ProductAttribute> list = new ArrayList<>();
        String sql = "SELECT * FROM Product_Attribute WHERE ProductDetailId = ? ORDER BY AttributeId";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet re = st.executeQuery()) {
                while (re.next()) {
                    ProductAttribute p = new ProductAttribute(
                            re.getInt("id"),
                            getProductDetail(re.getInt("productdetailid")),
                            getAttribute(re.getInt("attributeid")),
                            re.getString("value")
                    );
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching product attributes: " + e.getMessage());
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

    public List<Configuration> listConfigurationById(int id) {
        List<Configuration> list = new ArrayList<>();
        String sql = "SELECT MIN(pd.Id) AS id, c.Name AS name\n"
                + "FROM ProductDetail pd\n"
                + "JOIN Configuration c ON c.Id = pd.ConfigurationId\n"
                + "WHERE pd.ProductId = (SELECT ProductId FROM ProductDetail WHERE Id = " + id + ")\n"
                + "GROUP BY c.Name, c.Id\n"
                + "ORDER BY c.Id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                Configuration c = new Configuration(
                        re.getInt("id"),
                        re.getString("name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Color> listColorById(int id, int cid) {

        List<Color> list = new ArrayList<>();
        String sql = "select pd.Id, c.Name\n"
                + "from ProductDetail pd\n"
                + "join Color c on c.Id=pd.ColorId\n"
                + "and ProductId=(SELECT ProductId FROM ProductDetail WHERE Id =" + id + ")\n"
                + "and pd.ConfigurationId=" + cid;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                Color c = new Color(
                        re.getInt("id"),
                        re.getString("name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> getProductsByBrand(int bid) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM [product] WHERE brand_id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bid);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                products.add(new Product(id, name));
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Image getImage(int id) {
        String sql = "SELECT top 1 * FROM Image WHERE ProductDetailId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Image i = new Image(
                            re.getInt("id"),
                            getProductDetail(id),
                            null,
                            re.getString("image"));
                    return i;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Image> getImageList(String category, String brand, String price, String name) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Image> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT \n"
                + "        i.Id, \n"
                + "        i.ProductDetailId, \n"
                + "        i.FeedbackId, \n"
                + "        i.Image,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.Id ORDER BY i.Id) AS rn\n"
                + "    FROM Image i\n"
                + "    JOIN ProductDetail pd ON pd.Id = i.ProductDetailId\n"
                + "    JOIN Product p ON p.Id = pd.ProductId\n"
                + "    JOIN Brand b ON b.Id = p.BrandId\n"
                + "    JOIN Category c ON c.Id = p.CategoryId\n"
                + "	AND b.Name = 'asus'\n"
                + ")\n"
                + "SELECT \n"
                + "    Id, \n"
                + "    ProductDetailId, \n"
                + "    FeedbackId, \n"
                + "    Image\n"
                + "FROM \n"
                + "    RankedImages\n"
                + "WHERE \n"
                + "    rn = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                Image i = new Image(
                        re.getInt("id"),
                        getProductDetail(re.getInt("productDetail")),
                        null,
                        re.getString("name"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO d = new ProductDAO();
        List<ProductAttribute> list = d.getAttributeById(5);
        System.out.println(list.get(0).getAttribute().getName());

    }
}
