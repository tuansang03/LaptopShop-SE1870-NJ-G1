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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import model.*;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);  // Truyền tham số vào câu lệnh SQL

            try (ResultSet re = st.executeQuery()) {
                if (re.next()) {
                    // Lấy brand và category từ các phương thức khác
                    Brand brand = getBrandById(re.getInt("brandid"));
                    Category category = getCategoryById(re.getInt("categoryid"));

                    // Tạo đối tượng Product
                    Product p = new Product(
                            re.getInt("id"), // id
                            brand, // brand object
                            category, // category object
                            re.getString("name"), // product name
                            re.getString("status") // product status
                    );

                    return p;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Bạn có thể chọn xử lý ngoại lệ theo cách của mình hoặc ném nó lên lớp trên
            // Ví dụ: throw new RuntimeException(e);
        }

        // Trường hợp không tìm thấy sản phẩm hoặc có lỗi, trả về null
        return null;
    }

    public ArrayList<Product> readProduct() {
        ArrayList<Product> pList = new ArrayList<>();
        String sql = "SELECT p.*, b.Name, c.Name\n"
                + "FROM product p\n"
                + "JOIN brand b ON p.BrandId = b.Id\n"
                + "JOIN category c ON p.CategoryId = c.Id;";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                Brand b = new Brand();
                b.setId(rs.getInt(2));
                b.setName(rs.getString(6));
                p.setBrand(b);
                Category c = new Category();
                c.setId(rs.getInt(3));
                c.setName(rs.getString(7));
                p.setCategory(c);
                p.setName(rs.getString(4));
                pList.add(p);
            }
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

    public List<Product> list() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet re = st.executeQuery();
            while (re.next()) {
                Product p = new Product(
                        re.getInt("id"),
                        getBrandById(re.getInt("brand")),
                        getCategoryById(re.getInt("category")),
                        re.getString("name"),
                        re.getString("status"));
                Category c = new Category(re.getInt("productCategoryID"), re.getString("category_name"));
                list.add(p);
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
        String sql = "select * from Product_Attribute where ProductDetailId = " + id + " order by AttributeId";
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

    public List<Configuration> listConfigurationById(int id) {
        List<Configuration> list = new ArrayList<>();
        String sql = "SELECT pd.Id, c.Name\n"
                + "FROM ProductDetail pd\n"
                + "JOIN Configuration c ON c.Id = pd.ConfigurationId\n"
                + "WHERE pd.ProductId = (SELECT ProductId FROM ProductDetail WHERE Id = " + id + ")";
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

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<ProductList> list = dao.listProduct("Gaming", null, null, null);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);
            System.out.println(list.get(i).getId() + ", " + list.get(i).getName());
        }


    }

    public static String convert(String[] array) {
        if (array == null || array.length == 0) {
            return ""; // Trả về chuỗi rỗng nếu mảng null hoặc trống
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append("'").append(array[i]).append("'"); // Thêm dấu ngoặc đơn
            if (i < array.length - 1) {
                result.append(","); // Thêm dấu phẩy giữa các phần tử
            }
        }
        return result.toString();
    }

}
