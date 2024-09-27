/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Brand;
import model.Category;
import model.Color;
import model.Configuration;
import model.Image;
import model.Post;
import model.Product;
import model.ProductDetail;
import model.Role;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {

    public void insertUser(User user) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([Username]\n"
                + "           ,[Password]\n"
                + "           ,[Fullname]\n"
                + "           \n"
                + "           ,[Email]\n"
                + "           \n"
                + "           ,[RoleId]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (?,?,?,?,3)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getFullName());
            pre.setString(4, user.getEmail());
            pre.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isMailDuplicate(User user) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE email = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getEmail());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean isUserDuplicate(User user) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE Username =?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, user.getUserName());
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public User UserLogin(String username, String password) {
        String sql = "select * from [User] where Username=? and Password=?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFullName(rs.getString(4));
                u.setEmail(rs.getString(6));
                Role role = new Role();
                role.setId(rs.getInt(8));
                u.setRole(role);
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    ------------------------------EDIT NGAY 19/09/2024 -DOANKD -----------------------------------------------    
    public Product getProductByIdD(int id) {
        String sql = "select * from Product where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                Category c = getCategoryByIdD(rs.getInt("CategoryId"));
                Brand b = getBrandByIdD(rs.getInt("BrandId"));
                Product product = new Product(rs.getInt("Id"),
                        b,
                        c,
                        rs.getString("Name"),
                        rs.getString("Status"));
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Category getCategoryByIdD(int id) {
        String sql = "select * from Category where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category newCategory = new Category(rs.getInt("Id"),
                        rs.getString("Name"));
                return newCategory;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Brand getBrandByIdD(int id) {
        String sql = "select * from Brand where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Brand newBrand = new Brand(rs.getInt("Id"),
                        rs.getString("Name"));
                return newBrand;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getProductListD() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [Id]\n"
                + "      ,[BrandId]\n"
                + "      ,[CategoryId]\n"
                + "      ,[Name]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Product]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setId(rs.getInt("Id"));
                Category c = getCategoryByIdD(rs.getInt("CategoryId"));
                Brand b = getBrandByIdD(rs.getInt("BrandId"));
                newProduct.setBrand(b);
                newProduct.setCategory(c);
                newProduct.setName(rs.getString("Name"));
                newProduct.setStatus(rs.getString("Status"));
                list.add(newProduct);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Role getRoleByIdD(int id) {
        String sql = "select * from Role where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Role newRole = new Role(rs.getInt("Id"),
                        rs.getString("Name"));
                return newRole;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Color getColorById(int id) {
        String sql = "select * from Color where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Color newColor = new Color(rs.getInt("Id"),
                        rs.getString("Name"));
                return newColor;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserByIdD(int id) {
        String sql = "SELECT * FROM [User] where id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Role role = getRoleByIdD(rs.getInt("RoleId"));
                User u = new User(rs.getInt("id"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Fullname"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        role,
                        rs.getString("status"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Post> getPostListD() {

        List<Post> list = new ArrayList<>();
        String sql = "SELECT TOP 6 [Id], [UserId], [BrandId], [CategoryId], [Title], [ShortContent], [FullContent], [Thumbnail], [PublishDate] "
                + "FROM [dbo].[Post] "
                + "ORDER BY [Id] DESC";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("Id"));
                User u = getUserByIdD(rs.getInt("UserId"));
                p.setUser(u);
                Brand b = getBrandByIdD(rs.getInt("BrandId"));
                p.setBrand(b);
                Category c = getCategoryByIdD(rs.getInt("CategoryId"));
                p.setCategory(c);
                p.setTittle(rs.getString("Title"));
                p.setShortContent(rs.getString("ShortContent"));
                p.setFullContent(rs.getString("FullContent"));
                p.setThumbnail(rs.getString("Thumbnail"));
                p.setPublishDate(rs.getDate("PublishDate"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
public List<Post> getNewestPostListD() {

    List<Post> list = new ArrayList<>();
    String sql = "SELECT TOP 5 [Id], [UserId], [BrandId], [CategoryId], [Title], [ShortContent], [FullContent], [Thumbnail], [PublishDate] "
            + "FROM [dbo].[Post] "
            + "ORDER BY [PublishDate] DESC"; // Sắp xếp theo PublishDate

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Post p = new Post();
            p.setId(rs.getInt("Id"));
            User u = getUserByIdD(rs.getInt("UserId"));
            p.setUser(u);
            Brand b = getBrandByIdD(rs.getInt("BrandId"));
            p.setBrand(b);
            Category c = getCategoryByIdD(rs.getInt("CategoryId"));
            p.setCategory(c);
            p.setTittle(rs.getString("Title"));
            p.setShortContent(rs.getString("ShortContent"));
            p.setFullContent(rs.getString("FullContent"));
            p.setThumbnail(rs.getString("Thumbnail"));
            p.setPublishDate(rs.getDate("PublishDate"));
            list.add(p);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return list;
}

        

    public List<Post> getAllPostListD() {

        List<Post> list = new ArrayList<>();
        String sql = "SELECT  [Id], [UserId], [BrandId], [CategoryId], [Title], [ShortContent], [FullContent], [Thumbnail], [PublishDate] "
                + "FROM [dbo].[Post] "
                + "ORDER BY [Id] DESC";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("Id"));
                User u = getUserByIdD(rs.getInt("UserId"));
                p.setUser(u);
                Brand b = getBrandByIdD(rs.getInt("BrandId"));
                p.setBrand(b);
                Category c = getCategoryByIdD(rs.getInt("CategoryId"));
                p.setCategory(c);
                p.setTittle(rs.getString("Title"));
                p.setShortContent(rs.getString("ShortContent"));
                p.setFullContent(rs.getString("FullContent"));
                p.setThumbnail(rs.getString("Thumbnail"));
                p.setPublishDate(rs.getDate("PublishDate"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Configuration getConfigurationById(int id) {
        String sql = "select * from Configuration where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Configuration c = new Configuration(rs.getInt("Id"),
                        rs.getString("Name"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String getImageById(int id) {
        String sql = "select * from Image where ProductDetailId=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String c = rs.getString("Image");
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

   

    public List<ProductDetail> getListProductDetailD() {
        List<ProductDetail> list = new ArrayList<>();
        String sql = "SELECT [Id],\n"
                + "      [ProductId],\n"
                + "      [ColorId],\n"
                + "      [ConfigurationId],\n"
                + "      [Price],\n"
                + "      [Quantity],\n"
                + "      [ShortDescription],\n"
                + "      [Description],\n"
                + "      [Status]\n"
                + "  FROM [dbo].[ProductDetail]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductDetail newProduct = new ProductDetail();
                newProduct.setId(rs.getInt("Id"));
                Product product = getProductByIdD(rs.getInt("ProductId"));
                newProduct.setProduct(product);
                Color c = getColorById(rs.getInt("ColorId"));
                newProduct.setColor(c);
                Configuration c1 = getConfigurationById(rs.getInt("ConfigurationId"));
                newProduct.setConfiguration(c1);

                // Chỉnh sửa phần này
                newProduct.setPrice(rs.getInt("Price")); // Đặt giá trị cho Price
                newProduct.setQuantity(rs.getInt("Quantity")); // Đặt giá trị cho Quantity

                newProduct.setShortDescription(rs.getString("ShortDescription"));
                newProduct.setDescription(rs.getString("Description"));
                newProduct.setStatus(rs.getString("Status"));
                list.add(newProduct);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public ProductDetail getProductDetailById(int id) {
    ProductDetail newProduct = null;
    String sql = "SELECT [Id],\n"
            + "      [ProductId],\n"
            + "      [ColorId],\n"
            + "      [ConfigurationId],\n"
            + "      [Price],\n"
            + "      [Quantity],\n"
            + "      [ShortDescription],\n"
            + "      [Description],\n"
            + "      [Status]\n"
            + "  FROM [dbo].[ProductDetail] WHERE [Id] = ?"; // Thêm điều kiện WHERE

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, id); // Thiết lập giá trị cho tham số `id`
        ResultSet rs = st.executeQuery();

        // Nếu có kết quả từ ResultSet
        if (rs.next()) {
            newProduct = new ProductDetail();
            newProduct.setId(rs.getInt("Id"));
            Product product = getProductByIdD(rs.getInt("ProductId"));
            newProduct.setProduct(product);
            Color c = getColorById(rs.getInt("ColorId"));
            newProduct.setColor(c);
            Configuration c1 = getConfigurationById(rs.getInt("ConfigurationId"));
            newProduct.setConfiguration(c1);

            // Đặt giá trị cho Price và Quantity
            newProduct.setPrice(rs.getInt("Price"));
            newProduct.setQuantity(rs.getInt("Quantity"));

            newProduct.setShortDescription(rs.getString("ShortDescription"));
            newProduct.setDescription(rs.getString("Description"));
            newProduct.setStatus(rs.getString("Status"));
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return newProduct;
}


public List<Image> getPictureList() {
    List<Image> list = new ArrayList<>();
    String sql = "SELECT [Id]\n"
            + "      ,[ProductDetailId]\n"
            + "      ,[Image]\n"
            + "  FROM [dbo].[Image]";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Image image = new Image();
            image.setId(rs.getInt("Id"));
            
            // Lấy ProductDetail từ ProductDetailId
            ProductDetail productDetail = getProductDetailById(rs.getInt("ProductDetailId"));
            image.setProductDetail(productDetail);
            
            // Đặt giá trị cho trường Image
            image.setImage(rs.getString("Image"));
            
            // Thêm đối tượng Image vào danh sách
            list.add(image);  // Bạn đã bỏ qua dòng này
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return list;
}
public List<Image> getPictureListByProductName(String productName) {
    List<Image> list = new ArrayList<>();
    String sql = "SELECT i.Id, i.ProductDetailId, i.Image " +
                 "FROM Image i " +
                 "JOIN ProductDetail pd ON i.ProductDetailId = pd.Id " +
                 "JOIN Product p ON pd.ProductId = p.Id " +
                 "WHERE p.Name LIKE ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, "%" + productName + "%"); // Tìm kiếm theo tên sản phẩm
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Image image = new Image();
            image.setId(rs.getInt("Id"));
            
            // Lấy ProductDetail từ ProductDetailId
            ProductDetail productDetail = getProductDetailById(rs.getInt("ProductDetailId"));
            image.setProductDetail(productDetail);
            
            // Đặt giá trị cho trường Image
            image.setImage(rs.getString("Image"));
            
            // Thêm đối tượng Image vào danh sách
            list.add(image);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return list;
} public Post getPostById(int id) {
        String sql = "SELECT [Id], [UserId], [BrandId], [CategoryId], [Title], "
                + "[ShortContent], [FullContent], [Thumbnail], [PublishDate] "
                + "FROM [dbo].[Post] WHERE [Id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("Id"));
                User u = getUserByIdD(rs.getInt("UserId"));
                p.setUser(u);
                Brand b = getBrandByIdD(rs.getInt("BrandId"));
                p.setBrand(b);
                Category c = getCategoryByIdD(rs.getInt("CategoryId"));
                p.setCategory(c);
                p.setTittle(rs.getString("Title"));
                p.setShortContent(rs.getString("ShortContent"));
                p.setFullContent(rs.getString("FullContent"));
                p.setThumbnail(rs.getString("Thumbnail"));
                p.setPublishDate(rs.getDate("PublishDate"));
                return p; // Trả về bài viết tìm thấy
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null; // Trả về null nếu không tìm thấy
    }
public List<Post> getPostsByTitleOrContent(String searchString) {
    List<Post> postList = new ArrayList<>();
    String sql = "SELECT [Id], [UserId], [BrandId], [CategoryId], [Title], "
               + "[ShortContent], [FullContent], [Thumbnail], [PublishDate] "
               + "FROM [dbo].[Post] "
               + "WHERE [Title] LIKE ? OR [ShortContent] LIKE ?"; // So sánh tiêu đề hoặc nội dung ngắn

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        String searchPattern = "%" + searchString + "%"; // Tạo mẫu tìm kiếm
        st.setString(1, searchPattern);
        st.setString(2, searchPattern);
        
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Post p = new Post();
            p.setId(rs.getInt("Id"));
            
            User u = getUserByIdD(rs.getInt("UserId"));  // Lấy thông tin người dùng
            p.setUser(u);
            
            Brand b = getBrandByIdD(rs.getInt("BrandId")); // Lấy thông tin thương hiệu
            p.setBrand(b);
            
            Category c = getCategoryByIdD(rs.getInt("CategoryId")); // Lấy thông tin danh mục
            p.setCategory(c);
            
            p.setTittle(rs.getString("Title")); // Đặt tiêu đề
            p.setShortContent(rs.getString("ShortContent")); // Đặt nội dung ngắn
            p.setFullContent(rs.getString("FullContent")); // Đặt nội dung đầy đủ
            p.setThumbnail(rs.getString("Thumbnail")); // Đặt thumbnail
            p.setPublishDate(rs.getDate("PublishDate")); // Đặt ngày xuất bản
            
            postList.add(p); // Thêm bài viết vào danh sách
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return postList; // Trả về danh sách bài viết tìm thấy
}


}
//================================================================================================================

