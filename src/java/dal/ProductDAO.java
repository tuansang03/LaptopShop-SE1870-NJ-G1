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
        String sql = "SELECT p.Id AS product, pd.Id, i.FeedbackId, i.Image "
                + "FROM Product p "
                + "JOIN ProductDetail pd ON pd.ProductId = p.Id "
                + "JOIN Image i ON i.ProductDetailId = pd.Id "
                + "WHERE p.Id = (SELECT ProductId FROM ProductDetail WHERE Id = ?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet re = st.executeQuery()) {
                while (re.next()) {
                    Image i = new Image(
                            re.getInt("id"),
                            null,
                            null,
                            re.getString("image"));
                    list.add(i);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    public List<Image> getMiniImage(int id, int id2, int id3, String name) {
        List<Image> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();

        // Khởi tạo câu lệnh SQL
        sql.append("SELECT MIN(i.Id) AS Id, i.ProductDetailId, MIN(i.FeedbackId) AS FeedbackId, MIN(i.Image) AS Image ")
                .append("FROM Image i ")
                .append("JOIN ProductDetail pd ON pd.Id = i.ProductDetailId ")
                .append("JOIN Product p ON p.Id = pd.ProductId ");

        // Thêm điều kiện WHERE nếu cần
        List<String> conditions = new ArrayList<>();
        if (name != null) {
            conditions.add("p.Name LIKE N'%" + name + "%'");
        }
        // Thêm điều kiện để loại trừ id, id2, id3
        if (id > 0) {
            conditions.add("i.ProductDetailId <> " + id);
        }
        if (id2 > 0) {
            conditions.add("i.ProductDetailId <> " + id2);
        }
        if (id3 > 0) {
            conditions.add("i.ProductDetailId <> " + id3);
        }

        // Nếu có điều kiện, thêm chúng vào câu lệnh
        if (!conditions.isEmpty()) {
            sql.append("WHERE ").append(String.join(" AND ", conditions));
        }

        sql.append(" GROUP BY i.ProductDetailId");

        try (PreparedStatement st = connection.prepareStatement(sql.toString())) {
            try (ResultSet re = st.executeQuery()) {
                while (re.next()) {
                    Image i = new Image(
                            re.getInt("Id"), // Đúng tên trường
                            getProductDetail(re.getInt("ProductDetailId")), // Chỉnh sửa
                            null,
                            re.getString("Image"));
                    list.add(i);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public Role getRole(int id) {
        String sql = "SELECT * FROM Role WHERE Id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    Role i = new Role(
                            re.getInt("id"),
                            re.getString("name"));
                    return i;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public User getUser(int id) {
        String sql = "SELECT * FROM [User] WHERE Id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL
            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    User i = new User(
                            re.getInt("id"),
                            re.getString("username"),
                            re.getString("password"),
                            re.getString("fullname"),
                            re.getString("email"),
                            getRole(re.getByte("roleid")),
                            re.getString("status"));
                    return i;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Favorite> listFavorite(int uid) {
        List<Favorite> list = new ArrayList<>();
        String sql = "SELECT * FROM Favorite WHERE Userid = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, uid);
            try (ResultSet re = st.executeQuery()) {
                while (re.next()) {
                    Favorite c = new Favorite(
                            re.getInt("id"),
                            getProductDetail(re.getInt("productdetailid")),
                            getUser(re.getInt("userid"))
                    );
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Image> listWish(int uid) {
        List<Image> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT \n"
                + "        i.Id,\n"
                + "        i.ProductDetailId,\n"
                + "        i.FeedbackId,\n"
                + "        i.Image,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY i.ProductDetailId ORDER BY i.Id) AS RowNum\n"
                + "    FROM Image i\n"
                + "	JOIN ProductDetail pd ON pd.Id = i.ProductDetailId\n"
                + "    JOIN Favorite f ON f.ProductDetailId = pd.Id\n"
                + "    WHERE f.UserId = ?\n"
                + ")\n"
                + "SELECT Id, ProductDetailId, FeedbackId, Image\n"
                + "FROM RankedImages\n"
                + "WHERE RowNum = 1";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, uid);
            try (ResultSet re = st.executeQuery()) {
                while (re.next()) {
                    Image c = new Image(
                            re.getInt("id"),
                            getProductDetail(re.getInt("productdetailid")),
                            null,
                            re.getString("image"));
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void addWishlist(int uid, int pid) {
        String sql = "insert into Favorite (ProductDetailId, UserId) values (?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pid);
            pre.setInt(2, uid);
            pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteFromWishlist(int uid, int pid) {
        String sql = "delete from Favorite where UserId=? and ProductDetailId=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, uid);
            pre.setInt(2, pid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        ProductDAO d = new ProductDAO();
        d.deleteFromWishlist(1, 2);

    }
}
